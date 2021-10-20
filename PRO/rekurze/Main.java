package cz.polacek.rekurze;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in, "Windows-1250");
        System.out.print("Input your number: ");
        int a = sc.nextInt();
        int sum = recursion(a);
        int sum2 = fibonnacciRecursion(a);
        int fac = factorial(a);
        System.out.println("Recursion: " + Colors.ANSI_RED + sum + " " + Colors.ANSI_PURPLE + sum2 + Colors.ANSI_RESET);
        System.out.println("Factorial: " + Colors.ANSI_BLUE + fac + Colors.ANSI_RESET);
    }

    public static int fibonnacciRecursion(int value) {
        if (value <= 0) {
            return 0;
        } if (value == 1) {
            return 1;
        }
        return fibonnacciRecursion(value - 1) + fibonnacciRecursion(value - 2);
    }

    public static int recursion(int count) {
        int valueBefore = 0;
        int valueAfter = 1;
        int valueSum = valueBefore + valueAfter;
        for (int i = 0; i < count - 1; i++) {
            valueSum = valueBefore + valueAfter;
            valueBefore = valueAfter;
            valueAfter = valueSum;
        }
        return valueSum;
    }

    public static int factorial(int value) {
        if (value <= 0) {
            return 1;
        }
        return value * factorial(value - 1);
    }

    public static int[] quicksort(int[] arr) {
        return arr;
    }

    public static void writeSort(int[] arr) {
        for (int i : arr) {
            System.out.print(arr[i] + ", ");
        }
    }
}
