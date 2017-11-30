package edu.rosehulman.aixprize.pipeline.core;

import static org.junit.Assert.*;

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

	private void assertJCasIterator(Iterator<JCas> iterator, JCas... expected) {
		for (int i = 0; i < expected.length; i++) {
			assertTrue(iterator.hasNext());
			assertEquals(expected[i], iterator.next());
		}

		assertFalse(iterator.hasNext());
	}

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
	public void testGetViewIteratorWithSingleInternalViewsHasOneElement() throws CASException {
		Iterator<JCas> viewIterator = cas.getViewIterator();
		viewIterator.next();
		assertFalse(viewIterator.hasNext());
	}

	@Test
	public void testGetViewIteratorWithAddedViewsHoldsAllViews() throws CASException {
		JCas view2 = cas.createView("view2");
		JCas view1 = cas.createView("view1");

		Iterator<JCas> viewIterator = cas.getViewIterator();

		assertJCasIterator(viewIterator, cas, view2, view1);
	}

	@Test
	public void testGetViewIteratorWithNestedViews() throws CASException {
		JCas view2 = cas.createView("view2");
		JCas view1InView2 = view2.createView("view1InView2");
		JCas view1 = cas.createView("view1");
		JCas viewInView1 = view1.createView("viewInView1");
		JCas viewInViewInView1 = viewInView1.createView("viewInViewInView1");
		JCas view2InView2 = view2.createView("view2InView2");

		Iterator<JCas> viewIterator = cas.getViewIterator();

		assertJCasIterator(viewIterator, cas,
										 view2,
										 view1InView2,
										 view1,
										 viewInView1,
										 viewInViewInView1,
										 view2InView2);
	}
}
