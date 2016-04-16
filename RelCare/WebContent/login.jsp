<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>RelCare Hospitals</title>
</head>
<body>
<c:if test="${param.error=='true'}">
	<div style="font-color:red">Username and/or password is incorrect! Please try again.</div>
</c:if>
<c:if test="${param.registered=='true'}">
	<div style="font-color:red">You have been registered. Please log in.</div>
</c:if>
<div align="center">
<form style="align:center" method="post" action="<%=request.getContextPath()%>/j_spring_security_check">
<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/> 
<table border="0 none" >
		<tr>
			<td><span class="login-label">User Name</span> </td>
			<td><input type="text" value="" name="j_username" id="j_username" class="Login-Input"/></td>
		</tr>
		<tr>
			<td><span class="login-label">Password</span></td>
			<td><input type="password" value="" name="j_password" id="j_password" class="Login-Input"/></td>
		</tr>
		</table>
	<input type="submit" value="Login" name="Login-Submit" class="Login-Input" />
	
	 <div class="form-actions">
            <a href="<%=request.getContextPath()%>/reg" class="btn btn-link">Register</a>
     </div>
        
</form>
</div>
</body>
</html>