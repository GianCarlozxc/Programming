package com.dsa.lessons;

public class MediumHashing {
    public static void main(String[] args) {
        Integer[] hashTable = new Integer[10];
        int[] keys = {11, 12, 13, 14, 15};

        for (int key : keys) {
            hashTable[key % 10] = key;
        }

        System.out.println("Hash Table Contents (Slide 33):");
        for (int i = 0; i < hashTable.length; i++) {
            System.out.println("Index [" + i + "]: " + hashTable[i]);
        }
    }
}
