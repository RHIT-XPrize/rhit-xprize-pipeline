package edu.rosehulman.aixprize.pipeline.core;

import java.util.List;

import org.apache.uima.UimaContext;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;

public interface ExternalAnnotator {
	default void initialize(UimaContext context) { /* No-op */ }
	List<Annotation> process(JCas cas);
}
