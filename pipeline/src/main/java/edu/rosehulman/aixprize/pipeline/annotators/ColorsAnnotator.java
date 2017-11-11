package edu.rosehulman.aixprize.pipeline.annotators;

import edu.rosehulman.aixprize.pipeline.core.ExternalAnnotator;
import edu.rosehulman.aixprize.pipeline.http.HttpAnnotatorProtocol;

/**
 * Communicates with a Python script to find color words in a file.
 */
public class ColorsAnnotator extends ExternalAnnotator {

	public ColorsAnnotator() {
		super(new HttpAnnotatorProtocol());
	}
}