package com.higroup.Buda.api.payment.zalopay;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.UUID;

import com.higroup.Buda.entities.Purchase;

import org.apache.commons.codec.digest.HmacAlgorithms;
import org.apache.commons.codec.digest.HmacUtils;
import org.json.simple.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.higroup.Buda.api.payment.zalopay.vn.zalopay.crypto.HMACUtil;


@Service
public class ZaloPaymentService {


    private static Map<String, String> config = new HashMap<String, String>(){{
        put("appid", "553");
        put("key1", "9phuAOYhan4urywHTh0ndEXiV3pKHr5Q");
        put("key2", "Iyz2habzyr7AG8SgvoBCbKwKi3UzlLi3");
        put("endpoint", "https://sandbox.zalopay.com.vn/v001/tpe/createorder");
    }};

    public static String getCurrentTimeString(String format) {
        Calendar cal = new GregorianCalendar(TimeZone.getTimeZone("GMT+7"));
        SimpleDateFormat fmt = new SimpleDateFormat(format);
        fmt.setCalendar(cal);
        return fmt.format(cal.getTimeInMillis());
    }   

    public void createOrder(){
        String url = "https://sandbox.zalopay.com.vn/v001/tpe/createorderv";
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        JSONObject jsonObject = new JSONObject();

        HttpEntity<String> request = new HttpEntity<>(jsonObject.toString(), headers);
        
        
    }

    public static void main(String[] args) {
        final Map<String, String> embeddata = new HashMap<>(){{
            put("merchantinfo", "embeddata123");
        }};
        
        final List<Map<String, Object>> item = new ArrayList<>(){{
            add(new HashMap<>(){{
                put("itemid", "knb");
                put("itemname", "kim nguyen bao");
                put("itemprice", 198400);
                put("itemquantity", 1);
            }});
        }};
        // System.out.println(item.toString());


        MultiValueMap<String, Object> order = new LinkedMultiValueMap<>(){{
            add("appid", config.get("appid"));
            add("apptransid", getCurrentTimeString("yyMMdd") +"_"+ UUID.randomUUID()); // mã giao dich có định dạng yyMMdd_xxxx
            add("apptime", System.currentTimeMillis()); // miliseconds
            add("appuser", "demo");
            add("amount", 50000);
            add("description", "ZaloPay Intergration Demo");
            add("bankcode", "zalopayapp");
            add("item", item.toString());
            add("embeddata", new JSONObject(embeddata).toString());
        }};

        String data = order.get("appid") +"|"+ order.get("apptransid") +"|"+ order.get("appuser") +"|"+ order.get("amount")
                +"|"+ order.get("apptime") +"|"+ order.get("embeddata") +"|"+ order.get("item");
        order.add("mac", HMACUtil.HMacHexStringEncode(HMACUtil.HMACSHA256, config.get("key1"), data));

        
        // System.out.println(order.toString());
        String url = "https://sandbox.zalopay.com.vn/v001/tpe/createorder";
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(order, headers);
        
        ResponseEntity<String> response = restTemplate.postForEntity(url, request , String.class);
        System.out.println(response.getBody());
    }

}
    