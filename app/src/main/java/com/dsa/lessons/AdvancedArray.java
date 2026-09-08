package com.dsa.lessons;

import java.util.Scanner;

public class AdvancedArray {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter the number of elements: ");
        int size = input.nextInt();

        int[] numbers = new int[size];

        for (int i = 0; i < numbers.length; i++) {
            System.out.print("Enter number " + (i + 1) + ": ");
            numbers[i] = input.nextInt();
        }

        // Memory Address Offset Calculation (Slide 31)
        int baseAddress = 200;
        int elementSize = 4;
        System.out.println("\nMemory Addresses (Base = 200):");
        for (int i = 0; i < numbers.length; i++) {
            int address = baseAddress + (i * elementSize);
            System.out.println("Index [" + i + "] Address: " + address);
        }

        // Bubble Sort
        for (int i = 0; i < numbers.length - 1; i++) {
            for (int j = 0; j < numbers.length - 1 - i; j++) {
                if (numbers[j] > numbers[j + 1]) {
                    int temp = numbers[j];
                    numbers[j] = numbers[j + 1];
                    numbers[j + 1] = temp;
                }
            }
        }

        System.out.println("\nSorted Array:");
        for (int i = 0; i < numbers.length; i++) {
            System.out.print(numbers[i] + " ");
        }
        System.out.println();
        input.close();
    }
}
