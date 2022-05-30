<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.simplylern.model.Subject"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>LeanersAcademy:Subject</title>
	<link href='https://fonts.googleapis.com/css?family=Roboto:400,100,300,700' rel='stylesheet' type='text/css'/>

	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"/>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" />
    <jsp:include page="cssLoader.jsp"></jsp:include>
	

</head>
</head>
<body>
<div class="">
     <jsp:include page="layout/header.jsp"></jsp:include>

	<main class="container">
    <nav class="breadcrumbs">
      <ul>
        <li class="first-crumb"><a href="<%=request.getContextPath()%>/">Home</a></li>
     <!--    <li><a href="#">Personal Work</a></li> -->
        <li class="last-crumb">Add New Subject</li>
      </ul>
    </nav>
    <section class="content">
      <header>
        <h3>Add New Subject</h3>
      </header>
       <%!
     	Subject sub = new Subject();
       String action = "add";
       String value = "Add";
       String name = "";
      %>
      <% 
	   if(request.getAttribute("item") != null) {
		    sub = (Subject)request.getAttribute("item");
		    action = "edit&&id=" + sub.getId();
		    value = "Update Item with ID:" + sub.getId();
		    name = sub.getName();
	   } 
	   %>
       <form style="" action="<%=request.getContextPath()%>/subject?action=<%=action%>" method="post">
	  <div Subject="mb-3">
	    <label for="exampleInputEmail1" class="form-label">Subject Name</label>
	     <input type="text" class="form-control" id="name" name = "name" aria-describedby="nameHelp" required value="<%=name%>">
	     <div id="emailHelp" class="form-text error"></div>
	  </div>
	 

	  <button type="submit" class="btn btn-primary"><%=value%></button>
	</form>
    </section>
   </main>

</div>

<jsp:include page="layout/footer.jsp"></jsp:include>
<jsp:include page="cssLoaderFooter.jsp"></jsp:include>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"  crossorigin="*"></script>
</body>
</html>