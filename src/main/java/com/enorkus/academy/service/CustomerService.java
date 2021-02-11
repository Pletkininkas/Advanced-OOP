package com.enorkus.academy.service;

import com.enorkus.academy.entity.Customer;
import com.enorkus.academy.repository.CustomerRepository;
import com.enorkus.academy.repository.MemoryCustomerRepository;
import com.enorkus.academy.validator.CustomerValidator;
import org.springframework.util.StringUtils;

import java.util.List;

public class CustomerService {

    private CustomerRepository customerRepository;
    private CustomerValidator customerValidator;

    public CustomerService() {
        customerRepository = new MemoryCustomerRepository();
        customerValidator = new CustomerValidator();
    }

    public List<Customer> fetchCustomers() {
        return customerRepository.findAll();
    }

    public void insertCustomer(Customer customer) {
        customerValidator.validateCustomer(customer);

        Customer newCustomer = new Customer.CustomerBuilder(StringUtils.capitalize(customer.getFirstName()),
                StringUtils.capitalize(customer.getLastName()), formatPersonalNumber(customer.getPersonalNumber()))
                .age(customer.getAge())
                .countryCode(customer.getCountryCode())
                .city(customer.getCity())
                .employer(customer.getEmployer())
                .gender(customer.getGender())
                .maritalStatus(customer.getMaritalStatus())
                .middleName(customer.getMiddleName())
                .monthlyIncome(customer.getMonthlyIncome())
                .build();

        customerRepository.insert(newCustomer);
    }

    public void deleteCustomer(String customerId) {
        customerRepository.deleteById(customerId);
    }

    private String formatPersonalNumber(String personalNumber) {
        if(personalNumber.length() > 4 && personalNumber.charAt(4) != '-') {
            return personalNumber.substring(0, 4) + '-' + personalNumber.substring(4);
        } else {
            return personalNumber;
        }
    }
}
