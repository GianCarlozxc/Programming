package com.dsa.lessons;

public class AdvancedLinkedList {
    static class Node {
        String data;
        Node next;

        Node(String data) {
            this.data = data;
            this.next = null;
        }
    }

    public static void main(String[] args) {
        Node head = new Node("A");
        head.next = new Node("B");
        head.next.next = new Node("C");
        head.next.next.next = new Node("D");

        // Delete node "C" (Slide 32)
        Node curr = head;
        while (curr.next != null && !curr.next.data.equals("C")) {
            curr = curr.next;
        }
        if (curr.next != null) {
            curr.next = curr.next.next;
        }

        System.out.print("After Deleting 'C' (Slide 32): Head -> ");
        curr = head;
        while (curr != null) {
            System.out.print("[" + curr.data + "] -> ");
            curr = curr.next;
        }
        System.out.println("NULL");
    }
}
