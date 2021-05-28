package net.chrisgrollier.amsuite.osscat.service;

public class ArtefactId {
	
	public static final String INVALID_ID_ERROR_TYPE = InvalidIdException.class.getSimpleName().toLowerCase();

	public static final String NO_SEMICOLON_FOUND_MESSAGE = "id does not contain any semicolon but must at least contain 2";

	public static final String ONLY_ONE_SEMICOLON_FOUND_MESSAGE = "id contains only one semicolon but must at least contain 2";

	private String kind;

	private String name;

	private String type;

	public static ArtefactId fromStringId(String id) {
		int firstSemiColon = id.indexOf(':');
		if (firstSemiColon < 0) {
			throw new InvalidIdException(NO_SEMICOLON_FOUND_MESSAGE);
		}
		int lastSemiColon = id.lastIndexOf(':');
		if (lastSemiColon == firstSemiColon) {
			throw new InvalidIdException(ONLY_ONE_SEMICOLON_FOUND_MESSAGE);
		}
		final String kind = id.substring(0, firstSemiColon);
		final String name = id.substring(firstSemiColon + 1, lastSemiColon);
		final String type = id.substring(lastSemiColon + 1);
		return new ArtefactId(kind, name, type);

	}

	public ArtefactId(String kind, String name, String type) {
		super();
		this.kind = kind;
		this.name = "npm".equalsIgnoreCase(kind) && name.startsWith("@") ? '@' + name.substring(1).replace('@', '/')
				: name;
		this.type = type;
	}

	public String toStringId() {
		return this.kind + ':' + this.name + ':' + this.type;
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
	public final String toString() {
		return this.toStringId();
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
		ArtefactId other = (ArtefactId) obj;
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