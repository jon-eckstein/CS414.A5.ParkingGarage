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
public interface Rate extends java.rmi.Remote{

    /**
     * @return the endDateTime
     */
    Date getEndDateTime();

    /**
     * @return the isFlatRate
     */
    boolean getIsFlatRate();

    /**
     * @return the rate
     */
    BigDecimal getRate();

    /**
     * @return the startDateTime
     */
    Date getStartDateTime();
    
}
