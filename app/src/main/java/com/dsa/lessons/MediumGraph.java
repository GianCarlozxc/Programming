package com.dsa.lessons;

import java.util.*;

public class MediumGraph {
    public static void main(String[] args) {
        // Map representation with Adjacency List
        Map<String, List<String>> graph = new HashMap<>();

        graph.put("A", Arrays.asList("B", "C"));
        graph.put("B", Arrays.asList("A", "D"));
        graph.put("C", Arrays.asList("A", "D", "E"));
        graph.put("D", Arrays.asList("B", "C", "F"));
        graph.put("E", Arrays.asList("C", "F"));
        graph.put("F", Arrays.asList("D", "E"));

        System.out.println("Locations and their Connected Roads (Slide 15):");
        for (String location : graph.keySet()) {
            System.out.println("Location [" + location + "] -> Roads to: " + graph.get(location));
        }
    }
}
