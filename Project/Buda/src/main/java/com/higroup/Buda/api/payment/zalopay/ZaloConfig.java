package com.higroup.Buda.api.payment.zalopay;

import java.util.HashMap;
import java.util.Map;

public class ZaloConfig {
    public static Map<String, String> config = new HashMap<String, String>(){{
        put("appid", "553");
        put("key1", "9phuAOYhan4urywHTh0ndEXiV3pKHr5Q");
        put("key2", "Iyz2habzyr7AG8SgvoBCbKwKi3UzlLi3");
        put("create-order-endpoint", "https://sandbox.zalopay.com.vn/v001/tpe/createorder");
        put("get-order-status-endpoint", "https://sandbox.zalopay.com.vn/v001/tpe/getstatusbyapptransid");
    }};
}
