package com.dsa.lessons;

import java.util.ArrayList;

public class EasyListADT {
    public static void main(String[] args) {
        ArrayList<String> fruits = new ArrayList<>();

        fruits.add("Apple");
        fruits.add("Banana");
        fruits.add("Cherry");

        System.out.println("Element at index 1: " + fruits.get(1));
        fruits.set(1, "Blueberry");
        fruits.remove("Apple");

        System.out.println("List: " + fruits);
    }
}
