<%-- 
    Document   : cart
    Created on : Jan 7, 2021, 1:09:32 PM
    Author     : ADMIN
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">

    <head>
        <title>Cart</title>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <style>
            body {
                background: url(https://drawtodrive.com/uploads/20330/sudharsan999-koenigsegg-regera.jpeg) no-repeat fixed;
            }
        </style>
    </head>

    <body>
        <%@include file="/view/header.jsp" %>



        <div class="container">
            <h2>Cart:</h2>
            <table class="table">
                <thead>
                    <tr>

                        <th scope="col">Image</th>
                        <th scope="col">Car Name</th>
                        <th scope="col">Category</th>
                        <th scope="col">From</th>
                        <th scope="col">To</th>
                        <th scope="col">Color</th>
                        <th scope="col">Quantity</th>
                        <th scope="col">Price</th>
                        <th scope="col"></th>
                        <th scope="col"></th>
                    </tr>
                </thead>
                <tbody>
                    <c:set var="cartList" value="${sessionScope.cartList}"/>
                    <c:forEach var="cart" items="${cartList}">
                        <c:set var="car" value="${cart.getCar()}"/>
                    <form action="${pageContext.request.contextPath}/Servlet/CartServlet?carId=${car.getId()}">
                        <tr>

                            <td><img style="width: 15rem;" src="../${car.getImgPath()}" alt=""></td>
                            <td>${car.getCarName()}</td>
                            <td>${car.getCategoryName()}</td>
                            <td>
                                <input type="date" name="txtDateFrom" value="${cart.getCheckInString()}">
                            </td>
                            <td>
                                <input type="date" name="txtDateTo" value="${cart.getCheckOutString()}">
                            </td>
                            <td>${car.getColor()}</td>
                            <td>

                                <button type="submit" style="display: inline; color: red; width: 20px;;"name="action" value="descrease" >-</button>
                                <p style="display: inline;">${cart.getQuantity()}</p>
                                <button type="submit"  style="display: inline; color: green; width: 20px;" name="action" value="increase">+</button>
                            </td>
                            <td>${cart.getTotalOfPrice()}</td>
                            <td>
                                <button class="btn btn-primary" type="submit" name="action" value="update">Date Change</button>             
                            </td>
                            <td>
                                <a href="${pageContext.request.contextPath}/Servlet/CartServlet?carId=${car.getId()}&action=remove" class="btn btn-danger" role="button" aria-pressed="true">Remove</a>
                            </td>
                        <input type="hidden" name="carId" value="${car.getId()}">
                        </tr>
                    </form>
                </c:forEach>
                </tbody>
            </table>

            <h3>Total:${requestScope.totalAll}</h3>
            <a href="${pageContext.request.contextPath}/Servlet/PayCheckServlet" class="btn btn-primary" role="button" aria-pressed="true">Pay Check</a>
        </div>
            <c:set var="message" value="${requestScope.message}"/>
            <c:if test="${message != null}">
                <script>
                      alert('${message}');
                </script>
            </c:if>
        <!-- Optional JavaScript -->
        <!-- jQuery first, then Popper.js, then Bootstrap JS -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    </body>


</html>