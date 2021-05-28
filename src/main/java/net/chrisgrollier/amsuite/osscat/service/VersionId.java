package net.chrisgrollier.amsuite.osscat.service;

public class VersionId {

	public static final String NO_SEMICOLON_FOUND_MESSAGE = "id does not contain any semicolon but must at least contain 3";

	public static final String ONLY_ONE_SEMICOLON_FOUND_MESSAGE = "id contains only one semicolon but must at least contain 3";

	public static final String ONLY_TWO_SEMICOLONS_FOUND_MESSAGE = "id contains only two semicolons but must at least contain 3";

	private ArtefactId artefactId;

	private String version;

	public static VersionId fromStringId(String id) {

		final int firstSemiColon = id.indexOf(':');
		if (firstSemiColon < 0) {
			throw new InvalidIdException(NO_SEMICOLON_FOUND_MESSAGE);
		}
		final int secondSemiColon = id.indexOf(':', firstSemiColon + 1);
		if (secondSemiColon < 0) {
			throw new InvalidIdException(ONLY_ONE_SEMICOLON_FOUND_MESSAGE);
		}

		final int lastSemiColon = id.lastIndexOf(':');
		if (lastSemiColon == secondSemiColon) {
			throw new InvalidIdException(ONLY_TWO_SEMICOLONS_FOUND_MESSAGE);
		}
		final String kind = id.substring(0, firstSemiColon);
		final String nameAndversion = id.substring(firstSemiColon + 1, lastSemiColon);
		final int nameAndVersionLastSemicolon = nameAndversion.lastIndexOf(':');
		final String name = nameAndversion.substring(0, nameAndVersionLastSemicolon);
		final String version = nameAndversion.substring(nameAndVersionLastSemicolon + 1);
		final String type = id.substring(lastSemiColon + 1);
		return new VersionId(new ArtefactId(kind, name, type), version);
	}

	public VersionId(ArtefactId artefactId, String version) {
		super();
		this.artefactId = artefactId;
		this.version = version;
	}

	public ArtefactId getArtefactId() {
		return artefactId;
	}

	public String getVersion() {
		return version;
	}

	public String toStringId() {
		return this.artefactId.getKind() + ':' + this.artefactId.getName() + ':' + this.version + ':'
				+ this.artefactId.getType();
	}

	@Override
	public final String toString() {
		return this.toStringId();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((artefactId == null) ? 0 : artefactId.hashCode());
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
		VersionId other = (VersionId) obj;
		if (artefactId == null) {
			if (other.artefactId != null)
				return false;
		} else if (!artefactId.equals(other.artefactId))
			return false;
		if (version == null) {
			if (other.version != null)
				return false;
		} else if (!version.equals(other.version))
			return false;
		return true;
	}

}