/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cs414.a5.server;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author jeckstein
 */
public class RateManager {

    private ArrayList<Rate> rates;
    
    public RateManager(){
        rates = new ArrayList<Rate>();              
    }
    
    
    public BigDecimal getRegularRate(Date entryDateTime, Date exitDateTime) throws Exception {
        
        for(Rate rateRecord : rates){
            if(exitDateTime.before(rateRecord.getEndDateTime()) && entryDateTime.after(rateRecord.getStartDateTime())
                    && !rateRecord.getIsFlatRate())
                return rateRecord.getRate();            
        }
        
        throw new Exception("Could not find a rate within specified time period.");
    }

    public BigDecimal getFlatRate(Date exitDateTime) throws Exception {
        
        for(Rate rateRecord : rates){
            if(exitDateTime.before(rateRecord.getEndDateTime()) && exitDateTime.after(rateRecord.getStartDateTime())
                    && rateRecord.getIsFlatRate())
                return rateRecord.getRate();            
        }
        
        throw new Exception("Could not find a rate within specified time period.");
        
    }

    public void setRate(Date startDate, Date endDate, BigDecimal rate, boolean isFlatRate) throws Exception {
        //find the cross-over
        for(Rate existingRate : rates){
            Date start = existingRate.getStartDateTime();
            Date end = existingRate.getEndDateTime();
            if(isWithinRange(existingRate, startDate, endDate) && 
                   existingRate.getIsFlatRate() == isFlatRate ){
                throw new Exception("Rate in specified time period already exists.");
            }
            
        }
        Rate newRate = new Rate(startDate, endDate, rate, isFlatRate);
        rates.add(newRate);
    }
    
    private boolean isWithinRange(Rate rate, Date newStartDate, Date newEndDate) {
        return (rate.getStartDateTime().after(newStartDate) && rate.getStartDateTime().before(newEndDate))
                || (rate.getEndDateTime().after(newStartDate) && rate.getEndDateTime().before(newEndDate));
        /*
        return (rate.getStartDateTime().before(newStartDate) && rate.getStartDateTime().after(newStartDate)) ||
                (rate.getStartDateTime().before(newEndDate) && rate.getEndDateTime().after(newEndDate));*/
    }

    
}
