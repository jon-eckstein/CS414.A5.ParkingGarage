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
public class CardPayment extends Payment {
 
    private String cardNumber;
    private Date expireDate;
    
    public CardPayment(String cardNumber, Date datePaid, Date expireDate, BigDecimal amountPaid){
        this.cardNumber = cardNumber;
        this.expireDate = expireDate;
        this.datePaid = datePaid;
        this.amountPaid = amountPaid;
        
    }
    
    /**
     * @return the cardNumber
     */
    public String getCardNumber() {
        return cardNumber;
    }

    /**
     * @param cardNumber the cardNumber to set
     */
    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    /**
     * @return the expireDate
     */
    public Date getExpireDate() {
        return expireDate;
    }

    /**
     * @param expireDate the expireDate to set
     */
    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }
    
}
