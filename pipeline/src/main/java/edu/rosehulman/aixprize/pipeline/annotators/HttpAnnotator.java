package edu.rosehulman.aixprize.pipeline.annotators;

import java.io.*;
import java.net.*;
import java.util.*;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.*;
import org.apache.uima.UimaContext;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.*;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.resource.ResourceInitializationException;
import org.json.*;

public abstract class HttpAnnotator extends JCasAnnotator_ImplBase {
	protected static class NoMatchingAnnotationException extends Exception {
		private static final long serialVersionUID = 7484866497315133495L;
	}

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
			RequestCreator requestCreator = new RequestCreator(uri, cas);
			HttpUriRequest req = requestCreator.createRequest();
			receiveAnnotations(cas, this.client.execute(req));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void receiveAnnotations(JCas cas, HttpResponse resp) throws IOException {
		InputStream stream = resp.getEntity().getContent();
		BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
		JSONObject annotationJson;

		annotationJson = new JSONObject(reader.readLine());
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

	protected abstract Class<? extends Annotation> getAnnotationClass(String name) throws NoMatchingAnnotationException;

	protected Annotation createAnnotation(JCas cas, String annotationName, JSONObject annotationJson)
			throws Exception {
		Annotation annotation = getAnnotationClass(annotationName).getConstructor(JCas.class).newInstance(cas);

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