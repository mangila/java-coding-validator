package com.github.mangila.validator;

import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import java.util.regex.Pattern;

@Slf4j
public class SwedishPersonalNumberValidator {

    private static final Pattern SWEDISH_PERSONAL_NUMBER = Pattern.compile("^(19|20)\\d{6}([+\\-])\\d{4}");

    private SwedishPersonalNumberValidator() {
        // don´t invoke me
    }

    public static boolean isValid(@NotNull String swedishPersonalNumber) {
        var match = SWEDISH_PERSONAL_NUMBER.matcher(swedishPersonalNumber).matches();
        if (match) {
            var split = swedishPersonalNumber.split("([+\\-])");
            var basicIsoDate = split[0];
            var isValidDate = ValidatorUtil.isValidBasicIsoDate(basicIsoDate);
            if (isValidDate) {
                basicIsoDate = ValidatorUtil.removeCentury(basicIsoDate);
                var lastFourDigits = split[1];
                var hasValidLuhnDigit = ValidatorUtil.hasValidLuhnDigit(basicIsoDate.concat(lastFourDigits));
                if (!hasValidLuhnDigit) {
                    log.info("Not a valid checksum");
                }
                return hasValidLuhnDigit;
            }
            log.info("Not valid date");
            return false;
        }
        log.info("Not a valid format (yyyy-MM-dd-xxxx)");
        return false;
    }
}

