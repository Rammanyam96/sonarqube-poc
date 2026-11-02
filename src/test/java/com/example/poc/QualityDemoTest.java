
package com.example.poc;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QualityDemoTest {

    private final QualityDemo demo = new QualityDemo();

    @Test
    void calculateShouldDoublePositiveValue() {
        assertEquals(10, demo.calculate(5));
    }

    @Test
    void calculateShouldHandleZero() {
        assertEquals(0, demo.calculate(0));
    }

    @Test
    void statusShouldBeActiveWhenTrue() {
        assertEquals("ACTIVE", demo.getStatusA(true));
    }

    @Test
    void statusShouldBeInactiveWhenFalse() {
        assertEquals("INACTIVE", demo.getStatusA(false));
    }

    @Test
    void divideShouldReturnQuotient() {
        assertEquals(5, demo.divide(10, 2));
    }

    @Test
    void divideByZeroShouldThrowException() {
        assertThrows(
            IllegalArgumentException.class,
            () -> demo.divide(10, 0)
        );
    }
}
