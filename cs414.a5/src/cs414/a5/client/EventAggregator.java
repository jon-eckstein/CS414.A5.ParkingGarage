/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cs414.a5.client;

/**
 *
 * @author jeckstein
 */
public interface EventAggregator {

    <T> void publish(T payload);

    void subscribe(Class eventClass, AbstractView view);

    void unsubscribe(Class eventClass, AbstractView view);
    
}
