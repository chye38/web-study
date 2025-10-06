<%--
  Created by IntelliJ IDEA.
  User: chye
  Date: 25. 10. 6.
  Time: 오후 11:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<div class="container py-5">
    <h2>상품 수정</h2>
    <hr>

    <form action="/admin/productUpdateAction.do" method="post" enctype="multipart/form-data">
        <!-- 상품 ID는 hidden 필드로 보냄 -->
        <input type="hidden" name="productId" value="${product.productId}" />

        <div class="mb-3">
            <label for="productName" class="form-label">상품명</label>
            <input type="text" class="form-control" id="productName" name="productName" value="${product.productName}" required>
        </div>

        <div class="mb-3">
            <label for="productPrice" class="form-label">가격</label>
            <input type="number" class="form-control" id="productPrice" name="productPrice" value="${product.price}" min="0" required>
        </div>

        <div class="mb-3">
            <label for="productDescription" class="form-label">상품 설명</label>
            <textarea class="form-control" id="productDescription" name="productDescription" rows="5" required>${product.productDescription}</textarea>
        </div>

        <div class="mb-3">
            <label for="productImage" class="form-label">상품 이미지 (변경 시만 선택)</label>
            <input type="file" class="form-control" id="productImage" name="productImage" accept="image/*">
        </div>

        <c:if test="${not empty product.path}">
            <div class="mb-3">
                <label class="form-label">현재 이미지</label><br>
                <img src="${product.path}" alt="현재 상품 이미지" style="max-width: 200px;">
            </div>
        </c:if>

        <div class="d-grid gap-2 d-md-flex justify-content-md-end">
            <a href="/admin/productManager.do" class="btn btn-secondary">취소</a>
            <button type="submit" class="btn btn-primary">상품 수정</button>
        </div>
    </form>
</div>
