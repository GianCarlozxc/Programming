package com.dsa.lessons;

import java.util.*;

/**
 * ============================================================================
 * DATA STRUCTURES AND ALGORITHMS (DSA) - COMPLETE WEEK 2 LESSON SOLUTIONS
 * Reference: "DSA Week 2.pdf" (Prepared by: Ed Dela Cruz Jr. MIT)
 * Platform: Android Studio / Java SE
 * ============================================================================
 */
public class DSAMasterLesson {




    public static class ArrayLesson {

    public static int calculateMemoryAddress(int baseAddress, int index, int elementSizeBytes) {
        return baseAddress + (index * elementSizeBytes);
    }

    public static int[] insertAt(int[] original, int index, int element) {
        if (index < 0 || index > original.length) {
            throw new IndexOutOfBoundsException("Index: " + index);
        }
        int[] result = new int[original.length + 1];
        for (int i = 0; i < index; i++) result[i] = original[i];
        result[index] = element;
        for (int i = index; i < original.length; i++) result[i + 1] = original[i];
        return result;
    }

    public static int[] removeAt(int[] original, int index) {
        if (index < 0 || index >= original.length) {
            throw new IndexOutOfBoundsException("Index: " + index);
        }
        int[] result = new int[original.length - 1];
        for (int i = 0, j = 0; i < original.length; i++) {
            if (i != index) result[j++] = original[i];
        }
        return result;
    }

    public static int[] findMinMax(int[] arr) {
        if (arr == null || arr.length == 0) return new int[]{0, 0};
        int min = arr[0], max = arr[0];
        for (int x : arr) {
            if (x < min) min = x;
            if (x > max) max = x;
        }
        return new int[]{min, max};
    }

    public static void reverse(int[] arr) {
        int left = 0, right = arr.length - 1;
        while (left < right) {
            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left++;
            right--;
        }
    }

    public static int sum2DMatrix(int[][] matrix) {
        int sum = 0;
        for (int[] row : matrix) {
            for (int val : row) sum += val;
        }
        return sum;
    }

    public static String runDemonstration() {
        StringBuilder sb = new StringBuilder();
        sb.append("=========================================\n");
        sb.append("      LESSON 1: ARRAY DATA STRUCTURE     \n");
        sb.append("=========================================\n\n");

        sb.append("[1] Memory Offset Calculation (Slide 31):\n");
        int baseAddress = 200;
        sb.append("    Base Memory Address = ").append(baseAddress).append("\n");
        for (int i = 0; i <= 4; i++) {
            int addr = calculateMemoryAddress(baseAddress, i, 4);
            sb.append("    Index [").append(i).append("] -> Address: ").append(addr).append("\n");
        }

        int[] arr = {10, 25, 40, 55, 70};
        sb.append("\n[2] Initial Array: ").append(Arrays.toString(arr)).append("\n");

        arr = insertAt(arr, 2, 99);
        sb.append("    After inserting 99 at index 2: ").append(Arrays.toString(arr)).append("\n");

        arr = removeAt(arr, 4);
        sb.append("    After removing element at index 4: ").append(Arrays.toString(arr)).append("\n");

        int[] minMax = findMinMax(arr);
        sb.append("    Minimum = ").append(minMax[0]).append(", Maximum = ").append(minMax[1]).append("\n");

        reverse(arr);
        sb.append("    Reversed Array: ").append(Arrays.toString(arr)).append("\n\n");

        int[][] matrix = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        sb.append("[3] 2D Matrix:\n");
        for (int[] row : matrix) {
            sb.append("    ").append(Arrays.toString(row)).append("\n");
        }
        sb.append("    Total Matrix Sum = ").append(sum2DMatrix(matrix)).append("\n");
        sb.append("=========================================\n");
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(runDemonstration());
    }
}






    public static class StackLesson {

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





    public static class QueueLesson {

    public static class CustomQueue<T> {
        private final Object[] elements;
        private int front;
        private int rear;
        private int count;
        private final int capacity;

