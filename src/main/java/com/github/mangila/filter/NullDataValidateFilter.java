package com.github.mangila.filter;

import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

@Slf4j
public class NullDataValidateFilter implements ValidateFilter {

    private ValidateFilter nextValidateFilter;

    @Override
    public boolean validate(String data) {
        if (Objects.isNull(data)) {
            log.info("Data is null");
            return false;
        }
        return Objects.nonNull(nextValidateFilter) && nextValidateFilter.validate(data);
    }

    @Override
    public void setNextFilter(ValidateFilter validateFilter) {
        this.nextValidateFilter = validateFilter;
    }
}
