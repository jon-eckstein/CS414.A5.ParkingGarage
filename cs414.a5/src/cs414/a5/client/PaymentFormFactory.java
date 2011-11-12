/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cs414.a5.client;

import javax.swing.JPanel;

/**
 *
 * @author jeckstein
 */
public class PaymentFormFactory {
    
    public JPanel createPaymentForm(PaymentMethods payMethod){
        
        switch(payMethod){
            case CASH:
                return new PaymentMethodCash();                                
        }
        
        return null;
    }
    
}
