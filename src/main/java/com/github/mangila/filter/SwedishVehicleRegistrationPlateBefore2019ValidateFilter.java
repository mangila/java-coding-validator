package com.github.mangila.filter;

import com.github.mangila.validator.SwedishVehicleRegistrationPlateBefore2019Validator;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

@Slf4j
public class SwedishVehicleRegistrationPlateBefore2019ValidateFilter implements ValidateFilter {

    private ValidateFilter nextValidateFilter;

    @Override
    public boolean validate(String data) {
        if (SwedishVehicleRegistrationPlateBefore2019Validator.isValid(data)) {
            return true;
        }
        return Objects.nonNull(nextValidateFilter) && nextValidateFilter.validate(data);
    }

    @Override
    public void setNextFilter(ValidateFilter validateFilter) {
        this.nextValidateFilter = validateFilter;
    }
}
