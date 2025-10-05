<%--
  Created by IntelliJ IDEA.
  User: chye
  Date: 25. 10. 2.
  Time: 오전 4:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<div class="container py-5">
    <div class="d-flex justify-content-between align-items-center mb-3">
        <h2>상품 관리</h2>
        <!-- 상품 추가 버튼 -->
        <a href="/product/add.do" class="btn btn-primary">상품 추가</a>
    </div>

    <c:if test="${not empty productList}">
        <table class="table table-hover">
            <thead class="table-dark">
            <tr>
                <th scope="col">ID</th>
                <th scope="col">상품명</th>
                <th scope="col">가격</th>
                <th scope="col">설명</th>
                <th scope="col">이미지</th>
                <th scope="col">관리</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="product" items="${productList}">
                <tr>
                    <td>${product.id}</td>
                    <td>
                        <a href="/product/view.do?id=${product.id}">
                                ${product.name}
                        </a>
                    </td>
                    <td><fmt:formatNumber value="${product.price}" type="currency"/></td>
                    <td>${product.description}</td>
                    <td>
                        <img src="${product.imageUrl}" alt="${product.name}" style="height:50px; object-fit:cover;">
                    </td>
                    <td>
                        <a href="/product/edit.do?id=${product.id}" class="btn btn-sm btn-warning">수정</a>
                        <form action="/product/delete.do" method="post" style="display:inline;">
                            <input type="hidden" name="id" value="${product.id}" />
                            <button type="submit" class="btn btn-sm btn-danger" onclick="return confirm('삭제하시겠습니까?');">삭제</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>

    <c:if test="${empty productList}">
        <p class="text-center">등록된 상품이 없습니다.</p>
    </c:if>
</div>
