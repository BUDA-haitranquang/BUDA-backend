package com.higroup.Buda.restcontroller;

import java.time.ZonedDateTime;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.swing.text.StyledEditorKit.BoldAction;

import com.higroup.Buda.entities.Purchase;
import com.higroup.Buda.entities.Staff;
import com.higroup.Buda.entities.StaffNote;
import com.higroup.Buda.repositories.StaffNoteRepository;
import com.higroup.Buda.repositories.StaffRepository;
import com.higroup.Buda.services.StaffNoteService;
import com.higroup.Buda.services.StaffService;
import com.higroup.Buda.util.Checker.RequestUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/staff-note")
public class StaffNoteController {
    private final StaffService staffService;
    private final StaffNoteService staffNoteService;
    private final RequestUtil requestUtil;
    @Autowired
    public StaffNoteController(StaffNoteService staffNoteService, RequestUtil requestUtil, StaffService staffService)
    {
        this.staffNoteService = staffNoteService;
        this.requestUtil = requestUtil;
        this.staffService = staffService;
    }
    @PostMapping(path = "/register")
    public ResponseEntity<?> registerNewStaffNote(HttpServletRequest request, @RequestBody StaffNote staffNote)
    {
        Long jwtUserID = requestUtil.getUserIDFromUserToken(request);
        staffNote.setNoteDate(ZonedDateTime.now());
        return ResponseEntity.ok().body(staffNoteService.registerNewStaffNote(jwtUserID, staffNote));
    }

    @GetMapping(path = "userID/{userID}/all")
    public ResponseEntity<?> findAllByUserID(HttpServletRequest request, @PathVariable Long userID)
    {
        Long jwtUserID = requestUtil.getUserIDFromUserToken(request);
        if(!jwtUserID.equals(userID)){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("No authorized");
        }
        return ResponseEntity.ok(this.staffNoteService.findAllByUserID(userID));
    }

    @GetMapping(path = "staffID/{staffID}/all")
    public ResponseEntity<?> findAllByStaffID(HttpServletRequest request, @PathVariable Long staffID)
    {
        Long jwtUserID = requestUtil.getUserIDFromUserToken(request);
        Staff staff = staffService.findStaffByID(staffID);
        // check staff belong to user
        if(!staff.getUserID().equals(jwtUserID)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Staff not belong to userID: " + String.valueOf(staffID));
        }
        return ResponseEntity.ok(this.staffNoteService.findAllByStaffID(staffID));
    }

    @GetMapping(path = "staffID/{staffID}/all/unseen")
    public ResponseEntity<?> findAllUnseenByStaffID(HttpServletRequest request, @PathVariable Long staffID)
    {
        Long jwtUserID = requestUtil.getUserIDFromUserToken(request);
        Staff staff = staffService.findStaffByID(staffID);
        // check staff belong to user
        if(!staff.getUserID().equals(jwtUserID)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Staff not belong to userID: " + String.valueOf(staffID));
        }
        return ResponseEntity.ok(this.staffNoteService.findAllUnseenByStaffID(staffID));
    }

    @GetMapping(path = "noteID/{staffNoteID}")
    public ResponseEntity<?> findByStaffNoteID(HttpServletRequest request, @PathVariable Long staffNoteID){
        Long jwtUserID = requestUtil.getUserIDFromUserToken(request);
        StaffNote staffNote = this.staffNoteService.findStaffNotebyID(staffNoteID);
        if(staffNote == null){
            return ResponseEntity.badRequest().body("StaffNote id not exists: " + String.valueOf(staffNoteID));
        }
        if(!staffNote.getUserID().equals(jwtUserID)){
            return ResponseEntity.badRequest().body("StaffNote not belong to user: " + String.valueOf(jwtUserID));
        }
        return ResponseEntity.ok().body(staffNoteService.findStaffNotebyID(staffNoteID));
    }

    @DeleteMapping(path = "noteID/{staffNoteID}")
    public ResponseEntity<?> deleteByStaffNoteID(HttpServletRequest request, @PathVariable Long staffNoteID){
        Long jwtUserID = requestUtil.getUserIDFromUserToken(request);
        StaffNote staffNote = this.staffNoteService.findStaffNotebyID(staffNoteID);
        if(staffNote == null){
            return ResponseEntity.badRequest().body("StaffNote id not exists: " + String.valueOf(staffNoteID));
        }
        if(!staffNote.getUserID().equals(jwtUserID)){
            return ResponseEntity.badRequest().body("StaffNote not belong to user: " + String.valueOf(jwtUserID));
        }
        staffNoteService.deleteStaffNotebyID(staffNoteID);
        return ResponseEntity.ok("delete successfully!!");
    }

    @PutMapping(path = "noteID/{staffNoteID}")
    public ResponseEntity<?> updateStaffByID(HttpServletRequest request, @RequestBody StaffNote newstaffNote)
    {
        Long staffNoteID = newstaffNote.getStaffNoteID();
        Boolean is_seen = newstaffNote.getSeen();
        String message = newstaffNote.getMessage();
        Long staffID = newstaffNote.getStaffID();

        Long jwtUserID = requestUtil.getUserIDFromUserToken(request);
        StaffNote staffNote = this.staffNoteService.findStaffNotebyID(staffNoteID);
        if(staffNote == null){
            return ResponseEntity.badRequest().body("StaffNote id not exists: " + String.valueOf(staffNoteID));
        }
        if(!staffNote.getUserID().equals(jwtUserID)){
            return ResponseEntity.badRequest().body("StaffNote not belong to user: " + String.valueOf(jwtUserID));
        }
        if(is_seen == null){
            is_seen = staffNote.getSeen();
        }
        if(message == null){
            message = staffNote.getMessage();
        }
        if(staffID == null){
            staffID = staffNote.getStaffID();
        }
        ZonedDateTime noteDate = ZonedDateTime.now();
        return ResponseEntity.ok(staffNoteService.updateStaffNotebyID(staffNoteID, noteDate, message, is_seen, jwtUserID, staffID));
    }
}
