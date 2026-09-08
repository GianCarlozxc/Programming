package com.dsa.lessons;

import java.util.Stack;

public class EasyStack {
    public static void main(String[] args) {
        Stack<String> plates = new Stack<>();

        plates.push("Plate 1");
        plates.push("Plate 2");
        plates.push("Plate 3");

        System.out.println("Top Plate: " + plates.peek());
        System.out.println("Popped: " + plates.pop());
        System.out.println("Current Top: " + plates.peek());
    }
}
