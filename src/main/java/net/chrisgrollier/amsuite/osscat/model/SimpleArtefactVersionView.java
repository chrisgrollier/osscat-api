package net.chrisgrollier.amsuite.osscat.model;

import java.time.LocalDateTime;

import net.chrisgrollier.amsuite.osscat.entity.ArtefactVersion;

public class SimpleArtefactVersionView extends SimpleArtefactView {

	private Long versionId;

	private String version;

	private InfoView versionInfos;

	private Integer criticity;

	public SimpleArtefactVersionView(ArtefactVersion artefactVersion) {
		super(artefactVersion.getArtefact());
		this.versionId = artefactVersion.getId();
		this.version = artefactVersion.getVersion();
		if (artefactVersion.getComments() != null || artefactVersion.getDescription() != null
				|| artefactVersion.getLastMinorVersion() != null
				|| artefactVersion.getLastInfoUpdateDateTime() != null) {
			this.versionInfos = new InfoView();
			this.versionInfos.setDescription(artefactVersion.getDescription());
			this.versionInfos.setComments(artefactVersion.getComments());
			this.versionInfos.setLastVersion(artefactVersion.getLastMinorVersion());
			this.versionInfos.setLastInfoUpdateDateTime(artefactVersion.getLastInfoUpdateDateTime());
		}
		this.criticity = artefactVersion.getCriticity();
	}

	public Long getVersionId() {
		return versionId;
	}

	public String getVersion() {
		return version;
	}

	public LocalDateTime getVersionLastInfoUpdateDateTime() {
		return this.versionInfos == null ? null : versionInfos.getLastInfoUpdateDateTime();
	}

	public String getLastMinorVersion() {
		return this.versionInfos == null ? null : this.versionInfos.getLastVersion();
	}

	public String getVersionDescription() {
		return this.versionInfos == null ? null : this.versionInfos.getDescription();
	}

	public String getVersionComments() {
		return this.versionInfos == null ? null : this.versionInfos.getComments();
	}

	public Integer getCriticity() {
		return criticity;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((versionId == null) ? 0 : versionId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		SimpleArtefactVersionView other = (SimpleArtefactVersionView) obj;
		if (versionId == null) {
			if (other.versionId != null)
				return false;
		} else if (!versionId.equals(other.versionId))
			return false;
		return true;
	}

}
