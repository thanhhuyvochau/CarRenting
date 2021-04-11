<%-- 
    Document   : homepage
    Created on : Feb 21, 2021, 3:40:26 PM
    Author     : ADMIN
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">

    <head>
        <title>Search</title>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <style>
            body {
                background: url(../image/search-background.jpg) no-repeat fixed;
            }
        </style>
    </head>

    <body>

        <%@include file="/view/header.jsp" %>
        <div class="container">
            <h2>Search Form</h2>

            <div id="demo">
                <div class="container pt-4">
                    <form method="GET" class="form-group" action="${pageContext.request.contextPath}/Servlet/SearchServlet">
                        <div checked class="custom-control custom-radio">
                            <input checked onclick="takeOp1()" type="radio" class="custom-control-input" id="option-1" name="action" value="car_name">
                            <label class="custom-control-label" for="option-1">Name Search</label>
                        </div>
                        <div class="form-group">
                            <label for="txtCarName">Car Name:</label>
                            <input type="text" class="form-control" id="txtCarName" name="txtCarName" value="${requestScope.nameSearched}">
                        </div>
                        <div class="custom-control custom-radio">
                            <input type="radio" onclick="takeOp2()" class="custom-control-input" id="option-2" name="action" value="car_category">
                            <label class="custom-control-label" for="option-2">Category Search</label>
                        </div>
                        <div class="form-group">
                            <label for="category"></label>

                            <select id="category" name="category" class="custom-select">
                                <c:forEach var="cate" items="${requestScope.categoryList}">
                                    <option value="${cate.getCategoryId()}">${cate.getCategoryName()}</option>  
                                </c:forEach>
                            </select>
                        </div>
                        <div class="container">
                            <div class="row">
                                <div class="col">
                                    <label for="check-in"><h5>From</h5></label>
                                 

                                </div>
                                <div class="col"><label for="check-out"><h5>To</h5></label>
                                  
                            </div>
                            <div class="row">
                                <label class="form-label" for="typeNumber">Quantity:</label>
                                <input value="${requestScope.quantity}" name="quantity" onchange="checkNum()" type="number" id="typeNumber" class="form-control" />

                            </div>
                            <div class="row mt-2">
                                <button type="submit" class="btn btn-primary btn-lg btn-block">Search</button>
                            </div>

                        </div>
                    </form>

                </div>
            </div>
        </div>
        <div class="container">
            <table class="table">
                <thead>
                    <tr>
                        <th scope="col"></th>
                        <th scope="col">Image</th>
                        <th scope="col">Car Name</th>
                        <th scope="col">Category</th>
                        <th scope="col">Year</th>
                        <th scope="col">Color</th>
                        <th scope="col">Quantity</th>
                        <th scope="col">Price</th>
                        <th scope="col"></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="car" items="${requestScope.listCars}">     
                    <form action="${pageContext.request.contextPath}/Servlet/CartServlet">
                        <tr>
                            <th scope="row">1</th>
                            <td><img style="width: 15rem;" src="../${car.getImgPath()}"></td>
                            <td>${car.getCarName()}</td>
                            <td>${car.getCategoryName()}</td>
                            <td>${car.getYear()}</td>
                            <td>${car.getColor()}</td>
                            <td>${car.getQuantity()}</td>
                            <td>${car.getPrice()}</td>
                            <td><button name="action" value="add" type="submit" class="btn btn-danger ">Add To Cart</button></td>
                        <input type="hidden" name="carId" value="${car.getId()}">
                        </tr>
                        <input type="hidden" name="backURL" value="SearchServlet">
                        <input type="date" name="txtDateFrom" value="${requestScope.txtDateFrom}">
                        <input type="date" name="txtDateTo" value="${cart.txtDateTo}">
                    </form>
                </c:forEach>
                </tbody>
            </table>
            <ul class="pagination justify-content-center">
                <c:set var="page" value="${requestScope.page}"/>
                <c:if test="${page!=null}">
                    <c:forEach var="pageNum" begin="${1}" end="${page.getPageNum()}">
                        <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}\Servlet/SearchServlet?page=${pageNum}&action=${requestScope.action}&txtDateFrom=${requestScope.checkIn}&txtDateTo=${requestScope.checkOut}&${requestScope.searchField}=${dataSearch}&quantity=${requestScope.quantity}">${pageNum}</a></li> 
                        </c:forEach>                                     
                    </c:if>

            </ul>
        </div>
        <c:set var="message" value="${requestScope.message}"/>
        <c:if test="${message != null}">
            <script>
                alert('${message}');
            </script>
        </c:if>

        <script>
            var quan = document.getElementById("typeNumber").value;
            console.log(quan);
            if (quan == 0) {
                document.getElementById("typeNumber").value = 1;

            }
            function checkNum() {
                var num = document.getElementById("typeNumber").value;
                if (num <= 0) {
                    document.getElementById("typeNumber").value = 1;
                }
            }
            function takeOp2() {
                document.querySelector("#txtCarName").disabled = true;
                document.querySelector("#category").disabled = false;
            }

            function takeOp1() {
                document.querySelector("#category").disabled = true;
                document.querySelector("#txtCarName").disabled = false;
            }
            takeOp1();
        </script>

        <!-- Optional JavaScript -->
        <!-- jQuery first, then Popper.js, then Bootstrap JS -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    </body>


</html>