package edu.rosehulman.aixprize.pipeline.http;

import java.io.*;
import java.net.URI;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.uima.jcas.JCas;
import org.apache.uima.json.JsonCasSerializer;

class RequestCreator {
	private URI uri;
	private JCas cas;

	public RequestCreator(URI uri, JCas cas) {
		this.uri = uri;
		this.cas = cas;
	}

	public HttpUriRequest createRequest() throws IOException {
		RequestBuilder requestBuilder = RequestBuilder.post(uri);
		requestBuilder.setEntity(createEntity());
		return requestBuilder.build();
	}

	private HttpEntity createEntity() throws IOException {
		return new StringEntity(jsonifyCas(cas));
	}

	private String jsonifyCas(JCas cas) throws IOException {
		StringWriter serialized = new StringWriter();
		JsonCasSerializer.jsonSerialize(cas.getCas(), serialized);
		return serialized.toString();
	}
}
