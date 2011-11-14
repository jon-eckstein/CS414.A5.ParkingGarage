/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cs414.a5.client;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 *
 * @author jeckstein
 */
public abstract class AbstractPaymentView extends AbstractView {
    
   private BigDecimal invoiceAmount = new BigDecimal(0);
   private String ticketId;
   private String payAmount;
   
   
   public AbstractPaymentView(){
       eventAggregator.subscribe(InvoiceEvent.class, this);
   }
   
   /**
     * @return the amount
     */
    public BigDecimal getInvoiceAmount() {        
        return invoiceAmount;
    }

    /**
     * @param amount the amount to set
     */
    public void setInvoiceAmount(BigDecimal amount) {
        this.invoiceAmount = amount;
        firePropertyChange("invoiceAmount", null, null);
    }
    
    
    /**
     * @return the ticketId
     */
    public String getTicketId() {
        return ticketId;
    }

    /**
     * @param ticketId the ticketId to set
     */
    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }
    
    @Override
    public <T> void notifyOnEvent(T payload){
        if(payload.getClass() == InvoiceEvent.class){
            InvoiceEvent evt = (InvoiceEvent)payload;
            setInvoiceAmount(evt.getInvoiceAmount());
            setTicketId(evt.getTicketId());
            setPayAmount(evt.getInvoiceAmount().toString());
        }
    }

    /**
     * @return the payAmount
     */
    public String getPayAmount() {
        return payAmount;
    }

    /**
     * @param payAmount the payAmount to set
     */
    public void setPayAmount(String payAmount) {
        this.payAmount = payAmount;        
        firePropertyChange("payAmount", null, null);
    }
    
    public BigDecimal getBalanceAmount(){
        BigDecimal payed = new BigDecimal(getPayAmount());
        return getInvoiceAmount().subtract(payed);
    }
    
    
    
    
}
