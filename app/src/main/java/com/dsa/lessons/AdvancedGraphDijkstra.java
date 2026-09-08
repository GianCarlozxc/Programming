package com.dsa.lessons;

import java.util.*;

public class AdvancedGraphDijkstra {
    static class Edge {
        String target;
        int weightKm;

        Edge(String target, int weightKm) {
            this.target = target;
            this.weightKm = weightKm;
        }
    }

    public static void main(String[] args) {
        Map<String, List<Edge>> graph = new HashMap<>();

        // Google Maps Routes from Slides 22-27
        graph.put("A", Arrays.asList(new Edge("C", 2), new Edge("B", 3)));
        graph.put("B", Arrays.asList(new Edge("A", 3), new Edge("D", 4)));
        graph.put("C", Arrays.asList(new Edge("A", 2), new Edge("D", 3), new Edge("E", 5)));
        graph.put("D", Arrays.asList(new Edge("B", 4), new Edge("C", 3), new Edge("F", 2)));
        graph.put("E", Arrays.asList(new Edge("C", 5), new Edge("F", 2)));
        graph.put("F", Arrays.asList(new Edge("D", 2), new Edge("E", 2)));

        Map<String, Integer> dist = new HashMap<>();
        for (String node : graph.keySet()) dist.put(node, Integer.MAX_VALUE);
        dist.put("A", 0);

        PriorityQueue<String> pq = new PriorityQueue<>(Comparator.comparingInt(dist::get));
        pq.add("A");

        while (!pq.isEmpty()) {
            String curr = pq.poll();
            for (Edge edge : graph.getOrDefault(curr, Collections.emptyList())) {
                int newDist = dist.get(curr) + edge.weightKm;
                if (newDist < dist.get(edge.target)) {
                    dist.put(edge.target, newDist);
                    pq.add(edge.target);
                }
            }
        }

        System.out.println("Google Maps Shortest Path (Slide 24):");
        System.out.println("Shortest Route: A -> C -> D -> F");
        System.out.println("Minimum Distance = " + dist.get("F") + " km (Path-3: 2 + 3 + 2 = 7 km)");
    }
}
