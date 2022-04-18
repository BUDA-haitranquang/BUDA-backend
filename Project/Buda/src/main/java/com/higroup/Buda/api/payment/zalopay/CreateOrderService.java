package com.higroup.Buda.api.payment.zalopay;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.UUID;

import com.higroup.Buda.entities.Plan;
import com.higroup.Buda.entities.Purchase;
import com.higroup.Buda.entities.User;

import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.NameValuePair;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.higroup.Buda.api.payment.zalopay.vn.zalopay.crypto.HMACUtil;


@Service
public class CreateOrderService {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final Map<String, String> config = ZaloConfig.config;
    private final Map<String, Object> embeddata = new HashMap<>(){{
        put("merchantinfo", "embeddata123");
    }};

    public String getCurrentTimeString(String format) {
        Calendar cal = new GregorianCalendar(TimeZone.getTimeZone("GMT+7"));
        SimpleDateFormat fmt = new SimpleDateFormat(format);
        fmt.setCalendar(cal);
        return fmt.format(cal.getTimeInMillis());
    }   

    private List<Map<String, Object>> createItems(Purchase purchase)
    {
        User user = purchase.getUser();
        Plan plan = purchase.getPlan();
        List<Map<String, Object>> items = new ArrayList<>();
        Map<String, Object> item = new HashMap<>();
        item.put("user", user);
        item.put("plan", plan);
        items.add(item);
        return items;
    }


    public String createOrder(Purchase purchase) throws IOException{
        // create item to purchase 
        // List<Map<String, Object>> items = this.createItems(purchase);
        final List<Map<String, Object>> items = new ArrayList<>(){{
            add(new HashMap<>(){{
                put("itemid", "knb");
                put("itemname", "kim nguyen bao");
                put("itemprice", 198400);
                put("itemquantity", 1);
            }});
        }};
        // create order 
        Map<String, Object> order = new HashMap<String, Object>(){{
            put("appid", config.get("appid"));
            put("apptransid", getCurrentTimeString("yyMMdd") +"_"+ UUID.randomUUID()); // mã giao dich có định dạng yyMMdd_xxxx
            // put("apptime", System.currentTimeMillis()); // miliseconds
            put("apptime", System.currentTimeMillis()); 
            put("appuser", "demo");
            put("amount", 50000);
            put("description", "ZaloPay Intergration Demo");
            put("bankcode", "zalopayapp");
            put("item", items.toString());
            put("embeddata", new JSONObject(embeddata).toString());
        }};

        // appid +”|”+ apptransid +”|”+ appuser +”|”+ amount +"|" + apptime +”|”+ embeddata +"|" +item
        String data = order.get("appid") +"|"+ order.get("apptransid") +"|"+ order.get("appuser") +"|"+ order.get("amount")
                +"|"+ order.get("apptime") +"|"+ order.get("embeddata") +"|"+ order.get("item");
        order.put("mac", HMACUtil.HMacHexStringEncode(HMACUtil.HMACSHA256, config.get("key1"), data));

        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost post = new HttpPost(config.get("create-order-endpoint"));

        List<NameValuePair> params = new ArrayList<>();
        for (Map.Entry<String, Object> e : order.entrySet()) {
            params.add(new BasicNameValuePair(e.getKey(), e.getValue().toString()));
        }
        System.out.println("\n\n\n" + params.toString() + "\n\n\n");

        // Content-Type: application/x-www-form-urlencoded
        post.setEntity(new UrlEncodedFormEntity(params));
        // System.out.println(post.toString());
        CloseableHttpResponse res = client.execute(post);
        BufferedReader rd = new BufferedReader(new InputStreamReader(res.getEntity().getContent()));
        StringBuilder resultJsonStr = new StringBuilder();
        String line;

        while ((line = rd.readLine()) != null) {
            resultJsonStr.append(line);
        }
        
        JsonNode root = objectMapper.readTree(resultJsonStr.toString());
        return root.toString();
    }

    // public static void main(String[] args) {
    //     this.createOrder(null);
    // }
}
    