/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cs414.a5.client;

/**
 *
 * @author jeckstein
 */
public class ExceptionOccuredEvent {
    private Exception exception;

    public ExceptionOccuredEvent(Exception ex){
        this.exception = ex;
    }
    
    /**
     * @return the exception
     */
    public Exception getException() {
        return exception;
    }

    /**
     * @param exception the exception to set
     */
    public void setException(Exception exception) {
        this.exception = exception;
    }
}
