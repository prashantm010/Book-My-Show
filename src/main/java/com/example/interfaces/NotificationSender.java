package com.example.interfaces;

import com.example.models.Notification;
import com.example.models.User;

public interface NotificationSender {
    void send(User user, Notification notification);
}