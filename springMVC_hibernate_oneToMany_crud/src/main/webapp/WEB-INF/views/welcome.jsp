<!DOCTYPE html>
<html lang="en">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<head>
<title>Employee registration</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>

	<form:form modelAttribute="employeeForm" class="form-horizontal"
		action="save" method="post">
		<fieldset>
		<c:if test="${not empty success}">
		<font color="green">${success}</font>
		</c:if>
		<c:if test="${not empty error}">
		<font color="red">${error}</font>
		</c:if>

			<!-- Form Name -->
			<legend>Employee form</legend>

            <form:hidden path="id"/>
			<!-- Text input-->
			<div class="form-group">
				<label class="col-md-4 control-label" for="name">Enter name</label>
				<div class="col-md-4">
					<form:input path="name" id="name" type="text"
						placeholder="Enter name" class="form-control input-md" required=""></form:input>
						<form:errors path="name" cssStyle="color:red"></form:errors>

				</div>
			</div>
			<hr />

              
			<c:forEach items="${employeeForm.addresses}" var="addr"
				varStatus="row">
                 <form:hidden path="addresses[${row.index}].id" />
				<!-- Text input-->
				<div class="form-group">
					<label class="col-md-4 control-label" for="city">Enter city</label>
					<div class="col-md-4">
						<form:input path="addresses[${row.index}].city" id="city"
							type="text" placeholder="Enter city"
							class="form-control input-md" required=""></form:input>

					</div>
				</div>


				<!-- Text input-->
				<div class="form-group">
					<label class="col-md-4 control-label" for="pincode">Enter
						pincode</label>
					<div class="col-md-4">
						<form:input path="addresses[${row.index}].pincode" id="pincode"
							type="text" placeholder="Enter pincode"
							class="form-control input-md" required=""></form:input>

					</div>
				</div>
				<hr />
				<hr />
			
			</c:forEach>

			<!-- Button -->
			<div class="form-group">
				<label class="col-md-4 control-label" for=""></label>
				<div class="col-md-4">
				     <c:choose>
				          <c:when test="${employeeForm.id != null}">
				          <button type="submit" id="" name="" class="btn btn-primary">Update</button>
				          </c:when>
				     <c:otherwise>
					      <button type="submit" id="" name="" class="btn btn-primary">Save</button>
					</c:otherwise>
					</c:choose>
				</div>
			</div>
		</fieldset>
	</form:form>



	 <div class="container">
		<h2>Employee List</h2>
		<table class="table table-striped">
			<thead>
				<tr>
					<th>Id</th>
					<th>Name</th>
					<th>City</th>
					<th>Pincode</th>
					<th>Action</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${employees}" var="emp">
			
				<tr>
					<td>${emp.id}</td>
					<td>${emp.name}</td>
					<td>
					<c:forEach items="${emp.addresses}" var="addr">
					  ${addr.city}
					</c:forEach>
					</td> 
					<td>
					<c:forEach items="${emp.addresses}" var="addr">
					${addr.pincode}
					</c:forEach>
					</td> 
					<td><a href="edit?id=${emp.id}">Edit</a> | <a href="delete?id=${emp.id}">Delete</a></td>
				</tr>
				</c:forEach>
				
			</tbody>
		</table>
	</div> 

</body>
</html>
