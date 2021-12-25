package com.higroup.Buda.entities;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table
public class Picture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "picture_id")
    private Long pictureID;
    @Column(length = 200)
    private String pictureLink;
    @Column(name = "user_id")
    private Long userID;

    public Picture() {
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public Picture(String pictureLink, Long userID) {
        this.pictureLink = pictureLink;
        this.userID = userID;
    }

    public Picture(Long pictureID, String pictureLink) {
        this.pictureID = pictureID;
        this.pictureLink = pictureLink;
    }

    public Long getPictureID() {
        return this.pictureID;
    }

    public void setPictureID(Long pictureID) {
        this.pictureID = pictureID;
    }

    public String getPictureLink() {
        return this.pictureLink;
    }

    public void setPictureLink(String pictureLink) {
        this.pictureLink = pictureLink;
    }

    public Picture pictureID(Long pictureID) {
        setPictureID(pictureID);
        return this;
    }

    public Picture pictureLink(String pictureLink) {
        setPictureLink(pictureLink);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Picture)) {
            return false;
        }
        Picture picture = (Picture) o;
        return Objects.equals(pictureID, picture.pictureID) && Objects.equals(pictureLink, picture.pictureLink);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pictureID, pictureLink);
    }

    @Override
    public String toString() {
        return "{" +
            " pictureID='" + getPictureID() + "'" +
            ", pictureLink='" + getPictureLink() + "'" +
            "}";
    }

    
}
