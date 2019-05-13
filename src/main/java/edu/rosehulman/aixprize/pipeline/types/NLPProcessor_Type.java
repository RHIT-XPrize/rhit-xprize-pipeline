
/* First created by JCasGen Sun Oct 08 07:47:26 EDT 2017 */
package edu.rosehulman.aixprize.pipeline.types;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;
import org.apache.uima.jcas.tcas.Annotation_Type;

/**
 * Updated by JCasGen Tue Oct 17 17:10:22 EDT 2017
 * 
 * @generated
 */
public class NLPProcessor_Type extends Annotation_Type {
	/** @generated */
	@SuppressWarnings("hiding")
	public final static int typeIndexID = NLPProcessor.typeIndexID;
	/**
	 * @generated
	 * @modifiable
	 */
	@SuppressWarnings("hiding")
	public final static boolean featOkTst = JCasRegistry
			.getFeatOkTst("edu.rosehulman.aixprize.pipeline.types.NLPProcessor");

	/** @generated */
	final Feature casFeat_usesGesture;
	/** @generated */
	final int casFeatCode_usesGesture;
	
	/** @generated */
	final Feature casFeat_assignedName;
	/** @generated */
	final int casFeatCode_assignedName;
	
	/** @generated */
	final Feature casFeat_mods;
	/** @generated */
	final int casFeatCode_mods;
	
	/** @generated */
	final Feature casFeat_command;
	/** @generated */
	final int casFeatCode_command;

	/**
	 * @generated
	 * @param addr
	 *            low level Feature Structure reference
	 * @return the feature value
	 */
	public boolean getUsesGesture(int addr) {
		if (featOkTst && casFeat_usesGesture == null)
			jcas.throwFeatMissing("usesGesture", "edu.rosehulman.aixprize.pipeline.types.NLPProcessor");
		return ll_cas.ll_getBooleanValue(addr, casFeatCode_usesGesture);
	}
	
	/**
	 * @generated
	 * @param addr
	 *            low level Feature Structure reference
	 * @return the feature value
	 */
	public String getAssignedName(int addr) {
		if (featOkTst && casFeat_assignedName == null)
			jcas.throwFeatMissing("assignedName", "edu.rosehulman.aixprize.pipeline.types.NLPProcessor");
		return ll_cas.ll_getStringValue(addr, casFeatCode_assignedName);
	}
	
	public String getMods(int addr) {
		if (featOkTst && casFeat_mods == null)
			jcas.throwFeatMissing("mods", "edu.rosehulman.aixprize.pipeline.types.NLPProcessor");
		return ll_cas.ll_getStringValue(addr, casFeatCode_mods);
	}
	public String getCommand(int addr) {
		if (featOkTst && casFeat_command == null)
			jcas.throwFeatMissing("command", "edu.rosehulman.aixprize.pipeline.types.NLPProcessor");
		return ll_cas.ll_getStringValue(addr, casFeatCode_command);
	}

	/**
	 * @generated
	 * @param addr
	 *            low level Feature Structure reference
	 * @param v
	 *            value to set
	 */
	public void setUsesGesture(int addr, boolean v) {
		if (featOkTst && casFeat_usesGesture == null)
			jcas.throwFeatMissing("usesGesture", "edu.rosehulman.aixprize.pipeline.types.NLPProcessor");
		ll_cas.ll_setBooleanValue(addr, casFeatCode_usesGesture, v);
	}
	
	/**
	 * @generated
	 * @param addr
	 *            low level Feature Structure reference
	 * @param v
	 *            value to set
	 */
	public void setAssignedName(int addr, String v) {
		if (featOkTst && casFeat_assignedName == null)
			jcas.throwFeatMissing("assignedName", "edu.rosehulman.aixprize.pipeline.types.NLPProcessor");
		ll_cas.ll_setStringValue(addr, casFeatCode_assignedName, v);
	}
	
	public void setMods(int addr, String v) {
		if (featOkTst && casFeat_mods == null)
			jcas.throwFeatMissing("mods", "edu.rosehulman.aixprize.pipeline.types.NLPProcessor");
		ll_cas.ll_setStringValue(addr, casFeatCode_mods, v);
	}

	public void setCommand(int addr, String v) {
		if (featOkTst && casFeat_command == null)
			jcas.throwFeatMissing("command", "edu.rosehulman.aixprize.pipeline.types.NLPProcessor");
		ll_cas.ll_setStringValue(addr, casFeatCode_command, v);
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
	public NLPProcessor_Type(JCas jcas, Type casType) {
		super(jcas, casType);
		casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl) this.casType, getFSGenerator());

		casFeat_usesGesture = jcas.getRequiredFeatureDE(casType, "usesGesture", "uima.cas.Boolean", featOkTst);
		casFeatCode_usesGesture = (null == casFeat_usesGesture) ? JCas.INVALID_FEATURE_CODE
				: ((FeatureImpl) casFeat_usesGesture).getCode();
		
		casFeat_assignedName = jcas.getRequiredFeatureDE(casType, "assignedName", "uima.cas.String", featOkTst);
		casFeatCode_assignedName = (null == casFeat_assignedName) ? JCas.INVALID_FEATURE_CODE
				: ((FeatureImpl) casFeat_assignedName).getCode();
		
		casFeat_mods = jcas.getRequiredFeatureDE(casType, "mods", "uima.cas.String", featOkTst);
		casFeatCode_mods = (null == casFeat_mods) ? JCas.INVALID_FEATURE_CODE
				: ((FeatureImpl) casFeat_mods).getCode();
		casFeat_command = jcas.getRequiredFeatureDE(casType, "mods", "uima.cas.String", featOkTst);
		casFeatCode_command = (null == casFeat_command) ? JCas.INVALID_FEATURE_CODE
				: ((FeatureImpl) casFeat_command).getCode();

	}
}