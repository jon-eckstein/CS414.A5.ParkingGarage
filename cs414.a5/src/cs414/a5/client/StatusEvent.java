/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cs414.a5.client;

/**
 *
 * @author jeckstein
 */
public class StatusEvent {
    private String message;

    public StatusEvent(String message){
        this.message = message; 
    }
    
    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }
}

