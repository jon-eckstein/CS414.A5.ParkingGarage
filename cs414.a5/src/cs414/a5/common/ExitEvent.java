/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cs414.a5.common;

import cs414.a5.server.Iou;
import cs414.a5.server.Payment;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author jeckstein
 */
public interface ExitEvent extends java.rmi.Remote {

    Date getEntryDate();

    String getEntryGateId();

    Date getExitDate();

    /**
     * @return the balance
     */
    Iou getIou();

    /**
     * @return the payments
     */
    ArrayList<Payment> getPayments();

    String getTicketId();

    double getTotalHours();

    BigDecimal getTotalInvoiceAmount();

    BigDecimal totalPaid();
    
}
