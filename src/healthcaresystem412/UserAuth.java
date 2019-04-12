/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//Test Comment
package healthcaresystem412;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;


public class UserAuth {
    
  
    
    public static HashMap<String, User> map = new HashMap<>();
    
    public UserAuth() {
        String SQL = "SELECT user_name,user_type,user_password,user_first_last,user_dob " + 
                "FROM user ";
        
        try (Connection sqlConnection = PostgresConnector.connect();
                PreparedStatement prepState = sqlConnection.prepareStatement(SQL)) {
            
            ResultSet results = prepState.executeQuery();
            
            while (results.next()) {
                User userToAdd = new User(results.getString("user_name"),
                        results.getString("user_type"),
                        results.getString("user_password"),
                        results.getString("user_first_last"),
                        results.getString("user_dob"));
                        
                this.map.put(userToAdd.getUsername(), userToAdd);
            }
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    
    public void loadUserMap() {
        String SQL = "SELECT user_name,user_type,user_password,user_first_last,user_dob " + 
                "FROM user ";
        
        try (Connection sqlConnection = PostgresConnector.connect();
                PreparedStatement prepState = sqlConnection.prepareStatement(SQL)) {
            
            ResultSet results = prepState.executeQuery();
            
            while (results.next()) {
                User userToAdd = new User(results.getString("user_name"),
                        results.getString("user_type"),
                        results.getString("user_password"),
                        results.getString("user_first_last"),
                        results.getString("user_dob"));
                        
                this.map.put(userToAdd.getUsername(), userToAdd);
            }
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public boolean addMapping(String username, String password, String type, String name, String DOB){
        if(this.map.containsKey(username)) {
            return false;
        }
        User user = new User(username, type.toLowerCase(), password, name, DOB);
        this.map.put(user.getUsername(), user);
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
    
    public User getUser(String username) {
        return this.map.get(username);
    }
    
 
    
}
