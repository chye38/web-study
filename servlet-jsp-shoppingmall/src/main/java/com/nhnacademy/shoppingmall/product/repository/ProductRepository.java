package com.nhnacademy.shoppingmall.product.repository;

import com.nhnacademy.shoppingmall.product.domain.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    List<Product> getProductList();
    int save(Product product);
    int update(Product product);
    int deleteByProductId(int productId);
    Product getProduct(int productId);
    List<Product> findProductsByPage(int page, int pageSize);
    int countAll();
}
