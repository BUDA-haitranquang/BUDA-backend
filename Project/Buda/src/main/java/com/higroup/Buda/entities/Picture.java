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
    private Long pictureID;
    @Column(length = 200)
    private String link;


    public Picture() {
    }

    public Picture(Long pictureID, String link) {
        this.pictureID = pictureID;
        this.link = link;
    }

    public Long getPictureID() {
        return this.pictureID;
    }

    public void setPictureID(Long pictureID) {
        this.pictureID = pictureID;
    }

    public String getLink() {
        return this.link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Picture pictureID(Long pictureID) {
        setPictureID(pictureID);
        return this;
    }

    public Picture link(String link) {
        setLink(link);
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
        return Objects.equals(pictureID, picture.pictureID) && Objects.equals(link, picture.link);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pictureID, link);
    }

    @Override
    public String toString() {
        return "{" +
            " pictureID='" + getPictureID() + "'" +
            ", link='" + getLink() + "'" +
            "}";
    }
    
}
