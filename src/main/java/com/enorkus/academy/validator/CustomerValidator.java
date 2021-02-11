package com.enorkus.academy.validator;

import com.enorkus.academy.entity.Customer;

public class CustomerValidator {

    private MandatoryValueValidator mandatoryValueValidator;
    private CustomerAdultValidator customerAdultValidator;
    private CountryCodeValidator countryCodeValidator;

    public CustomerValidator() {
        this.mandatoryValueValidator = new MandatoryValueValidator();
        this.customerAdultValidator = new CustomerAdultValidator();
        this.countryCodeValidator = new CountryCodeValidator();
    }

    public void validateCustomer(Customer customer) {
        mandatoryValueValidator.validate(customer.getFirstName(), "First name is mandatory!");
        mandatoryValueValidator.validate(customer.getLastName(), "Last name is mandatory!");
        mandatoryValueValidator.validate(customer.getPersonalNumber(), "Personal number is mandatory!");
        customerAdultValidator.validate(customer.getAge(), "Customer must be 18 or older!");
        countryCodeValidator.validate(customer.getCountryCode(), "Invalid country code!");
    }
}
