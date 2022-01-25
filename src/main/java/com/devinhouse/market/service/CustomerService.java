package com.devinhouse.market.service;

import com.devinhouse.market.model.persistence.Customer;
import com.devinhouse.market.model.repository.CustomerRepository;
import com.devinhouse.market.model.transport.CustomerDTO;
import com.devinhouse.market.utils.Utils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public class CustomerService {

    private final Logger LOG = LogManager.getLogger(CustomerService.class);

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public ResponseEntity<HttpStatus> create(CustomerDTO customerDTO) {
        this.LOG.info("Validando os parâmetros da requisição");
        try {
            checkIfDTOIsNull(customerDTO);
        } catch (Exception e) {
            this.LOG.error("Erro ao salvar Cliente " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }

        this.LOG.info("Criando novo cliente");
        Customer customer = new Customer(customerDTO);
        customer.setIdentifier(Utils.generateUUID());
        this.customerRepository.save(customer);
        this.LOG.info("Cliente criado");
        return ResponseEntity.accepted().build();

    }

    private void checkIfDTOIsNull(CustomerDTO customerDTO) throws Exception {
        if (customerDTO == null) {
            throw new Exception("O parâmetro está nulo");
        }
    }


}
