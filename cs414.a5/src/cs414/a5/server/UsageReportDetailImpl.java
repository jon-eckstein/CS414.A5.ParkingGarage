/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cs414.a5.server;

import cs414.a5.common.UsageReportDetail;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author jeckstein
 */
public class UsageReportDetailImpl implements  Serializable, UsageReportDetail {
        private final Object detailHeader;
        private final int delimeter;
        private final int numSpotFilled;
        private final double percentageOccupied; 
        private final BigDecimal totalCharged;
                
        public UsageReportDetailImpl(Object detailHeader, int delim, int numSpotFilled, double percentageOccupied, BigDecimal totalCharged){
            this.detailHeader = detailHeader;
            this.delimeter = delim;
            this.numSpotFilled = numSpotFilled;
            this.percentageOccupied = percentageOccupied; 
            this.totalCharged = totalCharged;
        }

    /**
     * @return the detailDate
     */
    @Override
    public Object getDetailHeader() {
        return detailHeader;
    }

    /**
     * @return the delimeter
     */
    @Override
    public int getDelimeter() {
        return delimeter;
    }

    /**
     * @return the numSpotFilled
     */
    @Override
    public int getNumSpotFilled() {
        return numSpotFilled;
    }

    /**
     * @return the percentageOccupied
     */
    @Override
    public double getPercentageOccupied() {
        return percentageOccupied;
    }

    @Override
    public BigDecimal getTotalCharges() {
        return totalCharged;
    }
               
        
}
