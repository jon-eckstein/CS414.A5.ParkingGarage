/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cs414.a5.common;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author jeckstein
 */
public interface ParkingGarage extends java.rmi.Remote  {

    EntryEvent createEntryEvent(Date entryDate,String gateId) throws ParkingGarageException, java.rmi.RemoteException;

    EntryEvent createEntryEvent(String gateId) throws ParkingGarageException, java.rmi.RemoteException;
    
    ExitEvent createExitEvent(String ticketId, Date exitDateTime) throws ParkingGarageException, java.rmi.RemoteException;
    
    ExitEvent createExitEvent(Date exitDateTime) throws ParkingGarageException, java.rmi.RemoteException;
    
    int getAvailableSpotCount() throws java.rmi.RemoteException;

    int getTotalSpots() throws java.rmi.RemoteException;

    void processCardPayment(BigDecimal amount, String cardNumber, Date expireDate, String ticketId) throws ParkingGarageException, java.rmi.RemoteException;

    void processCashPayment(BigDecimal amount, String ticketId) throws ParkingGarageException, java.rmi.RemoteException;

    void processIou(BigDecimal amount, String customerName, String customerPhone, String customerAddress, String ticketId) throws ParkingGarageException, java.rmi.RemoteException;

    void setRate(BigDecimal rate, boolean isFlatRate) throws ParkingGarageException, java.rmi.RemoteException;
            
    void openGate(String gateId) throws ParkingGarageException, java.rmi.RemoteException;
    
    void closeGate(String gateId) throws ParkingGarageException, java.rmi.RemoteException;
    
    Rate[] getCurrentRates() throws ParkingGarageException, java.rmi.RemoteException;
    
    boolean authenticateAdmin(String username, String password) throws ParkingGarageException, java.rmi.RemoteException;
        
    UsageReportViewModel getUsageReport(Date startDate, Date endDate, int delimeter) throws ParkingGarageException, java.rmi.RemoteException;
    
    void cancelEntry(String ticketId) throws ParkingGarageException, java.rmi.RemoteException;
}
