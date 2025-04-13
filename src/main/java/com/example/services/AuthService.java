package com.example.services;

import com.example.enums.UserType;
import com.example.models.Account;
import com.example.models.User;
import com.example.storages.UserStore;

import java.util.ArrayList;
import java.util.Objects;

public class AuthService {
    private UserStore userStore = UserStore.getInstance();

    public Account register(User user) {
        if (Objects.nonNull(userStore.getUser(user.getAccount().getUsername()))) {
            throw new IllegalArgumentException("Username already exists!");
        }

        User userObj = new User(user.getName(), user.getEmail(), user.getPhone(), new ArrayList<>());
        Account account = new Account(user.getAccount().getUsername(), user.getAccount().getPassword(), user, user.getAccount().getUserType(), new ArrayList<>());

        userObj.setAccount(account);
        userStore.addUser(userObj);
        return account;
    }

    public Account login(String username, String password) {
        Account account = userStore.getUser(username).getAccount();
        if (Objects.isNull(account) || !account.getPassword().equals(password)) {
            throw new IllegalArgumentException("Invalid credentials");
        }
        System.out.println("Login successful: " + username);
        return account;
    }

}
