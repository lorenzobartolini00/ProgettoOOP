package it.univpm.DropboxAnalyzer;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Vector;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.univpm.DropboxAnalyzer.filter.RevisionFilter;
import it.univpm.DropboxAnalyzer.model.Revision;

class TestRevision {
	
	private Revision r = null;

	@BeforeEach
	void setUp() throws Exception {
		r = new Revision("2021-12-17T18:44:29.000+00:00", "2021-12-17T18:44:30.000+00:00", "5d35bedefdfb189398f81", Integer.toUnsignedLong(1055630), true);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void test() {
		//assertEquals(true, r.getIsDownloadable());
		assertEquals(1055630, r.getSize());
	}
	
	@Test
	void test1() {
		assertAll("valori", () -> assertEquals(true, r.getIsDownloadable()),
				() ->assertEquals(Integer.toUnsignedLong(1055630), r.getSize()));
	}

}
