package com.mylesgamez;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.request.UpdateRequest;
import org.apache.solr.common.SolrInputDocument;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.io.IOException;

/**
 * A utility class for loading product data from a MySQL database into Apache Solr.
 */
public class LoadProductsIntoSolr {
    public static void main(String[] args) {
        // Define the Solr core URL. Replace 'your_core_name' with the actual core name.
        String solrUrl = "http://localhost:8983/solr/your_core_name";
        SolrClient solrClient = new HttpSolrClient.Builder(solrUrl).build();

        // Database connection details.
        String jdbcUrl = "jdbc:mysql://localhost:3306/product_database";
        String dbUsername = "root";
        String dbPassword = "helloworld";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUsername, dbPassword);
             Statement statement = connection.createStatement()) {

            // SQL query to select product data. Modify this query based on your database schema.
            String selectQuery = "SELECT * FROM Product";
            ResultSet resultSet = statement.executeQuery(selectQuery);

            while (resultSet.next()) {
                SolrInputDocument doc = new SolrInputDocument();
                // Map database fields to Solr document fields.
                doc.addField("id", resultSet.getString("ProductID"));
                doc.addField("name", resultSet.getString("Name"));
                doc.addField("description", resultSet.getString("Description"));
                // Add more fields as needed

                UpdateRequest updateRequest = new UpdateRequest();
                updateRequest.add(doc);
                updateRequest.setAction(UpdateRequest.ACTION.COMMIT, true, true);
                solrClient.request(updateRequest);
            }

            System.out.println("Products loaded into Solr.");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error loading products into Solr: " + e.getMessage());
        } finally {
            try {
                solrClient.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
                System.err.println("Error closing Solr client: " + ioException.getMessage());
            }
        }
    }
}
