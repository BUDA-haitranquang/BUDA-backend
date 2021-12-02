package com.higroup.Buda.restcontroller;

import javax.servlet.http.HttpServletRequest;

import com.higroup.Buda.entities.Picture;
import com.higroup.Buda.services.PictureService;
import com.higroup.Buda.util.Checker.RequestUtil;

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
    private final RequestUtil requestUtil;
    
    @Autowired
    public PictureController(PictureService pictureService, RequestUtil requestUtil) {
        this.requestUtil = requestUtil;
        this.pictureService = pictureService;
    }
    @GetMapping(path = "{pictureID}")
    public Picture findPictureByPictureID(HttpServletRequest httpServletRequest, @PathVariable Long pictureID)
    {
        Long userID = this.requestUtil.getUserID(httpServletRequest);
        return this.pictureService.findPictureByPictureID(userID, pictureID);
    }
    @PostMapping
    public ResponseEntity<?> saveNewPicture(HttpServletRequest httpServletRequest, @RequestBody Picture picture)
    {
        Long userID = this.requestUtil.getUserID(httpServletRequest);
        return ResponseEntity.ok().body(this.pictureService.saveNewPicture(userID, picture));
    }
    @PutMapping
    public ResponseEntity<?> updatePicture(HttpServletRequest httpServletRequest, @RequestBody Picture picture)
    {
        Long userID = this.requestUtil.getUserID(httpServletRequest);
        return ResponseEntity.ok().body(this.pictureService.updatePicture(userID, picture));
    }
    @DeleteMapping(path = "{pictureID}")
    public ResponseEntity<?> deletePicture(HttpServletRequest httpServletRequest, @PathVariable Long pictureID)
    {
        Long userID = this.requestUtil.getUserID(httpServletRequest);
        this.pictureService.deletePicture(userID, pictureID);
        return ResponseEntity.ok().body("Delete successfully");
    }
}
