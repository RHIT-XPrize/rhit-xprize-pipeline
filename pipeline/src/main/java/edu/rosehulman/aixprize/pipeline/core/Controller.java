package edu.rosehulman.aixprize.pipeline.core;

import org.apache.commons.logging.Log;
import org.ros.namespace.GraphName;
import org.ros.node.*;
import org.apache.uima.*;

public class Controller extends AbstractNodeMain {

	@Override
	public GraphName getDefaultNodeName() {
		return GraphName.of("pipeline/core");
	}
	
	@Override
	public void onStart(ConnectedNode connectedNode) {
		Log log = connectedNode.getLog();
		log.info(UIMAFramework.getVersionString());
	}
}
