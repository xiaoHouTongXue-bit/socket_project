<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>操作界面</title>
</head>
<body>
<td >链接成功</td>
	<form action="OperatorServlet" method="post">
		<table>
			<tr>
				<td>输入：</td>
				<td><input type="text" name="first" value="${requestScope.first}" ></td>
			</tr>
			<tr>
				<td>输入：</td>
				<td><input type="text" name="second" value="${requestScope.second}"></td>
			</tr>
			<tr>
				<td ><input type="submit" value="提交"></td>
	</form>
			<td > ${requestScope.answer} </td>
			</tr>
</body>
</html>