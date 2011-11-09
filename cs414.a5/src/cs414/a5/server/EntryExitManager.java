/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cs414.a5.server;

import cs414.a5.common.ExitEvent;
import cs414.a5.common.EntryEvent;
import cs414.a5.common.ParkingGarageException;
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

    public EntryEvent createEntryEvent(Date entryDate, String gateId) throws ParkingGarageException {
        EntryEvent newEntry = new EntryEventImpl(getNextTicketId(), entryDate, gateId);
        openEntries.put(newEntry.getTicketId(), newEntry);
        return newEntry;
    }
    
    public EntryEvent createEntryEvent(String gateId) throws ParkingGarageException{
        return createEntryEvent(new Date(), gateId);
    }
    
    
    public ExitEvent createExitEvent(String ticketId, Date exitDateTime) throws ParkingGarageException{
        ExitEvent exit;
        BigDecimal rate;
                
        if(!Utilities.isNullOrEmpty(ticketId)){
            //this is a regular entry/exit event
            EntryEvent entry = openEntries.get(ticketId);
            if(entry == null) throw new ParkingGarageException("Could not find entry with given ticket ID.");
            rate = getRate(entry.getEntryDate(), exitDateTime);
            exit = new ExitEventImpl(entry, exitDateTime, rate);
            //remove the openEntry record...
            openEntries.remove(ticketId);
        }else{
            //lost or damaged ticket, flat rate transaction...
            rate = getRate(exitDateTime);
            exit = new ExitEventImpl(null, exitDateTime, rate);            
        }
        
        exitEvents.add(exit);
        return exit;                
    }
    
    public ExitEvent createExitEvent(String ticketId) throws ParkingGarageException{
        return createExitEvent(ticketId, new Date());
    }
    
           
    public EntryEvent getEntryEvent(String ticketId) throws ParkingGarageException {
        if(openEntries.containsKey(ticketId))
            return openEntries.get(ticketId);
        else
            throw new ParkingGarageException("Invalid ticket number.");                     
    }
    
    public ExitEvent getExitEvent(String ticketId) throws ParkingGarageException{
        for(ExitEvent event : exitEvents)
            if(event.getTicketId().equals(ticketId))
                return event;
        
        throw new ParkingGarageException("Could not find exit event.");
    }
    
    public EntryEventImpl[] getCurrentEntryEvents(){
        return (EntryEventImpl[])openEntries.values().toArray(new EntryEventImpl[openEntries.size()]);        
    }
    
    public ExitEventImpl[] getExitEvents(){
        return exitEvents.toArray(new ExitEventImpl[exitEvents.size()]);       
    }
    
    private BigDecimal getRate(Date entryDate, Date exitDate) throws ParkingGarageException{
        return rateManager.getRegularRate(entryDate, exitDate);
    }
    
    private BigDecimal getRate(Date exitDateTime) throws ParkingGarageException{
        return rateManager.getFlatRate(exitDateTime);
    }
    
    private synchronized String getNextTicketId(){
        return Integer.toString(ticketIdCounter++);
    }
    
}
