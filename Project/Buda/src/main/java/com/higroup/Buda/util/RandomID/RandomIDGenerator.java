package com.higroup.Buda.util.RandomID;

import org.apache.commons.lang3.RandomStringUtils;

import net.bytebuddy.utility.RandomString;

public class RandomIDGenerator {
    public static String randomIDString() {
        return RandomStringUtils.randomAlphabetic(4).toUpperCase().concat("-" + RandomStringUtils.randomNumeric(7));
    }
    public static void main(String[] args) {
        System.out.println(randomIDString());    
    }
}
