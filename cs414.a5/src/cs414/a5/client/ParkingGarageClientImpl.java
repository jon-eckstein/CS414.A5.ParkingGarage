/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cs414.a5.client;

import cs414.a5.common.EntryEvent;
import cs414.a5.common.ExitEvent;
import cs414.a5.common.ParkingGarage;
import cs414.a5.common.ParkingGarageException;
import cs414.a5.common.Rate;
import cs414.a5.common.UsageReportViewModel;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jeckstein
 */
public class ParkingGarageClientImpl {
    
    private static ParkingGarageClientImpl instance;    
    private ParkingGarage parkingGarageService;
    private String gateId;
    private static EventAggregator eventAggregator = EventAggreagtorImpl.getInstance();
    
    private ParkingGarageClientImpl() throws FileNotFoundException, IOException, NotBoundException{
        //initialize the rmi service...
        initParkingGarageClient();                
    }
    
       
     public static synchronized ParkingGarageClientImpl getInstance() {
        if (instance==null) {
            try {
                instance = new ParkingGarageClientImpl();
            } catch (Exception ex) {
                Logger.getLogger(ParkingGarageClientImpl.class.getName()).log(Level.SEVERE, null, ex);
                eventAggregator.publish(new ExceptionOccuredEvent(ex));
                return null;   
            }
        }
        return instance;
     }

    private void initParkingGarageClient() throws FileNotFoundException, IOException, NotBoundException {
        
        Properties props = new Properties();                    
        InputStream in = getClass().getResourceAsStream("client.properties");
        props.load(in);            
        String serviceHost = props.getProperty("rmi_parking_garage_server"); 
        String servicePort = props.getProperty("rmi_parking_garage_port"); 
        parkingGarageService = (ParkingGarage) Naming.lookup("rmi://" + serviceHost + ":" + servicePort + "/ParkingGarageService");
        setGateId(props.getProperty("terminal_gate_id"));
    }

    public EntryEvent createEntryEvent(Date entryDate, String gateId) throws ParkingGarageException, ServiceCommunicationException {        
        try {
            return parkingGarageService.createEntryEvent(entryDate, gateId);
        } catch (RemoteException ex) {
            Logger.getLogger(ParkingGarageClientImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new ServiceCommunicationException("Problem creating entry event: " + ex.getMessage());
        }
        
    }

    public void openGate(String gateId) throws ParkingGarageException, ServiceCommunicationException {
        try {
            parkingGarageService.openGate(gateId);            
        } catch (RemoteException ex) {
            Logger.getLogger(ParkingGarageClientImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new ServiceCommunicationException("Problem opening gate: " + ex.getMessage());
        }        
    }

    public void closeGate(String gateId) throws ParkingGarageException, ServiceCommunicationException {
       try {
            parkingGarageService.closeGate(gateId);            
        } catch (RemoteException ex) {
            Logger.getLogger(ParkingGarageClientImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new ServiceCommunicationException("Problem closing gate: " + ex.getMessage());
        }    
    }

    public ExitEvent createExitEvent(String ticketNum, Date date) throws ParkingGarageException, ServiceCommunicationException {
        try {
            return parkingGarageService.createExitEvent(ticketNum, date);            
        } catch (RemoteException ex) {
            Logger.getLogger(ParkingGarageClientImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new ServiceCommunicationException("Problem creating exit event: " + ex.getMessage());
        }
    }
    
    public ExitEvent createFlatRateExitEvent(Date date) throws ParkingGarageException, ServiceCommunicationException {
        try {
            return parkingGarageService.createExitEvent(date);            
        } catch (RemoteException ex) {
            Logger.getLogger(ParkingGarageClientImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new ServiceCommunicationException("Problem creating exit event: " + ex.getMessage());
        }
    }
    
    public int getAvailableSpotCount() throws ServiceCommunicationException{
        try {
            return parkingGarageService.getAvailableSpotCount();
        } catch (RemoteException ex) {
            Logger.getLogger(ParkingGarageClientImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new ServiceCommunicationException("Problem getting spot count: " + ex.getMessage());
        }
    }
    
    public void processCashPayment(BigDecimal amount, String ticketId) throws ParkingGarageException, ServiceCommunicationException {
        try {
            parkingGarageService.processCashPayment(amount, ticketId);            
        } catch (RemoteException ex) {
            Logger.getLogger(ParkingGarageClientImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new ServiceCommunicationException("Problem processing cash payment: " + ex.getMessage());
        }
    }
    
    public void processCardPayment(BigDecimal amount, String cardNumber, Date expireDate, String ticketId) throws ParkingGarageException, ServiceCommunicationException{
        try {
            parkingGarageService.processCardPayment(amount, cardNumber, expireDate, ticketId);            
        } catch (RemoteException ex) {
            Logger.getLogger(ParkingGarageClientImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new ServiceCommunicationException("Problem processing card payment: " + ex.getMessage());
        }
    }
    
    public void processIou(BigDecimal amount, String customerName, String customerPhone, String customerAddress, String ticketId) throws ParkingGarageException, ServiceCommunicationException{
        try {
            parkingGarageService.processIou(amount, customerName, customerPhone, customerAddress, ticketId);
        } catch (RemoteException ex) {
            Logger.getLogger(ParkingGarageClientImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new ServiceCommunicationException("Problem processing IOU: " + ex.getMessage());
        }
    }
    
    public Rate[] getCurrentRates() throws ParkingGarageException, ServiceCommunicationException{
        try {
            return parkingGarageService.getCurrentRates();
        } catch (RemoteException ex) {
            Logger.getLogger(ParkingGarageClientImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new ServiceCommunicationException("Problem getting current rate schedule: " + ex.getMessage());
        }
    }
    
    public boolean  authenticateAdmin(String username, String password) throws ParkingGarageException, ServiceCommunicationException{
        try {
            return parkingGarageService.authenticateAdmin(username, password);
        } catch (RemoteException ex) {
            Logger.getLogger(ParkingGarageClientImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new ServiceCommunicationException("Problem authenticating: " + ex.getMessage());
        }
    }
    
    public void setRate(BigDecimal rate, boolean isFlatRate) throws ParkingGarageException, ServiceCommunicationException{
        try {
            parkingGarageService.setRate(rate, isFlatRate);
        } catch (RemoteException ex) {
            Logger.getLogger(ParkingGarageClientImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new ServiceCommunicationException("Problem authenticating: " + ex.getMessage());
        }
    }
    
    public UsageReportViewModel getUsageReport(Date startDate, Date endDate, int delimeter) throws ServiceCommunicationException, ParkingGarageException{
        try {
            return parkingGarageService.getUsageReport(startDate, endDate, delimeter);
        } catch (RemoteException ex) {
            Logger.getLogger(ParkingGarageClientImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new ServiceCommunicationException("Problem getting usage report: " + ex.getMessage());
        }
    } 
    
    public void cancelEntry(String ticketId) throws ServiceCommunicationException, ParkingGarageException{
       try {
            parkingGarageService.cancelEntry(ticketId);
        } catch (RemoteException ex) {
            Logger.getLogger(ParkingGarageClientImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new ServiceCommunicationException("Problem canceling entry: " + ex.getMessage());
        } 
    }

    /**
     * @return the gateId
     */
    public String getGateId() {
        return gateId;
    }

    /**
     * @param gateId the gateId to set
     */
    public void setGateId(String gateId) {
        this.gateId = gateId;
    }
    
    
}
