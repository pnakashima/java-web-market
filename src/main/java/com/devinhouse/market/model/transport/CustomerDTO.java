package com.devinhouse.market.model.transport;

import com.devinhouse.market.model.persistence.Customer;
import com.devinhouse.market.model.persistence.Purchase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.persistence.*;
import java.time.LocalDate;

public class CustomerDTO {

    private Long id;

    private String identifier;

    private String name;

    private String cpf;

    private String email;

    private String phoneNumber;

    private LocalDate dob;

    public Purchase purchase;

    public CustomerDTO() {
    }

    public CustomerDTO(Long id, String identifier, String name, String cpf, String email, String phoneNumber, LocalDate dob, Purchase purchase) {
        this.id = id;
        this.identifier = identifier;
        this.name = name;
        this.cpf = cpf;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.dob = dob;
        this.purchase = purchase;
    }

    public CustomerDTO(Customer customer) {
        this.identifier = customer.getIdentifier();
        this.name = customer.getName();
        this.cpf = customer.getCpf();
        this.email = customer.getCpf();
        this.phoneNumber = customer.getPhoneNumber();
        this.dob = customer.getDob();
        this.purchase = customer.getPurchase();
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
        return "CustomerDTO{" +
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

//    public ResponseEntity<HttpStatus> update(CustomerDTO customerDTO, String identifier) {
//    }
}
