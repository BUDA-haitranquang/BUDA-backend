package com.higroup.Buda.services;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.higroup.Buda.entities.Staff;
import com.higroup.Buda.entities.StaffNote;
import com.higroup.Buda.entities.User;
import com.higroup.Buda.repositories.StaffNoteRepository;
import com.higroup.Buda.repositories.StaffRepository;
import com.higroup.Buda.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

@Service
public class StaffNoteService {
    private final StaffNoteRepository staffNoteRepository;
    private final StaffRepository staffRepository;
    private final UserRepository userRepository;
    @Autowired
    public StaffNoteService(StaffNoteRepository staffNoteRepository, UserRepository userRepository, StaffRepository staffRepository)
    {
        this.staffRepository = staffRepository;
        this.userRepository = userRepository;
        this.staffNoteRepository = staffNoteRepository;
    }
    @Transactional
    public StaffNote registerNewStaffNote(Long userID, StaffNote staffNote)
    {
        Optional<User> user = this.userRepository.findUserByUserID(userID);
        if (user.isEmpty())
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User Not Found !!");
        }
        Optional<Staff> staff = this.staffRepository.findStaffByStaffID(staffNote.getStaffID());
        if (staff.isEmpty())
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Staff Not Found !!");
        }
        staffNote.setUserID(userID);
        this.staffNoteRepository.save(staffNote);
        return staffNote;
    }
    public StaffNote findStaffNotebyID(Long id){
        Optional<StaffNote> staffNote = staffNoteRepository.findById(id);
        if(staffNote.isPresent()){
            return staffNote.get();
        }
        return null;
    }

    public List<StaffNote> findAllByStaffID(Long staffID)
    {
        return this.staffNoteRepository.findAllByStaffID(staffID);
    }
    public List<StaffNote> findAllByUserID(Long userID)
    {
        return this.staffNoteRepository.findAllByUserID(userID);
    }

    public List<StaffNote> findAllUnseenByStaffID(Long staffID)
    {
        return this.staffNoteRepository.findAllUnseenByStaffID(staffID);
    }
    @Transactional
    public void deleteStaffNotebyID(Long id){
        Optional<StaffNote> staffNote = this.staffNoteRepository.findStaffNoteByStaffNoteID(id);
        if (staffNote.isPresent())
        {
            this.staffNoteRepository.delete(staffNote.get());
        }
        else throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "StaffNote not found");
    }
    @Transactional
    public StaffNote updateStaffNotebyID(Long id, ZonedDateTime noteDate, String message, Boolean seen, Long userID, Long staffID){
        StaffNote staffNote = this.staffNoteRepository.findById(id).get();
        Staff staff = this.staffRepository.findById(staffID).get();
        // not found staffnote
        if(staffNote == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "staffnote not exists");
        }
        // staff id cannot be null
        if(staffID != null && staff == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "staff not exists");
        }
        // cannot change userid in staffnote
        if(!staffNote.getUserID().equals(userID)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "staffNote not belong to userID: " + String.valueOf(userID));
        }
        // check if staff exitst and belong to user
        if(staff != null && (!staff.getUserID().equals(userID))){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Staff not belong to userID: " + String.valueOf(userID));
        }
        staffNote.setStaffID(staffID);
        staffNote.setNoteDate(noteDate);
        staffNote.setMessage(message);
        staffNote.setSeen(seen);
        this.staffNoteRepository.save(staffNote);
        return staffNote;

    }
}
