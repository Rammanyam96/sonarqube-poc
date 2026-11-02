
package com.example.poc;

import java.util.Locale;
import java.util.Objects;

public class TextUtils {

    public String normalize(String input) {
        Objects.requireNonNull(input, "Input is required");
        return input.trim().replaceAll("\\s+", " ")
                .toLowerCase(Locale.ROOT);
    }

    public boolean isPalindrome(String input) {
        Objects.requireNonNull(input, "Input is required");

        String normalized = input
                .replaceAll("[^a-zA-Z0-9]", "")
                .toLowerCase(Locale.ROOT);

        int left = 0;
        int right = normalized.length() - 1;

        while (left < right) {
            if (normalized.charAt(left) != normalized.charAt(right)) {
                return false;
            }

            left++;
            right--;
        }

        return true;
    }

    public int countWords(String input) {
        Objects.requireNonNull(input, "Input is required");

        String trimmed = input.trim();

        if (trimmed.isEmpty()) {
            return 0;
        }

        return trimmed.split("\\s+").length;
    }

    public String maskEmail(String email) {
        Objects.requireNonNull(email, "Email is required");

        int atIndex = email.indexOf('@');

        if (atIndex <= 0 || atIndex == email.length() - 1
                || email.indexOf('@', atIndex + 1) >= 0) {
            throw new IllegalArgumentException("Invalid email format");
        }

        String username = email.substring(0, atIndex);
        String domain = email.substring(atIndex);

        if (username.length() == 1) {
            return "*" + domain;
        }

        return username.charAt(0)
                + "***"
                + domain;
    }
}
