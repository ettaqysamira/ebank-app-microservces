package net.ettaqy.ebankservice.controllers;

import net.ettaqy.ebankservice.entities.BankAccount;
import net.ettaqy.ebankservice.services.EbankService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EbankRestController {
    public EbankService ebankService;

    public EbankRestController(EbankService ebankService){
        this.ebankService= ebankService;
    }

    //tout bankAccount
    @GetMapping("/accounts")
    public List<BankAccount> getAllBankAccounts(){
        return ebankService.getAllBankAccounts();
    }

    @GetMapping("/accounts/{id}")
    public BankAccount getBankAccountBuId(@PathVariable String id){
        return ebankService.getBankAccountById(id);
    }

    //add bankAccount
    @PostMapping("/accounts")
    public BankAccount save(@RequestBody BankAccount bankAccount){
        return ebankService.save(bankAccount);
    }



}
