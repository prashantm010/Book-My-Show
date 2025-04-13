package com.example.models;

import com.example.enums.CommunicationChannel;
import com.example.utils.IdGenerator;
import lombok.Data;

import java.util.List;

@Data
public class User {
    String id;
    String name;
    String email;
    String phone;
    Account account;
    List<CommunicationChannel> notificationPreferences;

    public User(String name, String email, String phone, List<CommunicationChannel> notificationPreferences) {
        this.id = IdGenerator.getId();
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.notificationPreferences = notificationPreferences;
    }
}
