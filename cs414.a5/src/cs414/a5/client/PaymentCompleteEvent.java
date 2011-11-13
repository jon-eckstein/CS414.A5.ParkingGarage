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
public class PaymentCompleteEvent {
    private String ticketId;
    private BigDecimal amountPaid;
    private BigDecimal amountBalance;

    public PaymentCompleteEvent(String ticketId, BigDecimal amountPaid, BigDecimal amountBalance){
        this.ticketId = ticketId;
        this.amountPaid = amountPaid;
        this.amountBalance = amountBalance;
    }
    
    /**
     * @return the ticketId
     */
    public String getTicketId() {
        return ticketId;
    }

    /**
     * @return the amountPaid
     */
    public BigDecimal getAmountPaid() {
        return amountPaid;
    }

    /**
     * @return the amountBalance
     */
    public BigDecimal getAmountBalance() {
        return amountBalance;
    }
    
    
    
    
}
