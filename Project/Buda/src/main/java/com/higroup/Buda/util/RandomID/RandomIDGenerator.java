package com.higroup.Buda.util.RandomID;

import net.bytebuddy.utility.RandomString;

public class RandomIDGenerator {
    public static String randomIDString() {
        return RandomString.make(8);
    }
}
