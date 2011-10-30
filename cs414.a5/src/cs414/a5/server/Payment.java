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
public abstract class Payment {
    
    protected BigDecimal amountPaid;
    protected Date datePaid;

    /**
     * @return the amountPaid
     */
    public BigDecimal getAmountPaid() {
        return amountPaid;
    }

    /**
     * @param amountPaid the amountPaid to set
     */
    public void setAmountPaid(BigDecimal amountPaid) {
        this.amountPaid = amountPaid;
    }

    /**
     * @return the datePaid
     */
    public Date getDatePaid() {
        return datePaid;
    }

    /**
     * @param datePaid the datePaid to set
     */
    public void setDatePaid(Date datePaid) {
        this.datePaid = datePaid;
    }
    
}


 

