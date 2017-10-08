package edu.rosehulman.aixprize.pipeline.annotators;

import java.io.*;
import java.net.*;

import org.apache.uima.UimaContext;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.json.JsonCasSerializer;
import org.apache.uima.resource.ResourceInitializationException;
import org.json.JSONObject;

import edu.rosehulman.aixprize.pipeline.types.Color;

public class ColorsAnnotator extends JCasAnnotator_ImplBase {
	
	private Socket socket;

	@Override
	public void initialize(UimaContext aContext) throws ResourceInitializationException {
		try {
			socket = new Socket("127.0.0.1", 8080);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void process(JCas cas) throws AnalysisEngineProcessException {
		if (socket == null) {
			return;
		}

		try {
			transmitCas(cas);
			receiveAnnotations(cas);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void transmitCas(JCas cas) throws IOException {
		StringWriter serialized = new StringWriter();
		JsonCasSerializer.jsonSerialize(cas.getCas(), serialized);

		socket.getOutputStream().write(serialized.toString().getBytes());
	}

	private void receiveAnnotations(JCas cas) throws IOException {
		BufferedReader reader
			= new BufferedReader(new InputStreamReader(socket.getInputStream()));
		Annotation annotation;
		JSONObject annotationJson;

		while (true) {
			annotationJson = new JSONObject(reader.readLine());
			if (annotationJson.names().length() == 0) {
				break;
			}

			annotation = createAnnotation(cas, annotationJson);

			annotation.setBegin(annotationJson.getInt("begin"));
			annotation.setEnd(annotationJson.getInt("end"));

			annotation.addToIndexes();
		}
	}

	private Annotation createAnnotation(JCas cas, JSONObject annotationJson) {
		Color annotation = new Color(cas);

		annotation.setName(annotationJson.getString("name"));

		return annotation;
	}
}
