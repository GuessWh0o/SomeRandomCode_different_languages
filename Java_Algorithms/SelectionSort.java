import java.util.Arrays;

/**
 * Created by maks on 7/2/17.
 */
public class Main {

    private static int[] selectionSort(int[] array) {

        for(int i = 0; i < array.length - 1; i++) {
            int minElem = i;
            for(int j = i+1; j < array.length; j++) {
                if(array[j] < array[minElem]) {
                    minElem = j;
                }
            }
            int temp = array[minElem];
            array[minElem] = array[i];
            array[i] = temp;
        }
        return array;
    }

    public static void main(String[] args) {
        int[] initArray = {2, 4, 1, 10, 5, 7, 8};
        System.out.println("Initial array is: " + Arrays.toString(initArray)); 
        int[] selectionSortedArray = selectionSort(initArray);
        System.out.println("Selection Sorted array is: " + Arrays.toString(selectionSortedArray));
    }
}
