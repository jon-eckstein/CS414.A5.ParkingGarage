/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cs414.a5.common;

import java.math.BigDecimal;

/**
 *
 * @author jeckstein
 */
public interface UsageReportDetail extends java.rmi.Remote {

    /**
     * @return the delimeter
     */
    int getDelimeter();

    /**
     * @return the detailDate
     */
    Object getDetailHeader();

    /**
     * @return the numSpotFilled
     */
    int getNumSpotFilled();

    /**
     * @return the percentageOccupied
     */
    double getPercentageOccupied();
    
    BigDecimal getTotalCharges();
    
}
