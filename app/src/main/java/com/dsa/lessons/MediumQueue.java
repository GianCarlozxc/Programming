package com.dsa.lessons;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class MediumQueue {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Queue<String> printerQueue = new LinkedList<>();

        System.out.print("Enter number of print jobs: ");
        int jobs = input.nextInt();
        input.nextLine();

        for (int i = 0; i < jobs; i++) {
            System.out.print("Enter document name " + (i + 1) + ": ");
            printerQueue.add(input.nextLine());
        }

        System.out.println("\nPrinting documents in FIFO order:");
        while (!printerQueue.isEmpty()) {
            System.out.println("Printing: " + printerQueue.poll());
        }
        input.close();
    }
}
