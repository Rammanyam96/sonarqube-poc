
package com.example.poc;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class SonarIssueDemo {

    // Maintainability: unused private field
    private String unusedDescription = "POC demo";

    // Security: hardcoded credential
    private static final String PASSWORD = "admin123";

    // Security: predictable random numbers
    public int generateSecurityCode() {
        return new Random().nextInt(900000) + 100000;
    }

    // Security: weak hashing algorithm
    public byte[] hashPassword(String password)
            throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("MD5");
        return digest.digest(password.getBytes());
    }

    // Reliability: possible NullPointerException
    public int getTextLength(String input) {
        return input.length();
    }

    // Maintainability: unused local variable
    public int calculatePrice(int quantity, int unitPrice) {
        int unusedCalculation = quantity * 2;
        return quantity * unitPrice;
    }

    // Maintainability: unnecessary conditional logic
    public boolean isPositive(int value) {
        if (value > 0) {
            return true;
        } else {
            return false;
        }
    }

    // Expose a safe indicator for testing without returning the password.
    public boolean hasConfiguredCredential() {
        return !PASSWORD.isBlank();
    }
}
