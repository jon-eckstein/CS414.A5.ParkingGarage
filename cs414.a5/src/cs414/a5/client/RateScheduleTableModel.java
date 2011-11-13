/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cs414.a5.client;

import cs414.a5.common.Rate;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author jeckstein
 */
public class RateScheduleTableModel extends AbstractTableModel {

    Rate[] rates;
    
    public RateScheduleTableModel(Rate[] rates){
        this.rates = rates;
    }
    
    private String[] columnNames = {"Rate Type",
                                        "Amount"};
    
    @Override
    public int getRowCount() {
        return rates.length;
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public String getColumnName(int col) { return columnNames[col]; }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Rate rate = rates[rowIndex];
        switch(columnIndex) {
            case 0: return rate.getIsFlatRate() ? "Lost/Damaged Ticket" : "Hourly";
            case 1: return rate.getRate();            
            default: return null;
        }        
    }

   
    
    
    
}
