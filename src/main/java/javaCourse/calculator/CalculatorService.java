package javaCourse.calculator;

import org.springframework.stereotype.Service;

@Service
public class CalculatorService implements CalculatorServiceInterface {
    public String hello() {
        return "Hello to Calculator!";
    }

    public String plus(int a, int b) {
        checkParameters(a, b);
        int result = a + b;
        return a + " + " + b + " = " + result;
    }

    public String minus(int a, int b) {
        checkParameters(a, b);
        int result = a - b;
        return a + " - " + b + " = " + result;
    }

    public String multiply(int a, int b) {
        checkParameters(a, b);
        int result = a * b;
        return a + " * " + b + " = " + result;
    }

    public String divide(int a, int b) {
        checkParameters(a, b);
        if (b == 0) {
            throw new ArithmeticException("Divide by zero");
        }
        float result = (float) a / b;
        return a + " / " + b + " = " + result;
    }

    private void checkParameters(Integer a, Integer b) {
        if (a == null || b == null) {
            throw new IllegalArgumentException("Both num1 and num2 must be provided.");
        }
    }
}
