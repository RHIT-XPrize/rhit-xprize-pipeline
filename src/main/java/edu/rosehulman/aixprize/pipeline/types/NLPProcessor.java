package edu.rosehulman.aixprize.pipeline.types;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;
import org.apache.uima.jcas.tcas.Annotation;


public class NLPProcessor extends Annotation {

	/**
	 * @generated
	 * @ordered
	 */
//	@SuppressWarnings("hiding")
//	public final static String _TypeName = "edu.rosehulman.aixprize.pipeline.types.NLPProcessor";

	/**
	 * @generated
	 * @ordered
	 */
	@SuppressWarnings("hiding")
	public final static int typeIndexID = JCasRegistry.register(NLPProcessor.class);
	/**
	 * @generated
	 * @ordered
	 */
	@SuppressWarnings("hiding")
	public final static int type = typeIndexID;

	/**
	 * @generated
	 * @return index of the type
	 */
	@Override
	public int getTypeIndexID() {
		return typeIndexID;
	}

	/**
	 * Never called. Disable default constructor
	 * 
	 * @generated
	 */
	protected NLPProcessor() {
		/* intentionally empty block */}

	/**
	 * Internal - constructor used by generator
	 * 
	 * @generated
	 * @param casImpl
	 *            the CAS this Feature Structure belongs to
	 * @param type
	 *            the type of this Feature Structure
	 */
	public NLPProcessor(int addr, TOP_Type type) {
		super(addr, type);
		readObject();
	}

	/**
	 * @generated
	 * @param jcas
	 *            JCas to which this Feature Structure belongs
	 */
	public NLPProcessor(JCas jcas) {
		super(jcas);
		readObject();
	}

	/**
	 * @generated
	 * @param jcas
	 *            JCas to which this Feature Structure belongs
	 * @param begin
	 *            offset to the begin spot in the SofA
	 * @param end
	 *            offset to the end spot in the SofA
	 */
	public NLPProcessor(JCas jcas, int begin, int end) {
		super(jcas);
		setBegin(begin);
		setEnd(end);
		readObject();
	}

	/**
	 * <!-- begin-user-doc --> Write your own initialization here <!-- end-user-doc
	 * -->
	 *
	 * @generated modifiable
	 */
	private void readObject() {
		/* default - does nothing empty block */}

	// *--------------*
	// * Feature: confidence

	/**
	 * getter for confidence - gets
	 * 
	 * @generated
	 * @return value of the feature
	 */
	public boolean getUsesGesture() {
		if (NLPProcessor_Type.featOkTst && ((NLPProcessor_Type) jcasType).casFeat_usesGesture == null)
			jcasType.jcas.throwFeatMissing("usesGesture", "edu.rosehulman.aixprize.pipeline.types.NLPProcessor");
		return jcasType.ll_cas.ll_getBooleanValue(addr, ((NLPProcessor_Type) jcasType).casFeatCode_usesGesture);
	}
	
	/**
	 * getter for confidence - gets
	 * 
	 * @generated
	 * @return value of the feature
	 */
	public String getAssignedName() {
		if (NLPProcessor_Type.featOkTst && ((NLPProcessor_Type) jcasType).casFeat_assignedName == null)
			jcasType.jcas.throwFeatMissing("assignedName", "edu.rosehulman.aixprize.pipeline.types.NLPProcessor");
		return jcasType.ll_cas.ll_getStringValue(addr, ((NLPProcessor_Type) jcasType).casFeatCode_assignedName);
	}
	
	public String getMods() {
		if (NLPProcessor_Type.featOkTst && ((NLPProcessor_Type) jcasType).casFeat_mods == null)
			jcasType.jcas.throwFeatMissing("mods", "edu.rosehulman.aixprize.pipeline.types.NLPProcessor");
		return jcasType.ll_cas.ll_getStringValue(addr, ((NLPProcessor_Type) jcasType).casFeatCode_mods);
	}
	
	public String getCommands() {
		if (NLPProcessor_Type.featOkTst && ((NLPProcessor_Type) jcasType).casFeat_command == null)
			jcasType.jcas.throwFeatMissing("command", "edu.rosehulman.aixprize.pipeline.types.NLPProcessor");
		return jcasType.ll_cas.ll_getStringValue(addr, ((NLPProcessor_Type) jcasType).casFeatCode_command);
	}

	/**
	 * setter for confidence - sets
	 * 
	 * @generated
	 * @param v
	 *            value to set into the feature
	 */
	public void setAssignedName(String v) {
		if (NLPProcessor_Type.featOkTst && ((NLPProcessor_Type) jcasType).casFeat_assignedName == null)
			jcasType.jcas.throwFeatMissing("assignedName", "edu.rosehulman.aixprize.pipeline.types.NLPProcessor");
		jcasType.ll_cas.ll_setStringValue(addr, ((NLPProcessor_Type) jcasType).casFeatCode_assignedName, v);
	}
	
	/**
	 * setter for confidence - sets
	 * 
	 * @generated
	 * @param v
	 *            value to set into the feature
	 */
	public void setUsesGesture(boolean v) {
		if (NLPProcessor_Type.featOkTst && ((NLPProcessor_Type) jcasType).casFeat_usesGesture == null)
			jcasType.jcas.throwFeatMissing("usesGesture", "edu.rosehulman.aixprize.pipeline.types.NLPProcessor");
		jcasType.ll_cas.ll_setBooleanValue(addr, ((NLPProcessor_Type) jcasType).casFeatCode_usesGesture, v);
	}
	
	public void setMods(String v) {
		if (NLPProcessor_Type.featOkTst && ((NLPProcessor_Type) jcasType).casFeat_mods == null)
			jcasType.jcas.throwFeatMissing("mods", "edu.rosehulman.aixprize.pipeline.types.NLPProcessor");
		jcasType.ll_cas.ll_setStringValue(addr, ((NLPProcessor_Type) jcasType).casFeatCode_mods, v);
	}
	public void setCommands(String v) {
		if (NLPProcessor_Type.featOkTst && ((NLPProcessor_Type) jcasType).casFeat_command == null)
			jcasType.jcas.throwFeatMissing("command", "edu.rosehulman.aixprize.pipeline.types.NLPProcessor");
		jcasType.ll_cas.ll_setStringValue(addr, ((NLPProcessor_Type) jcasType).casFeatCode_command, v);
	}
}