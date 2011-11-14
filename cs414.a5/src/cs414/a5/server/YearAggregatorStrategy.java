/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cs414.a5.server;

import cs414.a5.common.ExitEvent;
import java.util.Calendar;

/**
 *
 * @author jeckstein
 */
public class YearAggregatorStrategy implements ReportAggregationStrategy {

    @Override
    public boolean isExitWithinRange(ExitEvent exit, Calendar calDate) {
        Calendar calDateEnd = (Calendar)calDate.clone();
        calDateEnd.add(Calendar.YEAR, 1);
        calDateEnd.add(Calendar.HOUR, -1);
        Calendar entryDate = Calendar.getInstance();
        Calendar exitDate = Calendar.getInstance();
        entryDate.setTime(exit.getEntryDate());
        exitDate.setTime(exit.getExitDate());
        return (entryDate.compareTo(calDate) >=0  && entryDate.compareTo(calDateEnd) <=0) 
                && (exitDate.compareTo(calDate) >=0  && exitDate.compareTo(calDateEnd) <=0);
        
    }
    
}
