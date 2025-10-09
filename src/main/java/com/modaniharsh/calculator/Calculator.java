package com.modaniharsh.calculator;

public class Calculator {

    public static double sqroot(double x) {
        return Math.sqrt(x);
    }

    public static long factorial(int x) {
        int arr[] = new int[x + 1];
        arr[0] = 1;
        for (int i = 1; i <= x; i++) {
            arr[i] = i * arr[i - 1];
        }
        return arr[x];
    }

    public static double log(double x) {
        return Math.log(x);
    }

    public static double power(double x, double b) {
        return Math.pow(x, b);
    }
    
}
