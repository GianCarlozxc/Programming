package com.dsa.lessons;

import java.util.*;

public class GraphDijkstraLesson {

    public static class Edge {
        public String target;
        public int weightKm;

        public Edge(String target, int weightKm) {
            this.target = target;
            this.weightKm = weightKm;
        }
    }

    public static class Graph {
        private final Map<String, List<Edge>> adjacencyList = new LinkedHashMap<>();

        public void addEdge(String u, String v, int weight) {
            adjacencyList.computeIfAbsent(u, k -> new ArrayList<>()).add(new Edge(v, weight));
            adjacencyList.computeIfAbsent(v, k -> new ArrayList<>()).add(new Edge(u, weight));
        }

        public static class PathResult {
            public int totalDistanceKm;
            public List<String> path;
            public List<String> traceLog;

            public PathResult(int totalDistanceKm, List<String> path, List<String> traceLog) {
                this.totalDistanceKm = totalDistanceKm;
                this.path = path;
                this.traceLog = traceLog;
            }
        }

        public PathResult findShortestPath(String start, String dest) {
            Map<String, Integer> dist = new HashMap<>();
            Map<String, String> prev = new HashMap<>();
            PriorityQueue<Map.Entry<String, Integer>> pq =
                    new PriorityQueue<>(Comparator.comparingInt(Map.Entry::getValue));
            Set<String> visited = new HashSet<>();
            List<String> log = new ArrayList<>();

            for (String node : adjacencyList.keySet()) {
                dist.put(node, Integer.MAX_VALUE);
            }
            dist.put(start, 0);
            pq.offer(new java.util.AbstractMap.SimpleEntry<>(start, 0));

            log.add("Starting Dijkstra's algorithm at source: " + start);

            while (!pq.isEmpty()) {
                Map.Entry<String, Integer> cur = pq.poll();
                String u = cur.getKey();
                int d = cur.getValue();

                if (visited.contains(u)) continue;
                visited.add(u);

                log.add("Visiting node [" + u + "] with current minimum distance: " + d + " km");

                if (u.equals(dest)) {
                    log.add("Reached destination node [" + dest + "]!");
                    break;
                }

                for (Edge edge : adjacencyList.getOrDefault(u, Collections.emptyList())) {
                    if (!visited.contains(edge.target)) {
                        int newDist = d + edge.weightKm;
                        if (newDist < dist.getOrDefault(edge.target, Integer.MAX_VALUE)) {
                            dist.put(edge.target, newDist);
                            prev.put(edge.target, u);
                            pq.offer(new java.util.AbstractMap.SimpleEntry<>(edge.target, newDist));
                            log.add("  -> Updated route to [" + edge.target + "]: " +
                                    d + " + " + edge.weightKm + " = " + newDist + " km via [" + u + "]");
                        }
                    }
                }
            }

            List<String> path = new LinkedList<>();
            String step = dest;
            while (step != null) {
                path.add(0, step);
                step = prev.get(step);
            }

            return new PathResult(dist.getOrDefault(dest, -1), path, log);
        }
    }

    public static Graph buildSlideGoogleMap() {
        Graph g = new Graph();
        g.addEdge("A", "C", 2);
        g.addEdge("A", "B", 3);
        g.addEdge("B", "D", 4);
        g.addEdge("C", "D", 3);
        g.addEdge("C", "E", 5);
        g.addEdge("D", "F", 2);
        g.addEdge("E", "F", 2);
        return g;
    }

    public static String runDemonstration() {
        StringBuilder sb = new StringBuilder();
        sb.append("=========================================\n");
        sb.append("   LESSON 8: GRAPH & DIJKSTRA'S ALGO     \n");
        sb.append("=========================================\n\n");

        sb.append("[1] Social Media Graph (Slide 14):\n");
        sb.append("    Users are Nodes: Jack, Rose\n");
        sb.append("    Friendship is an Edge: (Jack) <---> (Rose)\n\n");

        sb.append("[2] Google Maps Shortest Path Problem (Slides 22-27):\n");
        sb.append("    Map Edges:\n");
        sb.append("      A - C (2 km), A - B (3 km)\n");
        sb.append("      B - D (4 km), C - D (3 km)\n");
        sb.append("      C - E (5 km), D - F (2 km), E - F (2 km)\n\n");

        Graph g = buildSlideGoogleMap();
        Graph.PathResult result = g.findShortestPath("A", "F");

        sb.append("Dijkstra Step-by-Step Trace:\n");
        for (String line : result.traceLog) {
            sb.append("  ").append(line).append("\n");
        }

        sb.append("\nFinal Result:\n");
        sb.append("  Shortest Route : ").append(String.join(" -> ", result.path)).append("\n");
        sb.append("  Total Distance : ").append(result.totalDistanceKm).append(" km (Matches Path-3 in Slide 24!)\n");
        sb.append("=========================================\n");
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(runDemonstration());
    }
}
