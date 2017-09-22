<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
</head>
<body>
	
	<h3>Gym System Login</h3>
	
	<form method="post" action="${pageContext.request.contextPath}/login">
		<table border="0">
			<tr>
				<td>User Name</td>
				<td><input type="text" name="userName" value="${user.userName}" /> </td>
			</tr>
			<tr>
				<td>Password</td>
				<td><input type="password" name="password" value="${user.password}" /></td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="Submit" />
				</td>
			</tr>
		</table>
	</form>
	
	<p style="color:blue;">User Name: tom, password: tom001 or jerry/jerry001</p>

</body>
</html>