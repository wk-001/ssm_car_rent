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
    <title>菜单详情</title>
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
<div class="childrenBody">
    <div style="padding:10px 20px 1px 20px;">

        <!--搜索栏-->
        <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
            <legend>查询条件</legend>
        </fieldset>

        <form class="layui-form" method="post" id="searchMenu">

            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">菜单名称：</label>
                    <div class="layui-input-inline">
                        <input type="text" name="title"class="layui-input">
                    </div>
                </div>

                <div class="layui-inline">
                    <button type="button" class="layui-btn layui-btn-normal layui-icon layui-icon-search" id="doSearch">查询</button>
                    <button type="reset" class="layui-btn layui-btn-warm layui-icon layui-icon-refresh">重置</button>
                </div>
            </div>

        </form>

        <!--工具栏按钮-->
        <div id="toolBar" style="display: none">
            <button type="button" class="layui-btn layui-btn-sm layui-btn-normal" lay-event="add">增加</button>
            <button type="button" class="layui-btn layui-btn-sm layui-btn-normal" lay-event="update">编辑</button>
            <button type="button" class="layui-btn layui-btn-sm layui-btn-normal" lay-event="delete">删除</button>
            <button type="button" class="layui-btn layui-btn-sm layui-btn-normal" lay-event="getSelect">获取选中行</button>
            <button type="button" class="layui-btn layui-btn-sm layui-btn-normal" lay-event="refreshTable">刷新表格</button>
        </div>

        <!--数据表格-->
        <table class="layui-hide" id="menuTable" lay-filter="menuTable"></table>

        <!--数据编辑删除栏-->
        <div id="menuBar" style="display: none">
            <a class="layui-btn layui-btn-sm" lay-event="edit">编辑</a>
            <a class="layui-btn layui-btn-danger layui-btn-sm" lay-event="del">删除</a>
        </div>

        <!--添加修改弹出层-->
        <div style="display: none;padding: 20px" id="saveOrUpdate">
            <form class="layui-form" action="" lay-filter="userForm" id="userForm" method="post">
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">用户名</label>
                        <div class="layui-input-inline">
                            <input type="text" name="username" lay-verify="required" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">职业</label>
                        <div class="layui-input-inline">
                            <input type="text" name="classify" class="layui-input">
                        </div>
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">城市</label>
                        <div class="layui-input-inline">
                            <input type="text" name="city" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">签名</label>
                        <div class="layui-input-inline">
                            <input type="text" name="sign" class="layui-input">
                        </div>
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">性别</label>
                        <div class="layui-input-inline">
                            <input type="radio" name="sex" value="1" title="男" checked>
                            <input type="radio" name="sex" value="0" title="女">
                        </div>
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-input-block" style="padding-left:180px">
                        <button type="button" class="layui-btn layui-btn-normal layui-icon layui-icon-add-1" lay-submit lay-filter="addUser" id="addUser">添加</button>
                        <button type="reset" class="layui-btn layui-btn-warm layui-icon layui-icon-refresh">重置</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>

</body>
<style>
    /*解决数据表格复选框位置不居中的问题*/
    .layui-table-cell .layui-form-checkbox[lay-skin="primary"]{
        top: 50%;
        transform: translateY(-50%);
    }
