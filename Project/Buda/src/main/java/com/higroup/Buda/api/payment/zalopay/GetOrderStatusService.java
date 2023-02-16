package com.higroup.Buda.api.payment.zalopay;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.apache.http.NameValuePair; // https://mvnrepository.com/artifact/org.apache.httpcomponents/httpclient
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject; // https://mvnrepository.com/artifact/org.json/json
import org.springframework.stereotype.Service;

import com.higroup.Buda.api.payment.zalopay.vn.zalopay.crypto.HMACUtil;

@Service
public class GetOrderStatusService {
    private Logger logger = Logger.getLogger(GetOrderStatusService.class.getName());

    private final Map<String, String> config = ZaloConfig.config;
    public String getOrderStatus() throws Exception {
        String apptransid = "190308_123456"; 
        String data = config.get("appid") +"|"+ apptransid  +"|"+ config.get("key1"); // appid|apptransid|key1
        String mac = HMACUtil.HMacHexStringEncode(HMACUtil.HMACSHA256, config.get("key1"), data);

        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("appid", config.get("appid")));
        params.add(new BasicNameValuePair("apptransid", apptransid));
        params.add(new BasicNameValuePair("mac", mac));

        URIBuilder uri = new URIBuilder(config.get("endpoint"));
        uri.addParameters(params);

        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet get = new HttpGet(uri.build());

        CloseableHttpResponse res = client.execute(get);
        BufferedReader rd = new BufferedReader(new InputStreamReader(res.getEntity().getContent()));
        StringBuilder resultJsonStr = new StringBuilder();
        String line;

        while ((line = rd.readLine()) != null) {
            resultJsonStr.append(line);
        }

        JSONObject result = new JSONObject(resultJsonStr.toString());
        // for (String key : result.keySet()) {
        //     System.out.format("%s = %s\n", key, result.get(key));
        // }
        return result.toString();
    }
}
