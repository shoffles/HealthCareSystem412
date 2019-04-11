/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//Test Comment
package healthcaresystem412;

import java.util.HashMap;


public class UserAuth {
    
  
    
    public static HashMap<String, User> map = new HashMap<>();
    
    public UserAuth() {
        this.map.put("admin", new User( "admin", "doctor", "password", "Thomas Shoff", "3/13/1997"));
        this.map.put("user", new User("admin","patient", "password", "Joe Shmo", "3/13/1997"));
    }

    
    public boolean addMapping(String username, String password, String type, String name, String DOB){
        if(this.map.containsKey(username)) {
            return false;
        }
        User user = new User(username, type.toLowerCase(), password, name, DOB);
        this.map.put(user.getEmail(), user);
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
    
    public String getUserType(String username) {
        return this.map.get(username).getType();
    }
    
 
    
}
