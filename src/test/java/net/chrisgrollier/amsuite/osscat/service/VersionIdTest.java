package net.chrisgrollier.amsuite.osscat.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class VersionIdTest {

	private static final String KIND_TEST = "kind";
	
	private static final String NAME_TEST = "name";
	
	private static final String NAME2_TEST = "group:name";
	
	private static final String TYPE_TEST = "test";
	
	private static final String VERSION_TEST = "1.0.0";
	
	private static final ArtefactId ARTEFACT_ID_TEST = new ArtefactId(KIND_TEST, NAME_TEST, TYPE_TEST);
	
	private static final ArtefactId ARTEFACT_ID2_TEST = new ArtefactId(KIND_TEST, NAME2_TEST, TYPE_TEST);

	private static final String NPM_KIND_TEST = "npm";
	
	private static final String NPM_SCOPED_NAME_BASE_TEST = "@group/name";
	
	private static final String NPM_SCOPED_NAME_TEST = "@group@name";
	
	private static final ArtefactId ARTEFACT_SCOPED_NPM_TEST = new ArtefactId(NPM_KIND_TEST, NPM_SCOPED_NAME_TEST, TYPE_TEST);

	@Test
	void testFromStringId1() { 
		VersionId id = VersionId.fromStringId(KIND_TEST + ':' + NAME_TEST + ':' + VERSION_TEST + ':' + TYPE_TEST);
		assertEquals(ARTEFACT_ID_TEST, id.getArtefactId());
		assertEquals(KIND_TEST, id.getArtefactId().getKind());
		assertEquals(NAME_TEST, id.getArtefactId().getName());
		assertEquals(TYPE_TEST, id.getArtefactId().getType());
		assertEquals(VERSION_TEST, id.getVersion());
	}

	@Test
	void testFromStringId2() {
		VersionId id = VersionId.fromStringId(KIND_TEST + ':' + NAME2_TEST + ':' + VERSION_TEST + ':' + TYPE_TEST);
		assertEquals(ARTEFACT_ID2_TEST, id.getArtefactId());
		assertEquals(KIND_TEST, id.getArtefactId().getKind());
		assertEquals(NAME2_TEST, id.getArtefactId().getName());
		assertEquals(TYPE_TEST, id.getArtefactId().getType());
		assertEquals(VERSION_TEST, id.getVersion());
	}

	@Test
	void testFromStringId3Scoped() {
		VersionId id = VersionId.fromStringId(NPM_KIND_TEST + ':' + NPM_SCOPED_NAME_TEST + ':' + VERSION_TEST + ':' + TYPE_TEST);
		assertEquals(ARTEFACT_SCOPED_NPM_TEST, id.getArtefactId());
		assertEquals(NPM_KIND_TEST, id.getArtefactId().getKind());
		assertEquals(NPM_SCOPED_NAME_BASE_TEST, id.getArtefactId().getName());
		assertEquals(TYPE_TEST, id.getArtefactId().getType());
		assertEquals(VERSION_TEST, id.getVersion());
	}

	@Test
	void testFromIdKo1() {
		InvalidIdException e = assertThrows(InvalidIdException.class,
				() -> VersionId.fromStringId(KIND_TEST + ':' + NAME_TEST + ':' + VERSION_TEST  + TYPE_TEST));
		assertEquals(VersionId.ONLY_TWO_SEMICOLONS_FOUND_MESSAGE, e.getMessage());
	}

	@Test
	void testFromIdKo2() {
		InvalidIdException e = assertThrows(InvalidIdException.class,
				() -> VersionId.fromStringId(KIND_TEST + ':' + NAME_TEST + VERSION_TEST  + TYPE_TEST));
		assertEquals(VersionId.ONLY_ONE_SEMICOLON_FOUND_MESSAGE, e.getMessage());
	}

	@Test
	void testFromIdKo3() {
		InvalidIdException e = assertThrows(InvalidIdException.class,
				() -> VersionId.fromStringId(KIND_TEST  + NAME_TEST  + VERSION_TEST  + TYPE_TEST));
		assertEquals(VersionId.NO_SEMICOLON_FOUND_MESSAGE, e.getMessage());
	}

	@Test
	void testToStringId() {
		VersionId id = new VersionId(ARTEFACT_ID_TEST, VERSION_TEST);
		assertEquals(KIND_TEST + ':' + NAME_TEST + ':' + VERSION_TEST + ':' + TYPE_TEST, id.toStringId());
	}

}