        public CustomQueue(int capacity) {
            this.capacity = capacity;
            this.elements = new Object[capacity];
            this.front = 0;
            this.rear = -1;
            this.count = 0;
        }

        public void enqueue(T item) {
            if (isFull()) throw new IllegalStateException("Queue is full");
            rear = (rear + 1) % capacity;
            elements[rear] = item;
            count++;
        }

        @SuppressWarnings("unchecked")
        public T dequeue() {
            if (isEmpty()) throw new NoSuchElementException("Queue is empty");
            T val = (T) elements[front];
            elements[front] = null;
            front = (front + 1) % capacity;
            count--;
            return val;
        }

        @SuppressWarnings("unchecked")
        public T peek() {
            if (isEmpty()) throw new NoSuchElementException("Queue is empty");
            return (T) elements[front];
        }

        public int size() {
            return count;
        }

        public boolean isEmpty() {
            return count == 0;
        }

        public boolean isFull() {
            return count == capacity;
        }

        public String printQueue() {
            if (isEmpty()) return "[]";
            StringBuilder sb = new StringBuilder("[Front] ");
            for (int i = 0; i < count; i++) {
                int idx = (front + i) % capacity;
                sb.append(elements[idx]);
                if (i < count - 1) sb.append(" <- ");
            }
            sb.append(" [Rear]");
            return sb.toString();
        }
    }

    public static String simulatePrinterSpooler() {
        StringBuilder sb = new StringBuilder();
        CustomQueue<String> queue = new CustomQueue<>(10);
        sb.append("Printer receives print requests:\n");

        String[] jobs = {
            "Employee1_Report.docx",
            "Employee2_Invoice.pdf",
            "Employee3_Spreadsheet.xlsx",
            "Employee4_Presentation.pptx"
        };

        for (String job : jobs) {
            queue.enqueue(job);
            sb.append("  + Enqueued: ").append(job)
              .append(" (Pending jobs: ").append(queue.size()).append(")\n");
        }

        sb.append("\nPrinter starts processing jobs in FIFO order:\n");
        while (!queue.isEmpty()) {
            sb.append("  * Printing: ").append(queue.dequeue()).append(" [Completed]\n");
        }
        return sb.toString();
    }

    public static String runDemonstration() {
        StringBuilder sb = new StringBuilder();
        sb.append("=========================================\n");
        sb.append("       LESSON 3: QUEUE ADT (FIFO)        \n");
        sb.append("=========================================\n\n");

        sb.append("[1] Bus Boarding Queue Simulation (Slide 12):\n");
        CustomQueue<String> busQueue = new CustomQueue<>(5);
        busQueue.enqueue("Passenger Jack");
        busQueue.enqueue("Passenger Rose");
        busQueue.enqueue("Passenger Ed");

        sb.append("    Queue state: ").append(busQueue.printQueue()).append("\n");
        sb.append("    First to board bus (peek): ").append(busQueue.peek()).append("\n");
        sb.append("    Boarded bus (dequeue): ").append(busQueue.dequeue()).append("\n");
        sb.append("    Queue state now: ").append(busQueue.printQueue()).append("\n\n");

        sb.append("[2] Office Network Printer Simulation (Slide 13):\n");
        sb.append(simulatePrinterSpooler());
        sb.append("=========================================\n");
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(runDemonstration());
    }
}





    public static class ListADTLesson {

    public interface ListADT<T> {
        T get(int position);
        void insert(T element, int position);
        boolean remove(T element);
        T removeAt(int position);
        void replace(int position, T element);
        int size();
        boolean isEmpty();
        boolean isFull();
    }

    public static class CustomArrayList<T> implements ListADT<T> {
        private final Object[] data;
        private int count;
        private final int capacity;

        public CustomArrayList(int capacity) {
            this.capacity = capacity;
            this.data = new Object[capacity];
            this.count = 0;
        }

        private void checkIndex(int position) {
            if (position < 0 || position >= count) {
                throw new IndexOutOfBoundsException("Invalid index: " + position);
            }
        }

