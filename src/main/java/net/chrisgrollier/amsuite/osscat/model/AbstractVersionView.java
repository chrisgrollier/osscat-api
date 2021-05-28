package net.chrisgrollier.amsuite.osscat.model;

import javax.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;

public class AbstractVersionView {

	@Schema(description = "Version number", example = "5.3.5")
	@Size(max = 250)
	protected String version;

	@Schema(description = "Version description / content summary", example = "Added support to java 8", nullable = true)
	@Size(max = 250)
	protected String description;

	@Schema(description = "Version comments / remarks", example = "java 16 is not supported", nullable = true)
	@Size(max = 250)
	protected String comments;

	@Schema(description = "Last version number of the same major/minor version", example = "5.3.9", nullable = true)
	@Size(max = 250)
	protected String lastVersion;

	@Schema(description = "Criticity indicator. The higher value, the more component is considered critical", example = "2", nullable = true)
	protected Integer criticity;

	public AbstractVersionView() {
		super();
	}

	public String getVersion() {
		return version;
	}

	public String getDescription() {
		return description;
	}

	public String getComments() {
		return comments;
	}

	public String getLastVersion() {
		return lastVersion;
	}

	public Integer getCriticity() {
		return criticity;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		AbstractVersionView other = (AbstractVersionView) obj;
		if (version == null) {
			if (other.version != null)
				return false;
		} else if (!version.equals(other.version))
			return false;
		return true;
	}

}