package edu.rosehulman.aixprize.pipeline.annotators;

import java.io.*;
import java.net.Socket;

import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;
import org.apache.uima.json.JsonCasSerializer;


public class ColorsAnnotator extends JCasAnnotator_ImplBase {

	@Override
	public void process(JCas cas) throws AnalysisEngineProcessException {
		try {
			StringWriter serialized = new StringWriter();
			JsonCasSerializer.jsonSerialize(cas.getCas(), serialized);
			
			Socket socket = new Socket("127.0.0.1", 8080);
			socket.getOutputStream().write(serialized.toString().getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
