<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>LeanersAcademyLogin</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" crossorigin="*">
<jsp:include page="cssLoader.jsp"></jsp:include>
</head>
<body style="background:url(/images/bg3.jpeg)">
 <div class="container col-md-8 col-md-offset-3" style="overflow: auto;width:50%;margin:10% auto auto auto;border:1px solid #5e5e5e;padding:30px;border-radius:10px;">
  <h1>Login Form</h1>
  
  
  <form style="" action="<%=request.getContextPath()%>/login" method="post">
	  <div class="mb-3">
	    <label for="exampleInputEmail1" class="form-label">username</label>
	    <input type="text" class="form-control" id="username" aria-describedby="usernameHelp" required>
	    <div id="emailHelp" class="form-text">We'll never share your details with anyone else.</div>
	  </div>
	  <div class="mb-3">
	    <label for="exampleInputPassword1" class="form-label">Password</label>
	    <input type="password" class="form-control" id="exampleInputPassword1" required>
	    <span class="error">
	       <%="".equals(request.getAttribute("message")) || request.getAttribute("message") == null?"":request.getAttribute("message")%>
		</span>
	  </div>

	  <button type="submit" class="btn btn-primary">Sign In</button>
	</form>
  
 </div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"  crossorigin="*"></script>
</body>
</html>
