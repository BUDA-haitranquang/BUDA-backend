package com.higroup.Buda.entities;

import java.time.ZonedDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Staff_note")
public class StaffNote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "staff_note_id")
    private Long staffNoteID;
    @NotNull
    @Column(name = "user_id")
    private Long userID;
    @NotNull
    @Column(name = "staff_id")
    private Long staffID;
    private ZonedDateTime noteDate;
    @Column(length = 1000)
    private String message;
    private Boolean seen;


    public StaffNote() {
    }

    public StaffNote(Long staffNoteID, Long userID, Long staffID, ZonedDateTime noteDate, String message, Boolean seen) {
        this.staffNoteID = staffNoteID;
        this.userID = userID;
        this.staffID = staffID;
        this.noteDate = noteDate;
        this.message = message;
        this.seen = seen;
    }

    public Long getStaffNoteID() {
        return this.staffNoteID;
    }

    public void setStaffNoteID(Long staffNoteID) {
        this.staffNoteID = staffNoteID;
    }

    public Long getUserID() {
        return this.userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public Long getStaffID() {
        return this.staffID;
    }

    public void setStaffID(Long staffID) {
        this.staffID = staffID;
    }

    public ZonedDateTime getNoteDate() {
        return this.noteDate;
    }

    public void setNoteDate(ZonedDateTime noteDate) {
        this.noteDate = noteDate;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean isSeen() {
        return this.seen;
    }

    public Boolean getSeen() {
        return this.seen;
    }

    public void setSeen(Boolean seen) {
        this.seen = seen;
    }

    public StaffNote staffNoteID(Long staffNoteID) {
        setStaffNoteID(staffNoteID);
        return this;
    }

    public StaffNote userID(Long userID) {
        setUserID(userID);
        return this;
    }

    public StaffNote staffID(Long staffID) {
        setStaffID(staffID);
        return this;
    }

    public StaffNote noteDate(ZonedDateTime noteDate) {
        setNoteDate(noteDate);
        return this;
    }

    public StaffNote message(String message) {
        setMessage(message);
        return this;
    }

    public StaffNote seen(Boolean seen) {
        setSeen(seen);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof StaffNote)) {
            return false;
        }
        StaffNote staffNote = (StaffNote) o;
        return Objects.equals(staffNoteID, staffNote.staffNoteID) && Objects.equals(userID, staffNote.userID) && Objects.equals(staffID, staffNote.staffID) && Objects.equals(noteDate, staffNote.noteDate) && Objects.equals(message, staffNote.message) && Objects.equals(seen, staffNote.seen);
    }

    @Override
    public int hashCode() {
        return Objects.hash(staffNoteID, userID, staffID, noteDate, message, seen);
    }

    @Override
    public String toString() {
        return "{" +
            " staffNoteID='" + getStaffNoteID() + "'" +
            ", userID='" + getUserID() + "'" +
            ", staffID='" + getStaffID() + "'" +
            ", noteDate='" + getNoteDate() + "'" +
            ", message='" + getMessage() + "'" +
            ", seen='" + isSeen() + "'" +
            "}";
    }
    

}
