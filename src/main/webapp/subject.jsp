<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.simplylern.model.Subject"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>LeanersAcademy:Subject</title>
	<link href='https://fonts.googleapis.com/css?family=Roboto:400,100,300,700' rel='stylesheet' type='text/css'/>

	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"/>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/index.css"/>
	

</head>
</head>
<body>
     <jsp:include page="layout/header.jsp"></jsp:include>

	<main class="container">
    <nav class="breadcrumbs">
      <ul>
        <li class="first-crumb"><a href="<%=request.getContextPath()%>/">Home</a></li>
     <!--    <li><a href="#">Personal Work</a></li> -->
        <li class="last-crumb"><%="Subject" %></li>
      </ul>
    </nav>
    <section class="content">
      <header>
        <h3><%="Subjects" %></h3>
        <form action="<%=request.getContextPath()%>/subject?action=add" method="post">
        	<button class="pull-right btn btn-outline-primary rounded btn-sm p-1 m-1"><i class="fa fa-plus"></i>Add New Subject</button>
        </form>
      </header>
        <table class="table table-bordered border-primary">
	  <thead>
	    <tr>
	      <th scope="col">ID</th>
	      <th scope="col">NAME</th>
	      <th scope="col">Action</th>
	    </tr>
	  </thead>
	  <tbody>
		<% for(Subject sub:(ArrayList<Subject>)request.getAttribute("data")){ %>
            <tr>
                <td><%=sub.getId()%></td>
                <td><%=sub.getName()%></td>
                 <td>
                	<a class="btn btn-outline-info" href="<%=request.getContextPath()%>/subject?action=edit&&id=<%=sub.getId()%>"><i class="fa fa-check"></i> Edit</a> 
                	<a class="btn btn-outline-danger" href="<%=request.getContextPath()%>/subject?action=delete&&id=<%=sub.getId()%>"><i class="fa fa-trash"></i> Delete</a>
                </td>            
            </tr>
         <%}%>
	  </tbody>
	</table>
    </section>
   </main>


<jsp:include page="layout/footer.jsp"></jsp:include>
<jsp:include page="cssLoaderFooter.jsp"></jsp:include>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"  crossorigin="*"></script>
</body>
</html>