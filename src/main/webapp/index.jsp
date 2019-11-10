<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <base href="<%=basePath%>">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Title</title>
</head>
<body>
<jsp:forward page="login/toLogin"></jsp:forward>
</body>
<frameset rows="100,*,50" border="1">  <%--上：占100，中：自适应，下：50--%>
    <frame src="top.jsp">       <%--组装导航页面--%>
    <frameset cols="200,*" border="1">      <%--中间分为两部分，左侧菜单占200--%>
        <frame src="left.jsp">       <%--组装左侧菜单页面--%>
        <frame src="main.jsp">       <%--组装主页面--%>
    </frameset>
    <frame src="bottom.jsp">       <%--组装页脚页面--%>
</frameset>
</html>
