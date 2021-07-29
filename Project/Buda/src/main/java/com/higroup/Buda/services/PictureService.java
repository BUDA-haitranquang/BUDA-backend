package com.higroup.Buda.services;

import com.higroup.Buda.repositories.PictureRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PictureService {
    private PictureRepository pictureRepository;
    
    @Autowired
    public PictureService(PictureRepository pictureRepository) {
        this.pictureRepository = pictureRepository;
    }

}
