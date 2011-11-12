/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cs414.a5.client;

import java.math.BigDecimal;

/**
 *
 * @author jeckstein
 */
public class InvoiceEvent {
    private BigDecimal invoiceAmount;
    private String ticketId;

    public InvoiceEvent(BigDecimal invoiceAmount, String ticketId){
        this.invoiceAmount = invoiceAmount;
        this.ticketId = ticketId;
    }
    
    /**
     * @return the invoiceAmount
     */
    public BigDecimal getInvoiceAmount() {
        return invoiceAmount;
    }

    /**
     * @return the ticketId
     */
    public String getTicketId() {
        return ticketId;
    }
    
    
}
