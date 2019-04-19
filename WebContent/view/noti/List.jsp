<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta charset="UTF-8">
<title>List </title>
</head>
<body>

 <table border="1">
		<tr>
			<td>번호</td>
			<td>제목</td>
			<td>작성자</td>
			<td>작성일</td>
			<td>조회수</td>
		</tr>
<c:forEach var="dto" items="${data }" varStatus="no">	
		<tr>
			<td>${no.index }</td>
			<td>${dto.title }</td>
			<td>${dto.pname }</td>
			<td>${dto.regdate }</td>
			<td>${dto.no }</td>
		</tr>
</c:forEach>			
	
		
	</table> 
</body>
</html>