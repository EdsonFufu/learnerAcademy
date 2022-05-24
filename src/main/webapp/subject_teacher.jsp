<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.simplylern.model.Teacher"%>
<%@page import="com.simplylern.model.Subject"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>LeanersAcademy:Subject to Teacher</title>
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
        <li class="last-crumb">Assign Subject To Teacher </li>
      </ul>
    </nav>
    <section class="content">
      <header>
        <h4>Assign Subject To Teacher</h4>
      </header>
      <%!
      	Teacher t = new Teacher();
        String action = "add";
        String value = "Add";
        String name = "";
        int tId = 0;
      %>
      <% 
	   if(request.getAttribute("item") != null) {
		    t = (Teacher)request.getAttribute("item");
		    action = "assign-subject&&id=" + t.getId();
		    value = "Assign Subject to  Teacher[" + t.getName() + "]";
		    name = t.getName();
		    tId = t.getId();
	   } 
	   %>
       <form style="" action="<%=request.getContextPath()%>/teacher?action=<%=action%>" method="post">
	  <div class="mb-3 col-md-6">

	    <label for="exampleInputEmail1" class="form-label">Teacher</label>
	     <input type="hidden" class="form-control" id="action" name="action" aria-describedby="operationHelp" value="add" required>
	     <input type="text" class="form-control" id="name" name = "name" aria-describedby="nameHelp" readonly required value="<%=name%>"/>
	     <input type="hidden" class="form-control" id="teacher_id" name = "teacher_id" aria-describedby="nameHelp" required value="<%=tId%>"/>
	     <div id="emailHelp" class="form-text error"></div>
	     
	     <div id="teacherHelp" class="form-text error"></div>
	     
	     <label for="exampleInputEmail1" class="form-label">Subject</label>
	     <select class="form-control" id="subject_id" name = "subject_id" aria-describedby="nameHelp">
	     <% for(Subject s:(ArrayList<Subject>) request.getAttribute("subjects")){ %>
	          <option  value="<%=s.getId()%>"><%=s.getId() + ":" +s.getName()%></a> 
	      <%}%>
	     </select>
	     <div id="subjectHelp" class="form-text error"></div>
	     
	    
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
</html>