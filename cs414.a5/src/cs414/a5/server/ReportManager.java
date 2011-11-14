/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cs414.a5.server;

import cs414.a5.common.UsageReportViewModel;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author jeckstein
 */
public class ReportManager {
   
    ReportAggregationStrategyFactory strategyFactory;
    EntryExitManager entryExitManager;
    int totalSpots;
    
    public ReportManager(EntryExitManager eem, int totalSpots){
        entryExitManager = eem;
        this.totalSpots = totalSpots;  
        strategyFactory = new ReportAggregationStrategyFactory();
    }
    
    public UsageReportViewModel getUsageReport(Date startDate, Date endDate, int delimiter){
        
        ReportAggregationStrategy strategy = strategyFactory.getStrategy(delimiter);
        UsageReportViewModelImpl viewModel = new UsageReportViewModelImpl(startDate, endDate, delimiter);                        
        ExitEventImpl[] exitEvents = entryExitManager.getExitEvents();
                      
        Calendar calCounter=Calendar.getInstance();
        Calendar calEnd = Calendar.getInstance();        
        Calendar entryExitCal = Calendar.getInstance();
        
        for(calCounter.setTime(startDate), calEnd.setTime(endDate); calCounter.before(calEnd); calCounter.add(delimiter, 1)){
            int delimeterCounter = 0;
            BigDecimal chargeCounter = BigDecimal.ZERO;
            
            for(ExitEventImpl exit : exitEvents){
                entryExitCal.setTime(exit.getEntryDate());                
                boolean isWithin = strategy.isExitWithinRange(exit, calCounter);                        
                if(isWithin){
                    delimeterCounter++;
                    chargeCounter = chargeCounter.add(exit.getTotalInvoiceAmount());
                }
            }
            
            UsageReportDetailImpl detail = new UsageReportDetailImpl(getDetailHeader(delimiter, calCounter), 
                                                                    calCounter.get(delimiter), 
                                                                    delimeterCounter, delimeterCounter / (double)totalSpots, chargeCounter); 
            
            viewModel.addDetail(detail);                                               
        }
                           
        return viewModel;        
    }
    
    
    private Object getDetailHeader(int delimiter, Calendar counterDate){
        switch(delimiter){
            case Calendar.DATE: return counterDate.getTime(); 
            case Calendar.MONTH : return String.valueOf(counterDate.get(Calendar.MONTH)+1) + "-" +  String.valueOf(counterDate.get(Calendar.YEAR));
            case Calendar.YEAR : return String.valueOf(counterDate.get(Calendar.YEAR));
        }
        return null;
    }
        
    
}
