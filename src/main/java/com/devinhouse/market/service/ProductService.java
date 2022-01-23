package com.devinhouse.market.service;

import com.devinhouse.market.model.persistence.Product;
import com.devinhouse.market.model.repository.ProductRepository;
import com.devinhouse.market.model.transport.ProductDTO;
import com.devinhouse.market.utils.Utils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    private final Logger LOG = LogManager.getLogger(ProductService.class);

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ResponseEntity<HttpStatus> create(ProductDTO productDTO) {
        checkIfProductIsNull(productDTO);
        this.LOG.info("Preparando para converter o produto" + productDTO.getName() + " em persistência (salvar o produto no banco)");
        productDTO.setIdentifier(Utils.generateUUID());
        Product product = new Product(productDTO);
        Product saved = this.productRepository.save(product);

        if (saved == null) {
            this.LOG.error("Erro ao salvar o produto no banco");
            return ResponseEntity.badRequest().build();
        }
        this.LOG.info("Produto criado com sucesso");
        return ResponseEntity.ok().build();
    }

    private void checkIfProductIsNull(ProductDTO productDTO) {
        this.LOG.info("Validando os parâmetros da requisição");
        if (productDTO == null) {
            this.LOG.error("Erro parâmetro ProductDTO nulo!");
            throw new IllegalArgumentException("O objeto enviado está nulo.");
        }
    }

    public ResponseEntity<HttpStatus> update(ProductDTO productDTO) {
        checkIfProductIsNull(productDTO);
        this.LOG.info("Buscando o produto no banco");
        Product product = this.productRepository.findByIdentifier(productDTO.getIdentifier());
        Product product1 = new Product(productDTO);
        product1.setId(product.getId());
        this.productRepository.save(product1);
        this.LOG.info("Produto atualizado");
        return ResponseEntity.ok().build();
    }

    public List<ProductDTO> listAll() {
        this.LOG.info("Buscando produtos no banco");
        List<ProductDTO> productsDTO = new ArrayList<>();
        Iterable<Product> iterable = this.productRepository.findAll();
        iterable.forEach(p->productsDTO.add(new ProductDTO(p)));
        this.LOG.info("Encontrados " + productsDTO.size() + " produtos no banco");
        return productsDTO;
    }

    public ResponseEntity<HttpStatus> delete(String identifier) {
        if (identifier==null || identifier.isEmpty()) {
            this.LOG.error("O ID está nulo");
            throw new IllegalArgumentException("Erro ID vazio");
        }

        this.productRepository.deleteByIdentifier(identifier);
        this.LOG.info("Produto deletado");
        return ResponseEntity.accepted().build();
    }
}
