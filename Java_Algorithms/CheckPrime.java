package com.activaire.video.player.web_remote;

import java.util.Scanner;

public class CheckPrime {

    public static void main(String[] args) {
        int numberPrime;

        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNextInt()) {
            numberPrime = scanner.nextInt();

            int sqrtNum = (int) Math.sqrt(numberPrime);

            boolean isPrime = true;

            while (isPrime) {

                for (int i = sqrtNum; i > 1; i--) {
                    int divis = numberPrime % i;
                    if (divis == 0) {
                        isPrime = false;
                        break;
                    }
                }
                break;
            }

            System.out.println(isPrime);
        } else
            System.out.println("Please enter a number");

    }

}
