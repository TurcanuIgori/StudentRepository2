<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<script src="<c:url value="resources/js/js.js"/>" type="text/javascript"></script>
	<script	src="http://ajax.googleapis.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
	<link rel="stylesheet" href="http://code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
	<script src="http://code.jquery.com/jquery-1.10.2.js"></script>
	<script src="http://code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
	<title>Insert title here</title>
</head>
<body>
		<h3>Edit/Add new Student</h3>
		<form:form commandName="student" methodParam="POST" enctype="multipart/form-data">
			<table width="700px">
				<tr>
					<form:hidden path="id"/>					
					<td><form:label path="firstName">First Name:</form:label></td>
					<td>
						<form:input path="firstName"/>
						<form:errors path="firstName"/>
					</td>
					<td rowspan="4"><img form="student" src="image?id=${student.id}" id="image" width="150px">					
					</td>
				</tr>	
				<tr>
					<td><form:label path="lastName">Last Name:</form:label></td>
					<td>
						<form:input path="lastName"/>
						<form:errors path="lastName"/>
					</td>
				</tr>
				<tr>
					<td><form:label path="address.street">Street:</form:label></td>
					<td>
						<form:input path="address.street"/>
						<form:errors path="address.street"/>
					</td>
				</tr>	
				<tr>
					<td><form:label path="address.city">City:</form:label></td>
					<td>
						<form:input path="address.city"/>
						<form:errors path="address.city"/>
					</td>
				</tr>
				<tr>
					<td><form:label path="address.country">Country:</form:label></td>
					<td><form:input path="address.country"/></td>
					<td><input type="file" name="img" form="student" id="img" accept="image/*"	onchange="$('#image')[0].src=window.URL.createObjectURL(this.files[0])"	width="200px" form="student"></td>
				</tr>				
				<tr>
					<td><form:label path="dob">Date of Birth:</form:label></td>
					<td>
						<form:input path="dob" autocomplete="2016-01-01"/>
						<form:errors path="dob"/>
					</td>
					<td></td>
				</tr>
				<tr>
					<td><form:label path="gender">Gender:</form:label></td>
					<td>
						<form:radiobutton path="gender" value="M" label="Male"/>
						<form:radiobutton path="gender" value="F" label="Female"/>
					</td>
					<td></td>
				</tr>
				<tr>
					<td><form:label path="email">Email:</form:label></td>
					<td>
						<form:input path="email"/>
						<form:errors path="email"/>
					</td>
					<td></td>
				</tr>
				<tr>
					<td><form:label path="grup">Group:</form:label></td>
					<td>
						<form:select path="grup.id">
					   		<form:option value="0" label="Group"/>
					   	 	<c:forEach var="grup" items="${grups}">
					   			<form:option value="${grup.id}" label="${grup.name}"/>											   			
					   		</c:forEach>
					   	</form:select>
					</td>
					<td></td>
				</tr>							
				<tr>	
					<td>Phone(s):</td>
					<td>						
						<div id='TextBoxesGroup'>
							<div id="TextBoxDiv0">			
							<script>
								var counter = 1;
							</script>													   			   	
								<form:select path="listPhones[0].phoneType.id">
							   		<form:option value="0" label="PhoneType"/>
							   	 	<c:forEach var="type" items="${phonetype}">
							   			<form:option value="${type.id}" label="${type.type}"/>											   			
							   		</c:forEach>
							   	</form:select>
							   	<form:hidden path="listPhones[0].id"/>				   
								<form:input path="listPhones[0].phone"/>
								<form:errors path="listPhones[0].phone"/>
							</div>
							<c:forEach items="${student.listPhones}" var="phone" varStatus="index" begin="1">
								<script>
									counter++;
								</script>
								<div id="TextBoxDiv${index.index}">																   			   	
								   	<form:select path="listPhones[${index.index}].phoneType.id">
								   		<form:option value="0" label="PhoneType"/>
								   		 <c:forEach var="type" items="${phonetype}">
								   			<form:option value="${type.id}" label="${type.type}"/>											   			
								   		</c:forEach>
								   	</form:select>
								   	<form:hidden path="listPhones[${index.index}].id"/>				   
								   	<form:input path="listPhones[${index.index}].phone"/>
								   	<form:errors path="listPhones[${index.index}].phone"/>
								</div>
							</c:forEach>
						</div>						
					</td>
					<td>
						<input type='button' form="student" value='+' id='addInput' onClick="addPhoneDiv()">
						<input type='button' value='- ' form="student" id='removeInput' onclick="removeDiv()">
					</td>
					<form:hidden path="disciplineList[0].id" value="1"/>
					<form:hidden path="disciplineList[1].id" value="2"/>
				</tr>	
						
<!-- 			   	<tr> -->
<!-- 					<td> -->
<%-- 						<form:checkbox path="disciplineList[0].id" value="1" label="Informatica"/><br> --%>
<%-- 						<form:checkbox path="disciplineList[1].id" value="2" label="Matematica"/> --%>
<!-- 					</td> -->
<!-- 					<td></td> -->
<!-- 					<td></td> -->
<!-- 				</tr> -->
				<tr>
					<td></td>
					<td></td>
					<td><input type="submit" value="Save" /></td>
				</tr>
			</table>		
		</form:form>
</body>
</html>