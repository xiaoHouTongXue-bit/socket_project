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
			<td><input type="submit" name="first" value="${requestScope.first=1}" >左转</td>
		</tr>
		<tr>
			<td> </td>
			<td> <input type="hidden" name="second" value="${requestScope.second=0}"></td>
			<%--用之前的代码改的，所以second没有删除隐藏了--%>
			<%--不过如何给网页按钮设置大一点，网页显示按钮太小了--%>
		</tr>

		<tr>
			<td><input type="submit" name="first" value="${requestScope.first=2}" >右转</td>
		</tr>
		<tr>
			<td> </td>
			<td> <input type="hidden" name="second" value="${requestScope.second=0}"></td>
		</tr>
		<tr>
			<td><input type="submit" name="first" value="${requestScope.first=3}" >上</td>
		</tr>
		<tr>
			<td> </td>
			<td> <input type="hidden" name="second" value="${requestScope.second=0}"></td>
		</tr>
				<tr>
			<td><input type="submit" name="first" value="${requestScope.first=4}" >下</td>
		</tr>
		<tr>
			<td> </td>
			<td> <input type="hidden" name="second" value="${requestScope.second=0}"></td>
		</tr>
				<tr>
			<td><input type="submit" name="first" value="${requestScope.first=5}" >左</td>
		</tr>
		<tr>
			<td> </td>
			<td> <input type="hidden" name="second" value="${requestScope.second=0}"></td>
		</tr>
		<%--<tr>
            <td > ${requestScope.answer} </td>
        </tr>--%>
		<tr>
			<td><input type="submit" name="first" value="${requestScope.first=6}" >右</td>
		</tr>
		<tr>
			<td> </td>
			<td> <input type="hidden" name="second" value="${requestScope.second=0}"></td>
		</tr>
		<tr>
			<td><input type="submit" name="first" value="${requestScope.first=0}" >刷新</td>
		</tr>
		<tr>
			<td> </td>
			<td> <input type="hidden" name="second" value="${requestScope.second=0}"></td>
		</tr>


</form>
<tr>
	<td > ${requestScope.answer} </td>
</tr>
</body>
</html>