package com.github.mangila.validator;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import java.util.regex.Pattern;

@Slf4j
@UtilityClass
public class SwedishPersonalNumberValidator {

    private static final Pattern SWEDISH_PERSONAL_NUMBER = Pattern.compile("^(19|20)\\d{6}([+\\-])\\d{4}\\z");

    /**
     * 1. Do I match the pattern yyyyMMdd(-/+)-xxxx
     * 2. Am I a valid date? And not in the future?
     * 3. Am I old? Am I over 100 years of age and have a plus sign in my delimiter?
     * 4. Do I have a valid Luhn checksum?
     */
    public static boolean isValid(@NotNull String swedishPersonalNumber) {
        var match = SWEDISH_PERSONAL_NUMBER.matcher(swedishPersonalNumber).matches();
        if (match) { // 1
            var delimiter = swedishPersonalNumber.charAt(8);
            var split = swedishPersonalNumber.split("\\" + delimiter);
            var basicIsoDate = split[0];
            var isValidDate = ValidatorUtil.isValidBasicIsoDate(basicIsoDate); //2
            if (isValidDate) {
                var isOld = ValidatorUtil.isOld(basicIsoDate); // 3
                if (!isOld && delimiter == '+') {
                    log.info("Not a valid format (yyyyMMdd(-/+)xxxx)");
                    return false;
                }
                basicIsoDate = ValidatorUtil.removeCentury(basicIsoDate);
                var lastFourDigits = split[1];
                var hasValidLuhnDigit = ValidatorUtil.hasValidLuhnDigit(basicIsoDate.concat(lastFourDigits)); // 4
                if (!hasValidLuhnDigit) {
                    log.info("Not a valid checksum");
                }
                return hasValidLuhnDigit;
            }
            log.info("Not valid date");
            return false;
        }
        log.info("Not a valid format (yyyyMMdd(-/+)xxxx)");
        return false;
    }
}

