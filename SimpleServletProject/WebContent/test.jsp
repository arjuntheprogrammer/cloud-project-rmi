<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>testing jsp</h1>
<%!
	public int add (int a, int b){
	return a+b;
}

%>
<%

int i=1;
int j=2;
int k=i+j;
out.println("\nValue of k is "+k);
%>
<br>
The value of k is: <%=k%>

<%
k=add(234,324);

%>
<br>
The value of k is: <%=k%>



<%for(i=1; i<=5;i++){%>
	<br>the value of i= <%=i %>
	
<%}%>


</body>
</html>