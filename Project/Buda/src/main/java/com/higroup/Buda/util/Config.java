package com.higroup.Buda.util;

import java.util.UUID;

public class Config {
    //public final static String secretKey = SHA_256_Encode.encode(UUID.randomUUID().toString());
    public final static String secretKey = "HelloWeAreBuda";
    public static int HoursAccessToken = 5;
    public static int HoursRefreshToken = 48;
}
