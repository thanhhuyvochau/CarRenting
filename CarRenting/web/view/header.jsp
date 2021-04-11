<%-- 
    Document   : homepage
    Created on : Feb 21, 2021, 3:40:26 PM
    Author     : ADMIN
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">

    <head>

        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <title>Car Rental</title>

        <!-- Bootstrap core CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">


        <!-- Importa o CSS utilizado neste projeto -->
        <link href="./asset/style.css" rel="stylesheet">
        <link href="../asset/style.css" rel="stylesheet">


    </head>

    <body>

        <!-- Navegação -->
        <c:set value="${sessionScope.identify}" var="user"/>
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
            <div class="container">
                <a class="navbar-brand" href="${pageContext.request.contextPath}">2YdeV'Cars</a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarResponsive">
                    <ul class="navbar-nav ml-auto">
                        <li class="nav-item active">
                            <a class="nav-link" href="${pageContext.request.contextPath}">Home
                                <span class="sr-only">(current)</span>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/Servlet/SearchServlet?action=redirect">Search</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">History</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/Servlet/CartServlet?action=redirect">Cart</a>
                        </li>
                       
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/Servlet/LogoutServlet">Logout</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">${user.getEmail()}</a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>     
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

        
        <script src="./script/script.js"></script>
        <script src="../script/script.js"></script>


    </body>

</html>