/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cs414.a5.server;

import cs414.a5.common.ParkingGarage;
import java.rmi.Naming;

/**
 *
 * @author jeckstein
 */
public class ParkingGarageService {
    
    private String url;
    
    public ParkingGarageService(String url) {
        this.url = url;
          try {                           
                ParkingGarage c = new ParkingGarageImpl();
                Naming.rebind(url, c);
           } catch (Exception e) {
                   System.out.println("Trouble: " + e);
           }
    }
    
    
    public static void main(String args[])  { 
        //String server = "localhost";
        //String port = "1099";        
        //String url = new String("rmi://" + server + ":" + port + "/ParkingGarageService");
        String url = new String("rmi://" + args[0] + ":" + args[1] + "/ParkingGarageService");
        new ParkingGarageService(url);
    }
    
}
