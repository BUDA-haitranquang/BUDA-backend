package com.higroup.Buda.util;

import java.util.UUID;

public class Config {
    //  PROD
    //  public final static String url = "http://143.198.194.24:8080/";
    //  public final static String secretKey = SHA_256_Encode.encode(UUID.randomUUID().toString());
    //  public static int HoursAccessToken = 1;
    //  public static int HoursRefreshToken = 48;
    
    //  DEV
    public final static String url = "http://localhost:8080";
    public final static String secretKey = "HelloWeAreBuda";
    public static int HoursAccessToken = 1;
    public static int HoursRefreshToken = 48;
}
