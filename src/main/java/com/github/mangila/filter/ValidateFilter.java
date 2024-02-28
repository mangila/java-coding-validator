package com.github.mangila.filter;

public interface ValidateFilter {

    boolean validate(String data);

    void setNextFilter(ValidateFilter validateFilter);
}
