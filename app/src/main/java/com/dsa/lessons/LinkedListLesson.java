package com.dsa.lessons;

import java.util.Objects;

public class LinkedListLesson {

    public static class Node<T> {
        public T data;
        public Node<T> next;

        public Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    public static class SinglyLinkedList<T> {
        private Node<T> head;
        private int count;

        public SinglyLinkedList() {
            this.head = null;
            this.count = 0;
        }

        public void insertFirst(T data) {
            Node<T> node = new Node<>(data);
            node.next = head;
            head = node;
            count++;
        }

        public void insertLast(T data) {
            Node<T> node = new Node<>(data);
            if (head == null) {
                head = node;
            } else {
                Node<T> cur = head;
                while (cur.next != null) {
                    cur = cur.next;
                }
                cur.next = node;
            }
            count++;
        }

        public boolean delete(T data) {
            if (head == null) return false;
            if (Objects.equals(head.data, data)) {
                head = head.next;
                count--;
                return true;
            }
            Node<T> cur = head;
            while (cur.next != null && !Objects.equals(cur.next.data, data)) {
                cur = cur.next;
            }
            if (cur.next != null) {
                cur.next = cur.next.next;
                count--;
                return true;
            }
            return false;
        }

        public boolean contains(T data) {
            Node<T> cur = head;
            while (cur != null) {
                if (Objects.equals(cur.data, data)) return true;
                cur = cur.next;
            }
            return false;
        }

        public int size() {
            return count;
        }

        public boolean isEmpty() {
            return head == null;
        }

        public String printList() {
            if (head == null) return "Head -> NULL";
            StringBuilder sb = new StringBuilder("Head -> ");
            Node<T> cur = head;
            while (cur != null) {
                sb.append("[").append(cur.data).append("] -> ");
                cur = cur.next;
            }
            sb.append("NULL");
            return sb.toString();
        }
    }

    public static String runDemonstration() {
        StringBuilder sb = new StringBuilder();
        sb.append("=========================================\n");
        sb.append("        LESSON 5: LINKED LIST            \n");
        sb.append("=========================================\n\n");

        sb.append("[1] Constructing Linked List from Slide 32:\n");
        SinglyLinkedList<String> list = new SinglyLinkedList<>();
        list.insertLast("A");
        list.insertLast("B");
        list.insertLast("C");
        list.insertLast("D");

        sb.append("    List: ").append(list.printList()).append("\n");
        sb.append("    Total Nodes: ").append(list.size()).append("\n\n");

        sb.append("[2] Linked List Operations:\n");
        list.insertFirst("StartNode");
        sb.append("    After insertFirst('StartNode'):\n");
        sb.append("    ").append(list.printList()).append("\n");

        list.delete("C");
        sb.append("    After delete('C'):\n");
        sb.append("    ").append(list.printList()).append("\n");
        sb.append("    contains('B')? ").append(list.contains("B")).append("\n");
        sb.append("    contains('C')? ").append(list.contains("C")).append("\n");
        sb.append("=========================================\n");
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(runDemonstration());
    }
}
