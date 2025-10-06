package com.nhnacademy.shoppingmall.product.domain;

import java.util.Objects;

public class Product {
    private int productId;
    private String productName;
    private String productDescription;
    private int price;
    private String path;

    public Product(int productId, String productName, String productDescription, int price, String path) {
        this.productId = productId;
        this.productName = productName;
        this.productDescription = productDescription;
        this.price = price;
        this.path = path;
    }

    public Product(String productName, String productDescription, int price, String path) {
        this.productName = productName;
        this.productDescription = productDescription;
        this.price = price;
        this.path = path;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public int getPrice() {
        return price;
    }

    public void setProductPrice(int price) {
        this.price = price;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return price == product.price &&
                Objects.equals(productName, product.productName) &&
                Objects.equals(productDescription, product.productDescription) &&
                Objects.equals(path, product.path);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productName, productDescription, price, path);
    }
}
