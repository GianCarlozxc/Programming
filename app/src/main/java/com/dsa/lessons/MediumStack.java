package com.dsa.lessons;

import java.util.Scanner;
import java.util.Stack;

public class MediumStack {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Stack<Integer> stack = new Stack<>();

        System.out.print("How many numbers to push? ");
        int count = input.nextInt();

        for (int i = 0; i < count; i++) {
            System.out.print("Enter value " + (i + 1) + ": ");
            stack.push(input.nextInt());
        }

        System.out.println("\nPopping all elements (LIFO Order):");
        while (!stack.isEmpty()) {
            System.out.print(stack.pop() + " ");
        }
        System.out.println();
        input.close();
    }
}
