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

/**
 *
 * @author jeckstein
 */
public class RateManager {

    private ArrayList<Rate> rates;
    
    public RateManager(){
        rates = new ArrayList<Rate>();              
    }
    
    
    public Rate getRegularRate(Date entryDateTime, Date exitDateTime) throws ParkingGarageException {
        
        for(Rate rateRecord : rates){
            if(exitDateTime.before(rateRecord.getEndDateTime()) && entryDateTime.after(rateRecord.getStartDateTime())
                    && !rateRecord.getIsFlatRate())
                return rateRecord;            
        }
        
        throw new ParkingGarageException("Could not find a rate within specified time period.");
    }

    public Rate getFlatRate(Date exitDateTime) throws ParkingGarageException {
        
        for(Rate rateRecord : rates){
            if(exitDateTime.before(rateRecord.getEndDateTime()) && exitDateTime.after(rateRecord.getStartDateTime())
                    && rateRecord.getIsFlatRate())
                return rateRecord;            
        }
        
        throw new ParkingGarageException("Could not find a rate within specified time period.");
        
    }

    public void setRate(Date startDate, Date endDate, BigDecimal rate, boolean isFlatRate) throws ParkingGarageException {
        //find the cross-over
        for(Rate existingRate : rates){
            Date start = existingRate.getStartDateTime();
            Date end = existingRate.getEndDateTime();
            if(isWithinRange(existingRate, startDate, endDate) && 
                   existingRate.getIsFlatRate() == isFlatRate ){
                throw new ParkingGarageException("Rate in specified time period already exists.");
            }
            
        }
        Rate newRate = new RateImpl(startDate, endDate, rate, isFlatRate);
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