        @Override
        @SuppressWarnings("unchecked")
        public T get(int position) {
            checkIndex(position);
            return (T) data[position];
        }

        @Override
        public void insert(T element, int position) {
            if (isFull()) throw new IllegalStateException("List is full");
            if (position < 0 || position > count) {
                throw new IndexOutOfBoundsException("Invalid position: " + position);
            }
            for (int i = count; i > position; i--) {
                data[i] = data[i - 1];
            }
            data[position] = element;
            count++;
        }

        @Override
        public boolean remove(T element) {
            for (int i = 0; i < count; i++) {
                if (Objects.equals(data[i], element)) {
                    removeAt(i);
                    return true;
                }
            }
            return false;
        }

        @Override
        @SuppressWarnings("unchecked")
        public T removeAt(int position) {
            checkIndex(position);
            T val = (T) data[position];
            for (int i = position; i < count - 1; i++) {
                data[i] = data[i + 1];
            }
            data[count - 1] = null;
            count--;
            return val;
        }

        @Override
        public void replace(int position, T element) {
            checkIndex(position);
            data[position] = element;
        }

        @Override
        public int size() {
            return count;
        }

        @Override
        public boolean isEmpty() {
            return count == 0;
        }

        @Override
        public boolean isFull() {
            return count == capacity;
        }

        public String printList() {
            StringBuilder sb = new StringBuilder("[");
            for (int i = 0; i < count; i++) {
                sb.append(data[i]);
                if (i < count - 1) sb.append(", ");
            }
            sb.append("]");
            return sb.toString();
        }
    }

    public static String runDemonstration() {
        StringBuilder sb = new StringBuilder();
        sb.append("=========================================\n");
        sb.append("         LESSON 4: LIST ADT              \n");
        sb.append("=========================================\n\n");

        CustomArrayList<String> list = new CustomArrayList<>(10);
        sb.append("Initial state:\n");
        sb.append("  isEmpty(): ").append(list.isEmpty()).append("\n");
        sb.append("  size(): ").append(list.size()).append("\n\n");

        sb.append("Performing operations:\n");
        list.insert("Apple", 0);
        list.insert("Banana", 1);
        list.insert("Cherry", 2);
        sb.append("  After 3 inserts: ").append(list.printList()).append("\n");

        list.insert("Orange", 1);
        sb.append("  After inserting 'Orange' at position 1: ").append(list.printList()).append("\n");
        sb.append("  get(2): ").append(list.get(2)).append("\n");

        list.replace(2, "Blueberry");
        sb.append("  After replace(2, 'Blueberry'): ").append(list.printList()).append("\n");

        list.remove("Apple");
        sb.append("  After remove('Apple'): ").append(list.printList()).append("\n");

        String removed = list.removeAt(1);
        sb.append("  After removeAt(1) [removed '").append(removed).append("']: ").append(list.printList()).append("\n");
        sb.append("  Current size: ").append(list.size()).append("\n");
        sb.append("  isFull(): ").append(list.isFull()).append("\n");
        sb.append("=========================================\n");
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(runDemonstration());
    }
}





    public static class LinkedListLesson {

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



    public static class HashingLesson {

    public static class HashTable {
        private final Integer[] table;
        private final int capacity;

        public HashTable(int capacity) {
            this.capacity = capacity;
            this.table = new Integer[capacity];
        }

        public int hashFunction(int key) {
            return key % capacity;
        }

        public boolean insert(int key) {
            int idx = hashFunction(key);
            for (int i = 0; i < capacity; i++) {
                int p = (idx + i) % capacity;
                if (table[p] == null) {
                    table[p] = key;
                    return true;
                }
            }
            return false;
        }

        public int search(int key) {
            int idx = hashFunction(key);
            for (int i = 0; i < capacity; i++) {
                int p = (idx + i) % capacity;
                if (table[p] == null) return -1;
                if (table[p] == key) return p;
            }
            return -1;
        }

        public Integer[] getTable() {
            return table;
        }

