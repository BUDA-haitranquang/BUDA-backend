package com.higroup.Buda.jwt;

public class JwtSimple {
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public JwtSimple(String token) {
        this.token = token;
    }

    public JwtSimple() {
    }

}
