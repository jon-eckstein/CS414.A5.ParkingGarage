/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cs414.a5.server;

import cs414.a5.common.Utilities;
import java.util.Date;

/**
 *
 * @author jeckstein
 */
public class EntryEvent {
    private String ticketId;
    private Date entryDate;
    
    public EntryEvent(String ticketId, Date entryDate){
        
        if(entryDate != null)
            this.entryDate = entryDate;
        else throw new IllegalArgumentException("EntryDate cannot be null. Entry not created.");
        
        if(!Utilities.isNullOrEmpty(ticketId))
            this.ticketId = ticketId;
        else throw new IllegalArgumentException("TicketId cannot be null. Entry not created.");
    }

    /**
     * @return the ticketId
     */
    public String getTicketId() {
        return ticketId;
    }

    /**
     * @return the entryDate
     */
    public Date getEntryDate() {
        return entryDate;
    }
    
}
