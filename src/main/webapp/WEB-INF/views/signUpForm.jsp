<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Currency Exchange Rates</title>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="description" content=""/>
    <meta name="viewport" content="width=device-width"/>
    <base href="/"/>

    <link href="webjars/bootstrap/4.0.0-beta.2/css/bootstrap.min.css" type="text/css" rel="stylesheet"/>
    <link href="static/app.css" type="text/css" rel="stylesheet"/>
</head>

<body>
<div class="container">
    <form:form action="/signup" modelAttribute="signup" method="POST" class="form-signup">
        <h2 class="form-signup-heading">Create your account</h2>
        <spring:bind path="email">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input type="text" path="email" cssClass="form-control" placeholder="Email" autofocus="true"></form:input>
                <form:errors path="email"></form:errors>
            </div>
        </spring:bind>
        <spring:bind path="password">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input type="password" path="password" cssClass="form-control" placeholder="Password"></form:input>
                <form:errors path="password"></form:errors>
            </div>
        </spring:bind>
        <spring:bind path="confirmPassword">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input type="password" path="confirmPassword" cssClass="form-control" placeholder="Confirm password" ></form:input>
                <form:errors path="confirmPassword"></form:errors>
            </div>
        </spring:bind>
        <spring:bind path="dateOfBirth">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input path="dateOfBirth" cssClass="form-control" placeholder="Date of birth" ></form:input>
                <form:errors path="dateOfBirth"></form:errors>
            </div>
        </spring:bind>
        <spring:bind path="streetAddress">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input path="streetAddress" cssClass="form-control" placeholder="Stree address"></form:input>
                <form:errors path="streetAddress"></form:errors>
            </div>
        </spring:bind>
        <spring:bind path="zipCode">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input path="zipCode" cssClass="form-control" placeholder="Zip code"></form:input>
                <form:errors path="zipCode"></form:errors>
            </div>
        </spring:bind>
        <spring:bind path="city">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input path="city" cssClass="form-control" placeholder="City"></form:input>
                <form:errors path="city"></form:errors>
            </div>
        </spring:bind>
        <spring:bind path="country">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input path="country" cssClass="form-control" placeholder="Country"></form:input>
                <form:errors path="country"></form:errors>
            </div>
        </spring:bind>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Create</button>

    </form:form>

</div>
</body>

</html>