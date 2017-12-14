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
    <form:form action="/signup" modelAttribute="signIn" method="POST" class="form-signup">
        <h2 class="form-signup-heading">Sign In</h2>
        <spring:bind path="email">
            <div class="form-group">
                <form:input type="text" path="email" cssClass="form-control" placeholder="Email" autofocus="true"></form:input>
                <form:errors path="email" cssClass="has-error"></form:errors>
            </div>
        </spring:bind>
        <spring:bind path="password">
            <div class="form-group">
                <form:input type="password" path="password" cssClass="form-control" placeholder="Password"></form:input>
                <form:errors path="password" cssClass="has-error"></form:errors>
            </div>
        </spring:bind>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </form:form>
    <h4 class="text-center"><a href="/signup">Create an account</a></h4>

</div>
</body>

</html>