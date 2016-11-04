<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Shopping Cart</title>
</head>
<body>
<h2>Finalized Shopping Cart</h2>
<jsp:include page="menu.jsp" />
<div class="page-title">My Cart</div>
 
    <c:if test="${empty cartForm or empty cartForm.cartLines}">
        <h2>There is no items in Cart</h2>
        <a href="${pageContext.request.contextPath}/productList">Show
            Product List</a>
    </c:if>
    <c:if test="${not empty cartForm and not empty cartForm.cartLines}">
        <form:form method="POST" modelAttribute="cartForm"
            action="${pageContext.request.contextPath}/shoppingCart">
 
            <c:forEach items="${cartForm.cartLines}" var="cartLineInfo"
                varStatus="varStatus">
                <div class="product-preview-container">
                    <ul>
                        <li>Code: ${cartLineInfo.productInfo.code} <form:hidden
                                path="cartLines[${varStatus.index}].productInfo.code" />
 
                        </li>
                        <li>Name: ${cartLineInfo.productInfo.name}</li>
                        <li>Price: <span class="price">
                        
                          <fmt:formatNumber value="${cartLineInfo.productInfo.price}" type="currency"/>
                          
                        </span></li>
                        <li>Quantity: <form:input
                                path="cartLines[${varStatus.index}].quantity" /></li>
                        
                        <li>Sales Tax:
                          <span class="subtotal">
                          
                             <fmt:formatNumber value="${cartLineInfo.totalSalesTax}" type="currency"/>
                        
                          </span>
                        </li>
                        
                        <li>Subtotal:
                          <span class="subtotal">
                          
                             <fmt:formatNumber value="${cartLineInfo.subTotal}" type="currency"/>
                        
                          </span>
                        </li>
                    </ul>
                </div>
            </c:forEach>
            <h3>Cart Summary:</h3>
       <ul>
           <li>Total Amount:
           <span class="total">
             <fmt:formatNumber value="${summary.totalAmount}" type="currency"/>
           </span></li>
       		<li>Total Tax:
           <span class="total">
             <fmt:formatNumber value="${summary.totalSalesTax}" type="currency"/>
           </span></li>
       		<li>Total Cost:
           <span class="total">
             <fmt:formatNumber value="${summary.totalCost}" type="currency"/>
           </span></li>
       </ul>
            <div style="clear: both"></div>
            </form:form>
    </c:if>
</body>
</html>