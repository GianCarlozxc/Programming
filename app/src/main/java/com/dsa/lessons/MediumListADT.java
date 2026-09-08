package com.dsa.lessons;

import java.util.ArrayList;
import java.util.Scanner;

public class MediumListADT {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ArrayList<Integer> numbers = new ArrayList<>();

        System.out.print("Enter count of items: ");
        int n = input.nextInt();

        for (int i = 0; i < n; i++) {
            System.out.print("Enter item " + (i + 1) + ": ");
            numbers.add(input.nextInt());
        }

        System.out.print("Enter position to insert a new number: ");
        int pos = input.nextInt();
        System.out.print("Enter new value: ");
        int val = input.nextInt();

        numbers.add(pos, val);

        System.out.println("Updated List: " + numbers);
        System.out.println("List size: " + numbers.size());

        input.close();
    }
}
