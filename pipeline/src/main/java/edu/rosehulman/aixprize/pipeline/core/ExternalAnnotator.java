package edu.rosehulman.aixprize.pipeline.core;

import org.apache.uima.UimaContext;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;

public abstract class ExternalAnnotator extends JCasAnnotator_ImplBase {
	private ExternalAnnotatorProtocol protocol;

	public ExternalAnnotator(ExternalAnnotatorProtocol protocol) {
		this.protocol = protocol;
	}

	@Override
	public void initialize(UimaContext context) throws ResourceInitializationException {
		protocol.initialize(context);
	}

	@Override
	public void process(JCas cas) throws AnalysisEngineProcessException {
		protocol.transmitCas(cas);
		protocol.addAnnotations(cas);
	}
}
