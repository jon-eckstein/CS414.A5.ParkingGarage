/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cs414.a5.server;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author jeckstein
 */
public class CashPayment extends Payment {
    
    public CashPayment(BigDecimal amountPaid, Date datePaid){        
        this.amountPaid = amountPaid;        
        this.datePaid = datePaid;        
    }
               
}
