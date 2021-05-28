package net.chrisgrollier.amsuite.osscat.model;

import java.time.LocalDate;

import javax.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;

public abstract class AbstractSimpleArtefactView {

	@Schema(description = "Artefact description", example = "Chris Grollier's Open source Catalog API", nullable = true)
	@Size(max = 250)
	protected String description;

	@Schema(description = "Comments / remarks about this artefact", example = "Version 4.2.20.RELEASE is built on the basis of Spring 4.3.30.RELEASE, is split into sub-modules (core, remoting, web, config, ldap, ...) and requires Java 8", nullable = true)
	@Size(max = 250)
	protected String comments;

	@Schema(description = "Last release number", example = "5.3.5", nullable = true)
	@Size(max = 250)
	protected String lastVersion;

	@Schema(description = "Last release date", example = "2021-02-12", nullable = true)
	protected LocalDate lastReleaseDate;

	@Schema(description = "Info about this artefact's development roadmap", example = "Next major release is going to require that you run java 8", nullable = true)
	@Size(max = 250)
	protected String roadmapInfo;

	@Schema(description = "Vulnerability indicator. The higher value, the more component is considered vulnerable", example = "5", nullable = true)
	protected Integer vulnerabilityLevel;

	@Schema(description = "Obsolescence indicator. The higher value, the more component is considered obsolete", example = "2", nullable = true)
	protected Integer obsolescenceLevel;

	public String getDescription() {
		return description;
	}

	public String getComments() {
		return comments;
	}

	public String getLastVersion() {
		return lastVersion;
	}

	public LocalDate getLastReleaseDate() {
		return lastReleaseDate;
	}

	public String getRoadmapInfo() {
		return roadmapInfo;
	}

	public Integer getVulnerabilityLevel() {
		return vulnerabilityLevel;
	}

	public Integer getObsolescenceLevel() {
		return obsolescenceLevel;
	}

}
