package net.chrisgrollier.amsuite.osscat.model;

import net.chrisgrollier.amsuite.osscat.entity.Artefact;


public class SimpleArtefactView extends AbstractSimpleArtefactView {

	private Long id;

	private String kind;

	private String name;

	private String type;

	public SimpleArtefactView(Artefact artefact) {
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
		this.vulnerabilityLevel= artefact.getVulnerabilityLevel();
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		SimpleArtefactView other = (SimpleArtefactView) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
