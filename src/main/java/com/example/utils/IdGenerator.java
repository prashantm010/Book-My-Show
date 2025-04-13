package com.example.utils;

import java.util.UUID;

public class IdGenerator {
    public static String getId() {
        // Generate a unique ID for the object
        // This is a simple example; in a real application, you might want to use a more robust ID generation strategy
        return String.valueOf(UUID.randomUUID());
    }
}
