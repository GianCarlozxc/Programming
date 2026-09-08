package com.dsa.lessons;

public class AdvancedSearch {
    public static void main(String[] args) {
        char[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        char target = 'J';

        int low = 0;
        int high = alphabet.length - 1;
        int step = 1;

        System.out.println("Binary Search Step-by-Step for '" + target + "' (Slides 19-21):");
        while (low <= high) {
            int mid = low + (high - low) / 2;
            System.out.println("Step " + step++ + ": Range [" + alphabet[low] + " .. " + alphabet[high] + "], Mid = '" + alphabet[mid] + "'");

            if (alphabet[mid] == target) {
                System.out.println("--> Match found! Target '" + target + "' located at index " + mid + "!");
                break;
            } else if (target < alphabet[mid]) {
                System.out.println("    '" + target + "' lies before '" + alphabet[mid] + "', moving to left half.");
                high = mid - 1;
            } else {
                System.out.println("    '" + target + "' lies after '" + alphabet[mid] + "', moving to right half.");
                low = mid + 1;
            }
        }
    }
}
