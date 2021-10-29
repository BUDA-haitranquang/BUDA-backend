package com.higroup.Buda.restcontroller;

import com.higroup.Buda.entities.Picture;
import com.higroup.Buda.services.PictureService;

import org.apache.catalina.connector.Response;
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
@RequestMapping("/api/picture")
public class PictureController 
{
    private final PictureService pictureService;
    
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
    public ResponseEntity<?> saveNewPicture(@RequestBody Picture picture)
    {
        return ResponseEntity.ok().body(this.pictureService.saveNewPicture(picture));
    }
    @PutMapping
    public ResponseEntity<?> updatePicture(@RequestBody Picture picture)
    {
        return ResponseEntity.ok().body(this.pictureService.updatePicture(picture));
    }
    @DeleteMapping(path = "{pictureID}")
    public ResponseEntity<?> deletePicture(@PathVariable Long pictureID)
    {
        this.pictureService.deletePicture(pictureID);
        return ResponseEntity.ok().body("Delete successfully");
    }
}
