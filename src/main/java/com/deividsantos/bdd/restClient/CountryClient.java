package com.deividsantos.bdd.restClient;

import com.deividsantos.bdd.restClient.response.CountryResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CountryClient {

    @Autowired
    private ObjectMapper objectMapper;

    private CloseableHttpClient closeableHttpClient = HttpClients.createDefault();

    public CountryResponse getCountryByCode(String countryCode) throws IOException {
        HttpGet getRequest = new HttpGet("http://services.groupkt.com/country/get/iso2code/" + countryCode);
        getRequest.addHeader("content-type", "application/json");
        CloseableHttpResponse response = closeableHttpClient.execute(getRequest);
        return objectMapper.readValue(EntityUtils.toString(response.getEntity()), CountryResponse.class);
    }
}

