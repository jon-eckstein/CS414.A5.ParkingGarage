/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cs414.a5.server;

import cs414.a5.common.EntryEvent;
import cs414.a5.common.ExitEvent;
import cs414.a5.common.ParkingGarage;
import cs414.a5.common.ParkingGarageException;
import cs414.a5.common.Rate;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
   private int totalGates;
   private ArrayList<Gate> gates = new ArrayList<Gate>();
   public static final int DEFAULT_TOTAL_SPOTS = 100;
   public static final int DEFAULT_GATE_COUNT = 2;
   
   
   public ParkingGarageImpl(EntryExitManager eem, PaymentManager pm, int numSpots, int numGates) throws java.rmi.RemoteException{       
       entryExitManager = eem;
       rateManager = eem.rateManager;       
       paymentManager = pm;
       totalSpots = numSpots;
       totalGates = numGates;
       reportManager = new ReportManager(entryExitManager, totalSpots);       
       initGates();
   }
   
   public ParkingGarageImpl() throws java.rmi.RemoteException, ParseException, Exception{
       this(new EntryExitManager(new RateManager()), new PaymentManager(new PaymentGateway()), DEFAULT_TOTAL_SPOTS, DEFAULT_GATE_COUNT);
       setDefaultRates();       
   }
   
   private void setDefaultRates() throws ParseException, Exception{
        SimpleDateFormat dateFormatter = new SimpleDateFormat("MM/dd/yy h:mm a"); 
        Date startDate = dateFormatter.parse("01/01/2011 12:00 AM");
        Date endDate = dateFormatter.parse("12/31/2011 11:59 PM");
        BigDecimal hourlyRate = new BigDecimal("10.00");
        BigDecimal flatRate = new BigDecimal("40.00");
        //set the regular rate for the year...
        rateManager.setRate(startDate, endDate, hourlyRate, false);
        //set the flat rate for the year...
        rateManager.setRate(startDate, endDate, flatRate, true);
              
   }
   
   
   @Override
   public EntryEvent createEntryEvent(Date entryDate, String gateId) throws ParkingGarageException, java.rmi.RemoteException{
       int totalFilled = entryExitManager.getFilledSpots();
       
        if(totalFilled < totalSpots){
           return entryExitManager.createEntryEvent(entryDate, gateId);           
       }else{
           throw new ParkingGarageException("Parking lot is full.");
       }              
   }

   @Override
   public EntryEvent createEntryEvent(String gateId) throws ParkingGarageException, java.rmi.RemoteException{
       return createEntryEvent(new Date(), gateId);
   }
   
    @Override
   public ExitEvent createExitEvent(String ticketId, Date exitDateTime) throws ParkingGarageException, java.rmi.RemoteException{
       ExitEvent exit = entryExitManager.createExitEvent(ticketId, exitDateTime);
       return exit;        
   }
  
     @Override
   public ExitEvent createExitEvent(Date exitDateTime) throws ParkingGarageException, java.rmi.RemoteException{
       ExitEvent exit = entryExitManager.createExitEvent(null, exitDateTime);
       return exit;        
   }
    
    @Override
   public void processCardPayment(BigDecimal amount, String cardNumber, Date expireDate, String ticketId) throws ParkingGarageException, java.rmi.RemoteException{       
       ExitEventImpl exitEvent =(ExitEventImpl)entryExitManager.getExitEvent(ticketId);
       Payment payment = paymentManager.createCardPayment(amount, new Date(), cardNumber, expireDate);
       exitEvent.addPayment(payment);
   }
   
    @Override
   public void processCashPayment(BigDecimal amount, String ticketId) throws ParkingGarageException, java.rmi.RemoteException{
       ExitEventImpl exitEvent = (ExitEventImpl)entryExitManager.getExitEvent(ticketId);
       Payment payment = paymentManager.createCashPayment(amount, new Date());
       exitEvent.addPayment(payment);
   }
   
    @Override
   public void processIou(BigDecimal amount, String customerName, String customerPhone, String customerAddress, String ticketId) throws ParkingGarageException, java.rmi.RemoteException{
       ExitEventImpl exitEvent = (ExitEventImpl)entryExitManager.getExitEvent(ticketId);
       exitEvent.setIou(new Iou(amount, new Date(), customerName, customerAddress, customerPhone));             
   }
   
    @Override
   public void setRate(Date startDate, Date endDate, BigDecimal rate, boolean isFlatRate) throws ParkingGarageException, java.rmi.RemoteException{       
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
    
    @Override
    public void openGate(String gateId) throws ParkingGarageException{
        Gate g = findGate(gateId);
        if(g!=null)
            g.open();
        else
            throw new ParkingGarageException("Could not find gate " + gateId);
    }
    
    @Override
    public void closeGate(String gateId) throws ParkingGarageException{
        Gate g = findGate(gateId);
        if(g!=null)
            g.close();
        else
            throw new ParkingGarageException("Could not find gate " + gateId);               
    }
    
    @Override
    public Rate[] getCurrentRates() throws ParkingGarageException{
        Rate[] rates = new Rate[2];
        rates[0] = rateManager.getRegularRate(new Date(), new Date());
        rates[1] = rateManager.getFlatRate(new Date());
        return rates;
    }
    
    private void initGates() {        
        for(int i=0;i<=totalGates;i++){
            String gateId = String.valueOf(i+1);                    
            gates.add(new Gate(gateId));
        }        
    }
    
    private Gate findGate(String gateId){
        for(Gate g : gates){
           if(g.getId().equals(gateId))
               return g;
        }        
        return null;        
    }
}
