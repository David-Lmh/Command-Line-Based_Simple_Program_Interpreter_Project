package hk.edu.polyu.comp.comp2021.simple.model.initialize;

import java.util.Vector;
/**
 * data interface
 * */
public abstract class data
{
    /**
     * set MAX POSITIVE NUMBER = 99999
     * */
    int MAX_INT_VAL = 99999;
    /**
     * set MAX NEGATIVE NUMBER = -99999
     *  */
    int MIN_INT_VAL = -99999;

    /**
     *  element
     *  */
    public class ele
    {
        private String varName, val;
        /**
         * @param varName : varName
         * @param val : val
         * */
        ele(String varName,String val)
        {
            this.varName = varName;
            this.val = val;
        }
        /**
         * get varName
         * @return varName
         * */
        public String getVarName()
        {
            return varName;
        }
        /**
         * get val
         * @return val
         * */
        public String getVal()
        {
            return val;
        }

        /**
         * set val
         * @param x : x
         */
        public void setVal(String x)
        {
            val = x;
        }
    }
    /**
     * @param programName : programName
     * @param isDebugging : isDebugging
     * @return execution result
     * */
    public abstract String exe(String programName, boolean isDebugging);
    /**
     * @return label
     * */
    public abstract String getLab();

    /**
     * store
     * @param v : v
     */
    public abstract void store(Vector<String> v);
}