package com.dsa.lessons;

public class EasyHashing {
    public static void main(String[] args) {
        int[] values = {11, 12, 13, 14, 15};

        System.out.println("Hash Function: H(x) = x % 10 (Slide 33)");
        for (int val : values) {
            int index = val % 10;
            System.out.println("Value: " + val + " -> Index: " + index);
        }
    }
}
