package net.chrisgrollier.amsuite.osscat.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import net.chrisgrollier.amsuite.osscat.entity.Artefact;

public interface ArtefactDao extends JpaRepository<Artefact, Long> {

	Optional<Artefact> findByKindAndNameAndType(String kind, String name, String type);

	List<Artefact> findByKindAndNameLike(String kind, String name);

	List<Artefact> findByKindAndNameIn(String kind, String[] names);
}
