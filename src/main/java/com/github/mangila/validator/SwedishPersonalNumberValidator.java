package com.github.mangila.validator;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.validator.routines.checkdigit.LuhnCheckDigit;
import org.jetbrains.annotations.NotNull;

import java.util.regex.Pattern;

@Slf4j
public class SwedishPersonalNumberValidator {

    private static final Pattern SWEDISH_PERSONAL_NUMBER = Pattern.compile("^(19|20)\\d{6}([+\\-])\\d{4}");
    private static final Pattern SWEDISH_PERSONAL_NUMBER_DELIMITER = Pattern.compile("([+\\-])");

    private SwedishPersonalNumberValidator() {
        // don´t invoke me
    }

    public static boolean isValid(@NotNull String swedishPersonalNumber) {
        if (SWEDISH_PERSONAL_NUMBER.matcher(swedishPersonalNumber).matches()) {
            swedishPersonalNumber = removeCentury(swedishPersonalNumber);
            swedishPersonalNumber = removeDelimiter(swedishPersonalNumber);
            var hasValidLuhnDigit = LuhnCheckDigit.LUHN_CHECK_DIGIT.isValid(swedishPersonalNumber);
            if (!hasValidLuhnDigit) {
                log.info("Not a valid Swedish personal number (wrong checksum)");
            }
            return hasValidLuhnDigit;
        }
        log.info("Not a valid Swedish personal number (might have wrong structure)");
        return false;
    }

    /**
     * @param swedishPersonalNumber "19780202-2389".substring(2)
     * @return "780202-2389"
     */
    private static String removeCentury(String swedishPersonalNumber) {
        return swedishPersonalNumber.substring(2);
    }

    /**
     * @param swedishPersonalNumber "19780202-2389" "19780202+2389"
     * @return "197802022389"
     */
    private static String removeDelimiter(String swedishPersonalNumber) {
        return SWEDISH_PERSONAL_NUMBER_DELIMITER.matcher(swedishPersonalNumber).replaceAll("");
    }
}

