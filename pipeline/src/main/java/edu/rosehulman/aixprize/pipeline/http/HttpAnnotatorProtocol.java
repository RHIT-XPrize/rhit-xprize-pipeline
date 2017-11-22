package edu.rosehulman.aixprize.pipeline.http;

import java.io.*;
import java.net.*;
import java.util.*;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.*;
import org.apache.uima.UimaContext;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.cas.*;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;
import org.json.*;

import edu.rosehulman.aixprize.pipeline.core.ExternalAnnotatorProtocol;
import edu.rosehulman.aixprize.pipeline.http.HttpConfigurationLoader.NoConfigurationFound;

public class HttpAnnotatorProtocol implements ExternalAnnotatorProtocol {
	public static class NoMatchingAnnotationException extends Exception {
		private static final long serialVersionUID = 7484866497315133495L;
	}

	private Class<? extends JCasAnnotator_ImplBase> annotator;

	private URI uri;
	private CloseableHttpClient client;

	private HttpResponse response;

	public HttpAnnotatorProtocol(Class<? extends JCasAnnotator_ImplBase> boundAnnotator) {
		annotator = boundAnnotator;
	}

	@Override
	public void initialize(UimaContext context) {
		try {
			this.uri = buildUri();
			this.client = HttpClientBuilder.create().build();
		} catch (URISyntaxException | NoConfigurationFound e) {
			e.printStackTrace();
		}
	}

	private URI buildUri() throws URISyntaxException, NoConfigurationFound {
		HttpConfigurationLoader configurationLoader = HttpConfigurationLoader.getInstance();
		URIBuilder builder = new URIBuilder();

		builder.setHost(configurationLoader.getAddress(annotator));
		builder.setScheme("http");
		builder.setPort(configurationLoader.getPort(annotator));

		return builder.build();
	}

	@Override
	public void transmitCas(JCas cas) {
		try {
			RequestCreator requestCreator = new RequestCreator(uri, cas);
			HttpUriRequest req = requestCreator.createRequest();
			response = client.execute(req);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void addAnnotations(JCas cas) {
		JSONObject annotationJson;

		try {
			annotationJson = readResponseJson();
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}

		for (String annotationName : JSONObject.getNames(annotationJson)) {
			JSONArray jsonAnnotations = annotationJson.getJSONArray(annotationName);
			List<Annotation> annotations = new ArrayList<>();
			for (int i = 0; i < jsonAnnotations.length(); i++) {
				try {
					annotations.add(this.createAnnotation(cas, annotationName, jsonAnnotations.getJSONObject(i)));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			annotations.forEach(Annotation::addToIndexes);
		}
	}

	private JSONObject readResponseJson() throws IOException {
		InputStream stream = response.getEntity().getContent();
		InputStreamReader streamReader = new InputStreamReader(stream);
		BufferedReader reader = new BufferedReader(streamReader);

		return new JSONObject(reader.readLine());
	}

	protected Annotation createAnnotation(JCas cas, String annotationName, JSONObject annotationJson)
			throws Exception {
		Annotation annotation = (Annotation) Class.forName(annotationName).getConstructor(JCas.class).newInstance(cas);

		annotationJson.keys().forEachRemaining(field -> addFieldToAnnotation(cas, annotationJson, annotation, field));

		return annotation;
	}

	protected void addFieldToAnnotation(JCas cas, JSONObject annotationJson, Annotation annotation, String field) {
		try {
			Feature feature = cas.getRequiredFeature(annotation.getType(), field);
			annotation.setFeatureValueFromString(feature, annotationJson.get(field).toString());
		} catch (CASException e) {
			e.printStackTrace();
		}
	}
}