package com.higroup.Buda.restcontroller;

import java.lang.reflect.InvocationTargetException;

import javax.servlet.http.HttpServletRequest;
import com.higroup.Buda.entities.StaffNote;
import com.higroup.Buda.services.StaffNoteService;
import com.higroup.Buda.util.Checker.RequestUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/staff-note")
public class StaffNoteController {
    private final StaffNoteService staffNoteService;
    private final RequestUtil requestUtil;
    @Autowired
    public StaffNoteController(StaffNoteService staffNoteService, RequestUtil requestUtil)
    {
        this.staffNoteService = staffNoteService;
        this.requestUtil = requestUtil;
    }
    @PostMapping(path = "/register")
    public ResponseEntity<?> registerNewStaffNote(HttpServletRequest request, @RequestBody StaffNote staffNote)
    {
        Long jwtUserID = requestUtil.getUserIDFromUserToken(request);
        return ResponseEntity.ok().body(staffNoteService.registerNewStaffNote(jwtUserID, staffNote));
    }

    @GetMapping(path = "userID/all")
    public ResponseEntity<?> findAllByUserID(HttpServletRequest request)
    {
        Long jwtUserID = requestUtil.getUserIDFromUserToken(request);
        return ResponseEntity.ok(this.staffNoteService.findAllByUserID(jwtUserID));
    }

    @GetMapping(path = "staffID/{staffID}/all")
    public ResponseEntity<?> findAllByStaffID(HttpServletRequest request, @PathVariable Long staffID)
    {
        Long jwtUserID = requestUtil.getUserIDFromUserToken(request);
        return ResponseEntity.ok(this.staffNoteService.findAllByStaffID(staffID, jwtUserID));
    }

    @GetMapping(path = "staffID/{staffID}/all/unseen")
    public ResponseEntity<?> findAllUnseenByStaffID(HttpServletRequest request, @PathVariable Long staffID)
    {
        Long jwtUserID = requestUtil.getUserIDFromUserToken(request);
        return ResponseEntity.ok(this.staffNoteService.findAllUnseenByStaffID(staffID, jwtUserID));
    }

    @GetMapping(path = "noteID/{staffNoteID}")
    public ResponseEntity<?> findByStaffNoteID(HttpServletRequest request, @PathVariable Long staffNoteID){
        Long jwtUserID = requestUtil.getUserIDFromUserToken(request);
        return ResponseEntity.ok().body(staffNoteService.findStaffNotebyID(staffNoteID, jwtUserID));
    }

    @DeleteMapping(path = "noteID/{staffNoteID}")
    public ResponseEntity<?> deleteByStaffNoteID(HttpServletRequest request, @PathVariable Long staffNoteID){
        Long jwtUserID = requestUtil.getUserIDFromUserToken(request);
        staffNoteService.deleteStaffNotebyID(staffNoteID, jwtUserID);
        return ResponseEntity.ok("delete successfully!!");
    }

    @PutMapping(path = "update/{staffNoteID}")
    public ResponseEntity<?> updateStaffNoteByID(HttpServletRequest request, @RequestBody StaffNote newstaffNote, @PathVariable Long staffNoteID) throws IllegalAccessException, InvocationTargetException
    {
        Long jwtUserID = requestUtil.getUserIDFromUserToken(request);  
        return ResponseEntity.ok(staffNoteService.updateStaffNotebyID(staffNoteID, newstaffNote, jwtUserID));
    }
}
