package com.higroup.Buda.api.store.create;

import javax.servlet.http.HttpServletRequest;

import com.higroup.Buda.entities.Store;
import com.higroup.Buda.util.Checker.RequestUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/store/create")
@CrossOrigin("*")
public class CreateStoreController {
    private final CreateStoreService createStoreService;
    private final RequestUtil requestUtil;
    @Autowired
    public CreateStoreController(CreateStoreService createStoreService, RequestUtil requestUtil) {
        this.createStoreService = createStoreService;
        this.requestUtil = requestUtil;
    }
    @PostMapping
    public ResponseEntity<?> createStore(HttpServletRequest httpServletRequest, @RequestBody Store store) {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.createStoreService.createStore(userID, store));
    }
}
