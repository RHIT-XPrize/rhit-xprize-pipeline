package edu.rosehulman.aixprize.pipeline.annotators;

import java.io.*;
import java.net.Socket;
import java.util.List;

import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.CAS;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.json.JsonCasSerializer;
import org.json.*;

public class ColorsAnnotator extends JCasAnnotator_ImplBase {

	@Override
	public void process(JCas cas) throws AnalysisEngineProcessException {
		try {
			Socket socket = new Socket("127.0.0.1", 8080);
			transmitCas(cas.getCas(), socket);
			List<Annotation> annotations = receiveAnnotations(socket);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void transmitCas(CAS cas, Socket socket) throws IOException {
		StringWriter serialized = new StringWriter();
		JsonCasSerializer.jsonSerialize(cas, serialized);
		
		socket.getOutputStream().write(serialized.toString().getBytes());
	}
	
	private List<Annotation> receiveAnnotations(Socket socket) throws IOException {
		BufferedReader reader 
			= new BufferedReader(new InputStreamReader(socket.getInputStream()));
		String line;
		
		while ((line = reader.readLine()) != null) {
			JSONObject object = new JSONObject(line);
		}
		
		return null;
	}
}
