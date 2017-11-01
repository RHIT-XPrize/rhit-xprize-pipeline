package edu.rosehulman.aixprize.pipeline.core;

import java.io.*;
import java.util.*;

import org.apache.commons.io.IOUtils;
import org.json.*;

import edu.rosehulman.aixprize.pipeline.annotators.HttpAnnotator;

public class HttpConfigurationLoader {
	private static final String CONFIG_FILE_NAME = "servers.json";

	public static class NoConfigurationSpecified extends Exception {
		private static final long serialVersionUID = -7148498252286690391L;

		public NoConfigurationSpecified(Class<? extends HttpAnnotator> annotator) {
			super("No configuration loaded for " + annotator.getName());
		}
	}


	private static HttpConfigurationLoader self;

	public static HttpConfigurationLoader getInstance() {
		synchronized (self) {
			if (self == null)
				self = new HttpConfigurationLoader();
		}

		return self;
	}


	private Map<Class<?>, String> addressMap;
	private Map<Class<?>, Integer> portMap;

	private HttpConfigurationLoader() {
		addressMap = new HashMap<>();
		portMap = new HashMap<>();

		fillMaps(loadFileJSON());
	}

	private JSONObject loadFileJSON() {
		JSONObject json;

		try {
			json = new JSONObject(IOUtils.toString(new FileReader(CONFIG_FILE_NAME)));
		} catch (IOException e) {
			e.printStackTrace();
			json = new JSONObject();
		}

		return json;
	}

	private void fillMaps(JSONObject confJson) {
		confJson.keys().forEachRemaining(key -> {
			try {
				Class<?> keyClass = Class.forName(key);
				JSONObject annotatorObj = confJson.getJSONObject(key);

				addressMap.put(keyClass, annotatorObj.getString("address"));
				portMap.put(keyClass, annotatorObj.getInt("port"));
			} catch (ClassNotFoundException | JSONException e) {
				e.printStackTrace();
			}
		});
	}

	public String getAddress(Class<? extends HttpAnnotator> annotator)
		throws NoConfigurationSpecified {
		if (!addressMap.containsKey(annotator))
			throw new NoConfigurationSpecified(annotator);
		return addressMap.get(annotator);
	}

	public int getPort(Class<? extends HttpAnnotator> annotator)
			throws NoConfigurationSpecified {
		if (!portMap.containsKey(annotator))
			throw new NoConfigurationSpecified(annotator);

		return portMap.get(annotator);
	}
}
