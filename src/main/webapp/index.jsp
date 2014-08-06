<html>
<body>
<h2>Hello World!</h2>
<%
	request.getRequestDispatcher("entry.jhtml").forward(request, response); // has better performance in this way
%>
</body>
</html>