        public String printTable() {
            StringBuilder sb = new StringBuilder();
            sb.append("Index  : ");
            for (int i = 0; i < capacity; i++) {
                sb.append(String.format("[%2d] ", i));
            }
            sb.append("\nValues : ");
            for (int i = 0; i < capacity; i++) {
                if (table[i] == null) {
                    sb.append("[  ] ");
                } else {
                    sb.append(String.format("[%2d] ", table[i]));
                }
            }
            sb.append("\n");
            return sb.toString();
        }
    }

    public static String runDemonstration() {
        StringBuilder sb = new StringBuilder();
        sb.append("=========================================\n");
        sb.append("    LESSON 6: HASHING DATA STRUCTURE     \n");
        sb.append("=========================================\n\n");

        sb.append("[1] Direct Slide 33 Problem Solution:\n");
        sb.append("    Hash Function: H(x) = x % 10\n");
        sb.append("    Given Values: [11, 12, 13, 14, 15]\n\n");

        HashTable ht = new HashTable(10);
        int[] vals = {11, 12, 13, 14, 15};
        for (int v : vals) {
            ht.insert(v);
            sb.append("    H(").append(v).append(") = ").append(v).append(" % 10 = index ").append(ht.hashFunction(v)).append("\n");
        }

        sb.append("\n    Resulting Hash Table:\n");
        sb.append(ht.printTable());

        sb.append("\n[2] O(1) Search via Hashing:\n");
        int key = 14;
        sb.append("    Search key ").append(key).append(" -> H(").append(key).append(") = index ")
          .append(ht.search(key)).append(" (Found!)\n\n");

        sb.append("[3] Collision Resolution (Linear Probing):\n");
        sb.append("    Inserting value 24: H(24) = 24 % 10 = index 4 (Already occupied by 14!)\n");
        ht.insert(24);
        sb.append("    Linear Probing placed 24 into next free slot: index ").append(ht.search(24)).append("\n");
        sb.append("    Table state:\n");
        sb.append(ht.printTable());
        sb.append("=========================================\n");
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(runDemonstration());
    }
}





    public static class SearchSortLesson {

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



    public static class GraphDijkstraLesson {

    public static class Edge {
        public String target;
        public int weightKm;

        public Edge(String target, int weightKm) {
            this.target = target;
            this.weightKm = weightKm;
        }
    }

    public static class Graph {
        private final Map<String, List<Edge>> adjacencyList = new LinkedHashMap<>();

        public void addEdge(String u, String v, int weight) {
            adjacencyList.computeIfAbsent(u, k -> new ArrayList<>()).add(new Edge(v, weight));
            adjacencyList.computeIfAbsent(v, k -> new ArrayList<>()).add(new Edge(u, weight));
        }

        public static class PathResult {
            public int totalDistanceKm;
            public List<String> path;
            public List<String> traceLog;

            public PathResult(int totalDistanceKm, List<String> path, List<String> traceLog) {
                this.totalDistanceKm = totalDistanceKm;
                this.path = path;
                this.traceLog = traceLog;
            }
        }

