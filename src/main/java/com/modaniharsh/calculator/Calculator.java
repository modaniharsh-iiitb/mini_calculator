package com.modaniharsh.calculator;

public class Calculator {

    public static double sqroot(double x) {
        return Math.sqrt(x);
    }

    public static long factorial(int x) {
        if (x == 0) {
            return 1;
        }
        return x * factorial(x - 1);
    }

    public static double log(double x) {
        return Math.log(x);
    }

    public static double power(double x, double b) {
        return Math.pow(x, b);
    }
    
}
