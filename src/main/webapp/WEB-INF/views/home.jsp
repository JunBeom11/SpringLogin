<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<c:set var="path" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home!</title>
</head>
<body>
<c:if test="${ loginMember == null }">
	<form action="login" method="POST">
		아이디 : <input type="text" name="userId" required/><br>
		비밀번호 : <input type="password" name="userPwd"/><br><br>
		
		<button type="button" onclick="location.href='${ path }/member/enroll'">회원가입</button>
		<input type="submit" value="로그인"/>
	</form>
</c:if>

<c:if test="${ loginMember != null }">
	<a href="${ path }/member/view">
		${ loginMember.name }
	</a>님, 안녕하세요.
	
	<button onclick="location.replace('${path}/logout')">로그아웃</button>
	
</c:if>

</body>
</html>