/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cs414.a5.common;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author jeckstein
 */
public interface UsageReportViewModel extends java.rmi.Remote {

    /**
     * @return the delimiter
     */
    int getDelimiter();

    /**
     * @return the endDate
     */
    Date getEndDate();

    ArrayList<UsageReportDetail> getReportDetail();

    /**
     * @return the startDate
     */
    Date getStartDate();
    
}
