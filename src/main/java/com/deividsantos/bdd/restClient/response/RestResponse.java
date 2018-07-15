package com.deividsantos.bdd.restClient.response;

import java.util.List;

public class RestResponse {

    private List<String> messages;
    private Result result;

    public RestResponse() {
    }

    public RestResponse(Result result, List<String> messages) {
        this.result = result;
        this.messages = messages;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }

    @Override
    public String toString() {
        return "RestResponse{" +
                "result=" + result +
                ", messages=" + messages +
                '}';
    }
}
