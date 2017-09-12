package org.apache.uima.tutorial.ex1;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;
import org.apache.uima.tutorial.RoomNumber;

public class RoomNumberAnnotator extends JCasAnnotator_ImplBase {
	private Pattern yorktownPattern = Pattern.compile("\\b[0-4]\\d-[0-2]\\d\\d\\b");
	private Pattern hawthornePattern = Pattern.compile("\\b[G1-4][NS]-[A-Z]\\d\\d\\b");
	
	@Override
	public void process(JCas jcas) throws AnalysisEngineProcessException {
		String docText = jcas.getDocumentText();

		annotateDocument(jcas, yorktownPattern.matcher(docText), "Yorktown");
		annotateDocument(jcas, hawthornePattern.matcher(docText), "Hawthorne");
	}
	
	private void annotateDocument(JCas jcas, Matcher matcher, String building) {
		int pos = 0;
		
		while (matcher.find(pos)) {
			RoomNumber annotation = new RoomNumber(jcas);
			
			annotation.setBegin(matcher.start());
			annotation.setEnd(matcher.end());
			annotation.setBuilding(building);
			annotation.addToIndexes();
			
			pos = matcher.end();
		}
	}

}
