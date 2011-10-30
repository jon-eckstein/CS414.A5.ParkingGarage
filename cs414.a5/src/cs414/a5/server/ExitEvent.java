/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cs414.a5.server;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author jeckstein
 */
public class ExitEvent {
    
    private EntryEvent entryEvent;
    private ArrayList<Payment> payments;
    private Iou iou;
    private Date exitDateTime;    
    private BigDecimal rate;    
    private boolean isFlatRate = false;
    private final double  HOURS_IN_MILLI = 1000*60*60;
    
    public ExitEvent(EntryEvent entryEvent, Date exitDateTime, BigDecimal rate){
        this.entryEvent = entryEvent;
        this.exitDateTime = exitDateTime;
        this.rate = rate;
        if(entryEvent == null)
        isFlatRate = true;
    }
    
    
    public BigDecimal getTotal() {       
        if(isFlatRate)
            return rate;
        else{                
            double hourDiff =  (exitDateTime.getTime()/HOURS_IN_MILLI) - (getEntryDate().getTime()/HOURS_IN_MILLI);            
            if(hourDiff < 1)
                hourDiff = 1;
            
            return BigDecimal.valueOf(hourDiff).multiply(rate).round(new MathContext(4,RoundingMode.UP));                                       
        }
    }
    
    public String getTicketId(){
        if(entryEvent != null)
            return entryEvent.getTicketId();
        else
            return null;
    }
    
    public Date getEntryDate(){        
        if(entryEvent != null)
            return entryEvent.getEntryDate();
        else
            return null;        
    }
    
    public Date getExitDate(){
        return this.exitDateTime;
    }
    
    public void addPayment(Payment payment) throws Exception{
        
        if(totalPaid().add(payment.getAmountPaid()).compareTo(getTotal()) <= 0){
            getPayments().add(payment);
        }else{
            throw new Exception("Adding this payment will overpay the transaction. Payment not added.");
        }               
    }
    
    public void setIou(Iou owed) throws Exception{
        if(owed.getAmountOwed().compareTo(getTotal())==0)
            iou = owed;
        else
            throw new Exception("Balance owed does not equal transaction balance.");
    }
    
    /**
     * @return the balance
     */
    public Iou getIou() {
        return iou;
    }
    
    public BigDecimal totalPaid(){
        BigDecimal value = new BigDecimal(0);
        
        for(Payment payment : getPayments()){
            value = value.add(payment.getAmountPaid());
        }
        
        return value;        
    }

    /**
     * @return the payments
     */
    public ArrayList<Payment> getPayments() {
        if(payments == null)
            payments = new ArrayList<Payment>();
        return payments;
    }

    
    
    
    
    
    
    
    
}
