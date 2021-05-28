package net.chrisgrollier.amsuite.osscat.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class ArtefactIdTest {

	private static final String KIND_TEST = "kind";

	private static final String NAME_TEST = "name";

	private static final String TYPE_TEST = "test";

	private static final String NPM_KIND_TEST = "npm";

	private static final String SCOPED_NPM_BASE_NAME = "@scope/name";

	private static final String SCOPED_NPM_NAME = "@scope@name";

	@Test
	void testFromIdOk1() {
		ArtefactId id = ArtefactId.fromStringId(KIND_TEST + ':' + NAME_TEST + ':' + TYPE_TEST);
		assertEquals(KIND_TEST, id.getKind());
		assertEquals(NAME_TEST, id.getName());
		assertEquals(TYPE_TEST, id.getType());
	}

	@Test
	void testFromIdOk2Scoped() {
		ArtefactId id = ArtefactId.fromStringId(NPM_KIND_TEST + ':' + SCOPED_NPM_NAME + ':' + TYPE_TEST);
		assertEquals(NPM_KIND_TEST, id.getKind());
		assertEquals(SCOPED_NPM_BASE_NAME, id.getName());
		assertEquals(TYPE_TEST, id.getType());
	}

	@Test
	void testFromIdOk2ScopedBase() {
		ArtefactId id = ArtefactId.fromStringId(NPM_KIND_TEST + ':' + SCOPED_NPM_BASE_NAME + ':' + TYPE_TEST);
		assertEquals(NPM_KIND_TEST, id.getKind());
		assertEquals(SCOPED_NPM_BASE_NAME, id.getName());
		assertEquals(TYPE_TEST, id.getType());
	}

	@Test
	void testFromIdKo1() {
		InvalidIdException e = assertThrows(InvalidIdException.class,
				() -> ArtefactId.fromStringId(KIND_TEST + ':' + NAME_TEST + TYPE_TEST));
		assertEquals(ArtefactId.ONLY_ONE_SEMICOLON_FOUND_MESSAGE, e.getMessage());
	}

	@Test
	void testFromIdKo2() {
		InvalidIdException e = assertThrows(InvalidIdException.class,
				() -> ArtefactId.fromStringId(KIND_TEST + NAME_TEST + TYPE_TEST));
		assertEquals(ArtefactId.NO_SEMICOLON_FOUND_MESSAGE, e.getMessage());
	}

	@Test
	void testToId() {
		ArtefactId id = new ArtefactId(KIND_TEST, NAME_TEST, TYPE_TEST);
		assertEquals(KIND_TEST + ':' + NAME_TEST + ':' + TYPE_TEST, id.toStringId());
	}

}
