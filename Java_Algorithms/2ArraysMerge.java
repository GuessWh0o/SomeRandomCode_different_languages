import java.util.Arrays;

/**
 * Created by maks on 7/2/17.
 */
 
public class ArrayMerging {


    private static int[] arrayMerge(int[] array1, int[] array2) {

        int[] mergedArray = new int[array1.length + array2.length];
        int i = 0, j = 0, k = 0;
        while (i < array1.length && j < array2.length) {
            if (array1[i] < array2[j]) {
                mergedArray[k] = array1[i];
                i++;
            } else {
                mergedArray[k] = array2[j];
                j++;
            }
            k++;
        }

        while (i < array1.length) {
            mergedArray[k] = array1[i];
            i++;
            k++;
        }

        while (j < array2.length) {
            mergedArray[k] = array2[j];
            j++;
            k++;
        }

        return mergedArray;
    }

    public static void main(String[] args) {
        int[] array1 = {1, 2, 3, 4, 5, 6};
        int[] array2 = {7, 8, 9, 10, 11, 12};
        int[] mergedArray = arrayMerge(array1, array2);
        System.out.println("Merged Array is: " + Arrays.toString(mergedArray));
    }
}
