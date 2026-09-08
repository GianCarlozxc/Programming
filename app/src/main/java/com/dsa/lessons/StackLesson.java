package com.dsa.lessons;

import java.util.NoSuchElementException;
import java.util.Objects;

public class StackLesson {

    public static class CustomStack<T> {
        private final Object[] elements;
        private int top;
        private final int capacity;

        public CustomStack(int capacity) {
            this.capacity = capacity;
            this.elements = new Object[capacity];
            this.top = -1;
        }

        public void push(T element) {
            if (isFull()) throw new IllegalStateException("Stack is full");
            elements[++top] = element;
        }

        @SuppressWarnings("unchecked")
        public T pop() {
            if (isEmpty()) throw new NoSuchElementException("Stack is empty");
            T val = (T) elements[top];
            elements[top--] = null;
            return val;
        }

        @SuppressWarnings("unchecked")
        public T peek() {
            if (isEmpty()) throw new NoSuchElementException("Stack is empty");
            return (T) elements[top];
        }

        public int search(T element) {
            for (int i = top; i >= 0; i--) {
                if (Objects.equals(elements[i], element)) {
                    return top - i + 1;
                }
            }
            return -1;
        }

        public int size() {
            return top + 1;
        }

        public boolean isEmpty() {
            return top == -1;
        }

        public boolean isFull() {
            return top == capacity - 1;
        }

        public String printState() {
            if (isEmpty()) return "[]";
            StringBuilder sb = new StringBuilder("[Bottom] ");
            for (int i = 0; i <= top; i++) {
                sb.append(elements[i]);
                if (i < top) sb.append(" -> ");
            }
            sb.append(" [Top]");
            return sb.toString();
        }
    }

    public static String reverseString(String input) {
        if (input == null) return null;
        CustomStack<Character> stack = new CustomStack<>(input.length() + 1);
        for (char c : input.toCharArray()) {
            stack.push(c);
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.toString();
    }

    public static String runDemonstration() {
        StringBuilder sb = new StringBuilder();
        sb.append("=========================================\n");
        sb.append("       LESSON 2: STACK ADT (LIFO)        \n");
        sb.append("=========================================\n\n");

        sb.append("[1] Stack Operations Demo (Capacity = 5):\n");
        CustomStack<String> stack = new CustomStack<>(5);
        sb.append("    isEmpty()? ").append(stack.isEmpty()).append("\n");
        sb.append("    Pushing 'Plate 1', 'Plate 2', 'Plate 3'...\n");
        stack.push("Plate 1");
        stack.push("Plate 2");
        stack.push("Plate 3");
        sb.append("    Current Stack: ").append(stack.printState()).append("\n");
        sb.append("    Stack size(): ").append(stack.size()).append("\n");
        sb.append("    Stack peek(): ").append(stack.peek()).append(" (top element)\n");
        sb.append("    search('Plate 2'): ").append(stack.search("Plate 2")).append(" position(s) from top\n");
        sb.append("    Popped item: ").append(stack.pop()).append("\n");
        sb.append("    After pop: ").append(stack.printState()).append("\n\n");

        sb.append("[2] Real-World Example from Slide 8-11 (Reverse String):\n");
        String original = "ABCD";
        sb.append("    Original Input  : ").append(original).append("\n");
        sb.append("    Pushed to stack : 'A', 'B', 'C', 'D'\n");
        sb.append("    Popped in order : 'D', 'C', 'B', 'A'\n");
        sb.append("    Reversed Output : ").append(reverseString(original)).append("\n");
        sb.append("=========================================\n");
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(runDemonstration());
    }
}
