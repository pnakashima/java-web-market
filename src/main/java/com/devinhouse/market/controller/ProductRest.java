package com.devinhouse.market.controller;

import com.devinhouse.market.model.transport.ProductDTO;
import com.devinhouse.market.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.GeneratedValue;
import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductRest {

    private final ProductService productService;

    public ProductRest(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/create")
    public ResponseEntity<HttpStatus> create(@RequestBody ProductDTO product) {
        return productService.create(product);
    }

    @PutMapping("/update/{identifier}")
    public ResponseEntity<HttpStatus> update(@RequestBody ProductDTO product, @PathVariable String identifier) {
        return productService.update(product, identifier);
    }

    @GetMapping("/list")
    public List<ProductDTO> listAll() {
        return this.productService.listAll();
    }

    @DeleteMapping("/delete/{identifier}")
    public ResponseEntity<HttpStatus> delete(@PathVariable String identifier) {
        return this.productService.delete(identifier);
    }

    @GetMapping("/{identifier}")
    public ProductDTO getProduct(@PathVariable String identifier) throws Exception {
        return this.productService.getProductByIdentifier(identifier);
    }

}
