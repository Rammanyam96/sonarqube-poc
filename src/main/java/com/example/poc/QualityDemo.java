
package com.example.poc;

public class QualityDemo {

    // Sonar rule candidate: unused private field (S1068)
    private String unusedField = "POC";

    // Sonar rule candidate: unused local variable (S1481)
    public int calculate(int value) {
        int unused = value + 100;
        return value * 2;
    }

    public String getStatusA(boolean active) {
        if (active) {
            return "ACTIVE";
        }
        return "INACTIVE";
    }

    public String getStatusB(boolean active) {
        if (active) {
            return "ACTIVE";
        }
        return "INACTIVE";
    }

    public int divide(int a, int b) {
        if (b == 0) {
            throw new IllegalArgumentException(
                "Divisor cannot be zero");
        }
        return a / b;
    }
}
