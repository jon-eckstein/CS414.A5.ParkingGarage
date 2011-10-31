/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cs414.a5.server;

import java.util.Date;

/**
 *
 * @author jeckstein
 */
public class UsageReportDetail{
        private final Date detailDate;
        private final int delimeter;
        private final int numSpotFilled;
        private final double percentageOccupied; 
                
        public UsageReportDetail(Date detailDate, int delim, int numSpotFilled, double percentageOccupied){
            this.detailDate = detailDate;
            this.delimeter = delim;
            this.numSpotFilled = numSpotFilled;
            this.percentageOccupied = percentageOccupied; 
        }

    /**
     * @return the detailDate
     */
    public Date getDetailDate() {
        return detailDate;
    }

    /**
     * @return the delimeter
     */
    public int getDelimeter() {
        return delimeter;
    }

    /**
     * @return the numSpotFilled
     */
    public int getNumSpotFilled() {
        return numSpotFilled;
    }

    /**
     * @return the percentageOccupied
     */
    public double getPercentageOccupied() {
        return percentageOccupied;
    }
               
        
}
