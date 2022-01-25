package com.devinhouse.market.model.persistence;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;


@Entity
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String identifier;

    private LocalDate purchaseDate;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "purchases_products", joinColumns =
            {@JoinColumn(name = "purchase_id")}, inverseJoinColumns = {
            @JoinColumn(name = "product_id")
    })
    private List<Product> products;

    public Purchase(Long id, String identifier, LocalDate purchaseDate, List<Product> products) {
        this.id = id;
        this.identifier = identifier;
        this.purchaseDate = purchaseDate;
        this.products = products;
    }

    public Purchase() {
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

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Purchase{" +
                "id=" + id +
                ", identifier='" + identifier + '\'' +
                ", purchaseDate=" + purchaseDate +
                ", products=" + products +
                '}';
    }
}
