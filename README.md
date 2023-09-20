# Java Data Extraction API

The **Java Data Extraction API** is a project designed to facilitate the process of loading product data from a provided file into a MySQL database, transferring this data into an Apache Solr instance, and finally providing a RESTful API for easy data extraction.

## Project Components

### App.java

This class serves as the entry point for the Spring Boot application. It initializes and starts the application.

### LoadProductFileToMySQL.java

This utility class is responsible for loading SQL commands from a file (`Optional_ProductTable_DDL.sql`) into a MySQL database. It establishes a database connection, reads the SQL script, and executes individual commands.

### LoadProductsIntoSolr.java

This utility class transfers product data from the MySQL database into an Apache Solr instance. It connects to both the database and Solr, queries product data, maps it into Solr documents, and indexes these documents into Solr.

### Product.java

A simple Java class representing a product with fields like ID, name, and description. It's used for mapping product data retrieved from the database to Solr documents.

### ProductController.java

This Spring Boot controller provides a RESTful API for searching and retrieving product data from the Solr index. It accepts search queries and communicates with Solr to return relevant product information.

### application.properties

This configuration file contains settings for the application, including database connection details, Solr core URL, and other Spring Boot settings.

### pom.xml

The project's Maven configuration file defines dependencies, plugins, and project metadata.

### settings.xml

This Maven settings file configures plugin groups and repositories for the project.

## Getting Started

1. Ensure you have Java 11 or higher installed.

2. Set up a MySQL database and create the required table using the `Optional_ProductTable_DDL.sql` script.

3. Configure the database connection details and Solr core URL in `application.properties`.

4. Build the project using Maven by running:
mvn clean install

5. Run the Spring Boot application:
mvn spring-boot:run


6. Your API will be available at `http://localhost:8080/api/products/search`. Use this endpoint to search for products in Solr.
