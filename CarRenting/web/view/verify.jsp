<%-- 
    Document   : very.jsp
    Created on : Feb 27, 2021, 1:27:07 PM
    Author     : ADMIN
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Verify</title>
        <style> body {
                background-color: #BA68C8
            }

            .main-verification-input {
                background: #fff;
                padding: 0 120px 0 0;
                border-radius: 1px;
                margin-top: 6px
            }

            .fl-wrap {
                float: left;
                width: 100%;
                position: relative;
                border-radius: 4px
            }

            .main-verification-input:before {
                content: '';
                position: absolute;
                bottom: -40px;
                width: 50px;
                height: 1px;
                background: rgba(255, 255, 255, 0.41);
                left: 50%;
                margin-left: -25px
            }

            .main-verification-input-item {
                float: left;
                width: 100%;
                box-sizing: border-box;
                border-right: 1px solid #eee;
                height: 50px;
                position: relative
            }

            .main-verification-input-item input:first-child {
                border-radius: 100%
            }

            .main-verification-input-item input {
                float: left;
                border: none;
                width: 100%;
                height: 50px;
                padding-left: 20px
            }

            .main-verification-button {
                background: #4DB7FE
            }

            .main-verification-button {
                position: absolute;
                right: 0px;
                height: 50px;
                width: 120px;
                color: #fff;
                top: 0;
                border: none;
                border-top-right-radius: 4px;
                border-bottom-right-radius: 4px;
                cursor: pointer
            }

            .main-verification-input-wrap {
                max-width: 500px;
                margin: 20px auto;
                position: relative;
                margin-top: 129px
            }

            .main-verification-input-wrap ul {
                background-color: #fff;
                padding: 27px;
                color: #757575;
                border-radius: 4px
            }

            a {
                text-decoration: none !important;
                color: #9C27B0
            }

            :focus {
                outline: 0
            }

            @media only screen and (max-width: 768px) {
                .main-verification-input {
                    background: rgba(255, 255, 255, 0.2);
                    padding: 14px 20px 10px;
                    border-radius: 10px;
                    box-shadow: 0px 0px 0px 10px rgba(255, 255, 255, 0.0)
                }

                .main-verification-input-item {
                    width: 100%;
                    border: 1px solid #eee;
                    height: 50px;
                    border: none;
                    margin-bottom: 10px
                }

                .main-verification-input-item input {
                    border-radius: 6px !important;
                    background: #fff
                }

                .main-verification-button {
                    position: relative;
                    float: left;
                    width: 100%;
                    border-radius: 6px
                }
            }</style>

        <script type='text/javascript' src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>
        <script type='text/javascript' src='https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js'></script>
        <script type='text/javascript'></script>
    </head>
    <body oncontextmenu='return false' class='snippet-body'>
        <c:set var="message" value="${requestScope.message}"/>
        <c:if test="${message != null}">
            <script>
                alert('${message}');
            </script>
        </c:if>
          
        <c:set var="user" value="${sessionScope.identify}"/>    
        <c:if test="${user.getStatus().trim() == 'active'}">
            <h3>Your Account verified</h3>
            <a href="${pageContext.request.contextPath}">Back To Home Page</a>
        </c:if>
        <c:if test="${user.getStatus().trim() == 'new'}">
            <div class="row">
                <div class="col-md-12">
                    <div class="main-verification-input-wrap">
                        <ul>
                            <li>You will recieve a verification code on your mail after you registered. Enter that code below.</li>
                            <li>If somehow, you did not recieve the verification email then <a href="${pageContext.request.contextPath}/Servlet/VerifyServlet?action=resend">resend the verification email</a></li>
                        </ul>
                        <div class="main-verification-input fl-wrap">
                            <div class="main-verification-input-item">
                                <form id="verify-form" action="${pageContext.request.contextPath}/Servlet/VerifyServlet" method="POST">
                                    <input name="verify-code" type="text" value="" placeholder="Enter the verification code"> 
                                </form>
                            </div> 
                            <button type="submit" form="verify-form" class="main-verification-button">Verify Now</button>
                        </div>
                    </div>
                </div>
            </div>
        </c:if>
    </body>
</html>
