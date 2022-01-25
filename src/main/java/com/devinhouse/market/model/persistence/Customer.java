package com.devinhouse.market.model.persistence;

import com.devinhouse.market.model.transport.CustomerDTO;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String identifier;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String cpf;

    @Column(unique = true)
    private String email;

    private String phoneNumber;

    private LocalDate dob;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "purchase_id")
    private Purchase purchase;

    public Customer(Long id, String identifier, String name, String cpf, String email, String phoneNumber, LocalDate dob, Purchase purchase) {
        this.id = id;
        this.identifier = identifier;
        this.name = name;
        this.cpf = cpf;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.dob = dob;
        this.purchase = purchase;
    }

    public Customer(CustomerDTO customerDTO) {
        this.name = customerDTO.getName();
        this.cpf = customerDTO.getCpf();
        this.email = customerDTO.getEmail();
        this.phoneNumber = customerDTO.getPhoneNumber();
        this.dob = customerDTO.getDob();
    }

    public Customer() {

    }

    public Long getId() {
        return id;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public Purchase getPurchase() {
        return purchase;
    }

    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", identifier='" + identifier + '\'' +
                ", name='" + name + '\'' +
                ", cpf='" + cpf + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", dob=" + dob +
                ", purchase=" + purchase +
                '}';
    }
}
