package com.example.storages;// datastore/ShowStore.java

import com.example.models.User;

import java.util.HashMap;
import java.util.Map;

public class UserStore {

    private static UserStore instance = null;
    private Map<String, User> userMap;

    private UserStore() {
        userMap = new HashMap<>();
    }

    public static synchronized UserStore getInstance() {
        if (instance == null) {
            instance = new UserStore();
        }
        return instance;
    }

    public void addUser(User user) {
        if (userMap.containsKey(user.getAccount().getUsername())) {
            throw new IllegalArgumentException("User already exists.");
        }
        userMap.put(user.getId(), user);
    }

    public User getUser(String userName) {
        return userMap.get(userName);
    }

    public void removeUser(String userName) {
        if (!userMap.containsKey(userName)) {
            throw new IllegalArgumentException("User does not exist.");
        }
        userMap.remove(userName);
    }
}
