/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cs414.a5.client;

import cs414.a5.common.EntryEvent;

/**
 *
 * @author jeckstein
 */
public class ServiceOperationResult<T> {
    
    private T returnObject;
    private boolean isSuccess;
    private Exception exception;
    
    public ServiceOperationResult(T returnObject, boolean isSuccess, Exception exc){
        this.returnObject = returnObject;
        this.isSuccess = isSuccess;
        this.exception = exc;
    }

    ServiceOperationResult(T obj, boolean b) {
        this(obj,b,null);
    }
    
    public ServiceOperationResult(Exception ex){
        this(null,false,ex);
    }
    
    /**
     * @return the returnObject
     */
    public T getReturnObject() {
        return returnObject;
    }

    /**
     * @return the isSuccess
     */
    public boolean getIsSuccess() {
        return isSuccess;
    }

    /**
     * @return the message
     */
    public Exception getException() {
        return exception;
    }
    
}
