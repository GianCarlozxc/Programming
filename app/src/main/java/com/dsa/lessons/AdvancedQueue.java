package com.dsa.lessons;

public class AdvancedQueue {
    public static void main(String[] args) {
        String[] queue = new String[5];
        int front = 0, rear = -1, count = 0;

        // Enqueue passengers (Slide 12)
        queue[++rear] = "Passenger Jack"; count++;
        queue[++rear] = "Passenger Rose"; count++;
        queue[++rear] = "Passenger Ed";   count++;

        // Dequeue (FIFO)
        System.out.println("Bus Boarding Simulation (Slide 12):");
        while (count > 0) {
            System.out.println("Boarded bus: " + queue[front++]);
            count--;
        }
    }
}
