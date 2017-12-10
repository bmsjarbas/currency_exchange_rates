<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Currency Exchange Rates</title>
</head>

<body>
<div class="container">
    <form:form action="${signUpUrl}" modelAttribute="signUp" method="post">
        <h2>Create your account</h2>
        <spring:bind path="email">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input path="email" cssClass="form-control" placeholder="" autofocus="true"></form:input>
                <form:errors path="email"></form:errors>
            </div>
        </spring:bind>
        <spring:bind path="password">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input path="password" cssClass="form-control" placeholder=""></form:input>
                <form:errors path="password"></form:errors>
            </div>
        </spring:bind>
        <spring:bind path="confirmPassword">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input path="confirmPassword" cssClass="form-control" placeholder="" ></form:input>
                <form:errors path="confirmPassword"></form:errors>
            </div>
        </spring:bind>
        <spring:bind path="dateOfBirth">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input path="dateOfBirth" cssClass="form-control" placeholder="" ></form:input>
                <form:errors path="dateOfBirth"></form:errors>
            </div>
        </spring:bind>
        <spring:bind path="streetAddress">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input path="streetAddress" cssClass="form-control" placeholder=""></form:input>
                <form:errors path="streetAddress"></form:errors>
            </div>
        </spring:bind>
        <spring:bind path="zipCode">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input path="zipCode" cssClass="form-control" placeholder=""></form:input>
                <form:errors path="zipCode"></form:errors>
            </div>
        </spring:bind>
        <spring:bind path="city">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input path="city" cssClass="form-control" placeholder=""></form:input>
                <form:errors path="city"></form:errors>
            </div>
        </spring:bind>
        <spring:bind path="country">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input path="country" cssClass="form-control" placeholder=""></form:input>
                <form:errors path="country"></form:errors>
            </div>
        </spring:bind>

        <button class="btn btn-lg btn-primary btn-block" type="submit">Create</button>
    </form:form>
</div>
</body>

</html>