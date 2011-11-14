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
class PrefixLocationStrategy implements ViewLocationStrategy {

    private String viewPrefix = "View";
    
    public PrefixLocationStrategy() {
    }

    @Override
    public JPanel findView(String key) {
        try {
            String packageName =  this.getClass().getPackage().getName();
            String className = packageName + "." + viewPrefix + key;        
            return (JPanel)Class.forName(className).newInstance();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PrefixLocationStrategy.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (InstantiationException ex) {            
            Logger.getLogger(PrefixLocationStrategy.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (IllegalAccessException ex) {            
            Logger.getLogger(PrefixLocationStrategy.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
        
    }
    
}
