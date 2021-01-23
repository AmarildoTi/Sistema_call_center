<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%
    request.setAttribute("mensagem","Página não encontrada");
%>
<c:if test="${login != null}">
        <% request.getRequestDispatcher("/sistema/publico/login/home.jsp").forward(request, response); %>
</c:if>
<c:if test="${login == null}">
    <% request.getRequestDispatcher("/index.jsp").forward(request, response); %>
</c:if>
