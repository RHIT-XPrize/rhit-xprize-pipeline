package edu.rosehulman.aixprize.pipeline.core;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.*;
import java.util.Iterator;

import org.apache.uima.UIMAFramework;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.cas.CASException;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.*;
import org.apache.uima.util.*;
import org.junit.*;

public class TestJCas {
	private JCas cas;

	@Before
	public void setup() {
		File descriptor = new File("desc/Mock.xml");
		try {
			if (!descriptor.exists())
				throw new IOException("Couldn't find file.");

			XMLInputSource xmlInput = new XMLInputSource(descriptor);
			ResourceSpecifier specifier = UIMAFramework.getXMLParser().parseResourceSpecifier(xmlInput);

			AnalysisEngine analysisEngine = UIMAFramework.produceAnalysisEngine(specifier);
			cas = analysisEngine.newJCas();
		} catch (IOException | InvalidXMLException | ResourceInitializationException e) {
			fail("Couldn't load descriptor at " + descriptor.getAbsolutePath() + "\n"
					+ "With exception " + e.getMessage());
		}
	}

	@Test
	public void testGetViewIteratorNoInternalViewsReturnsSelfFirst() throws CASException {
		Iterator<JCas> viewIterator = cas.getViewIterator();
		assertEquals(cas, viewIterator.next());
	}

	@Test
	public void testGetViewIteratorWithSingleInternalViews() throws CASException {
		Iterator<JCas> viewIterator = cas.getViewIterator();
		viewIterator.next();
		assertFalse(viewIterator.hasNext());
	}
}
