package com.higroup.Buda.api.store.update;

import java.lang.reflect.InvocationTargetException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.higroup.Buda.entities.Store;
import com.higroup.Buda.util.Checker.RequestUtil;

@RestController
@RequestMapping("api/store/update")
@CrossOrigin("*")
public class UpdateStoreController {
    private final UpdateStoreService updateStoreService;
    private final RequestUtil requestUtil;

    @Autowired
    public UpdateStoreController(UpdateStoreService updateStoreService, RequestUtil requestUtil) {
        this.updateStoreService = updateStoreService;
        this.requestUtil = requestUtil;
    }

    @PutMapping
    public ResponseEntity<?> updateStoreByID(@RequestBody Store store, HttpServletRequest httpServletRequest)
            throws IllegalAccessException, InvocationTargetException {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok(this.updateStoreService.updateStore(userID, store));
    }
}
