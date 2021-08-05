package com.higroup.Buda.restcontroller;

import com.higroup.Buda.entities.Picture;
import com.higroup.Buda.services.PictureService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
    @GetMapping(path = "{pictureID}")
    public Picture findPictureByPictureID(@PathVariable Long pictureID)
    {
        return this.pictureService.findPictureByPictureID(pictureID);
    }
    @PostMapping
    public ResponseEntity<?> saveNewPicture(Picture picture)
    {
        return this.pictureService.saveNewPicture(picture);
    }
}
