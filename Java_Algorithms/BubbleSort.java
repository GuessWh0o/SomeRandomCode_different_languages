package com.activaire.video.player.web_remote;

import java.util.Arrays;

public class BubbleSort {

    public static void main(String[] args) {
        int[] a =
                {1, 5, 7, 2, 3, 6};
        VectorSort(a);
    }

    public static void VectorSort(int[] arr) {
        boolean flag = true;
        while (flag) {
            flag = false;
            for (int i = 0; i < arr.length - 1; i++) {
                if (arr[i] > arr[i + 1]) {
                    int temp;
                    temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;
                    flag = true;
                }
            }
        }
        System.out.print(Arrays.toString(arr));
    }
}
