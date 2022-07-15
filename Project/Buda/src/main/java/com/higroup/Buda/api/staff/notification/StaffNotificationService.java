package com.higroup.Buda.api.staff.notification;

import java.lang.reflect.InvocationTargetException;
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
import com.higroup.Buda.util.BeanUtils.NullAwareBeanUtilsBean;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class StaffNotificationService {
    private final StaffNoteRepository staffNoteRepository;
    private final StaffRepository staffRepository;
    private final UserRepository userRepository;
    @Autowired
    public StaffNotificationService(StaffNoteRepository staffNoteRepository, UserRepository userRepository, StaffRepository staffRepository)
    {
        this.staffRepository = staffRepository;
        this.userRepository = userRepository;
        this.staffNoteRepository = staffNoteRepository;
    }
    @Transactional
    public StaffNote registerNewStaffNote(Long userID, StaffNote staffNote)
    {
        Optional<Staff> staff = this.staffRepository.findStaffByStaffID(staffNote.getStaffID());
        if (staff.isEmpty())
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Staff Not Found !!");
        }
        staffNote.setUserID(userID);
        staffNote.setNoteDate(ZonedDateTime.now());
        this.staffNoteRepository.save(staffNote);
        return staffNote;
    }
    public StaffNote findStaffNotebyID(Long staffNoteID, Long userID){
        if(staffNoteID == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "staff note id invalid");
        }
        Optional<StaffNote> staffNote = staffNoteRepository.findById(staffNoteID);
        if(staffNote.isPresent()){
            if(staffNote.get().getUserID().equals(userID)) return staffNote.get();
            else throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "staff note not belong to user");
        }
        else{
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "staff note not exists");
        }
    }

    public List<StaffNote> findAllByStaffID(Long staffID, Long userID)
    {
        if(staffID == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "staff id invalid");
        }
        Optional<Staff> staff = this.staffRepository.findById(staffID);
        if(staff.isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "staff not exists");
        }
        else{
            if(!staff.get().getUserID().equals(userID)){
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "staff not belong user");
            }
        }
        return this.staffNoteRepository.findAllByStaffID(staffID);
    }
    public List<StaffNote> findAllByUserID(Long userID)
    {
        return this.staffNoteRepository.findAllByUserID(userID);
    }

    public List<StaffNote> findAllUnseenByStaffID(Long staffID, Long userID)
    {
        if(staffID == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "staff id invalid");
        }
        Staff staff = this.staffRepository.findById(staffID).get();
        if(staff == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "staff not exists");
        }
        else{
            if(!staff.getUserID().equals(userID)){
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "staff not belong user");
            }
        }
        return this.staffNoteRepository.findAllUnseenByStaffID(staffID);
    }
    @Transactional
    public void deleteStaffNotebyID(Long staffNoteID, Long userID){
        // check valid staffnote
        this.findStaffNotebyID(staffNoteID, userID);
        this.staffNoteRepository.deleteById(staffNoteID);
    }
    @Transactional
    public StaffNote updateStaffNotebyID(Long staffNoteID, StaffNote newStaffNote , Long userID) throws IllegalAccessException, InvocationTargetException{
        StaffNote staffNote = this.findStaffNotebyID(staffNoteID, userID);
        newStaffNote.setNoteDate(ZonedDateTime.now());
        BeanUtilsBean notNull = new NullAwareBeanUtilsBean();
        notNull.copyProperties(staffNote, newStaffNote);
        this.staffNoteRepository.save(staffNote);
        return staffNote;
    }
}
