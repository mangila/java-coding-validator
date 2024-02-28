package com.github.mangila.filter;

import com.github.mangila.validator.SwedishPersonalNumberValidator;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

@Slf4j
public class SwedishPersonalNumberValidateFilter implements ValidateFilter {

    private ValidateFilter nextValidateFilter;

    @Override
    public boolean validate(String data) {
        if (SwedishPersonalNumberValidator.isValid(data)) {
            return true;
        }
        return Objects.nonNull(nextValidateFilter) && nextValidateFilter.validate(data);
    }

    @Override
    public void setNextFilter(ValidateFilter validateFilter) {
        this.nextValidateFilter = validateFilter;
    }
}
