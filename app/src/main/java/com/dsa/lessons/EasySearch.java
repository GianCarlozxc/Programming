package com.dsa.lessons;

public class EasySearch {
    public static void main(String[] args) {
        char[] shelf = {'A', 'B', 'C', 'D', 'J', 'K'};
        char target = 'J';

        for (int i = 0; i < shelf.length; i++) {
            if (shelf[i] == target) {
                System.out.println("Linear Search (Slide 18): Found '" + target + "' at index " + i);
                break;
            }
        }
    }
}
