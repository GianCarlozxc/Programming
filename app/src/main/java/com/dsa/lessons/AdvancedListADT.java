package com.dsa.lessons;

public class AdvancedListADT {
    public static void main(String[] args) {
        Object[] data = new Object[10];
        int count = 0;

        // insert(element, pos)
        data[count++] = "Apple";
        data[count++] = "Banana";
        data[count++] = "Cherry";

        // insert at index 1
        for (int i = count; i > 1; i--) data[i] = data[i - 1];
        data[1] = "Orange";
        count++;

        // replace at index 2
        data[2] = "Blueberry";

        // remove at index 0
        for (int i = 0; i < count - 1; i++) data[i] = data[i + 1];
        data[--count] = null;

        System.out.println("Final List Elements (Slide 35):");
        for (int i = 0; i < count; i++) {
            System.out.print(data[i] + " ");
        }
        System.out.println("\nTotal Size: " + count);
    }
}
