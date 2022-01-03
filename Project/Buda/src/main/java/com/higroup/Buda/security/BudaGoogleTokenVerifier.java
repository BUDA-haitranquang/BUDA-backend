package com.higroup.Buda.security;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.higroup.Buda.customDTO.GoogleUserPayload;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class BudaGoogleTokenVerifier {

    @Value("${google.oauth2}")
    private static String googleCredentials;

    public static GoogleIdTokenVerifier googleIdTokenVerifier = 
    new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), new GsonFactory())
    .setAudience(Collections.singletonList(googleCredentials))
    .build();
    public BudaGoogleTokenVerifier()
    {

    }
    public static GoogleIdToken getGoogleIdToken(String idTokenString) throws GeneralSecurityException, IOException
    {
        return BudaGoogleTokenVerifier.googleIdTokenVerifier.verify(idTokenString);
    }
    public static GoogleUserPayload userCustomPayload(String idTokenString) throws GeneralSecurityException, IOException
    {
        GoogleIdToken googleIdToken = BudaGoogleTokenVerifier.getGoogleIdToken(idTokenString);
        GoogleUserPayload googleUserPayload = new GoogleUserPayload();
        if (googleIdToken!=null)
        {
            Payload payload = googleIdToken.getPayload();
            String userId = payload.getSubject();
            System.out.println("User ID: " + userId);

            // Get profile information from payload
            String email = payload.getEmail();
            boolean emailVerified = Boolean.valueOf(payload.getEmailVerified());
            String name = (String) payload.get("name");
            String pictureUrl = (String) payload.get("picture");
            String locale = (String) payload.get("locale");
            String familyName = (String) payload.get("family_name");
            String givenName = (String) payload.get("given_name");
            if (!emailVerified)
            {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This email has not been verified by Google");
            }
            googleUserPayload.setEmail(email);
            googleUserPayload.setFamilyName(familyName);
            googleUserPayload.setGivenName(givenName);
            googleUserPayload.setName(name);
            googleUserPayload.setPictureUrl(pictureUrl);
        }
        else throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid ID token");
        return googleUserPayload;
    }
}
