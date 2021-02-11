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
        String modifiedPersonalNumber = customer.getPersonalNumber();

        if(customer.getPersonalNumber().length() > 4) {
            modifiedPersonalNumber = customer.getPersonalNumber().substring(0, 4) + '-' + customer.getPersonalNumber().substring(4);
        }

        Customer newCustomer = new Customer.CustomerBuilder(StringUtils.capitalize(customer.getFirstName()),
                StringUtils.capitalize(customer.getLastName()), modifiedPersonalNumber)
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
}
