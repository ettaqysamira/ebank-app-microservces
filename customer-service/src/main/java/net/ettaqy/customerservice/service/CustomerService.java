package net.ettaqy.customerservice.service;

import net.ettaqy.customerservice.entities.Customer;
import net.ettaqy.customerservice.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    private CustomerRepository customerRepository;


    //injection des dependances par Contructor
    public CustomerService (CustomerRepository customerRepository){
        this.customerRepository=customerRepository;
    }

    public List<Customer> getAllCustomers(){

        return customerRepository.findAll();
    }


    public Customer findCustomerById(Long id){
        return customerRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Customer Not found"));
    }


    //ajouter customer
    public Customer saveCustomer(Customer customer){

        return customerRepository.save(customer); //save methode de JpaRepository
    }
}
