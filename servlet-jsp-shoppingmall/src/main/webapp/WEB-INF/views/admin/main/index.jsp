<%--
  Created by IntelliJ IDEA.
  User: nhn
  Date: 2023/11/08
  Time: 10:20 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<div class="container py-5">

    <!-- 상품이 있을 때만 카드 출력 -->
    <c:if test="${not empty productList}">
        <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">
            <c:forEach var="product" items="${productList}">
                <div class="col">
                    <div class="card shadow-sm">
                        <img src="${product.imageUrl}" class="card-img-top" alt="${product.name}" style="object-fit: cover; height: 225px;"/>

                        <div class="card-body">
                            <p class="card-text">${product.description}</p>
                            <div class="d-flex justify-content-between align-items-center">
                                <div class="btn-group">
                                    <!-- 모든 유저에게 View 버튼 표시 -->
                                    <a href="/product/view.do?id=${product.id}" class="btn btn-sm btn-outline-secondary">View</a>

                                    <!-- 관리자일 경우에만 Edit 버튼 표시 -->
                                    <c:if test="${not empty user and user.userAuth == 'ROLE_ADMIN'}">
                                        <a href="/product/edit.do?id=${product.id}" class="btn btn-sm btn-outline-warning">Edit</a>
                                    </c:if>
                                </div>
                                <small class="text-muted">
                                    <fmt:formatNumber value="${product.price}" type="currency"/>
                                </small>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>

        <!-- 페이지네이션 -->
        <nav aria-label="Page navigation">
            <ul class="pagination justify-content-center mt-4">
                <c:forEach var="i" begin="1" end="${totalPages}">
                    <li class="page-item ${i == currentPage ? 'active' : ''}">
                        <a class="page-link" href="?page=${i}">${i}</a>
                    </li>
                </c:forEach>
            </ul>
        </nav>
    </c:if>

    <!-- 상품이 없으면 안내 문구만 표시, 페이지네이션 생략 -->
    <c:if test="${empty productList}">
        <p class="text-center">등록된 상품이 없습니다.</p>
    </c:if>

</div>
