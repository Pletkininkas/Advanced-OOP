package com.enorkus.academy.validator;

import com.enorkus.academy.entity.CountryCode;
import com.enorkus.academy.exception.ValidationException;
import org.apache.commons.lang3.EnumUtils;

public class CountryCodeValidator extends Validator<String> {
    @Override
    public void validate(String attribute, String message) {
        if(!attribute.trim().equals("") && !EnumUtils.isValidEnum(CountryCode.class, attribute)) {
            throw new ValidationException(message);
        }
    }
}
