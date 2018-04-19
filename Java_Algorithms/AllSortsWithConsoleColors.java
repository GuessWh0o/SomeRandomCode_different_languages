import java.util.Arrays;

/**
 * Created by maks on 7/2/17.
 */


class ConsoleTextColors {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
}

public class Main {


    public static void main(String[] args) {
        int[] initArray = {2, 4, 1, 10, 5, 7, 8};
        System.out.println(ConsoleTextColors.ANSI_RED + "Initial array is: " + Arrays.toString(initArray));
        int[] bubbleSortedArray = bubbleSort(initArray);
        System.out.println(ConsoleTextColors.ANSI_BLUE + "Bubble Sorted array is: " + Arrays.toString(bubbleSortedArray));
        int[] selectionSortedArray = selectionSort(initArray);
        System.out.println(ConsoleTextColors.ANSI_CYAN + "Selection Sorted array is: " + Arrays.toString(selectionSortedArray));
        int[] insertionSortedArray = insertionSort(initArray);
        System.out.println(ConsoleTextColors.ANSI_GREEN + "Selection Sorted array is: " + Arrays.toString(insertionSortedArray));
        int[] quickSortedArray = quickSort(initArray, 0, initArray.length - 1);
        System.out.print(ConsoleTextColors.ANSI_PURPLE + "Quick Sorted array is: " + Arrays.toString(quickSortedArray));
    }

    private static int[] bubbleSort(int[] array) {
        boolean sorted = true;

        while (sorted) {
            sorted = false;
            for (int i = 0; i < array.length - 1; i++) {
                if (array[i] > array[i + 1]) {
                    int temp = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = temp;
                    sorted = true;
                }
            }
        }
        return array;
    }

    private static int[] selectionSort(int[] array) {

        for (int i = 0; i < array.length - 1; i++) {
            int minElem = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[minElem]) {
                    minElem = j;
                }
            }
            int temp = array[minElem];
            array[minElem] = array[i];
            array[i] = temp;
        }
        return array;
    }

    private static int[] insertionSort(int[] array) {

        for (int i = 1; i < array.length; i++) {
            int key = array[i];
            int j = i - 1;

            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = key;
        }
        return array;
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

