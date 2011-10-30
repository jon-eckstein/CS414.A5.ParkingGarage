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
public class PaymentGateway {

    public boolean processTransaction(String cardNumber, Date expireDate, BigDecimal amount) {        
        return true;
    }
    
}
