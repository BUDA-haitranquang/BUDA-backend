package com.higroup.Buda.entities;

import java.time.ZonedDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Staff_note", indexes = {
    @Index(columnList = "user_id", name = "staff_note_user_id_index"),
    @Index(columnList = "staff_id", name = "staff_note_staff_id_index")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
    private Boolean seen = Boolean.FALSE;

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
            ", seen='" + getSeen() + "'" +
            "}";
    }
    

}
