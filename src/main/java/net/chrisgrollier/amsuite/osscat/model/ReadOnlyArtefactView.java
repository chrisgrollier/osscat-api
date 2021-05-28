package net.chrisgrollier.amsuite.osscat.model;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

import io.swagger.v3.oas.annotations.media.Schema;
import net.chrisgrollier.amsuite.osscat.entity.Artefact;
import net.chrisgrollier.amsuite.osscat.entity.ArtefactVersion;

public class ReadOnlyArtefactView extends AbstractArtefactView<ReadOnlyArtefactView.VersionView> {

	@Schema(description = "Technical unique id of this artefact", example = "3")
	private Long id;

	@Schema(description = "Kind of this artefact. Usually related to the artefact building tool (e.g. mvn for maven, npm for nodejs package manager", example = "mvn")
	private String kind;

	@Schema(description = "Name of this artefact. Usually related to kind of artefact", example = "org.springframework.boot:spring-boot")
	private String name;

	@Schema(description = "Type of this artefact. Usually related to kind of artefact (e.g. for mvn kind, possible values could be jar, war, ear, etc. For npm kind is usually js)", example = "jar")
	private String type;

	@Schema(description = "This artefact info last update date time")
	private LocalDateTime lastInfoUpdateDateTime;

	public ReadOnlyArtefactView(Artefact artefact) {
		super();
		this.id = artefact.getId();
		this.kind = artefact.getKind();
		this.name = artefact.getName();
		this.type = artefact.getType();
		this.comments = artefact.getComments();
		this.description = artefact.getDescription();
		this.lastReleaseDate = artefact.getLastRealeaseDate();
		this.lastVersion = artefact.getLastMajorVersion();
		this.obsolescenceLevel = artefact.getObsolescenceLevel();
		this.roadmapInfo = artefact.getRoadmapInfo();
		this.versions = artefact.getVersions().stream().map(VersionView::new).collect(Collectors.toSet());
		this.vulnerabilityLevel = artefact.getVulnerabilityLevel();
		this.lastInfoUpdateDateTime = artefact.getLastInfoUpdateDateTime();
	}

	public Long getId() {
		return id;
	}

	public String getKind() {
		return kind;
	}

	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}

	public LocalDateTime getLastInfoUpdateDateTime() {
		return lastInfoUpdateDateTime;
	}

	public static class VersionView extends AbstractVersionView {

		@Schema(description = "Technical unique id of this version", example = "1")
		private Long id;

		@Schema(description = "This version info last update date time")
		private LocalDateTime lastInfoUpdateDateTime;

		private VersionView(ArtefactVersion artefactVersion) {
			super();
			this.id = artefactVersion.getId();
			this.comments = artefactVersion.getComments();
			this.criticity = artefactVersion.getCriticity();
			this.description = artefactVersion.getDescription();
			this.lastVersion = artefactVersion.getLastMinorVersion();
			this.version = artefactVersion.getVersion();
			this.lastInfoUpdateDateTime = artefactVersion.getLastInfoUpdateDateTime();
		}

		public Long getId() {
			return id;
		}

		public LocalDateTime getLastInfoUpdateDateTime() {
			return lastInfoUpdateDateTime;
		}

	}
}
