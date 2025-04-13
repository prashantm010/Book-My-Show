package com.example.services;

import com.example.interfaces.NotificationSender;
import com.example.models.Notification;
import com.example.models.User;

public class SMSSender implements NotificationSender {
    public void send(User user, Notification notification) {
        System.out.println("Sending SMS to " + user.getPhone());
    }
}