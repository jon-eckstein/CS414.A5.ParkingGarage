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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.MalformedURLException;
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
    public String serverMessage;
    
    private ParkingGarageClientImpl() throws FileNotFoundException, IOException, NotBoundException{
        //initialize the rmi service...
        initParkingGarageClient();                
    }
    
       
     public static synchronized ParkingGarageClientImpl getInstance() {
        if (instance==null) {
            try {
                instance = new ParkingGarageClientImpl();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ParkingGarageClientImpl.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            } catch (IOException ex) {
                Logger.getLogger(ParkingGarageClientImpl.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            } catch (NotBoundException ex) {
                Logger.getLogger(ParkingGarageClientImpl.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        }
        return instance;
     }

    private void initParkingGarageClient() throws FileNotFoundException, IOException, NotBoundException {
        //TODO: get properties working...            
        //Properties props = new Properties();            
        //props.load(new FileInputStream("app.properties"));            
        String serviceHost = "localhost"; //props.getProperty("rmi_parking_garage_server");
        String servicePort = "1099"; //props.getProperty("rmi_parking_garage_port");
        parkingGarageService = (ParkingGarage) Naming.lookup("rmi://" + serviceHost + ":" + servicePort + "/ParkingGarageService");
        
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
    
    
    
    
    
    
    

    
    
    
    
    
    
}
