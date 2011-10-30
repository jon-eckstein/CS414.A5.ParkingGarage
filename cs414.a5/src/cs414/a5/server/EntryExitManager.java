/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cs414.a5.server;

import cs414.a5.common.Utilities;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author jeckstein
 */
public class EntryExitManager {
        
    Map<String,EntryEvent> openEntries;
    ArrayList<ExitEvent> exitEvents;
    RateManager rateManager;
    private int ticketIdCounter = 1;     
    
    public EntryExitManager(RateManager rm){
        rateManager = rm;
        openEntries = new HashMap<String, EntryEvent>();
        exitEvents = new ArrayList<ExitEvent>();
    }
    
    
    public int getFilledSpots() {
       return openEntries.size();
    }

    public EntryEvent createEntryEvent(Date entryDate) {
        EntryEvent newEntry = new EntryEvent(Integer.toString(ticketIdCounter++), entryDate);
        openEntries.put(newEntry.getTicketId(), newEntry);
        return newEntry;
    }
    
    public EntryEvent createEntryEvent(){
        return createEntryEvent(new Date());
    }
    
    
    public ExitEvent createExitEvent(String ticketId, Date exitDateTime) throws Exception{
        ExitEvent exit;
        BigDecimal rate;
                
        if(!Utilities.isNullOrEmpty(ticketId)){
            //this is a regular entry/exit event
            EntryEvent entry = openEntries.get(ticketId);
            if(entry == null) throw new Exception("Could not find entry with given ticket ID.");
            rate = getRate(entry.getEntryDate(), exitDateTime);
            exit = new ExitEvent(entry, exitDateTime, rate);
            //remove the openEntry record...
            openEntries.remove(ticketId);
        }else{
            //lost or damaged ticket, flat rate transaction...
            rate = getRate(exitDateTime);
            exit = new ExitEvent(null, exitDateTime, rate);            
        }
        
        exitEvents.add(exit);
        return exit;                
    }
    
    public ExitEvent createExitEvent(String ticketId) throws Exception{
        return createExitEvent(ticketId, new Date());
    }
    
           
    public EntryEvent getEntryEvent(String ticketId) throws Exception {
        if(openEntries.containsKey(ticketId))
            return openEntries.get(ticketId);
        else
            throw new Exception("Invalid ticket number.");                     
    }
    
    public ExitEvent getExitEvent(String ticketId) throws Exception{
        for(ExitEvent event : exitEvents)
            if(event.getTicketId().equals(ticketId))
                return event;
        
        throw new Exception("Could not find exit event.");
    }
    
    public EntryEvent[] getCurrentEntryEvents(){
        return (EntryEvent[])openEntries.values().toArray(new EntryEvent[openEntries.size()]);        
    }
    
    public ExitEvent[] getExitEvents(){
        return exitEvents.toArray(new ExitEvent[exitEvents.size()]);       
    }
    
    private BigDecimal getRate(Date entryDate, Date exitDate) throws Exception{
        return rateManager.getRegularRate(entryDate, exitDate);
    }
    
    private BigDecimal getRate(Date exitDateTime) throws Exception{
        return rateManager.getFlatRate(exitDateTime);
    }
    
    
    
}
