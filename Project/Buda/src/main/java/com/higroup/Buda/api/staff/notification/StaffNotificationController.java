package com.higroup.Buda.api.staff.notification;

import java.lang.reflect.InvocationTargetException;

import javax.servlet.http.HttpServletRequest;
import com.higroup.Buda.entities.StaffNote;
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
@RequestMapping("/api/staff/notification")
public class StaffNotificationController {
    private final StaffNotificationService staffNotificationService;
    private final RequestUtil requestUtil;
    @Autowired
    public StaffNotificationController(StaffNotificationService staffNoteService, RequestUtil requestUtil)
    {
        this.staffNotificationService = staffNoteService;
        this.requestUtil = requestUtil;
    }
    @PostMapping(path = "/new")
    public ResponseEntity<?> registerNewStaffNote(HttpServletRequest request, @RequestBody StaffNote staffNote)
    {
        Long jwtUserID = requestUtil.getUserIDFromUserToken(request);
        return ResponseEntity.ok().body(staffNotificationService.registerNewStaffNote(jwtUserID, staffNote));
    }

    @GetMapping(path = "/all")
    public ResponseEntity<?> findAllByUserID(HttpServletRequest request)
    {
        Long jwtUserID = requestUtil.getUserIDFromUserToken(request);
        return ResponseEntity.ok(this.staffNotificationService.findAllByUserID(jwtUserID));
    }

    @GetMapping(path = "staffID/{staffID}/all")
    public ResponseEntity<?> findAllByStaffID(HttpServletRequest request, @PathVariable Long staffID)
    {
        Long jwtUserID = requestUtil.getUserIDFromUserToken(request);
        return ResponseEntity.ok(this.staffNotificationService.findAllByStaffID(staffID, jwtUserID));
    }

    @GetMapping(path = "staffID/{staffID}/all/unseen")
    public ResponseEntity<?> findAllUnseenByStaffID(HttpServletRequest request, @PathVariable Long staffID)
    {
        Long jwtUserID = requestUtil.getUserIDFromUserToken(request);
        return ResponseEntity.ok(this.staffNotificationService.findAllUnseenByStaffID(staffID, jwtUserID));
    }

    @GetMapping(path = "noteID/{staffNoteID}")
    public ResponseEntity<?> findByStaffNoteID(HttpServletRequest request, @PathVariable Long staffNoteID){
        Long jwtUserID = requestUtil.getUserIDFromUserToken(request);
        return ResponseEntity.ok().body(staffNotificationService.findStaffNotebyID(staffNoteID, jwtUserID));
    }

    @DeleteMapping(path = "noteID/{staffNoteID}")
    public ResponseEntity<?> deleteByStaffNoteID(HttpServletRequest request, @PathVariable Long staffNoteID){
        Long jwtUserID = requestUtil.getUserIDFromUserToken(request);
        staffNotificationService.deleteStaffNotebyID(staffNoteID, jwtUserID);
        return ResponseEntity.ok("delete successfully!!");
    }

    @PutMapping(path = "update/{staffNoteID}")
    public ResponseEntity<?> updateStaffNoteByID(HttpServletRequest request, @RequestBody StaffNote newstaffNote, @PathVariable Long staffNoteID) throws IllegalAccessException, InvocationTargetException
    {
        Long jwtUserID = requestUtil.getUserIDFromUserToken(request);  
        return ResponseEntity.ok(staffNotificationService.updateStaffNotebyID(staffNoteID, newstaffNote, jwtUserID));
    }
}
