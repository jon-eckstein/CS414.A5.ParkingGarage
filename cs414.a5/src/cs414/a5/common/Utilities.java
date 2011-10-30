/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cs414.a5.common;

/**
 *
 * @author jeckstein
 */
public class Utilities {    
    
    public static boolean isNullOrEmpty(String param){
        return param == null || param.trim().length() == 0;                
    }
 
    public static void printLn(String param){
        System.out.println(param);
    }
    
    public static Integer tryParseInt(String param){
        try{
         return Integer.parseInt(param);   
        }catch(NumberFormatException ex){
            return null;
        }
    }
    
}
