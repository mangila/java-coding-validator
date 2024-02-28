package com.github.mangila.validator;

import lombok.experimental.UtilityClass;
import org.apache.commons.validator.routines.checkdigit.LuhnCheckDigit;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@UtilityClass
public class ValidatorUtil {

    public static boolean isValidBasicIsoDate(@NotNull String basicIsoDate) {
        try {
            var date = LocalDate.parse(basicIsoDate, DateTimeFormatter.BASIC_ISO_DATE);
            var now = LocalDate.now();
            return date.isBefore(now) || date.isEqual(now);
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

    public static boolean isOld(@NotNull String basicIsoDate) {
        try {
            var date = LocalDate.parse(basicIsoDate, DateTimeFormatter.BASIC_ISO_DATE);
            var now = LocalDate.now();
            return now.minusYears(date.getYear()).getYear() >= 100;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}
