package com.dsa.lessons;

import java.util.Arrays;

public class SearchSortLesson {

    public static class Book {
        public String title;
        public int heightCm;

        public Book(String title, int heightCm) {
            this.title = title;
            this.heightCm = heightCm;
        }

        @Override
        public String toString() {
            return title + " (" + heightCm + "cm)";
        }
    }

    public static void bubbleSortBooks(Book[] books) {
        int n = books.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (books[j].heightCm > books[j + 1].heightCm) {
                    Book temp = books[j];
                    books[j] = books[j + 1];
                    books[j + 1] = temp;
                }
            }
        }
    }

    public static void selectionSortBooks(Book[] books) {
        int n = books.length;
        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < n; j++) {
                if (books[j].heightCm < books[minIdx].heightCm) {
                    minIdx = j;
                }
            }
            Book temp = books[minIdx];
            books[minIdx] = books[i];
            books[i] = temp;
        }
    }

    public static int linearSearch(char[] arr, char target, StringBuilder log) {
        for (int i = 0; i < arr.length; i++) {
            if (log != null) {
                log.append("  Step ").append(i + 1).append(": Checked index ").append(i)
                   .append(" ['").append(arr[i]).append("']\n");
            }
            if (arr[i] == target) {
                if (log != null) {
                    log.append("  --> Found '").append(target).append("' at index ").append(i).append("!\n");
                }
                return i;
            }
        }
        return -1;
    }

    public static int binarySearch(char[] arr, char target, StringBuilder log) {
        int low = 0;
        int high = arr.length - 1;
        int step = 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (log != null) {
                log.append("  Step ").append(step++).append(": Subarray range [")
                   .append(arr[low]).append(" .. ").append(arr[high]).append("], Middle element is '")
                   .append(arr[mid]).append("' (index ").append(mid).append(")\n");
            }

            if (arr[mid] == target) {
                if (log != null) {
                    log.append("  --> Match found! Book '").append(target).append("' located.\n");
                }
                return mid;
            } else if (target < arr[mid]) {
                if (log != null) {
                    log.append("  --> '").append(target).append("' lies BEFORE '")
                       .append(arr[mid]).append("'. Halving to left subarray.\n");
                }
                high = mid - 1;
            } else {
                if (log != null) {
                    log.append("  --> '").append(target).append("' lies AFTER '")
                       .append(arr[mid]).append("'. Halving to right subarray.\n");
                }
                low = mid + 1;
            }
        }
        return -1;
    }

    public static String runDemonstration() {
        StringBuilder sb = new StringBuilder();
        sb.append("=========================================\n");
        sb.append("   LESSON 7: SEARCHING & SORTING         \n");
        sb.append("=========================================\n\n");

        char[] alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        char target = 'J';

        sb.append("[1] Binary Search Problem from Slides 19-21:\n");
        sb.append("    Array: A B C D E F G H I J K L M N O P Q R S T U V W X Y Z\n");
        sb.append("    Target: Search for Book starting with '").append(target).append("'\n\n");

        StringBuilder binLog = new StringBuilder("Binary Search for 'J':\n");
        binarySearch(alpha, target, binLog);
        sb.append(binLog).append("\n");

        sb.append("[2] Comparing with Linear Search:\n");
        StringBuilder linLog = new StringBuilder("Linear Search for 'J':\n");
        linearSearch(alpha, target, linLog);
        sb.append(linLog).append("\n");

        sb.append("[3] Book Shelf Sorting by Height (Slide 16):\n");
        Book[] shelf = {
            new Book("History", 28),
            new Book("Math", 22),
            new Book("Science", 32),
            new Book("Literature", 19),
            new Book("Art", 25)
        };
        sb.append("    Unsorted Shelf: ").append(Arrays.toString(shelf)).append("\n");
        bubbleSortBooks(shelf);
        sb.append("    Sorted by Height (Ascending): ").append(Arrays.toString(shelf)).append("\n");
        sb.append("=========================================\n");
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(runDemonstration());
    }
}
