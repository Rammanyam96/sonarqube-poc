
package com.example.poc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class TextUtilsTest {

    private final TextUtils textUtils = new TextUtils();

    @Test
    void normalizesWhitespaceAndCase() {
        assertEquals("hello world", textUtils.normalize("  HELLO   WORLD  "));
    }

    @Test
    void identifiesPalindrome() {
        assertTrue(textUtils.isPalindrome("A man, a plan, a canal: Panama"));
    }

    @Test
    void identifiesNonPalindrome() {
        assertFalse(textUtils.isPalindrome("SonarQube"));
    }

    @Test
    void handlesEmptyStringAsPalindrome() {
        assertTrue(textUtils.isPalindrome(""));
    }

    @Test
    void countsWords() {
        assertEquals(3, textUtils.countWords("one two three"));
    }

    @Test
    void countsNoWordsInWhitespaceOnlyInput() {
        assertEquals(0, textUtils.countWords("   "));
    }

    @Test
    void masksEmailUsername() {
        assertEquals("r***@example.com",
                textUtils.maskEmail("ram@example.com"));
    }

    @Test
    void masksSingleCharacterUsername() {
        assertEquals("*@example.com",
                textUtils.maskEmail("r@example.com"));
    }

    @Test
    void rejectsEmailWithoutAtSymbol() {
        assertThrows(
                IllegalArgumentException.class,
                () -> textUtils.maskEmail("invalid-email"));
    }

    @Test
    void rejectsEmailWithEmptyUsername() {
        assertThrows(
                IllegalArgumentException.class,
                () -> textUtils.maskEmail("@example.com"));
    }

    @Test
    void rejectsEmailWithEmptyDomain() {
        assertThrows(
                IllegalArgumentException.class,
                () -> textUtils.maskEmail("user@"));
    }

    @Test
    void rejectsEmailWithMultipleAtSymbols() {
        assertThrows(
                IllegalArgumentException.class,
                () -> textUtils.maskEmail("user@@example.com"));
    }

    @Test
    void rejectsNullText() {
        assertThrows(
                NullPointerException.class,
                () -> textUtils.normalize(null));
    }

    @Test
    void rejectsNullEmail() {
        assertThrows(
                NullPointerException.class,
                () -> textUtils.maskEmail(null));
    }
}
