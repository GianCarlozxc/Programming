package com.dsa.lessons;

import java.util.Objects;

public class ListADTLesson {

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
