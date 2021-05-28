package net.chrisgrollier.amsuite.osscat.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ArtefactVersion {

	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne(optional = false)
	private Artefact artefact;

	@Column(nullable = false)
	private String version;

	private String description;

	private String comments;

	private String lastMinorVersion;

	private Integer criticity;

	private LocalDateTime lastInfoUpdateDateTime;

	public ArtefactVersion() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Artefact getArtefact() {
		return artefact;
	}

	public void setArtefact(Artefact artefact) {
		this.artefact = artefact;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	@Override
	public String toString() {
		return "ArtefactVersion [artefact=" + (artefact == null ? null : artefact.toShortString()) + ", version="
				+ version + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((artefact == null) ? 0 : artefact.hashCode());
		result = prime * result + ((version == null) ? 0 : version.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ArtefactVersion other = (ArtefactVersion) obj;
		if (artefact == null) {
			if (other.artefact != null)
				return false;
		} else if (!artefact.equals(other.artefact))
			return false;
		if (version == null) {
			if (other.version != null)
				return false;
		} else if (!version.equals(other.version))
			return false;
		return true;
	}

}
