/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cs414.a5.server;

import java.util.Calendar;

/**
 *
 * @author jeckstein
 */
public class ReportAggregationStrategyFactory {
    
    public ReportAggregationStrategy getStrategy(int delimiter){
        
        switch(delimiter){
            case Calendar.DATE: return new DateAggregatorStrategy();
            case Calendar.MONTH: return new MonthAggregatorStrategy();
            case Calendar.YEAR: return new YearAggregatorStrategy();
        }
        
        return null;
        
    }
    
}
