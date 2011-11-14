/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cs414.a5.client;

import cs414.a5.common.UsageReportDetail;
import cs414.a5.common.UsageReportViewModel;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author jeckstein
 */
public class UsageReportTableModel extends AbstractTableModel {
    
    private UsageReportViewModel viewModel;
    
    public UsageReportTableModel(UsageReportViewModel viewModel){
        this.viewModel = viewModel;
    }

     private String[] columnNames = {"Date",
                                     "# Spots Filled",
                                     "% Occupied", "Total Charged"};
    
    @Override
    public int getRowCount() {
        return viewModel.getReportDetail().size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int col) { return columnNames[col]; }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        UsageReportDetail detail = viewModel.getReportDetail().get(rowIndex);
        switch(columnIndex) {
            case 0: return detail.getDetailHeader();
            case 1: return detail.getNumSpotFilled();
            case 2: return detail.getPercentageOccupied();
            case 3: return detail.getTotalCharges();                    
            default: return null;
        }
    }
    
    
    
}
