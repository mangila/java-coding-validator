package com.github.mangila.validator;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SwedishVehicleRegistrationPlateBefore2019ValidatorTest {

    @Test
    void isValid() {
        assertThat(SwedishVehicleRegistrationPlateBefore2019Validator.isValid("ABC 123"))
                .isTrue();
        assertThat(SwedishVehicleRegistrationPlateBefore2019Validator.isValid("EFD 678"))
                .isTrue();
        assertThat(SwedishVehicleRegistrationPlateBefore2019Validator.isValid("JPW 999"))
                .isTrue();
    }

    @Test
    void notValid() {
        assertThat(SwedishVehicleRegistrationPlateBefore2019Validator.isValid("ABCD 1234"))
                .isFalse();
        assertThat(SwedishVehicleRegistrationPlateBefore2019Validator.isValid("123 ABC"))
                .isFalse();
        assertThat(SwedishVehicleRegistrationPlateBefore2019Validator.isValid("AW 123"))
                .isFalse();
        assertThat(SwedishVehicleRegistrationPlateBefore2019Validator.isValid("ABC 12"))
                .isFalse();
        assertThat(SwedishVehicleRegistrationPlateBefore2019Validator.isValid("EQJ 555"))
                .isFalse();
        assertThat(SwedishVehicleRegistrationPlateBefore2019Validator.isValid("EKV 555"))
                .isFalse();
        assertThat(SwedishVehicleRegistrationPlateBefore2019Validator.isValid("EIV 555"))
                .isFalse();
    }

    @Test
    void testNull() {
        assertThatThrownBy(() -> SwedishVehicleRegistrationPlateBefore2019Validator.isValid(null))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void testBlank() {
        assertThat(SwedishVehicleRegistrationPlateBefore2019Validator.isValid(""))
                .isFalse();
    }
}