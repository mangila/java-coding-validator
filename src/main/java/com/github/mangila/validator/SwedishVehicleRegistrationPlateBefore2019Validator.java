package com.github.mangila.validator;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import java.util.regex.Pattern;

@Slf4j
@UtilityClass
public class SwedishVehicleRegistrationPlateBefore2019Validator {
    private static final Pattern SWEDISH_REGISTRATION_PLATE_BEFORE_2019 = Pattern.compile("^((?![IQV])[A-Z]){3} \\d{3}");

    public static boolean isValid(@NotNull String swedishRegistrationPlate) {
        if (SWEDISH_REGISTRATION_PLATE_BEFORE_2019.matcher(swedishRegistrationPlate).matches()) {
            return true;
        }
        log.info("Not a valid swedish BEFORE_2019 registration plate");
        return false;
    }
}
