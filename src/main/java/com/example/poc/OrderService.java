
package com.example.poc;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class OrderService {

    private static final int MAX_QUANTITY = 100;
    private static final BigDecimal BULK_DISCOUNT_THRESHOLD =
            new BigDecimal("10");
    private static final BigDecimal VIP_DISCOUNT =
            new BigDecimal("0.10");
    private static final BigDecimal BULK_DISCOUNT =
            new BigDecimal("0.05");
    private static final BigDecimal FREE_SHIPPING_THRESHOLD =
            new BigDecimal("100.00");
    private static final BigDecimal SHIPPING_CHARGE =
            new BigDecimal("5.00");

    public enum CustomerType {
        REGULAR,
        VIP
    }

    public BigDecimal calculateTotal(
            CustomerType customerType,
            int quantity,
            BigDecimal unitPrice) {

        validateOrder(customerType, quantity, unitPrice);

        BigDecimal subtotal = unitPrice.multiply(
                BigDecimal.valueOf(quantity));

        BigDecimal discount = calculateDiscount(
                customerType, quantity, subtotal);

        BigDecimal shipping = calculateShipping(
                subtotal.subtract(discount));

        return subtotal.subtract(discount)
                .add(shipping)
                .setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal calculateDiscount(
            CustomerType customerType,
            int quantity,
            BigDecimal subtotal) {

        Objects.requireNonNull(customerType, "Customer type is required");
        Objects.requireNonNull(subtotal, "Subtotal is required");

        if (subtotal.signum() < 0 || quantity < 1
                || quantity > MAX_QUANTITY) {
            throw new IllegalArgumentException("Invalid order details");
        }

        if (customerType == CustomerType.VIP) {
            return subtotal.multiply(VIP_DISCOUNT);
        }

        if (quantity >= BULK_DISCOUNT_THRESHOLD.intValue()) {
            return subtotal.multiply(BULK_DISCOUNT);
        }

        return BigDecimal.ZERO;
    }

    public BigDecimal calculateShipping(BigDecimal amountAfterDiscount) {
        Objects.requireNonNull(
                amountAfterDiscount, "Order amount is required");

        if (amountAfterDiscount.signum() < 0) {
            throw new IllegalArgumentException("Amount cannot be negative");
        }

        if (amountAfterDiscount.compareTo(FREE_SHIPPING_THRESHOLD) >= 0) {
            return BigDecimal.ZERO;
        }

        return SHIPPING_CHARGE;
    }

    private void validateOrder(
            CustomerType customerType,
            int quantity,
            BigDecimal unitPrice) {

        Objects.requireNonNull(customerType, "Customer type is required");
        Objects.requireNonNull(unitPrice, "Unit price is required");

        if (quantity < 1 || quantity > MAX_QUANTITY) {
            throw new IllegalArgumentException(
                    "Quantity must be between 1 and 100");
        }

        if (unitPrice.signum() < 0) {
            throw new IllegalArgumentException(
                    "Unit price cannot be negative");
        }
    }
}
