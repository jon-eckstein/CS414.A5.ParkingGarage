/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cs414.a5.client;

import java.util.AbstractMap;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;

/**
 *
 * @author jeckstein
 */
public class EventAggreagtorImpl implements EventAggregator {
    
    private static EventAggreagtorImpl instance;
    private ArrayList<SimpleEntry<Class,EventObserver>> subscriberList = new ArrayList<AbstractMap.SimpleEntry<Class, EventObserver>>(); 
    
    private EventAggreagtorImpl(){        
    }
    
    public static synchronized EventAggreagtorImpl getInstance() {
        if(instance == null)
            instance = new EventAggreagtorImpl();
        return instance;
    }
    
    @Override
    public void subscribe(Class eventClass, EventObserver observer)
    {
        subscriberList.add(new SimpleEntry<Class, EventObserver>(eventClass, observer));
    }
    @Override
    public void unsubscribe(Class eventClass, EventObserver observer)
    {
        for(SimpleEntry<Class,EventObserver> entry : subscriberList){
            if(entry.getKey() == eventClass && entry.getValue() == observer){
                subscriberList.remove(entry);
            }
        }        
    }
    
    
    
    @Override
    public <T> void publish(T payload)
    {
        Class eventClass= payload.getClass();
        for(SimpleEntry<Class,EventObserver> entry : subscriberList){
            if(entry.getKey() == eventClass)
                entry.getValue().notifyOnEvent(payload);
        }        
    }
    
    
    
}
