/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package healthcaresystem412;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author konno
 */
public class PostgresConnector {
    private static String url = "jdbc:postgresql://localhost/healthcareapp";
    private static String user = "postgres";
    private static String password = "password";
    
    public static Connection connect() {
        Connection newConnection = null;
        try {
            newConnection = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to PostgreSQL");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        return newConnection;
    }
}
