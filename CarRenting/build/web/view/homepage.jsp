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

        <%@include file="/view/header.jsp" %>

        <!-- Conteúdo da Pagina -->
        <div class="container">
            <!-- Jumbotron Header -->
            <header style="background: url(${pageContext.request.contextPath}/${requestScope.background}) no-repeat center center fixed; -webkit-background-size: cover; -moz-background-size: cover; -o-background-size: cover; background-size: cover; color: white;" class="jumbotron my-4">
                <h1 class="display-3">A Warm Welcome!</h1>
                <p class="lead">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Ipsa, ipsam, eligendi, in quo sunt possimus non incidunt odit vero aliquid similique quaerat nam nobis illo aspernatur vitae fugiat numquam repellat.</p>
                
            </header>
                <input type="hidden" name="backURL" value="HomePageServlet">
            <!-- Page Features -->
            <c:set var="cars" value="${requestScope.carList}"/>

            <c:if test="${cars == null}">

                <h1>Database Error Connection</h1>
            </c:if>

            <c:if test="${cars != null}">
                <div class="row text-center">
                    <c:forEach var="car" items="${cars}">


                        <div class="col-lg-3 col-md-6 mb-4">
                            <div class="card">
                                <img style="height: 40%;" class="card-img-top" src="${pageContext.request.contextPath}/${car.getImgPath()}" alt="">
                                <div class="card-body">
                                    <h4 class="card-title">${car.getCarName()}</h4>
                                    <p class="card-text">Price:${car.getPriceLocale()}</p>
                                    <p class="card-text">Quantity:${car.getQuantity()}</p>
                                </div>
                                <div class="card-footer">
                                    <a href="${pageContext.request.contextPath}/Servlet/CartServlet?action=add&carId=${car.getId()}&backURL=HomePageServlet" class="btn btn-primary">Add to Cart</a>
                                </div>
                            </div>
                        </div>               

                    </c:forEach>
                </div>    
            </c:if>

            <!-- /Page Features -->
            <ul class="pagination justify-content-center">
                 <c:set var="page" value="${requestScope.page}"/>
                <c:if test="${page!=null}">
                    <c:forEach var="pageNum" begin="${1}" end="${page.getPageNum()}">
                        <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}\Servlet/HomePageServlet?page=${pageNum}">${pageNum}</a></li> 
                    </c:forEach>                                     
                </c:if>
              
                
                
               
            </ul>
        </div>
        <!-- /Conteúdo da Pagina -->

        <!-- Rodapé -->
        <footer class="py-5 bg-dark">
            <div class="container">
                <p class="m-0 text-center text-white">Copyright &copy; Mastertech 2017</p>
            </div>
        </footer>
        <!-- /Rodapé -->


        <!-- Bootstrap core JavaScript -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

        <!-- Importa o Javascript utilizado neste projeto -->
        <script src="./script/script.js"></script>
        <script src="../script/script.js"></script>


    </body>

</html>