</style>
<script type="text/javascript">
    var tableIns;       //定义全局变量 使layui方法块外也可以调用表格
    layui.use(['jquery','element','form','layer','table'], function(){
        var $ = layui.jquery
            ,element = layui.element
            ,form = layui.form
            ,layer = layui.layer
            ,table = layui.table

        //渲染数据表格
        tableIns = table.render({
            elem: '#menuTable'      //渲染目标对象 数据表格对应ID
            ,height: 'full-180'            //数据表格高度 可用高度-指定高度
            ,url: "<%=basePath%>menu/menuDataList"
            ,page: true //开启分页
            ,toolbar:"#toolBar"     //引用表头工具栏 toolBar是div的ID
            ,defaultToolbar: ['filter', 'print', 'exports']     //修改默认工具栏的功能和顺序
            //,limits: [10, 20, 30]   //可显示的数据条数
            //,limit: 10              //每页默认显示的数量
            ,even: true             //开启隔行背景
            //,totalRow:true	    //开启合并行
            ,text: {
                none: '暂无相关数据' //无数据时显示的内容 默认：无数据。
            }
            /*,done: function(res, curr, count){      //数据渲染完的回调
                //如果是异步请求数据方式，res即为你接口返回的信息。
                //如果是直接赋值的方式，res即为：{data: [], count: 99} data为当前页数据、count为数据总长度
                console.log(res);
                console.log(curr);      //得到当前页码
                console.log(count);     //得到数据总量
            }*/
            ,cols: [[ //表头
                {type:'checkbox', fixed: 'left'}    //fixed冻结列的位置
                ,{field:'id', title:'ID',align:'center'}
                ,{field:'pid', title:'父节点ID',align:'center'}
                ,{field:'title', title:'菜单名称',align:'center'}
                ,{field:'href', title:'菜单地址',align:'center'}
                ,{field:'spread', title:'是否展开',align:'center',templet:function(d){
                        return d.spread=='1'?'<span style="color: blue;">展开</span>':'<span style="color: red;">不展开</span>';
                        
                    }}
                ,{field:'target', title:'TARGET',align:'center'}
                ,{field:'icon', title:'菜单图标',align:'center',templet:function(d){
                        return "<div class='layui-icon'>"+d.icon+"</div>";
                    }}
                ,{field:'available', title:'是否可用',align:'center',templet:function(d){
                        return d.available=='1'?'<span style="color: blue;">可用</span>':'<span style="color: red;">不可用</span>';
                    }}
                ,{fixed: 'right', title:'操作', toolbar: '#menuBar',align:'center'}
            ]]
        });

        //监听头部工具栏事件 menuTable:数据表格的ID
        table.on('toolbar(menuTable)', function(obj){
            switch(obj.event){
                case 'add':             //工具栏按钮lay-event属性的值
                    openAddUser();
                    break;
                case 'delete':
                    layer.msg('删除');
                    break;
                case 'update':
                    layer.msg('编辑');
                    break;
                case 'refreshTable':        //在不刷新页面的情况刷新表格数据
                    /*table.reload("menuTable", {
                        url: '/json/user.json/'
                    });*/
                    tableIns.reload();      //在不刷新页面的情况刷新表格数据
                    break;
                case 'getSelect':   //获取checkbox选中行的数据
                    var checkStatus = table.checkStatus('menuTable'); //menuTable:数据表格的ID
                    console.log(checkStatus.data) //获取选中行的数据
                    console.log(checkStatus.data.length) //获取选中行数量，可作为是否有选中行的条件
                    console.log(checkStatus.isAll ) //表格是否全选
                    break;
            };
        });

        //菜单模糊查询
        $("#doSearch").click(function () {
            var params = $("#searchMenu").serialize();
            tableIns.reload({
                url:"<%=basePath%>menu/menuDataList?"+params
            });
        })

        // 监听复选框事件 menuTable:数据表格的ID
        /*table.on('checkbox(menuTable)', function(obj){
            console.log(obj.checked); //当前是否选中状态
            console.log(obj.data); //选中行的相关数据
            console.log(obj.type); //如果触发的是全选，则为：all，如果触发的是单选，则为：one
        });*/

        //监听单元格编辑 单元格被编辑，且值发生改变时触发，前提是单元格设置为可编辑
        // 回调函数返回一个object参数 menuTable:数据表格的ID
        table.on('edit(menuTable)', function(obj){ //注：edit是固定事件名，test是table原始容器的属性 lay-filter="对应的值"
            console.log(obj.value); //得到修改后的值
            console.log(obj.field); //当前编辑的字段名
            console.log(obj.data); //所在行的所有相关数据
            //发送post请求更新数据库数据
        });

        //监听行单击事件
        table.on('row(menuTable)', function(obj){
            console.log(obj.tr) //得到当前行元素对象
            console.log(obj.data) //得到当前行数据
            //obj.del(); //删除当前行
            //obj.update(fields) //修改当前行数据
        });

//监听行双击事件
        /*table.on('rowDouble(menuTable)', function(obj){
            console.log(obj.tr) //得到当前行元素对象
            console.log(obj.data) //得到当前行数据
            //obj.del(); //删除当前行
            //obj.update(fields) //修改当前行数据
        });*/

        //监听工具条  注：tool 是工具条事件名，menuTable 是 table 原始容器的属性 lay-filter="对应的值"
        table.on('tool(menuTable)', function(obj){
            var data = obj.data; //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
            var tr = obj.tr; //获得当前行 tr 的 DOM 对象（如果有的话）

            if(layEvent === 'del'){ //删除
                layer.confirm('真的删除行么', function(index){
                    obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
                    layer.close(index);
                    //向服务端发送删除指令
                });
            } else if(layEvent === 'edit'){ //编辑
                openUpdUser(data);      //修改当前行数据
                //同步更新缓存对应的值
                obj.update({
                    username: '123'
                    ,title: 'xxx'
                });
            } else if(layEvent === 'LAYTABLE_TIPS'){
                layer.alert('Hi，头部工具栏扩展的右侧图标。');
            }
        });

        var url = "";       //区分添加和修改提交的URL
        var mainModel = "";     //弹窗对象
        //打开添加用户弹出框
        function openAddUser(){
            mainModel = layer.open({
                type: 1
                ,title:'添加用户'
                ,content:$("#saveOrUpdate")
                ,area: ['750px','320px']     //弹窗宽高
                ,success:function (index) {
                    //打开弹窗清空整个form表单,jquery对象获取的是所有对象的数组，数组中是dom对象，dom对象才有reset();方法
                    $("#userForm")[0].reset();
                    url = "/user/add";
                }
            });
        }

        //打开修改用户弹出框
        function openUpdUser(data) {
            mainModel = layer.open({
                type: 1
                , title: '修改用户信息'
                , content: $("#saveOrUpdate")
                , area: ['750px', '320px']     //弹窗宽高
                ,success:function (index) {         //弹窗成功后回调
                    //给lay-filter="userForm"的表单赋值,name相同可以直接赋值
                    form.val("userForm",data);
                    url = "/user/update";
                }
            });
        }

        //保存数据，监听submit
        form.on('submit(addUser)', function(obj) {
            //序列化表单数据
            var params = $("#userForm").serialize();
            layer.msg(params);
            $.post(url,params,function (obj) {
                layer.msg(obj);
                //关闭弹窗
                layer.close(mainModel);
                // 刷新数据表格
                tableIns.reload();      //在不刷新页面的情况刷新表格数据
            })
        })

    });
    function reloadTable(id){
        tableIns.reload({
            url:"<%=basePath%>menu/menuDataList?id="+id
        });
    }
</script>

</html>
