package com.entregable1_2_3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserService {
    public UserService() {
    }
    @Autowired
    NotificationService notification;
}
