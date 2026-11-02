package net.ettaqy.ebankservice.services;

import net.ettaqy.ebankservice.entities.BankAccount;
import net.ettaqy.ebankservice.feign.CustomerRestClient;
import net.ettaqy.ebankservice.model.Customer;
import net.ettaqy.ebankservice.repository.BankAccountRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service //cette classe est bean
public class EbankService {

    private BankAccountRepository accountRepository;
    //injection de dep par constructeur (relation entre repository et service)
    public EbankService(BankAccountRepository accountRepository, CustomerRestClient customerRestClient){
        this.accountRepository= accountRepository;
        this.customerRestClient=customerRestClient;
    }

    //tout bankAccount
    public List<BankAccount> getAllBankAccounts(){
        return accountRepository.findAll();
    }


    //interface openFeign
    private CustomerRestClient customerRestClient;
    public BankAccount getBankAccountById(String id){
       BankAccount bankAccount =  accountRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Account Not Found"));
       bankAccount.setCustomer(
               customerRestClient.getCustomerById(bankAccount.getCustomerId())
       );
       return bankAccount;
    }

    //add bankAccount
    public BankAccount save(BankAccount bankAccount){
        try {
            Customer customer = customerRestClient.getCustomerById(bankAccount.getCustomerId());
            bankAccount.setId(UUID.randomUUID().toString());
            bankAccount.setCreatedAt(new Date());
            return accountRepository.save(bankAccount);
        }catch (Exception e){
            throw  new RuntimeException(e.getMessage());
        }

    }


}