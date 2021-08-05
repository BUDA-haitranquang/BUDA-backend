package com.higroup.Buda.services;

import com.higroup.Buda.repositories.StaffNoteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StaffNoteService {
    private final StaffNoteRepository staffNoteRepository;
    @Autowired
    public StaffNoteService(StaffNoteRepository staffNoteRepository)
    {
        this.staffNoteRepository = staffNoteRepository;
    }
}
