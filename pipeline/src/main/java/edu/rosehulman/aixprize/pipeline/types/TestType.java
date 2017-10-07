

/* First created by JCasGen Sat Oct 07 14:50:45 EDT 2017 */
package edu.rosehulman.aixprize.pipeline.types;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Sat Oct 07 14:50:45 EDT 2017
 * XML source: /mnt/ubuntu/home/lewis/aixprize_ws/src/edu_rosehulman_aixprize/pipeline/desc/TestAnnotatorDescriptor
 * @generated */
public class TestType extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(TestType.class);
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int type = typeIndexID;
  /** @generated
   * @return index of the type  
   */
  @Override
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected TestType() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public TestType(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public TestType(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public TestType(JCas jcas, int begin, int end) {
    super(jcas);
    setBegin(begin);
    setEnd(end);
    readObject();
  }   

  /** 
   * <!-- begin-user-doc -->
   * Write your own initialization here
   * <!-- end-user-doc -->
   *
   * @generated modifiable 
   */
  private void readObject() {/*default - does nothing empty block */}
     
 
    
  //*--------------*
  //* Feature: TestFeature

  /** getter for TestFeature - gets 
   * @generated
   * @return value of the feature 
   */
  public String getTestFeature() {
    if (TestType_Type.featOkTst && ((TestType_Type)jcasType).casFeat_TestFeature == null)
      jcasType.jcas.throwFeatMissing("TestFeature", "edu.rosehulman.aixprize.pipeline.types.TestType");
    return jcasType.ll_cas.ll_getStringValue(addr, ((TestType_Type)jcasType).casFeatCode_TestFeature);}
    
  /** setter for TestFeature - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setTestFeature(String v) {
    if (TestType_Type.featOkTst && ((TestType_Type)jcasType).casFeat_TestFeature == null)
      jcasType.jcas.throwFeatMissing("TestFeature", "edu.rosehulman.aixprize.pipeline.types.TestType");
    jcasType.ll_cas.ll_setStringValue(addr, ((TestType_Type)jcasType).casFeatCode_TestFeature, v);}    
  }

    