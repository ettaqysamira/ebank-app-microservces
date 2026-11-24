package net.ettaqy.customerservice.service;


import net.ettaqy.customerservice.entities.Customer;
import net.ettaqy.customerservice.repository.CustomerRepository;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Tool(description = "Get all customers")
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Tool(description = "Find customer by ID")
    public Customer findCustomerById(@ToolParam(description = "The customer ID") Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
    }

    @Tool(description = "Save a new customer with name and email")
    public Customer saveCustomer(@ToolParam(description = "Customer object with name and email") Customer customer) {
        return customerRepository.save(customer);
    }
}
