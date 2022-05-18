package com.higroup.Buda.util.Checker;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import com.higroup.Buda.entities.User;
import com.higroup.Buda.repositories.StaffRepository;
import com.higroup.Buda.repositories.UserRepository;
import com.higroup.Buda.security.jwt.JwtTokenUtil;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class RequestUtil {
    private final JwtTokenUtil jwtTokenUtil;
    private final UserRepository userRepository;
    private final StaffRepository staffRepository;
    @Value("${authentication.url}")
    private String authenticationURL;

    @Autowired
    public RequestUtil(JwtTokenUtil jwtTokenUtil, UserRepository userRepository, StaffRepository staffRepository) {
        this.userRepository = userRepository;
        this.jwtTokenUtil = jwtTokenUtil;
        this.staffRepository = staffRepository;
    }

    public Long getUserIDGeneral(HttpServletRequest httpServletRequest, String type) {
        final String token = httpServletRequest.getHeader("Authorization").substring(7);
        HttpPost httpPost = new HttpPost(authenticationURL + "api/token/verify" + type);
        HttpClient client = HttpClients.createDefault();
        String json = "{\"accessToken\":" + "\"" + token + "\"" + "}";
        StringEntity entity;
        entity = new StringEntity(json, ContentType.APPLICATION_JSON);
        httpPost.setEntity(entity);
        
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-type", "application/json");
        try {
            HttpResponse response = client.execute(httpPost);
            HttpEntity httpEntity = response.getEntity();
            JSONObject jsonObject = new JSONObject(EntityUtils.toString(httpEntity));
            return jsonObject.getLong("userID");
        } catch (Exception e) {

        }

        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid token");

    }

    public Long getUserIDFromUserToken(HttpServletRequest httpServletRequest) {
        return getUserIDGeneral(httpServletRequest, "normal");
    }

    public Long getProUserIDFromUserToken(HttpServletRequest httpServletRequest) {
        return getUserIDGeneral(httpServletRequest, "pro");
    }

    public Long getPremiumUserIDFromUserToken(HttpServletRequest httpServletRequest) {
        return getUserIDGeneral(httpServletRequest, "premium");
    }

    // on staff token
    public Long getStaffIDFromStaffToken(HttpServletRequest httpServletRequest) {
        return getUserIDGeneral(httpServletRequest, "staff");
    }

    public Long getUserIDFromStaffToken(HttpServletRequest httpServletRequest) {
        final String token = httpServletRequest.getHeader("Authorization").substring(7);
        Long userID = this.jwtTokenUtil.getUserIDFromToken(token);
        Optional<User> user = this.userRepository.findUserByUserID(userID);
        if ((userID != null) && (user.isPresent())
                && (jwtTokenUtil.isValid(token))) {
            if (jwtTokenUtil.getTokenTypeFromToken(token).equals("Access")) {
                return userID;
            } else
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid access token");
        }
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized");
    }
}
