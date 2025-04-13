package com.example.services;

import com.example.enums.CommunicationChannel;
import com.example.interfaces.NotificationSender;
import com.example.models.Notification;
import com.example.models.User;

import java.util.HashMap;
import java.util.Map;

public class NotificationService {

    private Map<CommunicationChannel, NotificationSender> channelSenders = new HashMap<>();

    public NotificationService() {
        channelSenders.put(CommunicationChannel.EMAIL, new EmailSender());
        channelSenders.put(CommunicationChannel.SMS, new SMSSender());
        channelSenders.put(CommunicationChannel.PUSH, new PushSender());
    }

    public void notify(User user, Notification notification) {
        for (CommunicationChannel channel : user.getNotificationPreferences()) {
            channelSenders.get(channel).send(user, notification);
        }
    }
}