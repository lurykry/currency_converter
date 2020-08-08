package ru.smsoft.currencyconverter.dto;

public class ServerResponse {

    private Double result;
    private String status;
    private String message;

    public ServerResponse() {
    }

    public ServerResponse(Double result, String status, String message) {
        this.result = result;
        this.status = status;
        this.message = message;
    }

    public Double getResult() {
        return result;
    }

    public void setResult(Double result) {
        this.result = result;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
