<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edit/Add Abonament</title>
</head>
<body>
	<form:form commandName="abonament" methodParam="POST">
		<table border="1" width="400px">				
				<a>Edit/Add Abonament at - ${abonament.person.firstName} ${abonament.person.lastName}</a>
					<form:input type="hidden" path="id"/>
					<form:input type="hidden" path="person.id"/>
					
				<tr>
					<td><form:label path="status">Status:</form:label></td>
					<td>
						<form:select path="status">						
					   			<form:option value="NONE" label="NONE"/>						   		
					   			<form:option value="SUSPENDED" label="SUSPENDED"/>
					   			<form:option value="ACTIVE" label="ACTIVE"/>
					   	</form:select>
					</td>					
				</tr>	
				<tr>
					<td><form:label path="startDate">Start Date:</form:label></td>
					<td>
						<form:input path="startDate"/>
						<form:errors path="startDate"/>
					</td>					
				</tr>
				<tr>
					<td><form:label path="endDate">End Date:</form:label></td>
					<td>
						<form:input path="endDate"/>
						<form:errors path="endDate"/>
					</td>					
				</tr>		
				<tr>
					<td></td>					
					<td><input type="submit" value="Save" /></td>
				</tr>
			</table>	
	</form:form>
</body>
</html>