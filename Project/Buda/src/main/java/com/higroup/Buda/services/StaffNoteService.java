package com.higroup.Buda.services;

import java.util.List;
import java.util.Optional;

import com.higroup.Buda.entities.Staff;
import com.higroup.Buda.entities.StaffNote;
import com.higroup.Buda.entities.User;
import com.higroup.Buda.repositories.StaffNoteRepository;
import com.higroup.Buda.repositories.StaffRepository;
import com.higroup.Buda.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

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
    public ResponseEntity<?> registerNewStaffNote(Long userID, StaffNote staffNote)
    {
        Optional<User> user = this.userRepository.findUserByUserID(userID);
        if (user.isEmpty())
        {
            return ResponseEntity.badRequest().body("User not found");
        }
        Optional<Staff> staff = this.staffRepository.findStaffByStaffID(staffNote.getStaffID());
        if (staff.isEmpty())
        {
            return ResponseEntity.badRequest().body("Staff not found");
        }
        staffNote.setUserID(userID);
        this.staffNoteRepository.save(staffNote);
        return ResponseEntity.ok().body(staffNote.toString());
    }
    public List<StaffNote> findAllByStaffID(Long staffID)
    {
        return this.staffNoteRepository.findAllByStaffID(staffID);
    }
    public List<StaffNote> findAllByUserID(Long userID)
    {
        return this.staffNoteRepository.findAllByUserID(userID);
    }
}
