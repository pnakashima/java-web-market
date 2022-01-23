package com.devinhouse.market.model.transport;

import com.devinhouse.market.model.persistence.Product;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class ProductDTO {

    private String identifier;

    private String name;

    private String description;

    private BigDecimal price;

    @JsonProperty("category")  // com essa anotacao, qdo vem do front end um json com category, o java ja sabe q é o categoryDTO
    private CategoryDTO categoryDTO;

    public ProductDTO() {
    }

    public ProductDTO(String name, String description, BigDecimal price, CategoryDTO categoryDTO) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.categoryDTO = categoryDTO;
    }

    public ProductDTO(Product product) {
        this.name = product.getName();
        this.description = product.getDescription();
        this.price = product.getPrice();
        this.categoryDTO = product.getCategory().generateTransportObject();
        this.identifier = product.getIdentifier();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public CategoryDTO getCategoryDTO() {
        return categoryDTO;
    }

    public void setCategoryDTO(CategoryDTO categoryDTO) {
        this.categoryDTO = categoryDTO;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }
}
