package com.nhnacademy.shoppingmall.product.service;

import com.nhnacademy.shoppingmall.common.page.Page;
import com.nhnacademy.shoppingmall.product.domain.Product;

public interface ProductService {
    Product getProduct(int productId);

    void addProduct(Product product);

    void updateProduct(Product product);

    void deleteProduct(int productId);

    Page<Product> getPagedProducts(int page, int pageSize);
}
