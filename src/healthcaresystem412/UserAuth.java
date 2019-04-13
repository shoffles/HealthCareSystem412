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
        // This method (Shown Below) loads the user mapping for user authentication
        loadUserMap();
    }

    
    public void loadUserMap() {
        // Loads the data for users from the postgres database
        String SQL = "SELECT user_id,user_name,user_type,user_password,user_first_last,user_dob " + 
                "FROM appuser ";
        
        try (Connection sqlConnection = PostgresConnector.connect();
                PreparedStatement prepState = sqlConnection.prepareStatement(SQL)) {
            
            // Results of the query are obtained
            ResultSet results = prepState.executeQuery();
            
            // Iterates through the results, creating users and adding them to the map
            while (results.next()) {
                User userToAdd = new User(results.getLong("user_id"),
                        results.getString("user_name"),
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
        // A boolean to see if the user was valid
        boolean userValid = false;
        
        if(this.map.containsKey(username)) {
            userValid = false;
        } else {

            User newUser;
            
            // Initializes the ID outside a code snippet, since two SQL Queries are run
            long id = 0;
            
            // First inserts the user into the database
            {
                // SQL to insert a new user into the database
                String SQL = "INSERT INTO appuser(user_name,user_type,user_password,user_first_last,user_dob) " 
                        + "VALUES(?,?,?,?,?)";

                try (Connection sqlConnection = PostgresConnector.connect();
                        PreparedStatement prepState = sqlConnection.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS)) {

                    // Sets values of the SQL statement to user credentials for saving
                    prepState.setString(1, username);
                    prepState.setString(2, type.toLowerCase());
                    prepState.setString(3, password);
                    prepState.setString(4, name);
                    prepState.setString(5, DOB);

                    int rowCount = prepState.executeUpdate();

                    if (rowCount > 0) {
                        try (ResultSet resultSet = prepState.getGeneratedKeys()) {
                            if (resultSet.next()) {
                                id = resultSet.getLong(1);
                                newUser = new User(id, username, type.toLowerCase(), password, name, DOB);
                            }   
                        } catch (SQLException e) {
                            System.out.println(e.getMessage());
                        }
                    }

                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
            
            // Second adds a recursive assignment so that a user may see their own reports
            {
                String SQL = "INSERT INTO doctor_to_patient_assignment(viewer_id, to_be_viewed_id) " 
                        + "VALUES(?,?)";

                try (Connection sqlConnection = PostgresConnector.connect();
                        PreparedStatement prepState = sqlConnection.prepareStatement(SQL)) {

                    // Sets both values to the new ID
                    prepState.setLong(1, id);
                    prepState.setLong(2, id);

                    prepState.executeUpdate();
                    
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
            
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
