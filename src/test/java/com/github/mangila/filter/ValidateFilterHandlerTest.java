package com.github.mangila.filter;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ValidateFilterHandlerTest {

    @Test
    void testChainSwedishPersonalNumberHandler() {
        var validateFilterHandler = new ValidateFilterHandler();
        validateFilterHandler.build(List.of(
                new NullDataValidateFilter(),
                new BlankDataValidateFilter(),
                new SwedishPersonalNumberValidateFilter()
        ));
        assertThat(validateFilterHandler.validate("19780202-2389")).isTrue();
    }

    @Test
    void testChainSwedishVehicleRegistrationPlateBefore2019Handler() {
        var validateFilterHandler = new ValidateFilterHandler();
        validateFilterHandler.build(List.of(
                new NullDataValidateFilter(),
                new BlankDataValidateFilter(),
                new SwedishVehicleRegistrationPlateBefore2019ValidateFilter()
        ));
        assertThat(validateFilterHandler.validate("ABC 123")).isTrue();
    }

    @Test
    void testChainSwedishPersonalNumberHandlerFail() {
        var validateFilterHandler = new ValidateFilterHandler();
        validateFilterHandler.build(List.of(
                new NullDataValidateFilter(),
                new BlankDataValidateFilter(),
                new SwedishPersonalNumberValidateFilter()
        ));
        assertThat(validateFilterHandler.validate(null)).isFalse();
        assertThat(validateFilterHandler.validate("   ")).isFalse();
        assertThat(validateFilterHandler.validate("19780202-1111")).isFalse();
    }

    @Test
    void testChainSwedishVehicleRegistrationPlateBefore2019HandlerFail() {
        var validateFilterHandler = new ValidateFilterHandler();
        validateFilterHandler.build(List.of(
                new NullDataValidateFilter(),
                new BlankDataValidateFilter(),
                new SwedishVehicleRegistrationPlateBefore2019ValidateFilter()
        ));
        assertThat(validateFilterHandler.validate(null)).isFalse();
        assertThat(validateFilterHandler.validate("   ")).isFalse();
        assertThat(validateFilterHandler.validate("EQJ 555")).isFalse();
    }
}