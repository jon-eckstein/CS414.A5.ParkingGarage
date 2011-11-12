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
    private ArrayList<SimpleEntry<Class,AbstractView>> subscriberList = new ArrayList<AbstractMap.SimpleEntry<Class, AbstractView>>(); 
    
    private EventAggreagtorImpl(){        
    }
    
    public static synchronized EventAggreagtorImpl getInstance() {
        if(instance == null)
            instance = new EventAggreagtorImpl();
        return instance;
    }
    
    @Override
    public void subscribe(Class eventClass, AbstractView view)
    {
        subscriberList.add(new SimpleEntry<Class, AbstractView>(eventClass, view));
    }
    @Override
    public void unsubscribe(Class eventClass, AbstractView view)
    {
        for(SimpleEntry<Class,AbstractView> entry : subscriberList){
            if(entry.getKey() == eventClass && entry.getValue() == view){
                subscriberList.remove(entry);
            }
        }        
    }
    
    
    
    @Override
    public <T> void publish(T payload)
    {
        Class eventClass= payload.getClass();
        for(SimpleEntry<Class,AbstractView> entry : subscriberList){
            if(entry.getKey() == eventClass)
                entry.getValue().eventOccurred(payload);
        }        
    }
    
    
    
}
