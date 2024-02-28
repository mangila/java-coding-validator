package com.github.mangila.filter;

import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

@Slf4j
public class BlankDataValidateFilter implements ValidateFilter {

    private ValidateFilter nextValidateFilter;

    @Override
    public boolean validate(String data) {
        if (data.isBlank()) {
            log.info("Data is blank");
            return false;
        }
        return Objects.nonNull(nextValidateFilter) && nextValidateFilter.validate(data);
    }

    @Override
    public void setNextFilter(ValidateFilter validateFilter) {
        this.nextValidateFilter = validateFilter;
    }
}
