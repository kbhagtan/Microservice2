package com.shopping.retailer.ShoppingRetailService.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Arrays;

@RestController
public class ShoppingController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/productAvail/{productName}")
    public String productAvailability(@RequestParam String productName) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        String json = restTemplate.exchange("http://localhost:8080/productDetails", HttpMethod.GET, entity, String.class).getBody();
        boolean flag = checkProduct(productName, json);
        if (flag)
            return "Available";
        else
            return "Unavailable";
    }

    @GetMapping("/addToBag/{quantity}/{productName}")
        public String addToCart(@PathVariable int quantity, String productName) {
        HttpHeaders headers = new HttpHeaders();
        String callingUrl = "http://localhost:8080/removeProductFromCart/{quantity}/{productName}?quantity={quantity}&productName={productName}";
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        String response = restTemplate.exchange(callingUrl, HttpMethod.GET, entity, String.class).getBody();
        if (response.equalsIgnoreCase("Added to cart"))
            return "Added to Cart";
        else
            return "Item Unavailable";
    }

    private boolean checkProduct(@RequestParam String productName, String json) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(json);
        int quantity;
        boolean flag = false;
        String prodName;
        for (JsonNode node : jsonNode) {
            quantity = node.get("quantity").asInt();
            prodName = node.get("productName").asText();

            if (prodName.equals(productName) && quantity > 0) {
                flag = true;
            }
        }
        return flag;
    }
}