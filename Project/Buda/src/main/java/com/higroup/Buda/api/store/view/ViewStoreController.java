package com.higroup.Buda.api.store.view;

import javax.servlet.http.HttpServletRequest;

import com.higroup.Buda.util.Checker.RequestUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/store/view")
@CrossOrigin("*")
public class ViewStoreController {
    private final ViewStoreService viewStoreService;
    private final RequestUtil requestUtil;
    @Autowired
    public ViewStoreController(ViewStoreService viewStoreService, RequestUtil requestUtil) {
        this.requestUtil = requestUtil;
        this.viewStoreService = viewStoreService;
    }
    @GetMapping(path = "/all")
    public ResponseEntity<?> findAllByUser(HttpServletRequest httpServletRequest) {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.viewStoreService.findAllStoresByUser(userID));
    }
}
