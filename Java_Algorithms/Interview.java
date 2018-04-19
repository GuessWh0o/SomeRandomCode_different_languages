package com.maks.interview;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Arrays;

/**
 * Created by Mks on 12-Jan-17.
 */

//Interview tasks newrosoft company.

public class Class_class {
    public static void main(String[] args) {
        float[] arrayTest = {1.0f, 5.0f, 7.0f, 3.0f, 4.0f};
        numGen();
        bubbleSort(arrayTest);
        genAndSort();
        // generateAndSortNumbers();
    }

    //Function to generate number within 100 - 200
    static float numGen() {
        float num;
        num = 100.0f + (float) ((1.0f + 200.0f - 100.0f) * Math.random());
        return num;
    }

    //Bubble Sort
    static float[] bubbleSort(float[] initVector) {
        float[] sortedVector = initVector;
        boolean flag = true;
        while (flag) {
            flag = false;
            for (int i = 0; i < sortedVector.length - 1; i++) {
                if (sortedVector[i] > sortedVector[i + 1]) {
                    float temp;
                    temp = sortedVector[i];
                    sortedVector[i] = sortedVector[i + 1];
                    sortedVector[i + 1] = temp;
                    flag = true;
                }
            }
        }
        return sortedVector;
    }

    //Write array to file
    static void writeArrayInFile(String fileName, float[] arrayToFile) {
        try {
        	(PrintStream out = new PrintStream(new FileOutputStream(fileName + ".txt"))) {
            out.print(Arrays.toString(arrayToFile));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            System.out.println("Can't create a file, check your arguments");
        }
    }

    static void generateAndSortNumbers() {
        String arrayString = "";

        //Generate 99 numbers
        for (int i = 0; i < 99; i++) {
            float temp = numGen();
            arrayString += temp + ", ";
        }
        String[] items = arrayString.replaceAll("\\[", "").replaceAll("\\]", "").replaceAll("\\s", "").split(",");

        float[] results = new float[items.length];

        for (int i = 0; i < items.length; i++) {
            try {
                results[i] = (float) Double.parseDouble(items[i]);
            } catch (NumberFormatException nfe) {
                System.out.println("Can not convert string to float array");
            }
        }

        bubbleSort(results);

        writeArrayInFile("newrosoft", results);
        System.out.println(Arrays.toString(results));
    }

    static void genAndSort() {
        float[] genSor = new float[100];

        for (int i = 0; i < 99; i++) {
            genSor[i] = numGen();
        }
        System.out.println("Gen sort is: " + Arrays.toString(genSor));
    }
}
