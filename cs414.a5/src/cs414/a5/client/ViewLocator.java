/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cs414.a5.client;

import java.awt.CardLayout;
import java.awt.Component;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

/**
 *
 * @author jeckstein
 */
public class ViewLocator {
    
    private String viewPrefix = "View"; 
    public ViewLocator(){
        
    }
    
    public void showView(JPanel parent, String key){
        CardLayout layout = (CardLayout) parent.getLayout();
        for(Component c : parent.getComponents()){
            if(c.getName() == viewPrefix + key){
                    layout.show(parent, c.getName());
                    return;
            }                
        }
        try {
            String packageName =  this.getClass().getPackage().getName();
            JPanel view = (JPanel)Class.forName(packageName + "." + viewPrefix + key).newInstance();            
            parent.add(view.getName(), view);
            parent.validate();                        
            layout.show(parent, view.getName());
        } catch (Exception ex) {
            Logger.getLogger(ViewLocator.class.getName()).log(Level.SEVERE, null, ex);
        }                    
    }
    
}
