/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cs414.a5.server;

import cs414.a5.common.ExitEvent;
import java.util.Calendar;

/**
 *
 * @author jeckstein
 */
public interface ReportAggregationStrategy {
    boolean isExitWithinRange(ExitEvent exit, Calendar rangeStartDate); 
}
