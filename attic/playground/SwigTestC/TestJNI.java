/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.5
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */


public class TestJNI {
  public final static native long new_longArray(int jarg1);
  public final static native void delete_longArray(long jarg1);
  public final static native int longArray_getitem(long jarg1, longArray jarg1_, int jarg2);
  public final static native void longArray_setitem(long jarg1, longArray jarg1_, int jarg2, int jarg3);
  public final static native long longArray_cast(long jarg1, longArray jarg1_);
  public final static native long longArray_frompointer(long jarg1);
  public final static native long new_OffsPairArray(int jarg1);
  public final static native void delete_OffsPairArray(long jarg1);
  public final static native long OffsPairArray_getitem(long jarg1, OffsPairArray jarg1_, int jarg2);
  public final static native void OffsPairArray_setitem(long jarg1, OffsPairArray jarg1_, int jarg2, long jarg3, OffsPair jarg3_);
  public final static native long OffsPairArray_cast(long jarg1, OffsPairArray jarg1_);
  public final static native long OffsPairArray_frompointer(long jarg1, OffsPair jarg1_);
  public final static native void doit(String jarg1);
  public final static native int doit2(long jarg1);
  public final static native void OffsPair_start_set(long jarg1, OffsPair jarg1_, int jarg2);
  public final static native int OffsPair_start_get(long jarg1, OffsPair jarg1_);
  public final static native void OffsPair_end_set(long jarg1, OffsPair jarg1_, int jarg2);
  public final static native int OffsPair_end_get(long jarg1, OffsPair jarg1_);
  public final static native long new_OffsPair();
  public final static native void delete_OffsPair(long jarg1);
  public final static native int doit3(long jarg1, OffsPair jarg1_);
  public final static native void doit4(int[] jarg1);
}