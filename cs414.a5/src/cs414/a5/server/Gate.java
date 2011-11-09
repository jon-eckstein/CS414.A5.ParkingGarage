/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cs414.a5.server;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jeckstein
 */
public class Gate {
    
    private String id;
    private Boolean isOpen=false;
    
    
    public Gate(String Id){
        id=Id;        
    }
    
    public void open(){
        if(!isOpen){
           simulateGateMovement();
           isOpen = true; 
        }
        else{
           return;
        }
    }
    
    public void close(){
        if(isOpen){
           simulateGateMovement();
           isOpen = false; 
        }
        else{
           return;
        }
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @return the isOpen
     */
    public Boolean getIsOpen() {
        return isOpen;
    }

    /**
     * @param isOpen the isOpen to set
     */
    public void setIsOpen(Boolean isOpen) {
        this.isOpen = isOpen;
    }
    
    private void simulateGateMovement(){
         try {
            //simulate the gate opening
            Thread.currentThread().sleep(1000);            
        } catch (InterruptedException ex) {
            Logger.getLogger(Gate.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
