package com.higroup.Buda.services;

import com.higroup.Buda.repositories.PictureRepository;

import org.springframework.beans.factory.annotation.Autowired;

public class PictureService {
    private PictureRepository pictureRepository;
    
    @Autowired
    public PictureService(PictureRepository pictureRepository) {
        this.pictureRepository = pictureRepository;
    }

}
