/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cs414.a5.common;

import java.util.Date;

/**
 *
 * @author jeckstein
 */
public interface EntryEvent extends java.rmi.Remote {

    /**
     * @return the entryDate
     */
    Date getEntryDate();

    /**
     * @return the gateId
     */
    String getGateId();

    /**
     * @return the ticketId
     */
    String getTicketId();
    
}
