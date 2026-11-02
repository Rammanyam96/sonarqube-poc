
package com.example.poc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.security.NoSuchAlgorithmException;

import org.junit.jupiter.api.Test;

class SonarIssueDemoTest {

    private final SonarIssueDemo demo = new SonarIssueDemo();

    @Test
    void generatesSixDigitCode() {
        int code = demo.generateSecurityCode();

        assertTrue(code >= 100000);
        assertTrue(code <= 999999);
    }

    @Test
    void hashesInputWithCurrentDemoImplementation()
            throws NoSuchAlgorithmException {
        byte[] hash = demo.hashPassword("example-password");

        assertEquals(16, hash.length);
    }

    @Test
    void returnsTextLength() {
        assertEquals(5, demo.getTextLength("Sonar"));
    }

    @Test
    void nullTextCausesNullPointerException() {
        assertThrows(
                NullPointerException.class,
                () -> demo.getTextLength(null));
    }

    @Test
    void calculatesPrice() {
        assertEquals(30, demo.calculatePrice(3, 10));
    }

    @Test
    void identifiesPositiveNumbers() {
        assertTrue(demo.isPositive(5));
    }

    @Test
    void zeroIsNotPositive() {
        assertFalse(demo.isPositive(0));
    }

    @Test
    void negativeNumberIsNotPositive() {
        assertFalse(demo.isPositive(-5));
    }

    @Test
    void credentialIsConfigured() {
        assertTrue(demo.hasConfiguredCredential());
    }
}
