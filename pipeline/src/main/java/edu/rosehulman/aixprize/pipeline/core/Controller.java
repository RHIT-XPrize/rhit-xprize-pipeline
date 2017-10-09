package edu.rosehulman.aixprize.pipeline.core;

import java.io.*;

import org.apache.commons.logging.Log;
import org.apache.uima.UIMAFramework;
import org.apache.uima.analysis_engine.*;
import org.apache.uima.cas.CAS;
import org.apache.uima.cas.text.AnnotationIndex;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.resource.*;
import org.apache.uima.util.*;
import org.ros.namespace.GraphName;
import org.ros.node.*;

import edu.rosehulman.aixprize.pipeline.types.TestType;

public class Controller extends AbstractNodeMain {

	@Override
	public GraphName getDefaultNodeName() {
		return GraphName.of("pipeline/core");
	}
	
	@Override
	public void onStart(ConnectedNode connectedNode) {
		Log log = connectedNode.getLog();
		log.info("UIMA Version: " + UIMAFramework.getVersionString());
		
		File testAnnotatorDescriptor 
			= new File("src/edu_rosehulman_aixprize/pipeline/desc/TestAnnotatorDescriptor.xml");
		File colorsAnnotatorDescriptor
			= new File("src/edu_rosehulman_aixprize/pipeline/desc/ColorsAnnotatorDescriptor.xml");
		if (!testAnnotatorDescriptor.exists()) {
			log.fatal("Couldn't find descriptor at " + testAnnotatorDescriptor.getAbsolutePath());
		}
		if (!colorsAnnotatorDescriptor.exists()) {
			log.fatal("Couldn't find descriptor at " + colorsAnnotatorDescriptor.getAbsolutePath());
		}
		try {
			XMLInputSource xmlInput = new XMLInputSource(colorsAnnotatorDescriptor);
			ResourceSpecifier specifier 
				= UIMAFramework.getXMLParser().parseResourceSpecifier(xmlInput);
			
			AnalysisEngine analysisEngine = UIMAFramework.produceAnalysisEngine(specifier);
			JCas cas = analysisEngine.newJCas();
			
			cas.setDocumentText("This is some document text. My face is blue and I am sad.");
			analysisEngine.process(cas);
			AnnotationIndex<Annotation> index = cas.getAnnotationIndex();
			index.forEach(annotation -> log.info("Found annotation: " + annotation));
			cas.reset();
		} catch (IOException e) {
			log.fatal("Failed to load descriptor.");
		} catch (InvalidXMLException e) {
			log.fatal("Invalid XML.");
		} catch (ResourceInitializationException e) {
			log.fatal("Failed to initialize the analysis engine.");
		} catch (AnalysisEngineProcessException e) {
			log.fatal("Failed to process the analysis.");
		}
	}
}
