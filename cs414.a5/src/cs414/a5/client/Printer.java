/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cs414.a5.client;

import cs414.a5.common.EntryEvent;
import cs414.a5.common.ExitEvent;

/**
 *
 * @author jeckstein
 */
public interface Printer {

    void printEntryTicket(EntryEvent entry);

    void printExitReceipt(ExitEvent exit);
    
}
