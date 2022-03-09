package com.higroup.Buda.security.jwt;

public class JwtResponse{
    private final String jwtaccesstoken;
    private final String jwtrefreshtoken;
    public JwtResponse(String jwtaccesstoken, String jwtrefreshtoken){
        this.jwtaccesstoken = jwtaccesstoken;
        this.jwtrefreshtoken = jwtrefreshtoken;
    }
    public String getAccessToken(){
        return this.jwtaccesstoken;
    }
    public String getRefreshToken(){
        return this.jwtrefreshtoken;
    }
}
