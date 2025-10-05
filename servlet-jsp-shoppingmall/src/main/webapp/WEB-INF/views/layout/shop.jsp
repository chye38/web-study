<%@ page contentType="text/html;charset=UTF-8" language="java" session="true" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<!doctype html>
<html lang="ko">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
    <title>쇼핑몰</title>
</head>
<body>

<div class="mainContainer">
    <header class="p-3 bg-dark text-white">
        <div class="container d-flex flex-wrap align-items-center justify-content-between">

            <ul class="nav me-auto mb-2 justify-content-center mb-md-0">
                <li><a href="/index.do" class="nav-link px-2 text-white">Home</a></li>
                <li><a href="/mypage.do" class="nav-link px-2 text-white">마이페이지</a></li>
            </ul>

            <form class="col-12 col-lg-auto mb-3 mb-lg-0 me-lg-3">
                <input type="search" class="form-control form-control-dark" placeholder="Search..." aria-label="Search">
            </form>

            <c:choose>
                <c:when test="${not empty user}">
                    <div class="text-end">
                        <span class="text-white fw-bold">
                            보유 포인트: <fmt:formatNumber value="${user.userPoint}" type="number" groupingUsed="true"/>점
                        </span>
                        <form method="POST" action="/logout.do" style="display:inline;">
                            <button type="submit" class="btn btn-outline-light me-2">로그아웃</button>
                        </form>
                        <a href="/cart.do" class="btn btn-warning ms-2">장바구니</a>
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="text-end">
                        <a class="btn btn-outline-light me-2" href="login.do">로그인</a>
                        <a class="btn btn-warning" href="signup.do">회원가입</a>
                    </div>
                </c:otherwise>
            </c:choose>

        </div>
    </header>

    <main class="py-5">
        <div class="container">
            <jsp:include page="${layout_content_holder}" />
        </div>
    </main>

    <footer class="text-muted py-5">
        <div class="container">
            <p class="float-end mb-1"><a href="#">Back to top</a></p>
            <p class="mb-1">shoppingmall example is © nhnacademy.com</p>
        </div>
    </footer>
</div>

</body>
</html>
