package edu.rosehulman.aixprize.pipeline.annotators;

import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;

import edu.rosehulman.aixprize.pipeline.types.TestType;

public class TestAnnotator extends JCasAnnotator_ImplBase {

	@Override
	public void process(JCas cas) throws AnalysisEngineProcessException {
		String docText = cas.getDocumentText();
		
		TestType annotation = new TestType(cas);
		annotation.setBegin(0);
		annotation.setEnd(docText.length());
		annotation.setTestFeature("Hello world!");
		annotation.addToIndexes();
	}

}
