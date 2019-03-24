/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//Test Comment
package healthcaresystem412;

import java.util.HashMap;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javax.swing.JOptionPane;


public class User {
    
  
    
    private HashMap<String, UserInfo> map = new HashMap<>();
    
    public User() {
        this.map.put("thomasshoff14@gmail.com".toLowerCase(), new UserInfo("Doctor", "password", "Thomas Shoff", "3/13/1997"));
    }

    
    public boolean addMapping(String username, String password, String type, String name, String DOB){
        if(this.map.containsKey(username)) {
            return false;
        }
        this.map.put(username, new UserInfo(type, password, name, DOB));
        return true;
    }
    
    public boolean checkUsernamePassword(String username, String password) {
        if(this.map.containsKey(username)) {
            if(this.map.get(username).getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }
    
 
    
}
