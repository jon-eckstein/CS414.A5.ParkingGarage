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
public abstract class AbstractPaymentView extends AbstractView {
    
   private BigDecimal invoiceAmount = new BigDecimal(0);
   private String ticketId;
   
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
    public <T> void eventOccurred(T payload){
        if(payload.getClass() == InvoiceEvent.class){
            InvoiceEvent evt = (InvoiceEvent)payload;
            setInvoiceAmount(evt.getInvoiceAmount());
            setTicketId(evt.getTicketId());
        }
    }
    
}
