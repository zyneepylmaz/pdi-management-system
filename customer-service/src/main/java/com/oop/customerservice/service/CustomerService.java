package com.oop.customerservice.service;

import com.oop.customerservice.dto.CustomerRequest;
import com.oop.customerservice.dto.CustomerResponse;
import com.oop.customerservice.entity.Customer;
import com.oop.customerservice.exception.ResourceNotFoundException;
import com.oop.customerservice.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public CustomerResponse createCustomer(CustomerRequest request) {
        Customer customer = new Customer(
                request.getCompanyName(),
                request.getSector(),
                request.getContactPerson(),
                request.getContactEmail(),
                request.getPhoneNumber(),
                request.getAddress(),
                true
        );

        Customer savedCustomer = customerRepository.save(customer);
        return mapToResponse(savedCustomer);
    }

    public List<CustomerResponse> getAllCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public List<CustomerResponse> getActiveCustomers() {
        return customerRepository.findByActiveTrue()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public CustomerResponse getCustomerById(Long id) {
        Customer customer = findCustomerById(id);
        return mapToResponse(customer);
    }

    public CustomerResponse updateCustomer(Long id, CustomerRequest request) {
        Customer customer = findCustomerById(id);

        customer.setCompanyName(request.getCompanyName());
        customer.setSector(request.getSector());
        customer.setContactPerson(request.getContactPerson());
        customer.setContactEmail(request.getContactEmail());
        customer.setPhoneNumber(request.getPhoneNumber());
        customer.setAddress(request.getAddress());

        Customer updatedCustomer = customerRepository.save(customer);
        return mapToResponse(updatedCustomer);
    }

    public void deleteCustomer(Long id) {
        Customer customer = findCustomerById(id);
        customerRepository.delete(customer);
    }

    public CustomerResponse deactivateCustomer(Long id) {
        Customer customer = findCustomerById(id);
        customer.setActive(false);

        Customer updatedCustomer = customerRepository.save(customer);
        return mapToResponse(updatedCustomer);
    }

    private Customer findCustomerById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + id));
    }

    private CustomerResponse mapToResponse(Customer customer) {
        return new CustomerResponse(
                customer.getId(),
                customer.getCompanyName(),
                customer.getSector(),
                customer.getContactPerson(),
                customer.getContactEmail(),
                customer.getPhoneNumber(),
                customer.getAddress(),
                customer.getActive(),
                customer.getCreatedAt()
        );
    }
}