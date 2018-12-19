package edu.rosehulman.aixprize.pipeline.http;

import java.io.*;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;
import java.util.Map.Entry;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.*;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.uima.cas.CASException;
import org.apache.uima.jcas.JCas;
import org.apache.uima.json.JsonCasSerializer;

class RequestCreator {
	private String uri;
	private JCas cas;
	private MultipartEntityBuilder multipartBuilder;

	public RequestCreator(String uri, JCas cas) {
		this.uri = uri;
		this.cas = cas;
	}

	public HttpUriRequest createRequest() throws IOException {
		multipartBuilder = MultipartEntityBuilder.create();
		
		RequestBuilder requestBuilder = RequestBuilder.post(uri);
//		URL oracle = new URL("http://localhost:56814/api/values");
//        URLConnection yc = oracle.openConnection();
//        BufferedReader in = new BufferedReader(new InputStreamReader(
//                                    yc.getInputStream()));
//        String inputLine;
//        while ((inputLine = in.readLine()) != null) 
//            System.out.println(inputLine);
//        in.close();
		requestBuilder.setEntity(createEntity());
//		HttpClient client = new DefaultHttpClient();
//		HttpPost post = new HttpPost("http://localhost:56814/api/values");
//
//		StringEntity input = new StringEntity("pf you");
//		input.setContentType("multipart/form-data");
//		post.setEntity(input);
//		HttpResponse response = client.execute(post);
//		System.out.println(response.getStatusLine()); 
		return requestBuilder.build();
	}

	private HttpEntity createEntity() throws IOException {
		Map<String, byte[]> binaries = readBinaries(cas);
		addBinaries(binaries);
		addCas(multipartBuilder, cas);

		return multipartBuilder.build();
	}

	private void addBinaries(Map<String, byte[]> binaries) {
		for (Entry<String, byte[]> entry : binaries.entrySet()) {
			multipartBuilder.addBinaryBody(entry.getKey(), entry.getValue(),
					ContentType.DEFAULT_BINARY, entry.getKey());
		}
	}

	private Map<String, byte[]> readBinaries(JCas cas) {
		Map<String, byte[]> binaries = new HashMap<>();

		addCurrentSofa(cas, binaries);
		addViewSofas(cas, binaries);

		return binaries;
	}

	private void addCurrentSofa(JCas cas, Map<String, byte[]> binaries) {
		InputStream sofaStream = cas.getSofaDataStream();

		if (sofaStream != null) {
			try {
				binaries.put(cas.getViewName(),
						IOUtils.toByteArray(sofaStream));
			} catch (IOException e) { /* No-op */ }
		}
	}

	private void addViewSofas(JCas cas, Map<String, byte[]> binaries) {
		try {
			getViewIterator().forEachRemaining(view ->
				binaries.putAll(readBinaries(view)));
		} catch (CASException e) { /* No-op */ }
	}

	private Iterator<JCas> getViewIterator() throws CASException {
		Iterator<JCas> views = cas.getViewIterator();

		// The first "view" is actually the current cas,
		// so remove that from the iterator before returning.
		views.next();

		return views;
	}

	private void addCas(MultipartEntityBuilder multipartBuilder, JCas cas) throws IOException {
		String sCas = jsonifyCas(cas);
		multipartBuilder.addTextBody("cas", sCas);
	}

	private String jsonifyCas(JCas cas) throws IOException {
		StringWriter serialized = new StringWriter();
		JsonCasSerializer.jsonSerialize(cas.getCas(), serialized);
		return serialized.toString();
	}
}
