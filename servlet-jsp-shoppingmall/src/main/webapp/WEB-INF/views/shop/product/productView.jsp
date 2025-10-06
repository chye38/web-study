<%--
  Created by IntelliJ IDEA.
  User: chye
  Date: 25. 10. 6.
  Time: 오후 10:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" session="true" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="container mt-5" style="max-width: 800px;">
    <div class="card mb-3">
        <!-- 상품 이미지 -->
        <img src="${product.path}" class="card-img-top" alt="${product.productName}" style="height: 400px; object-fit: contain; background-color: #f8f9fa;" />

        <div class="card-body">
            <!-- 상품 이름 -->
            <h3 class="card-title">${product.productName}</h3>

            <!-- 상품 설명 -->
            <p class="card-text">${product.productDescription}</p>

            <!-- 상품 가격 -->
            <h4 class="card-text text-primary">가격: <fmt:formatNumber value="${product.price}" type="number" /> 원</h4>

            <div class="d-flex justify-content-between align-items-center mt-4">
                <!-- 관리자만 수정 가능 -->
                <c:if test="${not empty user and user.userAuth == 'ROLE_ADMIN'}">
                    <a href="/admin/productEdit.do?id=${product.productId}" class="btn btn-warning">상품 수정</a>
                </c:if>

                <!-- 장바구니 추가 버튼 -->
                <form method="post" action="/cart/add.do" class="ms-auto">
                    <input type="hidden" name="productId" value="${product.productId}" />
                    <button type="submit" class="btn btn-success">장바구니에 추가</button>
                </form>
            </div>
        </div>
    </div>
</div>
