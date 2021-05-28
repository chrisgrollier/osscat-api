package net.chrisgrollier.amsuite.osscat.model;

import java.time.LocalDateTime;

public class VersionInfoView {

	private String versionDescription;

	private String versionComments;

	private String lastMinorVersion;

	private LocalDateTime lastInfoUpdateDateTime;
	
	private Integer criticity;

	public VersionInfoView() {
	}

	public String getVersionDescription() {
		return versionDescription;
	}

	public void setVersionDescription(String versionDescription) {
		this.versionDescription = versionDescription;
	}

	public String getVersionComments() {
		return versionComments;
	}

	public void setVersionComments(String versionComments) {
		this.versionComments = versionComments;
	}

	public String getLastMinorVersion() {
		return lastMinorVersion;
	}

	public void setLastMinorVersion(String lastMinorVersion) {
		this.lastMinorVersion = lastMinorVersion;
	}

	public Integer getCriticity() {
		return criticity;
	}

	public void setCriticity(Integer criticity) {
		this.criticity = criticity;
	}

	public LocalDateTime getLastInfoUpdateDateTime() {
		return lastInfoUpdateDateTime;
	}

	public void setLastInfoUpdateDateTime(LocalDateTime lastInfoUpdateDateTime) {
		this.lastInfoUpdateDateTime = lastInfoUpdateDateTime;
	}
}
