/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cs414.a5.server;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author jeckstein
 */
public class UsageReportViewModel {
    
    private Date startDate;
    private Date endDate;
    private int delimiter;
    
    ArrayList<UsageReportDetail> reportDetail = new ArrayList<UsageReportDetail>();
    
    public UsageReportViewModel(Date startDate, Date endDate, int delimiter){
        this.startDate = startDate;
        this.endDate = endDate;
        this.delimiter = delimiter;
    }
    
    public void addDetail(UsageReportDetail detail){
        reportDetail.add(detail);
    }
    
    public ArrayList<UsageReportDetail> getReportDetail(){
        return reportDetail;
    }

    /**
     * @return the startDate
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * @param startDate the startDate to set
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * @return the endDate
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * @param endDate the endDate to set
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * @return the delimiter
     */
    public int getDelimiter() {
        return delimiter;
    }

    /**
     * @param delimiter the delimiter to set
     */
    public void setDelimiter(int delimiter) {
        this.delimiter = delimiter;
    }
    
}
