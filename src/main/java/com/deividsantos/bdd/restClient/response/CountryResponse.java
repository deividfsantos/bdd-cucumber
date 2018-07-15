package com.deividsantos.bdd.restClient.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CountryResponse {

    @JsonProperty("RestResponse")
    private RestResponse restResponse;

    public CountryResponse() {
    }

    public CountryResponse(RestResponse restResponse) {
        this.restResponse = restResponse;
    }

    public RestResponse getRestResponse() {
        return restResponse;
    }

    public void setRestResponse(RestResponse restResponse) {
        this.restResponse = restResponse;
    }

    @Override
    public String toString() {
        return "CountryResponse{" +
                "restResponse=" + restResponse +
                '}';
    }
}
