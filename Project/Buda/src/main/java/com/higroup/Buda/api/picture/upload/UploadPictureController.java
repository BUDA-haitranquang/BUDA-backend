package com.higroup.Buda.api.picture.upload;

import javax.servlet.http.HttpServletRequest;

import com.higroup.Buda.util.Checker.RequestUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/picture/upload")
@CrossOrigin(origins = "http://localhost:3000, http://143.198.194.24:3000")
public class UploadPictureController {
    private final UploadPictureService uploadPictureService;
    private final RequestUtil requestUtil;
    @Autowired
    public UploadPictureController(UploadPictureService uploadPictureService, RequestUtil requestUtil){
        this.requestUtil = requestUtil;
        this.uploadPictureService = uploadPictureService;
    }
    @PostMapping
    public ResponseEntity<?> uploadPicture(HttpServletRequest httpServletRequest, @RequestParam(value = "file") MultipartFile file)
    {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.uploadPictureService.uploadPicture(userID, file));
    }
}
