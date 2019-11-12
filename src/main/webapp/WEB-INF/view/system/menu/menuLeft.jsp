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
    <title>菜单树</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta http-equiv="Access-Control-Allow-Origin" content="*">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <link rel="icon" href="<%=basePath%>layuicms2.0/favicon.ico">
    <link rel="stylesheet" href="<%=basePath%>layuicms2.0/layui/css/layui.css" media="all" />
    <link rel="stylesheet" href="<%=basePath%>layuicms2.0/css/public.css" media="all" />
    <link rel="stylesheet" href="<%=basePath%>layuicms2.0/layui_ext/dtree/dtree.css" media="all" />
    <link rel="stylesheet" href="<%=basePath%>layuicms2.0/layui_ext/dtree/font/dtreefont.css" media="all" />
    <script type="text/javascript" src="<%=basePath%>layuicms2.0/layui/layui.js"></script>
</head>
<body>
<%--菜单树--%>
<ul id="menuTree" class="dtree" data-id="0"></ul>
</body>
<script type="text/javascript">
    var menuTree;       //设置全局变量，否则其他页面无法访问
    layui.extend({
        dtree: '<%=basePath%>layuicms2.0/layui_ext/dist/dtree'   // dtree.js所在位置；{/}的意思即代表采用自有路径，即不跟随 base 路径
    }).use(['dtree','layer','jquery'], function(){
        var $ = layui.jquery,
            dtree = layui.dtree,
            layer = layui.layer;

        // 初始化树
        menuTree = dtree.render({
            elem: "#menuTree",  //页面容器
            method:'get',           //访问本地json文件必须用get请求，默认是post请求！！！

            /*layui+list集合数据格式（dataStyle + dataFormat）*/
            url: "<%=basePath%>menu/menuTree?spread=1",
            dataStyle: "layuiStyle",  //使用layui风格的数据格式
            dataFormat: "list",  //配置data的风格为list
            response:{message:"msg",statusCode:0},  //修改response中返回数据的定义

            /*dataFormat:"list",      //使用list风格的数据格式,从数据库查出的数据可以直接使用
            url:'/plugin/layui-v2.5.5/layui_ext/json/case/styleTree12.json',*/

            /*dataStyle: "layuiStyle",  //使用layui风格的数据格式
            url:'/plugin/layui-v2.5.5/layui_ext/json/case/asyncTree10.json',     //layui风格的数据格式
            response:{message:"msg",statusCode:0},  //使用layui风格需要修改response中返回数据的定义*/

            //url:'/plugin/layui-v2.5.5/layui_ext/json/case/asyncTree1.json',     //标准数据格式

            initLevel: 1,
            checkbar: true,
            checkbarType: "no-all",// 默认就是all上下级联，其他的值为： no-all半选  p-casc   self  only
            skin: "layui"
        });

        //监听父节点打开/关闭操作
        dtree.on("changeTree('menuTree')" ,function(obj){
            console.log(obj.param); // 点击当前节点传递的参数
            console.log(obj.dom); // 当前节点的jquery对象
            console.log(obj.show); // 节点展开为true，关闭为false
        });

        // 节点单击事件
        dtree.on("node('menuTree')" ,function(obj){
            var id = obj.param.nodeId;          //获取节点nodeID
            window.parent.right.reloadTable(id);        //window.parent拿到父窗口menuMain对象，调用父窗口中name=right的对象
        });


    });
</script>
</html>
