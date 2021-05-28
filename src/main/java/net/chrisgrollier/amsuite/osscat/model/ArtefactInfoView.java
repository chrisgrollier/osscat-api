package net.chrisgrollier.amsuite.osscat.model;

import java.time.LocalDate;

public class ArtefactInfoView extends InfoView {
	
	private String roadmapInfo;
	
	private Integer vulnerabilityLevel;
	
	private Integer obsolescenceLevel;
	
	private LocalDate lastReleaseDate;

	public ArtefactInfoView() {
	}

	public String getRoadmapInfo() {
		return roadmapInfo;
	}

	public void setRoadmapInfo(String roadmapInfo) {
		this.roadmapInfo = roadmapInfo;
	}

	public Integer getVulnerabilityLevel() {
		return vulnerabilityLevel;
	}

	public void setVulnerabilityLevel(Integer vulnerabilityLevel) {
		this.vulnerabilityLevel = vulnerabilityLevel;
	}

	public Integer getObsolescenceLevel() {
		return obsolescenceLevel;
	}

	public void setObsolescenceLevel(Integer obsolescenceLevel) {
		this.obsolescenceLevel = obsolescenceLevel;
	}

	public LocalDate getLastReleaseDate() {
		return lastReleaseDate;
	}

	public void setLastReleaseDate(LocalDate lastReleaseDate) {
		this.lastReleaseDate = lastReleaseDate;
	}

}
