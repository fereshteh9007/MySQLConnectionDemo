/*
 * This program demonstrates a simple JDBC connection to a database.
 * Connection details (including URL, username, and password) are read from
 * a configuration file named "database_config" located in the path "src/SimpleUsingJDBC/".
 *
 * Steps performed in this program:
 * 
 * 1. Load database connection properties from the configuration file.
 * 2. Print the loaded properties to verify the values.
 * 3. Attempt to connect to the database using JDBC.
 * 4. If successful, print a confirmation message and close the connection.
 *
 * Author: Fereshteh Rohani
 */

package SimpleUsingJDBC;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectToDB {

	public static void main(String[] args) throws SQLException {

        Properties props = new Properties();
        try {
            InputStream in = Files.newInputStream(Paths.get(
            		"src/SimpleUsingJDBC/database_config"));
            props.load(in);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String url = props.getProperty("jdbc.url");
        String username = props.getProperty("jdbc.username");
        String password = props.getProperty("jdbc.password");

        System.out.printf("%s\n%s\n%s\n\n", url, username, password);
        
        try {
            Connection connection = DriverManager.getConnection(
            		url, username, password);
            System.out.println("Connected to the database successfully!");
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
	}

}
