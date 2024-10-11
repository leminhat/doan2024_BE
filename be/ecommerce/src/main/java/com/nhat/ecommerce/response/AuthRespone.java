package com.nhat.ecommerce.response;

public class AuthRespone {
    private String jwt;
    private String message;

    public AuthRespone() {
    }

    public AuthRespone(String jwt, String message) {
        super();
        this.jwt = jwt;
        this.message = message;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
