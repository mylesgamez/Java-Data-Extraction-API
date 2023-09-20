package com.mylesgamez;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {

    private final SolrClient solrClient;

    @Autowired
    public ProductController(SolrClient solrClient) {
        this.solrClient = solrClient;
    }

    @GetMapping("/api/products/search")
    public List<Product> searchProducts(@RequestParam("query") String query) {
        try {
            SolrQuery solrQuery = new SolrQuery();
            solrQuery.setQuery(query);

            QueryResponse response = solrClient.query(solrQuery);

            SolrDocumentList results = response.getResults();

            List<Product> products = new ArrayList<>();

            for (SolrDocument doc : results) {
                Product product = new Product();
                product.setId((String) doc.getFieldValue("id"));
                product.setName((String) doc.getFieldValue("name"));
                product.setDescription((String) doc.getFieldValue("description"));
                // Map other fields to the Product object

                products.add(product);
            }

            return products;
        } catch (Exception e) {
            // Properly handle exceptions (e.g., log the error)
            throw new RuntimeException("Error while searching products", e);
        }
    }
}
