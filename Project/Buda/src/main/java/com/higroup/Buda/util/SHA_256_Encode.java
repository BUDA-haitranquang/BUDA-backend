package com.higroup.Buda.util;


import java.nio.charset.StandardCharsets;



import com.google.common.hash.Hashing;



public class SHA_256_Encode {

    public static String encode(String originString){

        String sha256hex = Hashing.sha256().hashString(originString, StandardCharsets.UTF_8).toString();

        return sha256hex;

    }

}