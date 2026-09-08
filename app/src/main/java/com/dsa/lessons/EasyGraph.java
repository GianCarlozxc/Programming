package com.dsa.lessons;

import java.util.*;

public class EasyGraph {
    public static void main(String[] args) {
        // Social Media Friendship Graph (Slide 14)
        Map<String, List<String>> network = new HashMap<>();

        network.put("Jack", Arrays.asList("Rose"));
        network.put("Rose", Arrays.asList("Jack", "Ed"));

        System.out.println("Social Network Connections (Slide 14):");
        System.out.println("Jack's Friends: " + network.get("Jack"));
        System.out.println("Rose's Friends: " + network.get("Rose"));
    }
}
