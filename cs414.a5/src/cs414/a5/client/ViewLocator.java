/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cs414.a5.client;

import java.awt.CardLayout;
import java.awt.Component;
import javax.swing.JPanel;

/**
 *
 * @author jeckstein
 */
public class ViewLocator {
    
    ViewLocationStrategy locationStrategy;
    
    private String viewPrefix = "View"; 
    public ViewLocator(ViewLocationStrategy strategy){
        locationStrategy = strategy;
    }
    
    public ViewLocator(){
        this(new PrefixLocationStrategy());
            
    }
    
    
    public void showView(JPanel parent, String key){
                                
        JPanel foundView = locationStrategy.findView(key);
        
        if(foundView != null){
            CardLayout layout = (CardLayout) parent.getLayout();
            String className = foundView.getClass().getName();

            //remove the component in order to refresh it's fields...
            for(Component c : parent.getComponents()){
                if(c.getClass().getName().equals(className)){
                    parent.remove(c);                
                }                
            }
            
            parent.add(foundView.getName(), foundView);
            parent.validate();                                    
            layout.last(parent);
        }                   
    }
    
}
