/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cs414.a5.client;

/**
 *
 * @author jeckstein
 */
public interface EventObserver {
    
    <T> void notifyOnEvent(T eventPayload);    
    
}
