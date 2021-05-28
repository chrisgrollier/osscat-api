package net.chrisgrollier.amsuite.osscat.model;

import java.time.LocalDateTime;

public class InfoView {

	private String description;
	
	private String comments;
	
	private String lastVersion;
	
	private LocalDateTime lastInfoUpdateDateTime;
	
	public InfoView() {
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getLastVersion() {
		return lastVersion;
	}

	public void setLastVersion(String lastVersion) {
		this.lastVersion = lastVersion;
	}

	public LocalDateTime getLastInfoUpdateDateTime() {
		return lastInfoUpdateDateTime;
	}

	protected void setLastInfoUpdateDateTime(LocalDateTime lastInfoUpdateDateTime) {
		this.lastInfoUpdateDateTime = lastInfoUpdateDateTime;
	}
}
