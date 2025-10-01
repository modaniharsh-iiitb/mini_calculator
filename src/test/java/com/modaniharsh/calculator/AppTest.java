package com.modaniharsh.calculator;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {

    @Test
    public void testSqroot() {
        assertTrue(Calculator.sqroot(4.0) == 2.0);
        assertTrue(Calculator.sqroot(9.0) == 3.0);
        assertTrue(Calculator.sqroot(16.0) == 4.0);
    }

    @Test
    public void testFactorial() {
        assertTrue(Calculator.factorial(0) == 1);
        assertTrue(Calculator.factorial(1) == 1);
        assertTrue(Calculator.factorial(5) == 120);
        assertTrue(Calculator.factorial(10) == 3628800);
    }

    @Test
    public void testLog() {
        assertTrue(Calculator.log(1.0) == 0.0);
        assertTrue(Calculator.log(Math.E) == 1.0);
        assertTrue(Calculator.log(Math.E * Math.E) == 2.0);
        assertTrue(Calculator.log(10.0) == Math.log(10.0));
    }

    @Test
    public void testPower() {
        assertTrue(Calculator.power(2.0, 3.0) == 8.0);
        assertTrue(Calculator.power(5.0, 0.0) == 1.0);
        assertTrue(Calculator.power(3.0, 4.0) == 81.0);
        assertTrue(Calculator.power(10.0, 2.0) == 100.0);
    }
}
