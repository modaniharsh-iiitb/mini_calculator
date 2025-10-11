package com.modaniharsh.calculator;

public class App {
    public static void main(String[] args) {
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        int choice;
        double num1, num3, base, exponent;
        int num2;
        
        while (true) {
            System.out.println("Welcome to the Mini Calculator!");
            System.out.println("Select an operation:");
            System.out.println("1. Square Root");
            System.out.println("2. Factorial");
            System.out.println("3. Natural Logarithm");
            System.out.println("4. Power");
            System.out.println("0. Exit");
            System.out.print("> ");

            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter a number: ");
                    num1 = scanner.nextDouble();
                    System.out.println("Square Root: " + Calculator.sqroot(num1));
                    break;

                case 2:
                    System.out.print("Enter a non-negative integer: ");
                    num2 = scanner.nextInt();
                    if (num2 < 0) {
                        System.out.println("Factorial is not defined for negative numbers.");
                    } else {
                        System.out.println("Factorial: " + Calculator.factorial(num2));
                    }
                    break;

                case 3:
                    System.out.print("Enter a positive number: ");
                    num3 = scanner.nextDouble();
                    if (num3 <= 0) {
                        System.out.println("Natural logarithm is not defined for non-positive numbers.");
                    } else {
                        System.out.println("Natural Logarithm: " + Calculator.log(num3));
                    }
                    break;

                case 4:
                    System.out.print("Enter the base: ");
                    base = scanner.nextDouble();
                    System.out.print("Enter the exponent: ");
                    exponent = scanner.nextDouble();
                    System.out.println("Power: " + Calculator.power(base, exponent));
                    break;

                case 0:
                    System.out.println("Exiting the calculator.");
                    scanner.close();
                    return;
                    
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
            System.out.println();

        }
    }
}
