package net.chrisgrollier.amsuite.osscat.service;

public class ResourceNotFoundException extends RuntimeException {

	/** Serial UID */
	private static final long serialVersionUID = 6173793358553544241L;

	public ResourceNotFoundException() {
		super();
	}

	public ResourceNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public ResourceNotFoundException(String message) {
		super(message);
	}

	public ResourceNotFoundException(Throwable cause) {
		super(cause);
	}

}
