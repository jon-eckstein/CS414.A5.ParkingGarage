/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cs414.a5.server;

/**
 *
 * @author jeckstein
 */
public class CredentialManagerImpl implements CredentialManager {
    
    private static final String DEFAULT_USERNAME = "admin";
    private static final String DEFAULT_PASSWORD = "password";
    private String username = DEFAULT_USERNAME;
    private String password = DEFAULT_PASSWORD;
    
    
    
    public CredentialManagerImpl(){}
    
    
    @Override
    public boolean authenticateUser(String username, String password){
        
        if(this.username.equals(username) && this.password.equals(password))
            return true;
        else
            return false;
        
    }
    
    @Override
    public void changeAdminUSernameAndPassword(String newUsername, String newPassword){
        this.username = newUsername;
        this.password = newPassword;
    }
    
}
