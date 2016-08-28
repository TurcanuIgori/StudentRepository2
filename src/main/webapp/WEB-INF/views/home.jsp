<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
	<head>				
		<title>Home</title>
	</head>
	<body>
		<h1>University</h1>
		<hr />
		<h2>List Students</h2>		
		<form:form commandName="searchData" methodParam="POST" >
			<table>
				<tr>
					<td>
						<form:label path="name">Name:</form:label>
					</td>
					<td>
						<form:input path="name"/>
					</td>
					<td>
						<form:label path="startDob">Date of Birth:</form:label>
					</td>
					<td>
						<form:input path="startDob"/>
					</td>
					<td>
						<form:input path="endDob"/>
					</td>
				</tr>
				<tr>
					<td>
						<form:label path="address">Address:</form:label>
					</td>
					<td>
						<form:input path="address"/>
					</td>
					<td>
						<form:label path="discipline">Discipline:</form:label>
					</td>
					<td>
						<form:select path="discipline">
							<form:option value="0" label="Discipline"/>
							<c:forEach var="discipline" items="${disciplineList}">
								<form:option value="${discipline.id}" label="${discipline.title}"/>			
							</c:forEach>
						</form:select>
					</td>
					<td>
						<form:input path="disciplineAvg"/>
					</td>
				</tr>
				<tr>
					<td>
					<form:label path="group">Group:</form:label>
					</td>
					<td>
						<form:select path="group">
					   		<form:option value="0" label="Group"/>
					   	 	<c:forEach var="grup" items="${groups}">
					   			<form:option value="${grup.id}" label="${grup.name}"/>											   			
					   		</c:forEach>
					   	</form:select>
					</td>
					<td>
						<form:label path="totalAvg">Toral Average: </form:label>
					</td>
					<td>
						<form:input path="totalAvg"/>
					</td>
					<td>
					</td>
				</tr>
				<tr>
					<td>
						<form:label path="gender">Gender:</form:label>
					</td>
					<td>
						<form:radiobutton path="gender" value="M" label="Male"/>
						<form:radiobutton path="gender" value="F" label="Female"/>						
					</td>
					<td>
					</td>
					<td>
					</td>
					<td>
						<input type="submit" name="search" value="Search" form="searchData">
						<input type="reset" name="reset" value="Reset" form="searchData">
					</td>
				</tr>
			</table>
		</form:form>
		<table border="1" width="100%">
			<tr>
			   <td>ID</td>
			   <td>Foto</td>
			   <td>First Name</td>
			   <td>Last Name</th>
			   <td>Gender</td>
			   <td>Date of birth</td>
			   <td>Group</td>
			   <td>Abonament</td>
			   <td>Address</td>			  
			   <td>Phone(s)</td>	
			   <td>Mark(s)</tD>			   		   			   		
			   <td>Actions</td>			   	   
			</tr>
			<c:forEach var="student" items="${students}">
			<tr>
			   <td><c:out value="${student.id}"/></td>
			   <td><img src="image?id=${student.id}" width="200px"></td>
			   <td><c:out value="${student.firstName}"/></td>
			   <td><c:out value="${student.lastName}"/></td>   
			   <td><c:out value="${student.gender}"/></td>
			   <td><c:out value="${student.dob}"/></td>
			   <td><c:out value="${student.grup.name}"/></td>
			   <td>			   	
<%-- 			  	<c:choose> --%>
<%-- 			  		<c:when test="${abonament.status == 'NONE'}"> --%>
<%-- 			  			<a href="abonament?id=${student.id}"><c:out value="${student.abonament.status}"/></a> --%>
<%-- 			  		</c:when> --%>
<%-- 			  		<c:when test="${abonament.id < 1}"> --%>
<%-- 			  			<a href="abonament?id=${student.id}">NONE</a> --%>
<%-- 			  		</c:when> --%>
<%-- 			  		<c:otherwise> --%>
<%-- 			  			<a href="abonament?id=${student.id}"><c:out value="${student.abonament.status}"/></a> --%>
<%-- 			  		</c:otherwise> --%>
<%-- 			  	</c:choose> --%>
					<c:if test="${empty student.abonament}">
						<a href="abonament?abonamentId=${student.id}">NONE</a>
					</c:if>
					<c:if test="${not empty student.abonament}">
						<a href="abonament?abonamentId=${student.id}"><c:out value="${student.abonament.status}"/></a>
						<c:if test="${student.abonament.status == 'ACTIVE'}">
							<br>From: ${student.abonament.startDate}<br>
							To: ${student.abonament.endDate}
						</c:if>						
					</c:if>
			   </td>
			   <td>${student.address.country}, or.${student.address.city}, str.${student.address.street}</td>
			   <td>
			   	<c:forEach var="phone" items="${student.listPhones}">
			   		<c:out value="${phone.phoneType.type}"/>
			   		<c:out value="${phone.phone}"/><br/>
			   	</c:forEach>
			   </td>
			   <td>
			   	<c:forEach var="mark" items="${student.averageList}">
			   		${mark.discipline.title}: ${mark.averageMark }	(${mark.discipline.scholarshipThreshold}) <br>   		
			   	</c:forEach>
			   </td>
			   	<td>
				   <a href="mark?stId=${student.id}"><img src="resources/img/mark.png" width="25px"><br> 
				   <a href="edit?id=${student.id}"><img src="resources/img/edit.png" width="25px"><br>
				   <a href="delete?id=${student.id}"><img src="resources/img/delete.jpg" width="25px">
			   	</td>
			</tr>
			</c:forEach>
			<tr>
			   <td colspan="11"></td>			   			   		   			   		
			   <td><a href="downloadPdf"><img src="resources/img/download.png" width="25px"></a> <a href="edit?id=0"><img src="resources/img/add.jpg" width="25px"></a></td>			   	   
			</tr>
		</table>					
	</body>
</html>
