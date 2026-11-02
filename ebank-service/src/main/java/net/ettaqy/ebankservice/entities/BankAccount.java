package net.ettaqy.ebankservice.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import lombok.*;
import net.ettaqy.ebankservice.model.Customer;

import java.util.Date;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class BankAccount {
    @Id
    private String id;
    private Date createdAt;
    private double balance;
    private String type;
    private long customerId;
    //indiquer à jpa cette attribut existe dans la classe  et pas representer dans bdd
    @Transient
    private Customer customer;
}
