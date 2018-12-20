package edu.rosehulman.aixprize.pipeline.types;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;
import org.apache.uima.jcas.tcas.Annotation_Type;

public class DetectedBlock_Type extends Annotation_Type {

	@SuppressWarnings("hiding")
	public final static int typeIndexID = Color.typeIndexID;

	@SuppressWarnings("hiding")
	public final static boolean featOkTst = JCasRegistry
			.getFeatOkTst("edu.rosehulman.aixprize.pipeline.types.DetectedBlock");

	final Feature casFeat_center_X;
	final int casFeatCode_center_X;
	
	final Feature casFeat_center_Y;
	final int casFeatCode_center_Y;
	
	final Feature casFeat_depth;
	final int casFeatCode_depth;

	public String getCenter_X(int addr) {
		if (featOkTst && casFeat_center_X == null)
			jcas.throwFeatMissing("center_X", "edu.rosehulman.aixprize.pipeline.types.DetectedBlock");
		return ll_cas.ll_getStringValue(addr, casFeatCode_center_X);
	}

	public void setCenter_X(int addr, String v) {
		if (featOkTst && casFeat_center_X == null)
			jcas.throwFeatMissing("center_X", "edu.rosehulman.aixprize.pipeline.types.DetectedBlock");
		ll_cas.ll_setStringValue(addr, casFeatCode_center_X, v);
	}
	
	public String getCenter_Y(int addr) {
		if (featOkTst && casFeat_center_Y == null)
			jcas.throwFeatMissing("center_Y", "edu.rosehulman.aixprize.pipeline.types.DetectedBlock");
		return ll_cas.ll_getStringValue(addr, casFeatCode_center_Y);
	}

	public void setCenter_Y(int addr, String v) {
		if (featOkTst && casFeat_center_Y == null)
			jcas.throwFeatMissing("center_Y", "edu.rosehulman.aixprize.pipeline.types.DetectedBlock");
		ll_cas.ll_setStringValue(addr, casFeatCode_center_Y, v);
	}
	
	public String getDepth(int addr) {
		if (featOkTst && casFeat_depth == null)
			jcas.throwFeatMissing("depth", "edu.rosehulman.aixprize.pipeline.types.DetectedBlock");
		return ll_cas.ll_getStringValue(addr, casFeatCode_depth);
	}

	public void setDepth(int addr, String v) {
		if (featOkTst && casFeat_depth == null)
			jcas.throwFeatMissing("depth", "edu.rosehulman.aixprize.pipeline.types.DetectedBlock");
		ll_cas.ll_setStringValue(addr, casFeatCode_depth, v);
	}


	/**
	 * initialize variables to correspond with Cas Type and Features
	 * 
	 * @generated
	 * @param jcas
	 *            JCas
	 * @param casType
	 *            Type
	 */
	public DetectedBlock_Type(JCas jcas, Type casType) {
		super(jcas, casType);
		casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl) this.casType, getFSGenerator());

		casFeat_center_X = jcas.getRequiredFeatureDE(casType, "center_X", "uima.cas.String", featOkTst);
		casFeatCode_center_X = (null == casFeat_center_X) ? JCas.INVALID_FEATURE_CODE
				: ((FeatureImpl) casFeat_center_X).getCode();
		
		casFeat_center_Y = jcas.getRequiredFeatureDE(casType, "center_Y", "uima.cas.String", featOkTst);
		casFeatCode_center_Y = (null == casFeat_center_Y) ? JCas.INVALID_FEATURE_CODE
				: ((FeatureImpl) casFeat_center_Y).getCode();
		
		casFeat_depth = jcas.getRequiredFeatureDE(casType, "depth", "uima.cas.String", featOkTst);
		casFeatCode_depth = (null == casFeat_depth) ? JCas.INVALID_FEATURE_CODE
				: ((FeatureImpl) casFeat_depth).getCode();
	}
}
