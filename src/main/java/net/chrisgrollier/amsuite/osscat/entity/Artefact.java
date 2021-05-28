package net.chrisgrollier.amsuite.osscat.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "kind", "name", "type" }) })
public class Artefact {

	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false)
	private String kind;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String type;

	@OneToMany(mappedBy = "artefact", cascade = { CascadeType.ALL }, orphanRemoval = true)
	private Set<ArtefactVersion> versions;

	private String description;

	private String comments;

	private String lastMajorVersion;

	private String roadmapInfo;

	private Integer vulnerabilityLevel;

	private Integer obsolescenceLevel;

	private LocalDate lastRealeaseDate;

	private LocalDateTime lastInfoUpdateDateTime;

	public Artefact() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Set<ArtefactVersion> getVersions() {
		return versions;
	}

	public void setVersions(Set<ArtefactVersion> versions) {
		this.versions = versions;
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

	public String getLastMajorVersion() {
		return lastMajorVersion;
	}

	public void setLastMajorVersion(String lastMajorVersion) {
		this.lastMajorVersion = lastMajorVersion;
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

	public LocalDate getLastRealeaseDate() {
		return lastRealeaseDate;
	}

	public void setLastRealeaseDate(LocalDate lastRealeaseDate) {
		this.lastRealeaseDate = lastRealeaseDate;
	}

	public LocalDateTime getLastInfoUpdateDateTime() {
		return lastInfoUpdateDateTime;
	}

	public void setLastInfoUpdateDateTime(LocalDateTime lastInfoUpdateDateTime) {
		this.lastInfoUpdateDateTime = lastInfoUpdateDateTime;
	}

	public String toShortString() {
		return "Artefact [kind=" + kind + ", name=" + name + ", type=" + type + "]";
	}

	@Override
	public String toString() {
		return "Artefact [kind=" + kind + ", name=" + name + ", type=" + type + ", description=" + description + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((kind == null) ? 0 : kind.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		Artefact other = (Artefact) obj;
		if (kind == null) {
			if (other.kind != null)
				return false;
		} else if (!kind.equals(other.kind))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

}
