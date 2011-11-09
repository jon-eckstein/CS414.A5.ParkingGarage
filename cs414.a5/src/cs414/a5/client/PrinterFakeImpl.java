/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cs414.a5.client;

import cs414.a5.common.EntryEvent;
import cs414.a5.common.ExitEvent;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jeckstein
 */
public class PrinterFakeImpl implements Printer {
    
    public PrinterFakeImpl(){}
    
    @Override
    public void printEntryTicket(EntryEvent entry){
        simulatePrint();
    }
    
    @Override
    public void printExitReceipt(ExitEvent exit){
        simulatePrint();
    }
    
    private void simulatePrint(){
        try {      
            Thread.sleep(1000);            
        } catch (InterruptedException ex) {
            Logger.getLogger(PrinterFakeImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
