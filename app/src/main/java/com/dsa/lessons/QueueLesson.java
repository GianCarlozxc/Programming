package com.dsa.lessons;

import java.util.NoSuchElementException;

public class QueueLesson {

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
