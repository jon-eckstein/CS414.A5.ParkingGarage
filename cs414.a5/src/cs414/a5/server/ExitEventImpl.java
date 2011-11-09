/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cs414.a5.server;

import cs414.a5.common.ExitEvent;
import cs414.a5.common.EntryEvent;
import cs414.a5.common.ParkingGarageException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author jeckstein
 */
public class ExitEventImpl implements Serializable,ExitEvent {
    
    private EntryEvent entryEvent;
    private ArrayList<Payment> payments;
    private Iou iou;
    private Date exitDateTime;    
    private BigDecimal rate;    
    private boolean isFlatRate = false;
    private final double  HOURS_IN_MILLI = 1000*60*60;
    
    public ExitEventImpl(EntryEvent entryEvent, Date exitDateTime, BigDecimal rate){
        this.entryEvent = entryEvent;
        this.exitDateTime = exitDateTime;
        this.rate = rate;
        if(entryEvent == null)
            isFlatRate = true;
    }
    
    
    @Override
    public BigDecimal getTotalInvoiceAmount() {       
        if(isFlatRate)
            return rate;
        else                            
            return BigDecimal.valueOf(getTotalHours()).multiply(rate).round(new MathContext(4,RoundingMode.UP));                                       
        
    }
    
    @Override
    public String getTicketId(){
        if(entryEvent != null)
            return entryEvent.getTicketId();
        else
            return null;
    }
   
   
    @Override
    public double getTotalHours(){
        double hours = (exitDateTime.getTime()/HOURS_IN_MILLI) - (getEntryDate().getTime()/HOURS_IN_MILLI);
        if(hours < 1)
            hours = 1;
        return hours;
    }
    
    @Override
    public Date getEntryDate(){        
        if(entryEvent != null)
            return entryEvent.getEntryDate();
        else
            return null;        
    }
    
    @Override
    public Date getExitDate(){
        return this.exitDateTime;
    }
    
    @Override
    public String getEntryGateId(){
        if(entryEvent != null)
            return entryEvent.getGateId();
        else
            return null;
    }
    
    public void addPayment(Payment payment) throws ParkingGarageException{
        
        if(totalPaid().add(payment.getAmountPaid()).compareTo(getTotalInvoiceAmount()) <= 0){
            getPayments().add(payment);
        }else{
            throw new ParkingGarageException("Adding this payment will overpay the transaction. Payment not added.");
        }               
    }
    
    public void setIou(Iou owed) throws ParkingGarageException{
        if(owed.getAmountOwed().compareTo(getTotalInvoiceAmount())==0)
            iou = owed;
        else
            throw new ParkingGarageException("Balance owed does not equal transaction balance.");
    }
    
    /**
     * @return the balance
     */
    @Override
    public Iou getIou() {
        return iou;
    }
    
    @Override
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
    @Override
    public ArrayList<Payment> getPayments() {
        if(payments == null)
            payments = new ArrayList<Payment>();
        return payments;
    }

    
    
    
    
    
    
    
    
}
