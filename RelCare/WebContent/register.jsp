<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>RelCare Hospitals</title>
</head>
<body>
<div align="center">
<form style="align:center" method="post" action="<%=request.getContextPath()%>/registeruser">
<div>Please enter the following details to register as a patient.</div>
<table border="0 none" >
		<tr>
			<td><span class="login-label">First Name:</span> </td>
			<td><input type="text" value="" name="fname" id="fname" class="Login-Input"/></td>
		</tr>
		<tr>
			<td><span class="login-label">Last Name:</span> </td>
			<td><input type="text" value="" name="lname" id="lname" class="Login-Input"/></td>
		</tr>
		<tr>
			<td><span class="login-label">Email:</span> </td>
			<td><input type="text" value="" name="email" id="email" class="Login-Input"/></td>
		</tr>
		<tr>
			<td><span class="login-label">Password:</span></td>
			<td><input type="password" value="" name="pword" id="pword" class="Login-Input"/></td>
		</tr>
		<tr>
			<td><span class="login-label">Gender:</span></td>
			<td><input type="radio" name="gender" id="gender" value="Female">Female</input>
			<input type="radio" name="gender" id="gender" value="Male">Male</input></td>
		</tr>
		<tr>
			<td><span class="login-label">Dob:</span></td>
			<td><input type="dob" value="" name="dob" id="dob" class="Login-Input"/>(MM/DD/YYYY)</td>
		</tr>
</table>
	<input type="submit" value="Register" name="Login-Submit" class="Login-Input" />
</form>
</div>
</body>
</html>