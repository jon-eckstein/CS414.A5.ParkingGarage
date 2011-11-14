/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cs414.a5.client;

import javax.swing.SwingWorker;

/**
 *
 * @author jeckstein
 */
public class AbstractEntryExitView extends AbstractView {
    protected static final int GATE_CLOSE_INTERVAL_SECONDS = 2;
            
    protected void closeGate(){        
        
        SwingWorker sw = new SwingWorker() {
                @Override
                protected Void doInBackground() throws Exception {
                    Thread.currentThread().sleep(GATE_CLOSE_INTERVAL_SECONDS * 1000);
                    eventAggregator.publish(new StatusEvent("Gate closing..."));
                    getServiceClient().closeGate(getGateId());
                    eventAggregator.publish(new StatusEvent("Gate closed."));
                    return null;
                }

                @Override
                protected void done() {
                    eventAggregator.publish(new GateClosedEvent());
                }
            };
            sw.execute();
    }
    
}
