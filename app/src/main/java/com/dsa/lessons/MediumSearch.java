package com.dsa.lessons;

import java.util.Scanner;

public class MediumSearch {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int[] sortedArray = {10, 20, 30, 40, 50, 60, 70, 80};

        System.out.print("Enter number to search in [10,20,30,40,50,60,70,80]: ");
        int target = input.hasNextInt() ? input.nextInt() : 50;

        int low = 0, high = sortedArray.length - 1;
        int foundIndex = -1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (sortedArray[mid] == target) {
                foundIndex = mid;
                break;
            } else if (target < sortedArray[mid]) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        if (foundIndex != -1) {
            System.out.println("Binary Search: Found " + target + " at index " + foundIndex);
        } else {
            System.out.println("Binary Search: " + target + " not found!");
        }

        input.close();
    }
}
