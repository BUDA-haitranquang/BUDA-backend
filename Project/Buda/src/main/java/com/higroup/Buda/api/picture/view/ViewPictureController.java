package com.higroup.Buda.api.picture.view;

import javax.servlet.http.HttpServletRequest;

import com.higroup.Buda.entities.Picture;
import com.higroup.Buda.util.Checker.RequestUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/picture/view")
@CrossOrigin("*")
public class ViewPictureController {
    private final ViewPictureService viewPictureService;
    private final RequestUtil requestUtil;
    @Autowired
    public ViewPictureController(RequestUtil requestUtil, ViewPictureService viewPictureService){
        this.requestUtil = requestUtil;
        this.viewPictureService = viewPictureService;
    }
    @GetMapping(path = "id/{pictureID}")
    public Picture findPictureByPictureID(HttpServletRequest httpServletRequest, @PathVariable Long pictureID)
    {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return this.viewPictureService.findPictureByPictureID(userID, pictureID);
    }
    
}
