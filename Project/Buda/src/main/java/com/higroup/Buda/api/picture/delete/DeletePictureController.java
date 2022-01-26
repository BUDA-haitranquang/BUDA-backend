package com.higroup.Buda.api.picture.delete;

import javax.servlet.http.HttpServletRequest;

import com.higroup.Buda.util.Checker.RequestUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/picture/delete")
@CrossOrigin("*")
public class DeletePictureController {
    private final DeletePictureService deletePictureService;
    private final RequestUtil requestUtil;
    @Autowired
    public DeletePictureController(DeletePictureService deletePictureService, RequestUtil requestUtil){
        this.requestUtil = requestUtil;
        this.deletePictureService = deletePictureService;
    }
    @DeleteMapping(path = "id/{pictureID}")
    public ResponseEntity<?> deletePictureByPictureID(HttpServletRequest httpServletRequest, 
    @PathVariable Long pictureID){
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        this.deletePictureService.deletePicture(userID, pictureID);
        return ResponseEntity.ok().body("Delete picture successfully");
    }
}
