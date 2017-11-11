package edu.rosehulman.aixprize.pipeline.core;

import org.apache.uima.UimaContext;
import org.apache.uima.jcas.JCas;

public interface ExternalAnnotatorProtocol {
	default void initialize(UimaContext context) { /* No-op */ }
	void transmitCas(JCas cas);
	void addAnnotations(JCas cas);
}
