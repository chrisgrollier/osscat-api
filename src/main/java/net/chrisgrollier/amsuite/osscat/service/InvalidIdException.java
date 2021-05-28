package net.chrisgrollier.amsuite.osscat.service;

public class InvalidIdException extends RuntimeException {

	/** Serial UID */
	private static final long serialVersionUID = -7665618715769820100L;

	public InvalidIdException() {
		super();
	}

	public InvalidIdException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidIdException(String message) {
		super(message);
	}

	public InvalidIdException(Throwable cause) {
		super(cause);
	}
	
}
