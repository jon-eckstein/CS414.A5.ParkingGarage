/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cs414.a5.server;

import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author jeckstein
 */
public class ReportManager {
   
    EntryExitManager entryExitManager;
    int totalSpots;
    
    public ReportManager(EntryExitManager eem, int totalSpots){
        entryExitManager = eem;
        this.totalSpots = totalSpots;               
    }
    
    public UsageReportViewModel getUsageReport(Date startDate, Date endDate, int delimiter){
        
        UsageReportViewModel viewModel = new UsageReportViewModel(startDate, endDate, delimiter);
        
        EntryEvent[] currentEntryEvents = entryExitManager.getCurrentEntryEvents();
        ExitEvent[] exitEvents = entryExitManager.getExitEvents();
        
        Calendar calCounter=Calendar.getInstance();
        Calendar calEnd = Calendar.getInstance();        
        Calendar entryExitCal = Calendar.getInstance();
        
        for(calCounter.setTime(startDate), calEnd.setTime(endDate); calCounter.before(calEnd); calCounter.add(delimiter, 1)){
            int delimeterCounter = 0;                
           
            for(EntryEvent entry : currentEntryEvents){
                entryExitCal.setTime(entry.getEntryDate());
                if(entryExitCal.after(calCounter))
                    delimeterCounter++;
            }
            
            for(ExitEvent exit : exitEvents){
                entryExitCal.setTime(exit.getEntryDate());
                Date calCounterDate = calCounter.getTime();
                boolean isWithin = isWithinRange(exit, calCounterDate); 
                        //(calCounterDate.before(exit.getExitDate()) 
                        //&& calCounterDate.after(exit.getEntryDate()));
                if(isWithin){
                    delimeterCounter++;
                }
            }
            
            UsageReportDetail detail = new UsageReportDetail(calCounter.getTime(), 
                    calCounter.get(delimiter), 
                    delimeterCounter, delimeterCounter / (double)totalSpots); 
            
            viewModel.addDetail(detail);                                               
        }
                           
        return viewModel;        
    }
    
    private boolean isWithinRange(ExitEvent exit, Date calDate) {          
        //return (calDate.before(exit.getExitDate()) && calDate.after(exit.getEntryDate()));
        return (calDate.compareTo(exit.getExitDate()) <=0 && calDate.compareTo(exit.getEntryDate()) <=0);
    }
    
}