        public PathResult findShortestPath(String start, String dest) {
            Map<String, Integer> dist = new HashMap<>();
            Map<String, String> prev = new HashMap<>();
            PriorityQueue<Map.Entry<String, Integer>> pq =
                    new PriorityQueue<>(Comparator.comparingInt(Map.Entry::getValue));
            Set<String> visited = new HashSet<>();
            List<String> log = new ArrayList<>();

            for (String node : adjacencyList.keySet()) {
                dist.put(node, Integer.MAX_VALUE);
            }
            dist.put(start, 0);
            pq.offer(new java.util.AbstractMap.SimpleEntry<>(start, 0));

            log.add("Starting Dijkstra's algorithm at source: " + start);

            while (!pq.isEmpty()) {
                Map.Entry<String, Integer> cur = pq.poll();
                String u = cur.getKey();
                int d = cur.getValue();

                if (visited.contains(u)) continue;
                visited.add(u);

                log.add("Visiting node [" + u + "] with current minimum distance: " + d + " km");

                if (u.equals(dest)) {
                    log.add("Reached destination node [" + dest + "]!");
                    break;
                }

                for (Edge edge : adjacencyList.getOrDefault(u, Collections.emptyList())) {
                    if (!visited.contains(edge.target)) {
                        int newDist = d + edge.weightKm;
                        if (newDist < dist.getOrDefault(edge.target, Integer.MAX_VALUE)) {
                            dist.put(edge.target, newDist);
                            prev.put(edge.target, u);
                            pq.offer(new java.util.AbstractMap.SimpleEntry<>(edge.target, newDist));
                            log.add("  -> Updated route to [" + edge.target + "]: " +
                                    d + " + " + edge.weightKm + " = " + newDist + " km via [" + u + "]");
                        }
                    }
                }
            }

            List<String> path = new LinkedList<>();
            String step = dest;
            while (step != null) {
                path.add(0, step);
                step = prev.get(step);
            }

            return new PathResult(dist.getOrDefault(dest, -1), path, log);
        }
    }

    public static Graph buildSlideGoogleMap() {
        Graph g = new Graph();
        g.addEdge("A", "C", 2);
        g.addEdge("A", "B", 3);
        g.addEdge("B", "D", 4);
        g.addEdge("C", "D", 3);
        g.addEdge("C", "E", 5);
        g.addEdge("D", "F", 2);
        g.addEdge("E", "F", 2);
        return g;
    }

    public static String runDemonstration() {
        StringBuilder sb = new StringBuilder();
        sb.append("=========================================\n");
        sb.append("   LESSON 8: GRAPH & DIJKSTRA'S ALGO     \n");
        sb.append("=========================================\n\n");

        sb.append("[1] Social Media Graph (Slide 14):\n");
        sb.append("    Users are Nodes: Jack, Rose\n");
        sb.append("    Friendship is an Edge: (Jack) <---> (Rose)\n\n");

        sb.append("[2] Google Maps Shortest Path Problem (Slides 22-27):\n");
        sb.append("    Map Edges:\n");
        sb.append("      A - C (2 km), A - B (3 km)\n");
        sb.append("      B - D (4 km), C - D (3 km)\n");
        sb.append("      C - E (5 km), D - F (2 km), E - F (2 km)\n\n");

        Graph g = buildSlideGoogleMap();
        Graph.PathResult result = g.findShortestPath("A", "F");

        sb.append("Dijkstra Step-by-Step Trace:\n");
        for (String line : result.traceLog) {
            sb.append("  ").append(line).append("\n");
        }

        sb.append("\nFinal Result:\n");
        sb.append("  Shortest Route : ").append(String.join(" -> ", result.path)).append("\n");
        sb.append("  Total Distance : ").append(result.totalDistanceKm).append(" km (Matches Path-3 in Slide 24!)\n");
        sb.append("=========================================\n");
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(runDemonstration());
    }
}

    public static void main(String[] args) {
        System.out.println("############################################################");
        System.out.println("       DATA STRUCTURES & ALGORITHMS - ALL LESSON ANSWERS    ");
        System.out.println("                 Prepared for Android Studio                ");
        System.out.println("############################################################\n");

        System.out.println(ArrayLesson.runDemonstration());
        System.out.println();
        System.out.println(StackLesson.runDemonstration());
        System.out.println();
        System.out.println(QueueLesson.runDemonstration());
        System.out.println();
        System.out.println(ListADTLesson.runDemonstration());
        System.out.println();
        System.out.println(LinkedListLesson.runDemonstration());
        System.out.println();
        System.out.println(HashingLesson.runDemonstration());
        System.out.println();
        System.out.println(SearchSortLesson.runDemonstration());
        System.out.println();
        System.out.println(GraphDijkstraLesson.runDemonstration());
        System.out.println();

        System.out.println("############################################################");
        System.out.println("               ALL LESSONS EXECUTED SUCCESSFULLY!           ");
        System.out.println("############################################################");
    }
}