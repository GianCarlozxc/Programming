package com.dsa.lessons;

public class HashingLesson {

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
