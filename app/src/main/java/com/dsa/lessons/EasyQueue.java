package com.dsa.lessons;

import java.util.LinkedList;
import java.util.Queue;

public class EasyQueue {
    public static void main(String[] args) {
        Queue<String> busQueue = new LinkedList<>();

        busQueue.add("Passenger Jack");
        busQueue.add("Passenger Rose");
        busQueue.add("Passenger Ed");

        System.out.println("First in line: " + busQueue.peek());
        System.out.println("Boarded bus: " + busQueue.poll());
        System.out.println("Next in line: " + busQueue.peek());
    }
}
