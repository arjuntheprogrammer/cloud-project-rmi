<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<%
	String userName=request.getParameter("name");
		
	if(userName!=null){
		session.setAttribute("sessionUserName", userName);
		application.setAttribute("applicationUserName", userName);
		pageContext.setAttribute("pageContextUserName", userName);
	}
	
%>
<br>
the username in request object is <%=userName %>
<br>
the username in session object is <%=session.getAttribute("sessionUserName") %>
<br>
the username in application context bject is <%=application.getAttribute("applicationUserName") %>
<br>
the username in page context bject is <%=pageContext.getAttribute("pageContextUserName") %>

</body>
</html>