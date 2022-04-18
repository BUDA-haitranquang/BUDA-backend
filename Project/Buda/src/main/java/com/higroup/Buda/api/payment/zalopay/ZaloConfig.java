package com.higroup.Buda.api.payment.zalopay;

import java.util.HashMap;
import java.util.Map;

public class ZaloConfig {
    public static Map<String, String> config = new HashMap<String, String>(){{
        put("appid", "554");
        put("key1", "8NdU5pG5R2spGHGhyO99HN1OhD8IQJBn");
        put("key2", "uUfsWgfLkRLzq6W2uNXTCxrfxs51auny");
        put("create-order-endpoint", "https://sandbox.zalopay.com.vn/v001/tpe/createorder");
        put("get-order-status-endpoint", "https://sandbox.zalopay.com.vn/v001/tpe/getstatusbyapptransid");
    }};
}
