package com.dsa.lessons;

public class AdvancedHashing {
    public static void main(String[] args) {
        int capacity = 10;
        Integer[] table = new Integer[capacity];
        int[] keys = {11, 12, 13, 14, 15, 24};

        for (int key : keys) {
            int index = key % capacity;
            while (table[index] != null) {
                index = (index + 1) % capacity; // Linear Probing
            }
            table[index] = key;
            System.out.println("Key " + key + " mapped to index " + (key % capacity) + " -> placed in slot " + index);
        }

        System.out.println("\nFinal Hash Table with Linear Probing (Slide 33):");
        for (int i = 0; i < capacity; i++) {
            System.out.println("Slot [" + i + "] = " + table[i]);
        }
    }
}
