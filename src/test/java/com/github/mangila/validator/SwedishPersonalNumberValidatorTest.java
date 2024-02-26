package com.github.mangila.validator;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SwedishPersonalNumberValidatorTest {

    @Test
    void isValid() {
        assertThat(SwedishPersonalNumberValidator.isValid("19780202-2389"))
                .isTrue();
        assertThat(SwedishPersonalNumberValidator.isValid("19820411-2380"))
                .isTrue();
    }

    @Test
    void notValidDate() {
        assertThat(SwedishPersonalNumberValidator.isValid("20770101-1111"))
                .isFalse();
    }

    @Test
    void notValidChecksum() {
        assertThat(SwedishPersonalNumberValidator.isValid("19780202-1111"))
                .isFalse();
    }

    @Test
    void notValidFormat() {
        assertThat(SwedishPersonalNumberValidator.isValid("780202-2389"))
                .isFalse();
    }

    @Test
    void testNull() {
        assertThatThrownBy(() -> SwedishPersonalNumberValidator.isValid(null))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void testBlank() {
        assertThat(SwedishPersonalNumberValidator.isValid(""))
                .isFalse();
    }
}