package net.chrisgrollier.amsuite.osscat.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import net.chrisgrollier.amsuite.osscat.entity.Artefact;
import net.chrisgrollier.amsuite.osscat.entity.ArtefactVersion;

public interface ArtefactVersionDao extends JpaRepository<ArtefactVersion, Long> {

	Optional<ArtefactVersion> findByArtefactAndVersion(Artefact artefact, String version);
}
