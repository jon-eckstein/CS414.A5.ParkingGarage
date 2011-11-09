/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cs414.a5.server;

import cs414.a5.common.ParkingGarageException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author jeckstein
 */
public class PaymentManager {
 
    PaymentGateway paymentGateway;
    
    public PaymentManager(PaymentGateway pg){                
        paymentGateway = pg;
    }

    public CardPayment createCardPayment(BigDecimal amount, Date datePaid, String cardNumber, Date expireDate) throws ParkingGarageException {
        
        if(paymentGateway.processTransaction(cardNumber,expireDate, amount)){
            return new CardPayment(cardNumber, datePaid, expireDate, amount);
        }else{
            throw new ParkingGarageException("Card could not be authorized.");
        }                
    }

    public CashPayment createCashPayment(BigDecimal amount, Date datePaid) {
        return new CashPayment(amount, datePaid);        
    }
    
    
    public Iou createIou(BigDecimal amountOwed, Date dateOwed, String customerName, String customerAddress, String customerPhoneNumber){        
         return new Iou(amountOwed, dateOwed,  customerName, customerAddress, customerPhoneNumber);        
    }

    

    
    
    
    
}
