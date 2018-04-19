import java.util.Arrays;

/**
 * Created by maks on 7/2/17.
 */
 
    private static int[] insertionSort(int[] array) {

        for(int i = 1; i < array.length; i++) {
            int key = array[i];
            int j = i - 1;

            while(j >= 0 && array[j] > key) {
                array[j+1] = array[j];
                j--;
            }
            array[j+1] = key;
        }
        return array;
    }

    public static void main(String[] args) {
        int[] initArray = {2, 4, 1, 10, 5, 7, 8};
        System.out.println("Initial array is: " + Arrays.toString(initArray));
        int[] insertionSortedArray = insertionSort(initArray);
        System.out.println("Selection Sorted array is: " + Arrays.toString(insertionSortedArray));
    }
}

