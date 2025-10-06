package com.nhnacademy.shoppingmall.product.service.impl;

import com.nhnacademy.shoppingmall.common.page.Page;
import com.nhnacademy.shoppingmall.product.domain.Product;
import com.nhnacademy.shoppingmall.product.repository.ProductRepository;
import com.nhnacademy.shoppingmall.product.service.ProductService;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product getProduct(int productId) {
        log.debug("getProduct");
        return productRepository.getProduct(productId);
    }

    @Override
    public void addProduct(Product product) {
        log.info("Adding product with id " + product.getProductId());
        productRepository.save(product);
    }

    @Override
    public void updateProduct(Product product) {
        log.info("update product");
        productRepository.update(product);
    }

    @Override
    public void deleteProduct(int productId) {
        log.debug("Delete product with id: " + productId);
        productRepository.deleteByProductId(productId);
    }

    @Override
    public Page<Product> getPagedProducts(int page, int pageSize) {
        // 페이징 처리된 상품 목록을 가져옵니다.
        long totalCount = productRepository.countAll();
        List<Product> productList = productRepository.findProductsByPage(page, pageSize);
        return new Page<>(productList, totalCount);
    }
}
