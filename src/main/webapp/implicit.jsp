<%--
  Created by IntelliJ IDEA.
  User: chosun-nhn47
  Date: 25. 9. 16.
  Time: 오후 4:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Collections" %>
<%@ page import="java.util.Objects" %>
<%@ page import="java.util.ArrayList" %>

<%@ page contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="false" language="java" %>
<%!
    private List<String> getClasses(Class<?> clazz) {
        if (Objects.isNull(clazz)) {
            return Collections.emptyList();
        }

        List<String> classes = new ArrayList<>();
        while (clazz != null) {
            classes.add(clazz.getName());
            clazz = clazz.getSuperclass();
        }

        return classes;
    }
%>
<%
    response.setContentType("text/plain");                                  // response

    out.println("Hello, " + request.getParameter("name"));                  // request
    out.println("servlet name = " + config.getServletName());               // config
    out.println("context path = " + application.getContextPath());          // application

    out.println("this == page? " + (this == page));

    List<String> classes = getClasses(getClass());                          // page
    out.println("page classes = " + String.join(" > ", classes));

    classes = getClasses(pageContext.getClass());                           // pageContext
    out.println("pageContext classes = " + String.join(" > ", classes));
%>