/*
import healthcaresystem412.PostgresConnector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
// Report report = new Report(this.titleField.getText(), this.reportField.getText(), this.usernameField.getText(), this.nameField.getText(), this.ageField.getText());
        // Add to DB
        long viewerId = 0;
        long toBeViewedId = 0;
        
        // This block queries the appuser table to set a user ID for entry into the report field
        {
            String SQL = "SELECT user_id,user_name,user_type,user_password,user_first_last,user_dob " + 
                    "FROM appuser " +   
                    "WHERE user_name = ?";

            try (Connection sqlConnection = PostgresConnector.connect();
                    PreparedStatement prepState = sqlConnection.prepareStatement(SQL)) {

                prepState.setString(1, this.viewerField.getText());
                
                // Results of the query are obtained
                ResultSet results = prepState.executeQuery();

                // Finds the userID from the query
                results.next();
                viewerId = results.getInt("user_id");
                
                
                
                

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        
        // This block gets the userId of the person who can now be viewed by the user of the previous block
        {
            String SQL = "SELECT user_id,user_name,user_type,user_password,user_first_last,user_dob " + 
                    "FROM appuser " +   
                    "WHERE user_name = ?";

            try (Connection sqlConnection = PostgresConnector.connect();
                    PreparedStatement prepState = sqlConnection.prepareStatement(SQL)) {

                prepState.setString(1, this.toBeViewedField.getText());
                
                // Results of the query are obtained
                ResultSet results = prepState.executeQuery();

                // Finds the userID from the query
                results.next();
                toBeViewedId = results.getInt("user_id");
                
                
                
                

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        
        // This block inserts a report into the report table using the ID scraped from the Username search
        {
            String SQL = "INSERT INTO doctor_to_patient_assignment (viewer_id, to_be_viewed_id) " 
                    + "VALUES(?,?)";

            try (Connection sqlConnection = PostgresConnector.connect();
                    PreparedStatement prepState = sqlConnection.prepareStatement(SQL)) {

                // Sets both values to the new ID
                prepState.setInt(1, (int) viewerId);
                prepState.setInt(2, (int) toBeViewedId);

                prepState.executeUpdate();

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
*/


