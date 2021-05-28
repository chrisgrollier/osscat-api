package net.chrisgrollier.amsuite.osscat.model;

import java.time.LocalDate;
import java.util.Set;

public class UpdatableArtefactView extends AbstractArtefactView<UpdatableVersionView> {

	public UpdatableArtefactView() {
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public void setLastVersion(String lastVersion) {
		this.lastVersion = lastVersion;
	}

	public void setLastReleaseDate(LocalDate lastReleaseDate) {
		this.lastReleaseDate = lastReleaseDate;
	}

	public void setRoadmapInfo(String roadmapInfo) {
		this.roadmapInfo = roadmapInfo;
	}

	public void setVulnerabilityLevel(Integer vulnerabilityLevel) {
		this.vulnerabilityLevel = vulnerabilityLevel;
	}

	public void setObsolescenceLevel(Integer obsolescenceLevel) {
		this.obsolescenceLevel = obsolescenceLevel;
	}

	public void setVersions(Set<UpdatableVersionView> versions) {
		this.versions = versions;
	}
}
