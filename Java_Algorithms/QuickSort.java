package com.activaire.video.player.web_remote;

import java.util.Arrays;

public class QuickSort {
    public static void main(String[] args) {
        int[] a = {1, 5, 7, 2, 3, 6};
        quickSort(a, 0, a.length - 1);
        System.out.print(Arrays.toString(a) + "\n");
    }


    
    private static int[] quickSort(int[] arr, int low, int high) {
        if (low >= high) {
            return arr;
        }

        int pivot = arr[(low + (high - low) / 2)];
        int index = part(arr, low, high, pivot);
        quickSort(arr, low, index - 1);
        quickSort(arr, index, high);
        return arr;
    }

    private static int part(int[] array, int low, int high, int pivot) {
        while (low <= high) {
            while (array[low] < pivot) {
                low++;
            }

            while (array[high] > pivot) {
                high--;
            }

            if (low <= high) {
                int temp = array[low];
                array[low] = array[high];
                array[high] = temp;
                low++;
                high--;
            }
        }
        return low;
    }
}
