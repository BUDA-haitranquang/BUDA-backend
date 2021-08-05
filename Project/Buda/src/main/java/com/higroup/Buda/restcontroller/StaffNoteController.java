package com.higroup.Buda.restcontroller;

import java.util.List;

import com.higroup.Buda.entities.StaffNote;
import com.higroup.Buda.repositories.StaffNoteRepository;
import com.higroup.Buda.services.StaffNoteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/staff-note")
public class StaffNoteController {
    private final StaffNoteService staffNoteService;
    @Autowired
    public StaffNoteController(StaffNoteService staffNoteService)
    {
        this.staffNoteService = staffNoteService;
    }
    @PostMapping(path = "userID/{userID}/register")
    public ResponseEntity<?> registerNewStaffNote(@PathVariable Long userID, @RequestBody StaffNote staffNote)
    {
        return this.staffNoteService.registerNewStaffNote(userID, staffNote);
    }
    @GetMapping(path = "userID/{userID}/all")
    public List<StaffNote> findAllByUserID(@PathVariable Long userID)
    {
        return this.staffNoteService.findAllByUserID(userID);
    }
    @GetMapping(path = "staffID/{staffID}/all")
    public List<StaffNote> findAllByStaffID(@PathVariable Long staffID)
    {
        return this.staffNoteService.findAllByStaffID(staffID);
    }
}
