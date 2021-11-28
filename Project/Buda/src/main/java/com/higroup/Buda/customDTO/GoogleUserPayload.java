package com.higroup.Buda.customDTO;

public class GoogleUserPayload {
    private String userID;
    private String name;
    private String email;
    private String pictureUrl;
    private String familyName;
    private String givenName;
    
    public GoogleUserPayload() {
    }
    public GoogleUserPayload(String userID, String name, String email, String pictureUrl, String familyName,
            String givenName) {
        this.userID = userID;
        this.name = name;
        this.email = email;
        this.pictureUrl = pictureUrl;
        this.familyName = familyName;
        this.givenName = givenName;
    }
    public String getUserID() {
        return userID;
    }
    public void setUserID(String userID) {
        this.userID = userID;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPictureUrl() {
        return pictureUrl;
    }
    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }
    public String getFamilyName() {
        return familyName;
    }
    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }
    public String getGivenName() {
        return givenName;
    }
    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }
    
}
