<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD Xjsp 1.0 Transitional//EN" "http://www.w3.org/TR/xjsp1/DTD/xjsp1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/jsp; charset=utf-8" />
<title>Book Store</title>

</head>
<html>
<body>
	<h2>Hello World!</h2>

	<form action="<%=request.getContextPath()%>/AndroidServerServlet" method="post">
		<table>
			<tr>
				<td>username:</td>
				<td><input type="text" name="username" /></td>
			</tr>
			<tr>
				<td>password:</td>
				<td><input type="password" name="password" /></td>
			</tr>
			<tr>
				<td></td>
				<td align="right"><input type="submit" align="right" /></td>
			</tr>
		</table>
	</form>
</body>
</html>
