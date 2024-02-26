package com.github.mangila.validator;

import org.apache.commons.validator.routines.checkdigit.LuhnCheckDigit;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ValidatorUtil {

    private ValidatorUtil() {
        // don´t invoke me
    }

    public static boolean isValidBasicIsoDate(@NotNull String basicIsoDate) {
        try {
            var date = LocalDate.parse(basicIsoDate, DateTimeFormatter.BASIC_ISO_DATE);
            return date.isBefore(LocalDate.now());
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public static String removeCentury(@NotNull String basicIsoDate) throws StringIndexOutOfBoundsException {
        return basicIsoDate.substring(2);
    }

    public static boolean hasValidLuhnDigit(@NotNull String code) {
        return LuhnCheckDigit.LUHN_CHECK_DIGIT.isValid(code);
    }
}
