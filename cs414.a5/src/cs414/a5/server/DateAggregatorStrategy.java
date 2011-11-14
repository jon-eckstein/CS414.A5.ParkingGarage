/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cs414.a5.server;

import cs414.a5.common.EntryEvent;
import cs414.a5.common.ExitEvent;
import cs414.a5.common.UsageReportDetail;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author jeckstein
 */
class DateAggregatorStrategy implements ReportAggregationStrategy {    
    
    public DateAggregatorStrategy() {
    }
   
    @Override
    public boolean isExitWithinRange(ExitEvent exit, Calendar calDate) {                  
        Calendar calDateEnd = (Calendar)calDate.clone();
        calDateEnd.add(Calendar.HOUR, 23);
        Calendar entryDate = Calendar.getInstance();
        Calendar exitDate = Calendar.getInstance();
        entryDate.setTime(exit.getEntryDate());
        exitDate.setTime(exit.getExitDate());
        return (entryDate.compareTo(calDate) >=0  && entryDate.compareTo(calDateEnd) <=0) 
                && (exitDate.compareTo(calDate) >=0  && exitDate.compareTo(calDateEnd) <=0);
    }
}
