package net.chrisgrollier.amsuite.osscat.model;

import java.util.Set;

import io.swagger.v3.oas.annotations.media.Schema;

public abstract class AbstractArtefactView<V> extends AbstractSimpleArtefactView {

	@Schema(description = "Set of versions of this artefact")
	protected Set<V> versions;

	protected AbstractArtefactView() {
		super();
	}

	public Set<V> getVersions() {
		return versions;
	}

}