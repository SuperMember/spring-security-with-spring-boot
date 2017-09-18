package com.demo.springsecurity.entity;

public class ResponseMessage {
    private String token;
    private String tokenHash;

    public ResponseMessage(String token, String tokenHash) {
        this.token = token;
        this.tokenHash = tokenHash;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTokenHash() {
        return tokenHash;
    }

    public void setTokenHash(String tokenHash) {
        this.tokenHash = tokenHash;
    }
}
