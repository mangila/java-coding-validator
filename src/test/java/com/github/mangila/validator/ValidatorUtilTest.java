package com.github.mangila.validator;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ValidatorUtilTest {

    @Test
    void isValidDateBasicIsoDate() {
        assertThat(ValidatorUtil.isValidBasicIsoDate("19990101"))
                .isTrue();
        assertThat(ValidatorUtil.isValidBasicIsoDate("19931209"))
                .isTrue();
        assertThat(ValidatorUtil.isValidBasicIsoDate("20201201"))
                .isTrue();
        assertThat(ValidatorUtil.isValidBasicIsoDate("20240226"))
                .isTrue();
        var nowString = LocalDate.now()
                .toString()
                .replaceAll("-", "");
        assertThat(ValidatorUtil.isValidBasicIsoDate(nowString))
                .isTrue();
    }

    @Test
    void isNotValidDateBasicIsoDateisBefore() {
        assertThat(ValidatorUtil.isValidBasicIsoDate("20771209"))
                .isFalse();
        assertThat(ValidatorUtil.isValidBasicIsoDate("20550101"))
                .isFalse();
    }

    @Test
    void isNotValidDateBasicIsoDateFormat() {
        assertThat(ValidatorUtil.isValidBasicIsoDate("123123123123"))
                .isFalse();
        assertThat(ValidatorUtil.isValidBasicIsoDate("20151515"))
                .isFalse();
    }

    @Test
    void hasValidLuhnDigit() {
        assertThat(ValidatorUtil.hasValidLuhnDigit("7802022389"))
                .isTrue();
        assertThat(ValidatorUtil.hasValidLuhnDigit("8204112380"))
                .isTrue();
    }

    @Test
    void testNull() {
        assertThatThrownBy(() -> ValidatorUtil.removeCentury(null))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> ValidatorUtil.isValidBasicIsoDate(null))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> ValidatorUtil.hasValidLuhnDigit(null))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void testBlank() {
        assertThatThrownBy(() -> ValidatorUtil.removeCentury(""))
                .isInstanceOf(StringIndexOutOfBoundsException.class);
        assertThat(ValidatorUtil.isValidBasicIsoDate("")).isFalse();
        assertThat(ValidatorUtil.hasValidLuhnDigit("")).isFalse();
    }
}