package com.higroup.Buda.restcontroller;

import com.higroup.Buda.services.PictureService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/picture")
public class PictureController 
{
    private PictureService pictureService;
    
    @Autowired
    public PictureController(PictureService pictureService) {
        this.pictureService = pictureService;
    }

}
