
/* Apache UIMA v3 - First created by JCasGen Wed Dec 12 23:28:16 EST 2018 */

package edu.rosehulman.aixprize.pipeline.types;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;
import org.apache.uima.jcas.tcas.Annotation;

public class DetectedBlock extends Annotation {
	/**
	 * @generated
	 * @ordered
	 */
	@SuppressWarnings("hiding")
	public final static int typeIndexID = JCasRegistry.register(DetectedBlock.class);
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
	protected DetectedBlock() {
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
	public DetectedBlock(int addr, TOP_Type type) {
		super(addr, type);
		readObject();
	}

	/**
	 * @generated
	 * @param jcas
	 *            JCas to which this Feature Structure belongs
	 */
	public DetectedBlock(JCas jcas) {
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
	public DetectedBlock(JCas jcas, int begin, int end) {
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

	public String getCenter_X(int addr) {
		if (DetectedBlock_Type.featOkTst && ((DetectedBlock_Type) jcasType).casFeat_center_X == null)
			jcasType.jcas.throwFeatMissing("center_X", "edu.rosehulman.aixprize.pipeline.types.DetectedBlock");
		return jcasType.ll_cas.ll_getStringValue(addr, ((DetectedBlock_Type) jcasType).casFeatCode_center_X);
	}

	public void setCenter_X(int addr, String v) {
		if (DetectedBlock_Type.featOkTst && ((DetectedBlock_Type) jcasType).casFeat_center_X == null)
			jcasType.jcas.throwFeatMissing("center_X", "edu.rosehulman.aixprize.pipeline.types.DetectedBlock");
		jcasType.ll_cas.ll_setStringValue(addr, ((DetectedBlock_Type) jcasType).casFeatCode_center_X, v);
	}

	public String getCenter_Y(int addr) {
		if (DetectedBlock_Type.featOkTst && ((DetectedBlock_Type) jcasType).casFeat_center_Y == null)
			jcasType.jcas.throwFeatMissing("center_Y", "edu.rosehulman.aixprize.pipeline.types.DetectedBlock");
		return jcasType.ll_cas.ll_getStringValue(addr, ((DetectedBlock_Type) jcasType).casFeatCode_center_Y);
	}

	public void setCenter_Y(int addr, String v) {
		if (DetectedBlock_Type.featOkTst && ((DetectedBlock_Type) jcasType).casFeat_center_Y == null)
			jcasType.jcas.throwFeatMissing("center_Y", "edu.rosehulman.aixprize.pipeline.types.DetectedBlock");
		jcasType.ll_cas.ll_setStringValue(addr, ((DetectedBlock_Type) jcasType).casFeatCode_center_Y, v);
	}

	public String getDepth(int addr) {
		if (DetectedBlock_Type.featOkTst && ((DetectedBlock_Type) jcasType).casFeat_depth == null)
			jcasType.jcas.throwFeatMissing("depth", "edu.rosehulman.aixprize.pipeline.types.DetectedBlock");
		return jcasType.ll_cas.ll_getStringValue(addr, ((DetectedBlock_Type) jcasType).casFeatCode_depth);
	}

	public void setDepth(int addr, String v) {
		if (DetectedBlock_Type.featOkTst && ((DetectedBlock_Type) jcasType).casFeat_depth == null)
			jcasType.jcas.throwFeatMissing("depth", "edu.rosehulman.aixprize.pipeline.types.DetectedBlock");
		jcasType.ll_cas.ll_setStringValue(addr, ((DetectedBlock_Type) jcasType).casFeatCode_depth, v);
	}
}
