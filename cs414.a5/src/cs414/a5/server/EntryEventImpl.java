/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cs414.a5.server;

import cs414.a5.common.EntryEvent;
import cs414.a5.common.ParkingGarageException;
import cs414.a5.common.Utilities;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author jeckstein
 */
public class EntryEventImpl implements Serializable,EntryEvent {
    private String ticketId;
    private Date entryDate;
    private String gateId;
    
    public EntryEventImpl(String ticketId, Date entryDate, String gateId) throws ParkingGarageException{
        
        if(entryDate != null)
            this.entryDate = entryDate;
        else throw new ParkingGarageException("EntryDate cannot be null. Entry not created.");
        
        if(!Utilities.isNullOrEmpty(ticketId))
            this.ticketId = ticketId;
        else throw new ParkingGarageException("TicketId cannot be null. Entry not created.");
    }

    /**
     * @return the ticketId
     */
    
    @Override
    public String getTicketId() {
        return ticketId;
    }

    /**
     * @return the entryDate
     */
    
    @Override
    public Date getEntryDate() {
        return entryDate;
    }

    /**
     * @return the gateId
     */
    @Override
    public String getGateId() {
        return gateId;
    }
    
}
