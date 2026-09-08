package com.dsa.app;

import java.util.ArrayList;
import java.util.List;

public class ReviewerRepository {

    public static List<ReviewerItem> getAllReviewItems() {
        List<ReviewerItem> list = new ArrayList<>();

        // -------------------------------------------------------------
        // LESSON 1: ARRAY DATA STRUCTURE (Slide 31, 3-12)
        // -------------------------------------------------------------
        list.add(new ReviewerItem(
                1, "LESSON 1", "Array Memory Address Formula", ReviewerItem.Type.FORMULA,
                "What is the formula to calculate the memory address of an array element at index i?",
                "Address(i) = Base_Address + (Index * Element_Size_in_Bytes)",
                "For example, if Base Address = 200, Element Size = 4 bytes (int), and Index = 3:\nAddress = 200 + (3 * 4) = 212."
        ));

        list.add(new ReviewerItem(
                1, "LESSON 1", "Array Physical Characteristics", ReviewerItem.Type.CONCEPT,
                "What are the three core physical properties of a standard array?",
                "1. Contiguous Memory (adjacent memory cells)\n2. Fixed Size / Static Capacity (cannot resize after allocation)\n3. Homogeneous Elements (all elements share the same type)",
                "Because elements are stored contiguously and each has a uniform byte size, random access via index calculation is instantaneous O(1)."
        ));

        list.add(new ReviewerItem(
                1, "LESSON 1", "Array Time Complexities", ReviewerItem.Type.CONCEPT,
                "What are the Big-O time complexities for Array access, insertion, and deletion?",
                "• Access: O(1) constant time\n• Search: O(N) linear time (unsorted)\n• Insert at end: O(1)\n• Insert/Delete at middle/beginning: O(N) because of shifting elements",
                "Shifting elements to fill a hole or make room takes linear time proportional to the number of trailing elements."
        ));

        list.add(new ReviewerItem(
                1, "LESSON 1", "Array Quiz", ReviewerItem.Type.QUIZ,
                "An integer array starts at base address 300. Assuming each int takes 4 bytes, what is the address of numbers[4]?",
                "316",
                "Formula: Base + (Index * Size) = 300 + (4 * 4) = 300 + 16 = 316.",
                new String[]{"304", "312", "316", "320"}, 2
        ));

        // -------------------------------------------------------------
        // LESSON 2: STACK DATA STRUCTURE (LIFO) (Slides 7-11, 36)
        // -------------------------------------------------------------
        list.add(new ReviewerItem(
                2, "LESSON 2", "Stack Definition & Principles", ReviewerItem.Type.CONCEPT,
                "What principle does a Stack follow, and what are its primary operations?",
                "LIFO (Last-In, First-Out)\nOperations:\n• push(x): Adds x to the top\n• pop(): Removes and returns the top element\n• peek(): Returns top element without removing it\n• isEmpty(): Checks if stack has 0 elements",
                "The last item placed on the stack is always the first item removed, similar to a stack of plates or cafeteria trays."
        ));

        list.add(new ReviewerItem(
                2, "LESSON 2", "Slide 8-11: String Reversal Trace", ReviewerItem.Type.CONCEPT,
                "How does a Stack reverse the string 'ABCD' step-by-step?",
                "1. Push 'A', 'B', 'C', 'D' onto stack. Stack top is 'D'.\n2. Pop 'D' -> Output: D\n3. Pop 'C' -> Output: DC\n4. Pop 'B' -> Output: DCB\n5. Pop 'A' -> Output: DCBA",
                "Because push places characters from bottom to top, popping reverses the sequence to 'DCBA'."
        ));

        list.add(new ReviewerItem(
                2, "LESSON 2", "Stack Operations Complexity", ReviewerItem.Type.FORMULA,
                "What is the time complexity of push(), pop(), and peek() in a stack?",
                "All primary stack operations run in O(1) constant time.",
                "Since operations only ever touch the top pointer/index, no element shifting is required."
        ));

        list.add(new ReviewerItem(
                2, "LESSON 2", "Stack Quiz", ReviewerItem.Type.QUIZ,
                "If elements 'X', 'Y', and 'Z' are pushed into an empty stack in that order, what is the sequence obtained by calling pop() three times?",
                "Z, Y, X",
                "Because Stack follows LIFO (Last-In, First-Out), the last element pushed ('Z') is popped first, followed by 'Y', then 'X'.",
                new String[]{"X, Y, Z", "Z, Y, X", "Y, Z, X", "Z, X, Y"}, 1
        ));

        // -------------------------------------------------------------
        // LESSON 3: QUEUE DATA STRUCTURE (FIFO) (Slides 3-6, 17)
        // -------------------------------------------------------------
        list.add(new ReviewerItem(
                3, "LESSON 3", "Queue Definition & Principle", ReviewerItem.Type.CONCEPT,
                "What principle does a Queue follow, and where do insertion and removal occur?",
                "FIFO (First-In, First-Out)\n• enqueue(x): Inserts at the REAR / Tail\n• dequeue(): Removes and returns from the FRONT / Head\n• peek(): Inspects the front element",
                "Real-world analogy: A line of customers at a ticketing booth or print job spooling."
        ));

        list.add(new ReviewerItem(
                3, "LESSON 3", "Circular Queue Index Wrap Formula", ReviewerItem.Type.FORMULA,
                "What formula is used in circular queues to wrap the rear and front pointers back to 0?",
                "next_index = (current_index + 1) % capacity",
                "Modulo operator % allows circular reuse of slots without shifting elements when items are dequeued."
        ));

        list.add(new ReviewerItem(
                3, "LESSON 3", "Queue Quiz", ReviewerItem.Type.QUIZ,
                "Which computer science problem typically uses a FIFO Queue structure?",
                "Breadth-First Search (BFS) and printer print queues",
                "Queues manage tasks in the arrival order: print spooling, CPU task scheduling, and level-order tree/graph traversal (BFS).",
                new String[]{"Undo/Redo history", "Depth-First Search (DFS)", "Breadth-First Search (BFS) and printer print queues", "Function call call-stack"}, 2
        ));

        // -------------------------------------------------------------
        // LESSON 4: LIST ADT OPERATIONS (Slides 3-5, 23)
        // -------------------------------------------------------------
        list.add(new ReviewerItem(
                4, "LESSON 4", "List ADT Definition", ReviewerItem.Type.CONCEPT,
                "What is a List Abstract Data Type (ADT)?",
                "An ordered sequence of elements where each item has a positional index (0, 1, 2, ...).",
                "Unlike a raw fixed-size array, an ADT defines behaviors: insert(index, item), remove(index), get(index), set(index, item), and size()."
        ));

        list.add(new ReviewerItem(
                4, "LESSON 4", "Insertion & Deletion Shifting", ReviewerItem.Type.CONCEPT,
                "Why is inserting into an array-based List at index 0 an O(N) operation?",
                "All N existing elements must be shifted one position to the right to make room at index 0.",
                "Similarly, deleting index 0 requires shifting all remaining elements left by 1 position to fill the gap."
        ));

        list.add(new ReviewerItem(
                4, "LESSON 4", "List ADT Quiz", ReviewerItem.Type.QUIZ,
                "When inserting an element at index 1 of an array list with 5 elements, how many elements must be shifted?",
                "4 elements",
                "The elements at indices 1, 2, 3, and 4 must shift right to positions 2, 3, 4, and 5 (total 4 elements).",
                new String[]{"1 element", "3 elements", "4 elements", "5 elements"}, 2
        ));

        // -------------------------------------------------------------
        // LESSON 5: SINGLY LINKED LIST (Slides 3-6, 32)
        // -------------------------------------------------------------
        list.add(new ReviewerItem(
                5, "LESSON 5", "Node Anatomy & Pointer Linking", ReviewerItem.Type.CONCEPT,
                "What are the components of a Singly Linked List Node?",
                "1. Data Field: Stores the actual value (e.g. int, String)\n2. Next Pointer / Reference: Stores memory reference to the subsequent node (or null if last)",
                "The list begins at a reference pointer called 'head'. If head == null, the list is empty."
        ));

        list.add(new ReviewerItem(
                5, "LESSON 5", "Linked List vs Array Comparison", ReviewerItem.Type.CONCEPT,
                "How do Singly Linked Lists compare to Arrays in terms of memory and performance?",
                "• Sizing: Linked list is dynamic (grows node-by-node); Array is fixed/static.\n• Insertion at Head: Linked list is O(1); Array is O(N).\n• Random Access: Linked list is O(N) sequential traversal; Array is O(1) direct index lookup.\n• Memory Overhead: Linked list requires extra pointer memory per node.",
                "Use Linked Lists when frequent insertions/deletions happen at the ends without needing random index lookups."
        ));

        list.add(new ReviewerItem(
                5, "LESSON 5", "Linked List Quiz", ReviewerItem.Type.QUIZ,
                "What is the time complexity to access the 50th element of a Singly Linked List?",
                "O(N)",
                "Unlike arrays with direct address calculation, a linked list must traverse node by node starting from head until the 50th node.",
                new String[]{"O(1)", "O(log N)", "O(N)", "O(N^2)"}, 2
        ));

        // -------------------------------------------------------------
        // LESSON 6: HASHING DATA STRUCTURE (Slides 4-7, 28)
        // -------------------------------------------------------------
        list.add(new ReviewerItem(
                6, "LESSON 6", "Hash Modulo Formula & Collisions", ReviewerItem.Type.FORMULA,
                "What is the division-remainder hash function formula and what causes a collision?",
                "Hash Index = key % Table_Size",
                "A collision happens when two distinct keys yield the same hash slot: hash(key1) == hash(key2)."
        ));

        list.add(new ReviewerItem(
                6, "LESSON 6", "Linear Probing Collision Resolution", ReviewerItem.Type.CONCEPT,
                "How does Open Addressing with Linear Probing resolve collisions?",
                "If slot index is already occupied, check the next sequential slot (index + 1) % Table_Size until an empty slot is found.",
                "Linear probing probes slots sequentially: h, h+1, h+2, ... with wrap-around at the table boundary."
        ));

        list.add(new ReviewerItem(
                6, "LESSON 6", "Hashing Quiz", ReviewerItem.Type.QUIZ,
                "In a hash table of size 10 using Linear Probing, where will key 35 be placed if slot 5 is already occupied by 15, and slot 6 is empty?",
                "Slot 6",
                "Hash index: 35 % 10 = 5. Slot 5 is full, so linear probing checks (5 + 1) % 10 = 6, which is empty.",
                new String[]{"Slot 5", "Slot 6", "Slot 7", "Slot 0"}, 1
        ));

        // -------------------------------------------------------------
        // LESSON 7: SEARCHING & SORTING (Slides 3-6, 12, 38)
        // -------------------------------------------------------------
        list.add(new ReviewerItem(
                7, "LESSON 7", "Binary Search Midpoint Formula & Prerequisite", ReviewerItem.Type.FORMULA,
                "What is the formula for Binary Search midpoint, and what is the mandatory prerequisite?",
                "mid = low + (high - low) / 2\nPrerequisite: The array MUST be sorted.",
                "Using low + (high - low) / 2 prevents potential 32-bit integer overflow compared to (low + high) / 2."
        ));

        list.add(new ReviewerItem(
                7, "LESSON 7", "Linear Search vs Binary Search Comparison", ReviewerItem.Type.CONCEPT,
                "Compare Linear Search and Binary Search time complexity.",
                "• Linear Search: O(N) — works on sorted and unsorted lists.\n• Binary Search: O(log N) — divides search range by half each iteration, requires sorted list.",
                "For 1,000,000 items, Linear Search takes up to 1,000,000 comparisons, while Binary Search takes at most ~20 comparisons!"
        ));

        list.add(new ReviewerItem(
                7, "LESSON 7", "Bubble Sort Mechanism", ReviewerItem.Type.CONCEPT,
                "How does Bubble Sort work and what is its worst-case complexity?",
                "Repeatedly steps through adjacent pairs, swapping them if out of order. Largest unsorted element bubbles to the end after each pass. Worst-case: O(N^2).",
                "After pass k, the k largest elements are in their final sorted positions."
        ));

        list.add(new ReviewerItem(
                7, "LESSON 7", "Search/Sort Quiz", ReviewerItem.Type.QUIZ,
                "What is the maximum number of comparisons Binary Search needs to find an element in a sorted array of 64 items?",
                "6 comparisons (or 7 including final check)",
                "log2(64) = 6. Each comparison divides the candidate space in half: 64 -> 32 -> 16 -> 8 -> 4 -> 2 -> 1.",
                new String[]{"6", "16", "32", "64"}, 0
        ));

        // -------------------------------------------------------------
        // LESSON 8: GRAPH & DIJKSTRA SHORTEST PATH (Slides 3-7, 25)
        // -------------------------------------------------------------
        list.add(new ReviewerItem(
                8, "LESSON 8", "Graph Components & Representations", ReviewerItem.Type.CONCEPT,
                "What constitutes a Graph and what are the two common data representations?",
                "Graph G = (V, E) consisting of Vertices (nodes) and Edges (connecting links).\nRepresentations:\n1. Adjacency Matrix: 2D array V x V where matrix[u][v] = weight\n2. Adjacency List: Array or Map of linked lists storing neighbors per vertex",
                "Adjacency lists are more space-efficient for sparse graphs O(V + E), whereas matrices are O(V^2)."
        ));

        list.add(new ReviewerItem(
                8, "LESSON 8", "Dijkstra Edge Relaxation Formula", ReviewerItem.Type.FORMULA,
                "What is the Edge Relaxation condition used in Dijkstra's Algorithm?",
                "if (dist[u] + weight(u, v) < dist[v]) {\n    dist[v] = dist[u] + weight(u, v);\n}",
                "If the path from source to v through u is shorter than current best known distance to v, update dist[v]."
        ));

        list.add(new ReviewerItem(
                8, "LESSON 8", "Dijkstra Algorithm Quiz", ReviewerItem.Type.QUIZ,
                "Can standard Dijkstra's Algorithm guarantee correct shortest paths in graphs with negative edge weights?",
                "No, Dijkstra assumes non-negative edge weights.",
                "Dijkstra greedily finalizes vertices assuming distance cannot decrease once marked visited. Negative edge weights require the Bellman-Ford algorithm.",
                new String[]{"Yes, it handles all weights", "No, Dijkstra requires non-negative edge weights", "Yes, but only in directed graphs", "Only if there are fewer than 10 vertices"}, 1
        ));

        // -------------------------------------------------------------
        // LESSON 9: JAVA SWING & DESKTOP GUI (Book Chapters 5-7)
        // -------------------------------------------------------------
        list.add(new ReviewerItem(
                9, "SWING GUI", "AWT vs Swing Comparison", ReviewerItem.Type.CONCEPT,
                "What is the key architectural difference between Java AWT and Java Swing?",
                "• AWT: Heavyweight components tied directly to native OS peers (platform dependent).\n• Swing: Lightweight components rendered purely in Java bytecode (platform independent, uniform look).",
                "Swing runs on top of AWT foundation classes and offers pluggable look-and-feel."
        ));

        list.add(new ReviewerItem(
                9, "SWING GUI", "Swing Architecture Quiz", ReviewerItem.Type.QUIZ,
                "Which Swing class represents a top-level window containing title bar, minimize, and close buttons?",
                "JFrame",
                "JFrame is the top-level container window. JPanel is an intermediate container, while JButton and JLabel are basic controls.",
                new String[]{"JPanel", "JFrame", "JWindow", "JComponent"}, 1
        ));

        return list;
    }

    public static List<ReviewerItem> getItemsByFilter(int lessonFilter, ReviewerItem.Type typeFilter) {
        List<ReviewerItem> result = new ArrayList<>();
        for (ReviewerItem item : getAllReviewItems()) {
            boolean matchesLesson = (lessonFilter == 0) || (item.getLessonId() == lessonFilter);
            boolean matchesType = (typeFilter == null) || (item.getType() == typeFilter);
            if (matchesLesson && matchesType) {
                result.add(item);
            }
        }
        return result;
    }
}
