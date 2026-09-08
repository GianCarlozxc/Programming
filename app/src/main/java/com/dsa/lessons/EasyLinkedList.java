package com.dsa.lessons;

import java.util.LinkedList;

public class EasyLinkedList {
    public static void main(String[] args) {
        LinkedList<String> list = new LinkedList<>();

        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");

        System.out.println("Linked List: " + list);
        System.out.println("Head Node: " + list.getFirst());
        System.out.println("Tail Node: " + list.getLast());
    }
}
