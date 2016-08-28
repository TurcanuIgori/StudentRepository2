<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<script src="<c:url value="resources/js/ajax.js"/>" type="text/javascript"></script>
	<script src="<c:url value="resources/js/js.js"/>" type="text/javascript"></script>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Insert title here</title>
</head>
<body>
	<form:form commandName="mark" methodParam="POST">
		ADD NEW MARK
		<table border="1" width="400px">
			<tr>
				<td colspan="2">					
					Add mark to ${mark.student.firstName} ${mark.student.lastName}
					<form:hidden path="student.id"/>
				</td>				
			</tr>
			<tr>
			<td>
				<form:label path="discipline">Discipline:</form:label>
			</td>
			<td>
				<form:select path="discipline.id" onchange="getProffessorsInfo()">
					<form:option value="0" label="Discipline"/>
					<c:forEach var="discipline" items="${mark.student.disciplineList}">
						<form:option value="${discipline.id}" label="${discipline.title}"/>			
					</c:forEach>
				</form:select>
			</td>
			</tr>
			<tr>
				<td>					
					<form:label path="proffessor">Proffessor:</form:label>
				</td>
				<td>
					<form:select path="proffessor.id">
						<form:option value="0" label="Proffessor"/>
<%-- 						<c:forEach var="discipline" items="${mark.student.disciplineList}"> --%>
<%-- 							<form:option value="${discipline.id}" label="${discipline.title}"/>			 --%>
<%-- 						</c:forEach> --%>
					</form:select>
				</td>
			</tr>
			<tr>
				<td>					
					<form:label path="mark">Mark:</form:label>
				</td>
				<td>
					<form:input path="mark"/>
					<form:errors path="mark"/>
				</td>				
			</tr>
			<tr>
				<td>				
				</td>
				<td><input type="submit" value="Save" /></td>				
			</tr>
		</table>		
	</form:form>
</body>
</html>