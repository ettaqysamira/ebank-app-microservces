package net.ettaqy.customerservice.controllers;

import net.ettaqy.customerservice.entities.Customer;
import net.ettaqy.customerservice.service.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerRestController {
    private CustomerService customerService;

    //constructeur
    public CustomerRestController(CustomerService customerService){
        this.customerService = customerService;
    }



    //récuperer tous customer
    @GetMapping("/customers")
    public List<Customer> getAllCustomers(){

        return customerService.getAllCustomers();
    }

    @GetMapping("/customers/{id}")
    //récuperer un seul customer by id
    public Customer findCustomerById(@PathVariable Long id){
        return customerService.findCustomerById(id);
    }

    @PostMapping("/customers")
    //ajouter customer
    public Customer saveCustomer(@RequestBody Customer customer){
    //methode Service
        return customerService.saveCustomer(customer);
    }

}
