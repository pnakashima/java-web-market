package com.devinhouse.market.service;

import com.devinhouse.market.model.persistence.Category;
import com.devinhouse.market.model.persistence.Product;
import com.devinhouse.market.model.repository.ProductRepository;
import com.devinhouse.market.model.transport.ProductDTO;
import com.devinhouse.market.utils.Utils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    private final Logger LOG = LogManager.getLogger(ProductService.class);

    private final CategoryService categoryService;

    public ProductService(ProductRepository productRepository, CategoryService categoryService) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
    }

    public ResponseEntity<HttpStatus> create(ProductDTO productDTO) {
        checkIfProductIsNull(productDTO);
        this.LOG.info("Preparando para converter o produto " + productDTO.getName() + " em persistência (salvar o produto no banco)");
        productDTO.setIdentifier(Utils.generateUUID());
        Product product = new Product(productDTO);

        Category category = this.categoryService.checkExistentCategory(productDTO.getCategoryDTO());
        product.setCategory(category);

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


    @Transactional
    public ResponseEntity<HttpStatus> update(ProductDTO productDTO, String identifier) {
        checkIfProductIsNull(productDTO);

        this.LOG.info("Buscando o produto no banco");
        Product product = this.productRepository.findByIdentifier(identifier);

        if (product==null) {
            this.LOG.info("Identificador não encontrado: " + identifier);
            return ResponseEntity.notFound().build();
        }

        this.LOG.info("Produto encontrado, atualizando...");
        product.setDescription(productDTO.getDescription());
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());

//        ou usa essa linha abaixo, ou o @Transactional
//        this.productRepository.save(product);

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

    public ProductDTO getProductByIdentifier(String identifier) throws Exception {
        if (identifier == null || identifier.isEmpty()) {
            this.LOG.error("Identificador nulo");
            throw new IllegalArgumentException("Identificador nulo");
        }
        this.LOG.info("Buscando produto com identificador " + identifier);
        Product product = this.productRepository.findByIdentifier(identifier);

        if(product==null) {
            this.LOG.error("Produto não encontrado");
            throw new Exception("Produto não encontrado");
        }

        this.LOG.info("Produto" + product.getName() + "encontrado");
        return new ProductDTO(product);

    }
}
