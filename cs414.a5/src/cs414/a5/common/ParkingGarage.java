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

    String createEntryEvent(Date entryDate) throws Exception, java.rmi.RemoteException;

    String createEntryEvent() throws Exception, java.rmi.RemoteException;

    int getAvailableSpotCount() throws java.rmi.RemoteException;

    int getTotalSpots() throws java.rmi.RemoteException;

    void processCardPayment(BigDecimal amount, String cardNumber, Date expireDate, String ticketId) throws Exception, java.rmi.RemoteException;

    void processCashPayment(BigDecimal amount, String ticketId) throws Exception, java.rmi.RemoteException;

    BigDecimal processExit(String ticketId, Date exitDateTime) throws Exception, java.rmi.RemoteException;

    void processIou(BigDecimal amount, String customerName, String customerPhone, String customerAddress, String ticketId) throws Exception, java.rmi.RemoteException;

    void setRate(Date startDate, Date endDate, BigDecimal rate, boolean isFlatRate) throws Exception, java.rmi.RemoteException;
    
}
