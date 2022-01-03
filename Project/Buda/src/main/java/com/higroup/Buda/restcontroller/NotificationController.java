package com.higroup.Buda.restcontroller;

import javax.servlet.http.HttpServletRequest;

import com.higroup.Buda.services.NotificationService;
import com.higroup.Buda.util.Checker.RequestUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("api/notification")
public class NotificationController {
    private final NotificationService notificationService;
    private final RequestUtil requestUtil;
    @Autowired
    public NotificationController(NotificationService notificationService, RequestUtil requestUtil)
    {
        this.notificationService = notificationService;
        this.requestUtil = requestUtil;
    }
    @GetMapping("/all")
    public ResponseEntity<?> findAllNotificationByCurrentUser(HttpServletRequest httpServletRequest)
    {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.notificationService.findAllNotificationByUserID(userID));
    }
    @GetMapping("/all/new")
    public ResponseEntity<?> findAllPendingNotificationByCurrentUser(HttpServletRequest httpServletRequest)
    {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.notificationService.findAllPendingNotificationByUserID(userID));
    }
}
