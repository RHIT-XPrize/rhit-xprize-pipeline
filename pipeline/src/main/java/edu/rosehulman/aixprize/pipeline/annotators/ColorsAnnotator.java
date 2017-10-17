package edu.rosehulman.aixprize.pipeline.annotators;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.*;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.*;
import org.apache.uima.UimaContext;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.json.JsonCasSerializer;
import org.apache.uima.resource.ResourceInitializationException;
import org.json.*;

import edu.rosehulman.aixprize.pipeline.types.Color;

/**
 * Communicates with a Python script to find color words in a file. 
 * Interface: 
 * - writes a standard JSON-ified CAS to the Socket at port 8080 
 * - receives a series of annotations in JSON, one per line, with fields of: 
 *   + begin: int 
 *   + end: int 
 *   + name: String 
 * - the last annotation line should be an empty JSON object
 */
public class ColorsAnnotator extends JCasAnnotator_ImplBase {

	private URI uri;
	private CloseableHttpClient client;

	@Override
	public void initialize(UimaContext aContext) throws ResourceInitializationException {
		try {
			this.uri = new URIBuilder().setHost("127.0.0.1")
									   .setScheme("http")
									   .setPort(8080)
									   .build();
			this.client = HttpClientBuilder.create().build();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void process(JCas cas) throws AnalysisEngineProcessException {
		if (this.uri == null) {
			return;
		}

		try {
			HttpUriRequest req = createRequest(cas);
			receiveAnnotations(cas, this.client.execute(req));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private HttpUriRequest createRequest(JCas cas) throws IOException {
		StringWriter serialized = new StringWriter();
		JsonCasSerializer.jsonSerialize(cas.getCas(), serialized);
		String sCas = serialized.toString();
		return RequestBuilder.post(this.uri)
							  .setEntity(new StringEntity(sCas))
							  .build();
	}

	private void receiveAnnotations(JCas cas, HttpResponse resp) throws IOException {
		InputStream stream = resp.getEntity().getContent();
		BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
		JSONObject annotationJson;

		annotationJson = new JSONObject(reader.readLine());
		JSONArray colors = annotationJson.getJSONArray("Color");
		List<Annotation> colorAnnotations = new ArrayList<>();
		for (int i = 0; i < colors.length(); i++) {
			colorAnnotations.add(this.createAnnotation(cas, colors.getJSONObject(i)));
		}

		colorAnnotations.forEach(Annotation::addToIndexes);
	}

	private Annotation createAnnotation(JCas cas, JSONObject annotationJson) {
		Color annotation = new Color(cas);
		annotation.setName(annotationJson.getString("color"));
		annotation.setBegin(annotationJson.getInt("begin"));
		annotation.setEnd(annotationJson.getInt("end"));
		return annotation;
	}
}
