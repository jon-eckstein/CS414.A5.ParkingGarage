/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cs414.a5.server;

import cs414.a5.common.UsageReportDetail;
import cs414.a5.common.UsageReportViewModel;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author jeckstein
 */
public class UsageReportViewModelImpl implements Serializable, UsageReportViewModel {
    
    private Date startDate;
    private Date endDate;
    private int delimiter;
    
    ArrayList<UsageReportDetail> reportDetail = new ArrayList<UsageReportDetail>();
    
    public UsageReportViewModelImpl(Date startDate, Date endDate, int delimiter){
        this.startDate = startDate;
        this.endDate = endDate;
        this.delimiter = delimiter;
    }
    
    public void addDetail(UsageReportDetailImpl detail){
        reportDetail.add(detail);
    }
    
    @Override
    public ArrayList<UsageReportDetail> getReportDetail(){
        return reportDetail;
    }

    /**
     * @return the startDate
     */
    @Override
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
    @Override
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
    @Override
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
