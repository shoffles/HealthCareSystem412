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
import java.sql.Statement;
import java.util.HashMap;


public class UserAuth {
    
  
    
    public static HashMap<String, User> map = new HashMap<>();
    
    public UserAuth() {
        loadUserMap();
    }

    
    public void loadUserMap() {
        String SQL = "SELECT user_name,user_type,user_password,user_first_last,user_dob " + 
                "FROM appuser ";
        
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
        
        boolean userValid = false;
        
        if(this.map.containsKey(username)) {
            userValid = false;
        } else {
            userValid = true;
            
            User newUser = new User(username, type.toLowerCase(), password, name, DOB);
            this.map.put(newUser.getUsername(), newUser);
            /*


            */
            String SQL = "INSERT INTO appuser(user_name,user_type,user_password,user_first_last,user_dob) " 
                    + "VALUES(?,?,?,?,?)";

            long id = 0;



            try (Connection sqlConnection = PostgresConnector.connect();
                    PreparedStatement prepState = sqlConnection.prepareStatement(SQL)) {

                prepState.setString(1, username);
                prepState.setString(2, type.toLowerCase());
                prepState.setString(3, password);
                prepState.setString(4, name);
                prepState.setString(5, DOB);


                int rowCount = prepState.executeUpdate();

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            /*


            */
            userValid = true;
        }
        
        return userValid;
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
