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
    <title>菜单管理</title>
</head>

<%--如果使用frameset框架，主页面不能有body--%>
<%--页面布局 组装其他页面--%>
<frameset cols="200,*" border="1">      <%--中间分为两部分，左侧菜单占200--%>
    <frame src="<%=basePath%>sys/toMenuLeft" name="left">       <%--组装左侧菜单页面--%>
    <frame src="<%=basePath%>sys/toMenuRight" name="right">       <%--组装主页面--%>
</frameset>
</html>
