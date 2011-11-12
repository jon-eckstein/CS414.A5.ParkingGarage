/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cs414.a5.client;

/**
 *
 * @author jeckstein
 */
public class ServiceCommunicationException extends Exception {
    
    public ServiceCommunicationException(String message, Throwable exc){
        super(message, exc);        
    }
    
    public ServiceCommunicationException(String message){
        super(message);
    }
    
}
