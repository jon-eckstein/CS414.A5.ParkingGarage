/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cs414.a5.server;

import cs414.a5.common.ParkingGarage;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author jeckstein
 */
public class ParkingGarageImpl extends java.rmi.server.UnicastRemoteObject 
                                implements ParkingGarage {
   private EntryExitManager entryExitManager;
   private RateManager rateManager;
   private PaymentManager paymentManager;
   private ReportManager reportManager;
   private int totalSpots;  
   public static final int DEFAULT_TOTAL_SPOTS = 100;
   
   
   public ParkingGarageImpl(EntryExitManager eem, PaymentManager pm, int numSpots) throws java.rmi.RemoteException{       
       entryExitManager = eem;
       rateManager = eem.rateManager;       
       paymentManager = pm;
       totalSpots = numSpots;
       reportManager = new ReportManager(entryExitManager, totalSpots);
   }
   
   public ParkingGarageImpl() throws java.rmi.RemoteException{
       this(new EntryExitManager(new RateManager()), new PaymentManager(new PaymentGateway()), DEFAULT_TOTAL_SPOTS);
   }
   
   
   @Override
   public String createEntryEvent(Date entryDate) throws Exception, java.rmi.RemoteException{
       int totalFilled = entryExitManager.getFilledSpots();
       
        if(totalFilled < totalSpots){
           EntryEvent spot = entryExitManager.createEntryEvent(entryDate);
           return spot.getTicketId();           
       }else{
           throw new Exception("Parking lot is full.");
       }              
   }

    @Override
   public String createEntryEvent() throws Exception, java.rmi.RemoteException{
       return createEntryEvent(new Date());
   }
   
    @Override
   public BigDecimal processExit(String ticketId, Date exitDateTime) throws Exception, java.rmi.RemoteException{
       ExitEvent exit = entryExitManager.createExitEvent(ticketId, exitDateTime);
       return exit.getTotal();        
   }
  
    
    @Override
   public void processCardPayment(BigDecimal amount, String cardNumber, Date expireDate, String ticketId) throws Exception, java.rmi.RemoteException{       
       ExitEvent exitEvent = entryExitManager.getExitEvent(ticketId);
       Payment payment = paymentManager.createCardPayment(amount, new Date(), cardNumber, expireDate);
       exitEvent.addPayment(payment);
   }
   
    @Override
   public void processCashPayment(BigDecimal amount, String ticketId) throws Exception, java.rmi.RemoteException{
       ExitEvent exitEvent = entryExitManager.getExitEvent(ticketId);
       Payment payment = paymentManager.createCashPayment(amount, new Date());
       exitEvent.addPayment(payment);
   }
   
    @Override
   public void processIou(BigDecimal amount, String customerName, String customerPhone, String customerAddress, String ticketId) throws Exception, java.rmi.RemoteException{
       ExitEvent exitEvent = entryExitManager.getExitEvent(ticketId);
       exitEvent.setIou(new Iou(amount, new Date(), customerName, customerAddress, customerPhone));             
   }
   
    @Override
   public void setRate(Date startDate, Date endDate, BigDecimal rate, boolean isFlatRate) throws Exception, java.rmi.RemoteException{       
        rateManager.setRate(startDate, endDate, rate, isFlatRate);       
   }
   
    @Override
   public int getAvailableSpotCount(){
       return getTotalSpots() - entryExitManager.getFilledSpots();
   }
   
   public UsageReportViewModel getUsageReport(Date startDate, Date endDate, int delimeter){
       return reportManager.getUsageReport(startDate, endDate, delimeter);
   }
   
    @Override
    public int getTotalSpots() {
        return totalSpots;
    }    
}
