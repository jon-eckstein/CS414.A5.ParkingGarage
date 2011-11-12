/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cs414.a5.client;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

/**
 *
 * @author jeckstein
 */
public abstract class AbstractView extends JPanel {
    
    protected EventAggregator eventAggregator;
    private ParkingGarageClientImpl parkingGarageService;
    private String gateId="1";
    
    public AbstractView(){
        eventAggregator = EventAggreagtorImpl.getInstance();
    }
    
    /**
     * @return the parkingGarageService
     */
    protected ParkingGarageClientImpl getServiceClient() {
        parkingGarageService = ParkingGarageClientImpl.getInstance();
        if(parkingGarageService == null){
            HandleException(new ServiceCommunicationException("Could not connect to parking garage service."));            
        }        
        return parkingGarageService;
    }
    
    protected void HandleException(Exception ex) {
        eventAggregator.publish(new ExceptionOccuredEvent(ex));               
        Logger.getLogger(ParkingGarageUI.class.getName()).log(Level.SEVERE, null, ex);
    }

    /**
     * @return the gateId
     */
    protected String getGateId() {
        return gateId;
    }

    /**
     * @param gateId the gateId to set
     */
    protected void setGateId(String gateId) {
        this.gateId = gateId;
    }
    
    protected <T> void eventOccurred(T payload) {}
        
    
    
          
    
}
