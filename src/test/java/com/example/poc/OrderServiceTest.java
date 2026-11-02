
package com.example.poc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

class OrderServiceTest {

    private final OrderService service = new OrderService();

    @Test
    void regularCustomerPaysShippingForSmallOrder() {
        BigDecimal total = service.calculateTotal(
                OrderService.CustomerType.REGULAR,
                2,
                new BigDecimal("10.00"));

        assertEquals(new BigDecimal("25.00"), total);
    }

    @Test
    void vipCustomerReceivesTenPercentDiscount() {
        BigDecimal total = service.calculateTotal(
                OrderService.CustomerType.VIP,
                2,
                new BigDecimal("50.00"));

        assertEquals(new BigDecimal("90.00"), total);
    }

    @Test
    void regularCustomerReceivesBulkDiscount() {
        BigDecimal discount = service.calculateDiscount(
                OrderService.CustomerType.REGULAR,
                10,
                new BigDecimal("200.00"));

        assertEquals(new BigDecimal("10.0000"), discount);
    }

    @Test
    void vipDiscountTakesPrecedenceOverBulkDiscount() {
        BigDecimal discount = service.calculateDiscount(
                OrderService.CustomerType.VIP,
                10,
                new BigDecimal("200.00"));

        assertEquals(new BigDecimal("20.0000"), discount);
    }

    @Test
    void regularCustomerWithoutBulkOrderGetsNoDiscount() {
        BigDecimal discount = service.calculateDiscount(
                OrderService.CustomerType.REGULAR,
                2,
                new BigDecimal("50.00"));

        assertEquals(BigDecimal.ZERO, discount);
    }

    @Test
    void freeShippingAppliesAtThreshold() {
        assertEquals(
                BigDecimal.ZERO,
                service.calculateShipping(new BigDecimal("100.00")));
    }

    @Test
    void shippingChargeAppliesBelowThreshold() {
        assertEquals(
                new BigDecimal("5.00"),
                service.calculateShipping(new BigDecimal("99.99")));
    }

    @Test
    void rejectsZeroQuantity() {
        assertThrows(
                IllegalArgumentException.class,
                () -> service.calculateTotal(
                        OrderService.CustomerType.REGULAR,
                        0,
                        new BigDecimal("10.00")));
    }

    @Test
    void rejectsQuantityAboveMaximum() {
        assertThrows(
                IllegalArgumentException.class,
                () -> service.calculateTotal(
                        OrderService.CustomerType.REGULAR,
                        101,
                        new BigDecimal("10.00")));
    }

    @Test
    void rejectsNegativePrice() {
        assertThrows(
                IllegalArgumentException.class,
                () -> service.calculateTotal(
                        OrderService.CustomerType.REGULAR,
                        1,
                        new BigDecimal("-10.00")));
    }

    @Test
    void rejectsNullCustomerType() {
        assertThrows(
                NullPointerException.class,
                () -> service.calculateTotal(
                        null, 1, new BigDecimal("10.00")));
    }

    @Test
    void rejectsNullUnitPrice() {
        assertThrows(
                NullPointerException.class,
                () -> service.calculateTotal(
                        OrderService.CustomerType.REGULAR, 1, null));
    }

    @Test
    void rejectsNegativeShippingAmount() {
        assertThrows(
                IllegalArgumentException.class,
                () -> service.calculateShipping(new BigDecimal("-1.00")));
    }
}
