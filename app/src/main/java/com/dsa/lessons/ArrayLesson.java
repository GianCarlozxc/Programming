package com.dsa.lessons;

import java.util.Arrays;

public class ArrayLesson {

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
