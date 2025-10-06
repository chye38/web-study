<%--
  Created by IntelliJ IDEA.
  User: chye
  Date: 25. 10. 6.
  Time: 오전 5:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="container py-5">
    <h2>상품 등록</h2>
    <hr>

    <%--
        상품 추가 폼입니다. 파일 업로드를 위해 form 태그에 반드시 enctype="multipart/form-data" 속성을 추가해야 합니다.
        action 속성은 폼 데이터를 처리할 서블릿의 URL(/productAdd.do)로 지정합니다.
    --%>
    <form action="/admin/productAddAction.do" method="post" enctype="multipart/form-data">
        <div class="mb-3">
            <label for="productName" class="form-label">상품명</label>
            <input type="text" class="form-control" id="productName" name="productName" required>
        </div>

        <div class="mb-3">
            <label for="productPrice" class="form-label">가격</label>
            <input type="number" class="form-control" id="productPrice" name="productPrice" min="0" required>
        </div>

        <div class="mb-3">
            <label for="productDescription" class="form-label">상품 설명</label>
            <textarea class="form-control" id="productDescription" name="productDescription" rows="5" required></textarea>
        </div>

        <div class="mb-3">
            <label for="productImage" class="form-label">상품 이미지</label>
            <input type="file" class="form-control" id="productImage" name="productImage" accept="image/*" required>
        </div>

        <div class="d-grid gap-2 d-md-flex justify-content-md-end">
            <a href="/admin/productManager.do" class="btn btn-secondary">취소</a>
            <button type="submit" class="btn btn-primary">상품 등록</button>
        </div>
    </form>
</div>