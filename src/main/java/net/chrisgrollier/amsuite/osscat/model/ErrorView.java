package net.chrisgrollier.amsuite.osscat.model;

import io.swagger.v3.oas.annotations.media.Schema;
import net.chrisgrollier.amsuite.osscat.service.ArtefactId;

public class ErrorView {

	@Schema(description = "Error message", example = ArtefactId.NO_SEMICOLON_FOUND_MESSAGE)
	private final String error;

	@Schema(description = "Error type", example = "invalididexception")
	private final String type;

	public ErrorView(Throwable t) {
		this(t != null ? t.getMessage() : null, t != null ? t.getClass() : null);
	}

	public ErrorView(String error, String type) {
		super();
		this.error = error;
		this.type = type;
	}

	public ErrorView(String error, Class<?> clazz) {
		this(error, clazz != null ? clazz.getSimpleName().toLowerCase() : null);
	}

	public String getError() {
		return error;
	}

	public String getType() {
		return type;
	}

}
