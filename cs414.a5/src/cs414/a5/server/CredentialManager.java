/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cs414.a5.server;

/**
 *
 * @author jeckstein
 */
public interface CredentialManager {

    boolean authenticateUser(String username, String password);

    void changeAdminUSernameAndPassword(String newUsername, String newPassword);
    
}
