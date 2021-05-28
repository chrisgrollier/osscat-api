package net.chrisgrollier.amsuite.osscat.service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import net.chrisgrollier.amsuite.osscat.dao.ArtefactDao;
import net.chrisgrollier.amsuite.osscat.dao.ArtefactVersionDao;
import net.chrisgrollier.amsuite.osscat.entity.Artefact;
import net.chrisgrollier.amsuite.osscat.entity.ArtefactVersion;
import net.chrisgrollier.amsuite.osscat.model.ArtefactInfoView;
import net.chrisgrollier.amsuite.osscat.model.InfoView;
import net.chrisgrollier.amsuite.osscat.model.ReadOnlyArtefactView;
import net.chrisgrollier.amsuite.osscat.model.SimpleArtefactVersionView;
import net.chrisgrollier.amsuite.osscat.model.SimpleArtefactView;
import net.chrisgrollier.amsuite.osscat.model.UpdatableArtefactView;
import net.chrisgrollier.amsuite.osscat.model.VersionInfoView;

public class ArtefactViewService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ArtefactViewService.class);

	@Autowired
	private ArtefactDao artefactDao;

	@Autowired
	private ArtefactVersionDao artefactVersionDao;

	public ArtefactViewService() {
	}

	@Transactional
	public CreateOrUpdateResult<ReadOnlyArtefactView> createOrUpdateArtefact(String id,
			UpdatableArtefactView artefactVersionView) {
		ArtefactId artefactId = ArtefactId.fromStringId(id);
		final AtomicBoolean created = new AtomicBoolean(false);
		Artefact artefact = this.findOptionalArtefactByFunctionalId(artefactId).orElseGet(() -> {
			Artefact a = new Artefact();
			a.setKind(artefactId.getKind());
			a.setName(artefactId.getName());
			a.setType(artefactId.getType());
			a.setVersions(new HashSet<>());
			created.set(true);
			return a;
		});
		LocalDateTime now = LocalDateTime.now();
		final AtomicBoolean hasChanged = new AtomicBoolean(Patcher.start()
				.acceptIfNotNullNotEquals(v -> artefact.setComments(v), artefactVersionView.getComments(),
						() -> artefact.getComments())
				.acceptIfNotNullNotEquals(v -> artefact.setDescription(v), artefactVersionView.getDescription(),
						() -> artefact.getDescription())
				.acceptIfNotNullNotEquals(v -> artefact.setLastMajorVersion(v), artefactVersionView.getLastVersion(),
						() -> artefact.getLastMajorVersion())
				.acceptIfNotNullNotEquals(v -> artefact.setRoadmapInfo(v), artefactVersionView.getRoadmapInfo(),
						() -> artefact.getRoadmapInfo())
				.acceptIfNotNullNotEquals(v -> artefact.setVulnerabilityLevel(v),
						artefactVersionView.getVulnerabilityLevel(), () -> artefact.getVulnerabilityLevel())
				.acceptIfNotNullNotEquals(v -> artefact.setObsolescenceLevel(v),
						artefactVersionView.getObsolescenceLevel(), () -> artefact.getObsolescenceLevel())
				.acceptIfNotNullNotEquals(v -> artefact.setLastRealeaseDate(v),
						artefactVersionView.getLastReleaseDate(), () -> artefact.getLastRealeaseDate())
				.acceptIfHasChanged(v -> artefact.setLastInfoUpdateDateTime(v), now).finish());
		artefactVersionView.getVersions().forEach(av -> {
			ArtefactVersion artefactVersion = artefact.getVersions().stream()
					.filter(v -> v.getVersion().equals(av.getVersion())).findFirst().orElseGet(() -> {
						ArtefactVersion v = new ArtefactVersion();
						v.setVersion(av.getVersion());
						v.setArtefact(artefact);
						v.setLastInfoUpdateDateTime(now);
						artefact.getVersions().add(v);
						hasChanged.compareAndSet(false, true);
						return v;
					});
			hasChanged.compareAndSet(false,
					Patcher.start()
							.acceptIfNotNullNotEquals(v -> artefactVersion.setComments(v), av.getComments(),
									() -> artefactVersion.getComments())
							.acceptIfNotNullNotEquals(v -> artefactVersion.setDescription(v), av.getDescription(),
									() -> artefactVersion.getDescription())
							.acceptIfNotNullNotEquals(v -> artefactVersion.setLastMinorVersion(v), av.getLastVersion(),
									() -> artefactVersion.getLastMinorVersion())
							.acceptIfNotNullNotEquals(v -> artefactVersion.setCriticity(v), av.getCriticity(),
									() -> artefactVersion.getCriticity())
							.acceptIfHasChanged(v -> artefactVersion.setLastInfoUpdateDateTime(v), now).finish());
		});
		if (created.get() || hasChanged.get()) {
			this.artefactDao.saveAndFlush(artefact);
		}
		return CreateOrUpdateResult.of(created, new ReadOnlyArtefactView(artefact));
	}

	public List<ReadOnlyArtefactView> findReportableArtefactViews(String kind, String[] names, String group) {
		LOGGER.debug("findReportableArtefactViews");
		List<Artefact> artefacts = names != null && names.length > 0 ? this.artefactDao.findByKindAndNameIn(kind, names)
				: group != null ? this.artefactDao.findByKindAndNameLike(kind, group + ":%")
						: this.artefactDao.findAll();
		return artefacts.stream().map(ReadOnlyArtefactView::new).collect(Collectors.toList());
	}

	public ReadOnlyArtefactView findArtefactViewById(String id) {
		LOGGER.debug("findArtefactViewById");
		return this.findArtefactViewByFunctionalId(id);
	}

	public List<SimpleArtefactView> getAllSimple() {
		LOGGER.debug("getAllSimple");
		return this.artefactDao.findAll(Sort.by("kind", "name", "type")).stream().map(SimpleArtefactView::new)
				.collect(Collectors.toList());
	}

	public List<ReadOnlyArtefactView> getAll() {
		LOGGER.debug("getAll");
		return this.artefactDao.findAll(Sort.by("kind", "name", "type")).stream().map(ReadOnlyArtefactView::new)
				.collect(Collectors.toList());
	}

	public SimpleArtefactVersionView findArtefactVersionViewById(Long id) {
		LOGGER.debug("findArtefactVersionViewById");
		return this.artefactVersionDao.findById(id).map(SimpleArtefactVersionView::new)
				.orElseThrow(() -> new ResourceNotFoundException("artefact version with id '" + id + "' not found."));
	}

	public SimpleArtefactVersionView findArtefactVersionViewById(String id) {
		LOGGER.debug("findVersionViewById");
		return this.findArtefactVersionByFunctionalId(id).map(SimpleArtefactVersionView::new)
				.orElseThrow(() -> new ResourceNotFoundException("artefact version with id '" + id + "' not found"));
	}

	@Transactional
	public SimpleArtefactView updateArtefactTextInfo(Long id, ArtefactInfoView textInfo) {
		LOGGER.debug("updateArtefactTextInfo");
		Artefact artefact = this.artefactDao.getById(id);
		artefact.setDescription(textInfo.getDescription());
		artefact.setComments(textInfo.getComments());
		artefact.setLastMajorVersion(textInfo.getLastVersion());
		artefact.setRoadmapInfo(textInfo.getRoadmapInfo());
		artefact.setVulnerabilityLevel(textInfo.getVulnerabilityLevel());
		artefact.setObsolescenceLevel(textInfo.getObsolescenceLevel());
		artefact.setLastInfoUpdateDateTime(LocalDateTime.now());
		return new SimpleArtefactView(this.artefactDao.saveAndFlush(artefact));
	}

	@Transactional
	public SimpleArtefactView patchArtefactTextInfo(String id, ArtefactInfoView textInfo) {
		LOGGER.debug("patchArtefactTextInfo");
		ArtefactId artefactId = ArtefactId.fromStringId(id);
		Artefact artefact = this.findArtefactByFunctionalId(artefactId);
		boolean hasChanged = Patcher.start()
				.acceptIfNotNullNotEquals(v -> artefact.setComments(v), textInfo.getComments(),
						() -> artefact.getComments())
				.acceptIfNotNullNotEquals(v -> artefact.setDescription(v), textInfo.getDescription(),
						() -> artefact.getDescription())
				.acceptIfNotNullNotEquals(v -> artefact.setLastMajorVersion(v), textInfo.getLastVersion(),
						() -> artefact.getLastMajorVersion())
				.acceptIfNotNullNotEquals(v -> artefact.setRoadmapInfo(v), textInfo.getRoadmapInfo(),
						() -> artefact.getRoadmapInfo())
				.acceptIfNotNullNotEquals(v -> artefact.setVulnerabilityLevel(v), textInfo.getVulnerabilityLevel(),
						() -> artefact.getVulnerabilityLevel())
				.acceptIfNotNullNotEquals(v -> artefact.setObsolescenceLevel(v), textInfo.getObsolescenceLevel(),
						() -> artefact.getObsolescenceLevel())
				.acceptIfNotNullNotEquals(v -> artefact.setLastRealeaseDate(v), textInfo.getLastReleaseDate(),
						() -> artefact.getLastRealeaseDate())
				.acceptIfHasChanged(v -> artefact.setLastInfoUpdateDateTime(v), LocalDateTime.now()).finish();
		return hasChanged ? new SimpleArtefactView(this.artefactDao.saveAndFlush(artefact))
				: new SimpleArtefactView(artefact);
	}

	@Transactional
	public SimpleArtefactVersionView updateArtefactVersionTextInfo(Long id, InfoView textInfo) {
		LOGGER.debug("updateArtefactVersionTextInfo");
		ArtefactVersion artefactVersion = this.artefactVersionDao.getById(id);
		artefactVersion.setDescription(textInfo.getDescription());
		artefactVersion.setComments(textInfo.getComments());
		artefactVersion.setLastMinorVersion(textInfo.getLastVersion());
		// artefactVersion.setCriticity();
		artefactVersion.setLastInfoUpdateDateTime(LocalDateTime.now());
		return new SimpleArtefactVersionView(this.artefactVersionDao.saveAndFlush(artefactVersion));
	}

	@Transactional
	public SimpleArtefactVersionView patchArtefactVersionTextInfo(String id, VersionInfoView versionInfo) {
		LOGGER.debug("patchArtefactVersionTextInfo");
		ArtefactVersion artefactVersion = this.findArtefactVersionByFunctionalId(id)
				.orElseThrow(() -> new ResourceNotFoundException("artefact version with id '" + id + "' not found"));
		boolean hasChanged = Patcher.start()
				.acceptIfNotNullNotEquals(v -> artefactVersion.setComments(v), versionInfo.getVersionComments(),
						() -> artefactVersion.getComments())
				.acceptIfNotNullNotEquals(v -> artefactVersion.setDescription(v), versionInfo.getVersionDescription(),
						() -> artefactVersion.getDescription())
				.acceptIfNotNullNotEquals(v -> artefactVersion.setLastMinorVersion(v),
						versionInfo.getLastMinorVersion(), () -> artefactVersion.getLastMinorVersion())
				.acceptIfNotNullNotEquals(v -> artefactVersion.setCriticity(v), versionInfo.getCriticity(),
						() -> artefactVersion.getCriticity())
				.acceptIfHasChanged(v -> artefactVersion.setLastInfoUpdateDateTime(v), LocalDateTime.now()).finish();
		return hasChanged ? new SimpleArtefactVersionView(this.artefactVersionDao.saveAndFlush(artefactVersion))
				: new SimpleArtefactVersionView(artefactVersion);
	}

	public long countArtefact() {
		LOGGER.debug("countArtefact");
		return this.artefactDao.count();
	}

	private Optional<ArtefactVersion> findArtefactVersionByFunctionalId(String id) {
		return this.findArtefactVersionByFunctionalId(VersionId.fromStringId(id));
	}

	private ReadOnlyArtefactView findArtefactViewByFunctionalId(String id) {
		ArtefactId artefactId = ArtefactId.fromStringId(id);
		return new ReadOnlyArtefactView(this.findArtefactByFunctionalId(artefactId));

	}

	private Optional<ArtefactVersion> findArtefactVersionByFunctionalId(VersionId versionId) {
		return this.findArtefactByFunctionalId(versionId.getArtefactId()).getVersions().stream()
				.filter(v -> versionId.getVersion().equals(v.getVersion())).findFirst();
	}

	private Artefact findArtefactByFunctionalId(ArtefactId artefactId) {
		return this.findOptionalArtefactByFunctionalId(artefactId).orElseThrow(
				() -> new ResourceNotFoundException("artefact with id '" + artefactId.toString() + "' not found."));
	}

	private Optional<Artefact> findOptionalArtefactByFunctionalId(ArtefactId artefactId) {
		return this.artefactDao.findByKindAndNameAndType(artefactId.getKind(), artefactId.getName(),
				artefactId.getType());
	}

}
