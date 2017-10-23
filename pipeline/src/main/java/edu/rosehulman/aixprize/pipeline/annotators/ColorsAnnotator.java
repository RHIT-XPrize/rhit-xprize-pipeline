package edu.rosehulman.aixprize.pipeline.annotators;

import org.apache.uima.jcas.tcas.Annotation;

import edu.rosehulman.aixprize.pipeline.types.Color;

/**
 * Communicates with a Python script to find color words in a file.
 */
public class ColorsAnnotator extends HttpAnnotator {

	@Override
	protected Class<? extends Annotation> getAnnotationClass(String name) throws NoMatchingAnnotationException {
		if (name.equals("Color"))
			return Color.class;
		throw new HttpAnnotator.NoMatchingAnnotationException();
	}
}