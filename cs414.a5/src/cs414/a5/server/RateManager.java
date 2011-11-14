/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cs414.a5.server;

import cs414.a5.common.ParkingGarageException;
import cs414.a5.common.Rate;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jeckstein
 */
public class RateManager {

    private ArrayList<Rate> rates;
    private static boolean isSetInProgress=false;
    
    
    public RateManager(){
        rates = new ArrayList<Rate>();              
    }
    
    
    public Rate getRegularRate() throws ParkingGarageException {
       
        sleepIfSetInProgress();
        
        for(Rate rateRecord : rates){
            if(!rateRecord.getIsFlatRate())
                return rateRecord;            
        }
        
        throw new ParkingGarageException("No regular rate set.");
    }

    public Rate getFlatRate() throws ParkingGarageException {
        sleepIfSetInProgress();
            
        for(Rate rateRecord : rates){
            if(rateRecord.getIsFlatRate())
                return rateRecord;            
        }
        
        throw new ParkingGarageException("No flat rate set.");
        
    }

    /***
     * Sleep the thread if a set is in progress.  This prevents a client from entering the garage for free.
     */
    private void sleepIfSetInProgress() {
         while(isSetInProgress) 
         try {
            Thread.sleep(100);
        } catch (InterruptedException ex) {
            Logger.getLogger(RateManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public synchronized void setRate(BigDecimal rate, boolean isFlatRate) throws ParkingGarageException {
        try{
            isSetInProgress=true;
            Rate rateToRemove=null;
            for(Rate existingRate : rates){            
                if(existingRate.getIsFlatRate() == isFlatRate ){
                    rateToRemove = existingRate;                    
                }
            }
            if(rateToRemove!=null)
                rates.remove(rateToRemove);
            
            Rate newRate = new RateImpl(rate, isFlatRate);
            rates.add(newRate);
        }finally{
            isSetInProgress=false;
        }
    }
    
    private boolean isWithinRange(Rate rate, Date newStartDate, Date newEndDate) {
        return (rate.getStartDateTime().after(newStartDate) && rate.getStartDateTime().before(newEndDate))
                || (rate.getEndDateTime().after(newStartDate) && rate.getEndDateTime().before(newEndDate));
        /*
        return (rate.getStartDateTime().before(newStartDate) && rate.getStartDateTime().after(newStartDate)) ||
                (rate.getStartDateTime().before(newEndDate) && rate.getEndDateTime().after(newEndDate));*/
    }

    
}
