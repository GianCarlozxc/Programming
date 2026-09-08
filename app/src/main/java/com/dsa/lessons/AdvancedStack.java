package com.dsa.lessons;

import java.util.Scanner;

public class AdvancedStack {
    private char[] stack;
    private int top;

    public AdvancedStack(int capacity) {
        stack = new char[capacity];
        top = -1;
    }

    public void push(char c) {
        stack[++top] = c;
    }

    public char pop() {
        return stack[top--];
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter string to reverse (e.g. ABCD): ");
        String text = input.hasNextLine() ? input.nextLine() : "ABCD";

        AdvancedStack customStack = new AdvancedStack(text.length());

        for (int i = 0; i < text.length(); i++) {
            customStack.push(text.charAt(i));
        }

        StringBuilder reversed = new StringBuilder();
        while (!customStack.isEmpty()) {
            reversed.append(customStack.pop());
        }

        System.out.println("Original String: " + text);
        System.out.println("Reversed String: " + reversed.toString());
        input.close();
    }
}
