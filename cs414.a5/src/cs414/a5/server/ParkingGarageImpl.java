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
import cs414.a5.common.UsageReportViewModel;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

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
   private CredentialManager credentialManager;
   private int totalSpots; 
   private int totalGates;
   private ArrayList<Gate> gates = new ArrayList<Gate>();
   private Properties props;
   
   
   
   public ParkingGarageImpl(EntryExitManager eem, PaymentManager pm) throws java.rmi.RemoteException, IOException, ParseException, ParkingGarageException{       
       getProperties();       
       this.totalSpots = Integer.parseInt(props.getProperty("num_total_spots"));
       this.totalGates = Integer.parseInt(props.getProperty("num_total_gates"));
       entryExitManager = eem;
       rateManager = entryExitManager.rateManager;       
       paymentManager = pm;       
       reportManager = new ReportManager(entryExitManager, totalSpots);  
       credentialManager = new CredentialManagerImpl();       
       initGates();
       setDefaultRates();
   }
   
   public ParkingGarageImpl() throws java.rmi.RemoteException, ParseException, ParkingGarageException, IOException{       
        this(new EntryExitManager(new RateManager()), new PaymentManager(new PaymentGateway()));              
   }
   
   private void setDefaultRates() throws ParseException, ParkingGarageException{       
        BigDecimal hourlyRate = new BigDecimal(props.getProperty("default_regular_rate_amt"));
        BigDecimal flatRate = new BigDecimal(props.getProperty("default_flat_rate_amt"));
        //set the regular rate for the year...
        rateManager.setRate(hourlyRate, false);
        //set the flat rate for the year...
        rateManager.setRate(flatRate, true);              
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
   public void setRate(BigDecimal rate, boolean isFlatRate) throws ParkingGarageException, java.rmi.RemoteException{       
        rateManager.setRate(rate, isFlatRate);       
   }
   
    @Override
   public int getAvailableSpotCount(){
       return getTotalSpots() - entryExitManager.getFilledSpots();
   }
   
    @Override
   public UsageReportViewModel getUsageReport(Date startDate, Date endDate, int delimeter) throws ParkingGarageException, java.rmi.RemoteException{
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
        rates[0] = rateManager.getRegularRate();
        rates[1] = rateManager.getFlatRate();
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

    @Override
    public boolean authenticateAdmin(String username, String password) throws ParkingGarageException, RemoteException {
        return credentialManager.authenticateUser(username, password);
    }

    @Override
    public void cancelEntry(String ticketId) throws ParkingGarageException, RemoteException {
        entryExitManager.cancelEntry(ticketId);
    }

    private void getProperties() throws IOException {
        //get defaults from properties file..
        props = new Properties();                    
        InputStream in = getClass().getResourceAsStream("server.properties");
        props.load(in);        
        
    }
}
