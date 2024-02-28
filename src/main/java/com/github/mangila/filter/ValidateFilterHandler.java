package com.github.mangila.filter;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class ValidateFilterHandler implements ValidateFilter {

    private ValidateFilter nextValidateFilter;

    @Override
    public boolean validate(String data) {
        return nextValidateFilter.validate(data);
    }

    @Override
    public void setNextFilter(ValidateFilter validateFilter) {
        this.nextValidateFilter = validateFilter;
    }

    public void build(List<ValidateFilter> validateFilters) {
        this.nextValidateFilter = validateFilters.getFirst();
        for (int i = 0; i <= validateFilters.size(); i++) {
            var h1 = validateFilters.get(i);
            if ((i + 1) == validateFilters.size()) {
                break;
            }
            var h2 = validateFilters.get(i + 1);
            h1.setNextFilter(h2);
        }
    }
}
