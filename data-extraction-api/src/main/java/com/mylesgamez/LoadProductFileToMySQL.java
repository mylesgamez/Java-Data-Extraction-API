package com.mylesgamez;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * A utility class for loading SQL commands from a file into a MySQL database.
 */
public class LoadProductFileToMySQL {
    public static void main(String[] args) {
        // Database connection details.
        String jdbcUrl = "jdbc:mysql://localhost:3306/product_database";
        String username = "root";
        String password = "helloworld";

        // Path to the SQL script file to be loaded into the database.
        String sqlFilePath = "Optional_ProductTable_DDL.sql"; // Ensure this file is available

        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
             Statement statement = connection.createStatement();
             BufferedReader reader = new BufferedReader(new FileReader(sqlFilePath))) {

            String line;
            StringBuilder sqlScript = new StringBuilder();

            // Read the contents of the SQL script file line by line.
            while ((line = reader.readLine()) != null) {
                sqlScript.append(line);
            }

            // Split the SQL script into individual commands (assuming they are separated by ';').
            String[] sqlCommands = sqlScript.toString().split(";");
            
            // Execute each SQL command.
            for (String sqlCommand : sqlCommands) {
                statement.executeUpdate(sqlCommand);
            }

            System.out.println("Product file loaded into the database successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error loading the product file into the database: " + e.getMessage());
        }
    }
}
