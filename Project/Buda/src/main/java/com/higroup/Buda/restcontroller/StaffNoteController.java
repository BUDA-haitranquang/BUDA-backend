package com.higroup.Buda.restcontroller;

import com.higroup.Buda.repositories.StaffNoteRepository;
import com.higroup.Buda.services.StaffNoteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/staffnote")
public class StaffNoteController {
    private StaffNoteService staffNoteService;
    @Autowired
    public StaffNoteController(StaffNoteService staffNoteService)
    {
        this.staffNoteService = staffNoteService;
    }

}
