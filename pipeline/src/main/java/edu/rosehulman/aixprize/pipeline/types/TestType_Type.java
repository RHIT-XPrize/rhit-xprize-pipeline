
/* First created by JCasGen Sat Oct 07 14:50:45 EDT 2017 */
package edu.rosehulman.aixprize.pipeline.types;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;
import org.apache.uima.jcas.tcas.Annotation_Type;

/** 
 * Updated by JCasGen Sat Oct 07 14:50:45 EDT 2017
 * @generated */
public class TestType_Type extends Annotation_Type {
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = TestType.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("edu.rosehulman.aixprize.pipeline.types.TestType");
 
  /** @generated */
  final Feature casFeat_TestFeature;
  /** @generated */
  final int     casFeatCode_TestFeature;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getTestFeature(int addr) {
        if (featOkTst && casFeat_TestFeature == null)
      jcas.throwFeatMissing("TestFeature", "edu.rosehulman.aixprize.pipeline.types.TestType");
    return ll_cas.ll_getStringValue(addr, casFeatCode_TestFeature);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setTestFeature(int addr, String v) {
        if (featOkTst && casFeat_TestFeature == null)
      jcas.throwFeatMissing("TestFeature", "edu.rosehulman.aixprize.pipeline.types.TestType");
    ll_cas.ll_setStringValue(addr, casFeatCode_TestFeature, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	 * @generated
	 * @param jcas JCas
	 * @param casType Type 
	 */
  public TestType_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_TestFeature = jcas.getRequiredFeatureDE(casType, "TestFeature", "uima.cas.String", featOkTst);
    casFeatCode_TestFeature  = (null == casFeat_TestFeature) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_TestFeature).getCode();

  }
}



    