package com.nhnacademy.shoppingmall.product.repository.impl;

import com.nhnacademy.shoppingmall.common.mvc.transaction.DbConnectionThreadLocal;
import com.nhnacademy.shoppingmall.product.domain.Product;
import com.nhnacademy.shoppingmall.product.repository.ProductRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductRepositroyImpl implements ProductRepository {

    @Override
    public List<Product> getProductList() {
        // DB에서 모든 상품 목록을 조회합니다.
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "SELECT ProductID, ProductName, ProductDescription, Price, Path FROM Products";

        List<Product> products = new ArrayList<>();

        try (PreparedStatement pstmt = connection.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Product product = new Product(
                        rs.getInt("ProductID"),
                        rs.getString("ProductName"),
                        rs.getString("ProductDescription"),
                        rs.getInt("Price"),
                        rs.getString("Path")
                );
                products.add(product);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return products;
    }

    @Override
    public int save(Product product) {
        // 새로운 상품을 DB에 저장합니다.
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "INSERT INTO Products (ProductName, ProductDescription, Price, Path) VALUES (?,?,?,?)";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, product.getProductName());
            pstmt.setString(2, product.getProductDescription());
            pstmt.setInt(3, product.getPrice());
            pstmt.setString(4, product.getPath());

            return pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int update(Product product) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "UPDATE Products SET  ProductName = ?, ProductDescription = ?, Price = ?, Path = ? WHERE ProductID = ?";

        try(PreparedStatement pstmt = connection.prepareStatement(sql)){
            pstmt.setString(1, product.getProductName());
            pstmt.setString(2, product.getProductDescription());
            pstmt.setInt(3, product.getPrice());
            pstmt.setString(4, product.getPath());
            pstmt.setInt(5, product.getProductId());
            return pstmt.executeUpdate();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public int deleteByProductId(int productId) {
        // 상품 ID를 기준으로 상품을 삭제합니다.
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "DELETE FROM Products WHERE ProductID = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, productId);
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Product getProduct (int productId) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "SELECT ProductId, ProductName, ProductDescription, Price, Path FROM Products WHERE ProductID = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, productId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Product product = new Product(
                            rs.getInt("ProductId"),
                            rs.getString("ProductName"),
                            rs.getString("ProductDescription"),
                            rs.getInt("Price"),
                            rs.getString("Path")
                    );

                    return product;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    /**
     * 페이징 처리된 상품 목록을 조회하는 메서드
     * @param page 페이지 번호 (1부터 시작)
     * @param pageSize 한 페이지에 보여줄 상품 수
     * @return 페이징 처리된 상품 목록
     */
    public List<Product> findProductsByPage(int page, int pageSize) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        // 최신 상품 순으로 정렬하기 위해 ProductID를 내림차순으로 정렬합니다.
        String sql = "SELECT ProductID, ProductName, ProductDescription, Price, Path FROM Products ORDER BY ProductID ASC LIMIT ? OFFSET ?";

        List<Product> products = new ArrayList<>();

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, pageSize);
            // OFFSET 계산: (페이지번호 - 1) * 페이지크기
            pstmt.setInt(2, (page - 1) * pageSize);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Product product = new Product(
                            rs.getInt("ProductID"),
                            rs.getString("ProductName"),
                            rs.getString("ProductDescription"),
                            rs.getInt("Price"),
                            rs.getString("Path")
                    );
                    products.add(product);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return products;
    }

    /**
     * 전체 상품의 개수를 조회하는 메서드
     * @return 전체 상품 수
     */
    public int countAll() {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "SELECT COUNT(*) FROM Products";
        int count = 0;

        try (PreparedStatement pstmt = connection.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return count;
    }
}