<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
	<section class="ftco-section">
		
		<div class="wrap">
			<div class="container">
				<div class="row justify-content-between">
						<div class="col">
							<p class="mb-0 phone"><span class="fa fa-phone"></span> <a href="#">+255777 99 99 99</a></p>
						</div>
						<div class="col d-flex justify-content-end">
							<div class="social-media">
				    		<p class="mb-0 d-flex">
				    			<a href="#" class="d-flex align-items-center justify-content-center"><span class="fa fa-facebook"><i class="sr-only">Facebook</i></span></a>
				    			<a href="#" class="d-flex align-items-center justify-content-center"><span class="fa fa-twitter"><i class="sr-only">Twitter</i></span></a>
				    			<a href="#" class="d-flex align-items-center justify-content-center"><span class="fa fa-instagram"><i class="sr-only">Instagram</i></span></a>
				    			<a href="#" class="d-flex align-items-center justify-content-center"><span class="fa fa-dribbble"><i class="sr-only">Dribbble</i></span></a>
				    		</p>
			        </div>
						</div>
				</div>
			</div>
		</div>
		<nav class="navbar navbar-expand-lg navbar-dark ftco_navbar bg-dark ftco-navbar-light" id="ftco-navbar">
	    <div class="container">
	    	<a class="navbar-brand" href="<%=request.getContextPath()%>/home">Learners <span>Academy</span></a>
	    	<form action="#" class="searchform order-sm-start order-lg-last">
          <div class="form-group d-flex">
            <input type="text" class="form-control pl-3" placeholder="Search">
            <button type="submit" placeholder="" class="form-control search"><span class="fa fa-search"></span></button>
          </div>
        </form>
	      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#ftco-nav" aria-controls="ftco-nav" aria-expanded="false" aria-label="Toggle navigation">
	        <span class="fa fa-bars"></span> Menu
	      </button>
	      <div class="collapse navbar-collapse" id="ftco-nav">
	        <ul class="navbar-nav m-auto">
	        	<li class="nav-item active"><a href="<%=request.getContextPath()%>/" class="nav-link">Home</a></li>
	        	<!-- <li class="nav-item dropdown">
              	<a class="nav-link dropdown-toggle" href="#" id="dropdown04" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Page</a>
	              <div class="dropdown-menu" aria-labelledby="dropdown04">
	              	<a class="dropdown-item" href="#">Page 1</a>
	                <a class="dropdown-item" href="#">Page 2</a>
	                <a class="dropdown-item" href="#">Page 3</a>
	                <a class="dropdown-item" href="#">Page 4</a>
	              </div>
	            </li> -->
	        	<li class="nav-item"><a href="<%=request.getContextPath()%>/class-room" class="nav-link">ClassRoom</a></li>
	        	<li class="nav-item"><a href="<%=request.getContextPath()%>/student" class="nav-link">Students</a></li>
	        	<li class="nav-item"><a href="<%=request.getContextPath()%>/teacher" class="nav-link">Teachers</a></li>
	        	<li class="nav-item"><a href="<%=request.getContextPath()%>/subject" class="nav-link">Subjects</a></li>
	        	<li class="nav-item"><a href="<%=request.getContextPath()%>/student-master-list" class="nav-link">Student Master List</a></li>
	       
	        </ul>
	      </div>
	    </div>
	  </nav>
    <!-- END nav -->

	</section>
</body>
</html>