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
    <title>角色管理</title>
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

        <form class="layui-form" method="post" id="searchData">

            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">角色名称：</label>
                    <div class="layui-input-inline">
                        <input type="text" name="rolename"class="layui-input">
                    </div>
                </div>

                <div class="layui-inline">
                    <label class="layui-form-label">角色详情：</label>
                    <div class="layui-input-inline">
                        <input type="text" name="roledesc"class="layui-input">
                    </div>
                </div>

                <div class="layui-inline">
                    <label class="layui-form-label">是否可用:</label>
                    <div class="layui-input-inline">
                        <input type="radio" name="available" value="1" title="可用">
                        <input type="radio" name="available" value="0" title="不可用">
                    </div>
                </div>

                <div class="layui-inline">
                    <button type="button" class="layui-btn layui-btn-normal layui-icon layui-icon-search" id="doSearch">查询</button>
                    <button type="reset" class="layui-btn layui-btn-warm layui-icon layui-icon-refresh">重置</button>
                </div>
            </div>

        </form>

        <!--工具栏按钮-->
        <div id="topToolBar" style="display: none">
            <button type="button" class="layui-btn layui-btn-sm layui-btn-normal" lay-event="add">增加</button>
            <button type="button" class="layui-btn layui-btn-sm layui-btn-normal" lay-event="update">编辑</button>
            <button type="button" class="layui-btn layui-btn-sm layui-btn-normal" lay-event="delete">删除</button>
            <button type="button" class="layui-btn layui-btn-sm layui-btn-danger" lay-event="batchDelete">批量删除</button>
            <button type="button" class="layui-btn layui-btn-sm layui-btn-normal" lay-event="getSelect">获取选中行</button>
            <button type="button" class="layui-btn layui-btn-sm layui-btn-normal" lay-event="refreshTable">刷新表格</button>
        </div>

        <!--数据表格-->
        <table class="layui-hide" id="dataTable" lay-filter="dataTable"></table>

        <!--数据编辑删除栏-->
        <div id="dataToolBar" style="display: none">
            <a class="layui-btn layui-btn-sm" lay-event="edit">编辑</a>
            <a class="layui-btn layui-btn-normal layui-btn-sm" lay-event="assignMenu">分配菜单</a>
            <a class="layui-btn layui-btn-danger layui-btn-sm" lay-event="del">删除</a>
        </div>

        <!-- 添加和修改的弹出层开始 -->
        <div style="display: none;padding: 20px" id="saveOrUpdateDiv" >
            <form class="layui-form"  lay-filter="dataForm" id="dataForm">
                <div class="layui-form-item">
                    <label class="layui-form-label">角色名称:</label>
                    <div class="layui-input-block">
                        <input type="hidden" name="roleid">     <%--修改时需要用到ID--%>
                        <input type="text" name="rolename"  placeholder="请输入角色名称" autocomplete="off"
                               class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">角色详情:</label>
                    <div class="layui-input-block">
                        <input type="text" name="roledesc" placeholder="请输入角色详情" autocomplete="off"
                               class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">是否可用:</label>
                        <div class="layui-input-inline">
                            <input type="radio" name="available" value="1" checked="checked" title="可用">
                            <input type="radio" name="available" value="0" title="不可用">
                        </div>
                    </div>
                </div>
                <div class="layui-form-item" style="padding-left:80px">
                    <div class="layui-input-block">
                        <button type="button" class="layui-btn layui-btn-normal layui-btn-sm layui-icon layui-icon-release" lay-filter="doSubmit" lay-submit="">提交</button>
                        <button type="reset" class="layui-btn layui-btn-warm layui-btn-sm layui-icon layui-icon-refresh" >重置</button>
                    </div>
                </div>
            </form>
        </div>
        <!-- 添加和修改的弹出层结束 -->

        <%--角色分配菜单开始--%>
        <div style="display: none" id="selectMenuTree">
            <ul id="menuTree" class="dtree" data-id="0"></ul>
        </div>
        <%--角色分配菜单结束--%>

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
    layui.extend({
        dtree: '<%=basePath%>layuicms2.0/layui_ext/dist/dtree'   // dtree.js所在位置；{/}的意思即代表采用自有路径，即不跟随 base 路径
    }).use(['jquery','element','form','layer','table','dtree'], function(){
        var $ = layui.jquery
            ,element = layui.element
            ,form = layui.form
            ,layer = layui.layer
            ,table = layui.table
            ,dtree = layui.dtree

        //渲染数据表格
        tableIns = table.render({
            elem: '#dataTable'      //渲染目标对象 数据表格对应ID
            ,height: 'full-180'            //数据表格高度 可用高度-指定高度
            ,method: 'post'
            ,url: "<%=basePath%>role/roleList"
            ,page: true //开启分页
            ,toolbar:"#topToolBar"     //引用表头工具栏 topToolBar是div的ID
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
                ,{field:'roleid', title:'ID',align:'center'}
                ,{field:'rolename', title:'角色名称',align:'center'}
                ,{field:'roledesc', title:'角色详情',align:'center'}
                ,{field:'available', title:'是否可用',align:'center',templet:function(d){
                        return d.available=='1'?'<span style="color: blue;">可用</span>':'<span style="color: red;">不可用</span>';
                    }}
                ,{fixed: 'right', title:'操作', toolbar: '#dataToolBar',align:'center'}
            ]]
            ,done: function(res, curr, count){
                //如果当前页面数据全部删除，并且不是第一页的情况，就跳转到前一页
                if(res.data.length==0&&curr!=1){
                    tableIns.reload({
                        page:{curr:curr-1}           //跳转到前一页
                    });
                }
            }

        });

        //监听头部工具栏事件 dataTable:数据表格的ID
        table.on('toolbar(dataTable)', function(obj){
            switch(obj.event){
                case 'add':             //工具栏按钮lay-event="add"属性的值
                    openAddRole();
                    break;
                case 'delete':
                    layer.msg('删除');
                    break;
                case 'batchDelete':
                    batchDelete();
                    break;
                case 'update':
                    layer.msg('编辑');
                    break;
                case 'refreshTable':        //在不刷新页面的情况刷新表格数据
                    /*table.reload("dataTable", {
                        url: '/json/user.json/'
                    });*/
                    tableIns.reload();      //在不刷新页面的情况刷新表格数据
                    break;
                case 'getSelect':   //获取checkbox选中行的数据
                    var checkStatus = table.checkStatus('dataTable'); //dataTable:数据表格的ID
                    console.log(checkStatus.data) //获取选中行的数据
                    console.log(checkStatus.data.length) //获取选中行数量，可作为是否有选中行的条件
                    console.log(checkStatus.isAll ) //表格是否全选
                    break;
            };
        });

        //角色模糊查询
        $("#doSearch").click(function () {
            var params = $("#searchData").serialize();
            console.log(params);
            tableIns.reload({
                url:"<%=basePath%>role/roleList?"+params
            });
        })

        // 监听复选框事件 dataTable:数据表格的ID
        /*table.on('checkbox(dataTable)', function(obj){
            console.log(obj.checked); //当前是否选中状态
            console.log(obj.data); //选中行的相关数据
            console.log(obj.type); //如果触发的是全选，则为：all，如果触发的是单选，则为：one
        });*/

        //监听单元格编辑 单元格被编辑，且值发生改变时触发，前提是单元格设置为可编辑
        // 回调函数返回一个object参数 dataTable:数据表格的ID
        table.on('edit(dataTable)', function(obj){ //注：edit是固定事件名，test是table原始容器的属性 lay-filter="对应的值"
            console.log(obj.value); //得到修改后的值
            console.log(obj.field); //当前编辑的字段名
            console.log(obj.data); //所在行的所有相关数据
            //发送post请求更新数据库数据
        });

        //监听行单击事件
        table.on('row(dataTable)', function(obj){
            console.log(obj.tr) //得到当前行元素对象
            console.log(obj.data) //得到当前行数据
            //obj.del(); //删除当前行
            //obj.update(fields) //修改当前行数据
        });

//监听行双击事件
        /*table.on('rowDouble(dataTable)', function(obj){
            console.log(obj.tr) //得到当前行元素对象
            console.log(obj.data) //得到当前行数据
            //obj.del(); //删除当前行
            //obj.update(fields) //修改当前行数据
        });*/

        //监听工具条  注：tool 是工具条事件名，dataTable 是 table 原始容器的属性 lay-filter="对应的值"
        table.on('tool(dataTable)', function(obj){
            var data = obj.data; //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
            var tr = obj.tr; //获得当前行 tr 的 DOM 对象（如果有的话）

            if(layEvent === 'del'){ //删除
                layer.confirm('确定删除角色['+data.rolename+']吗？', function(index){
                    obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
                    layer.close(index);
                    //向服务端发送删除指令
                    $.post("<%=basePath%>role/deleteRole",{"roleid":data.roleid},function (result) {
                        layer.msg(result.msg);
                        // 刷新数据表格
                        tableIns.reload();      //在不刷新页面的情况刷新表格数据
                    })
                });
            } else if(layEvent === 'edit'){ //编辑
                openUpdRole(data);      //修改当前行数据
            } else if(layEvent === 'assignMenu'){   //角色分配权限
                openMenuTree(data);
            }
        });

        var url = "";       //区分添加和修改提交的URL
        var mainModel = "";     //弹窗对象
        //打开添加用户弹出框
        function openAddRole(){
            mainModel = layer.open({
                type: 1
                ,title:'添加角色'
                ,content:$("#saveOrUpdateDiv")
                ,maxmin: true       //最大化/最小化
                ,area: ['550px','300px']     //弹窗宽高
                ,success:function (index) {
                    //在弹出层加载成功后的回调方法中去掉最小化按钮；
                    index.find('.layui-layer-min').remove();
                    //打开弹窗清空整个form表单,jquery对象获取的是所有对象的数组，数组中是dom对象，dom对象才有reset();方法
                    $("#dataForm")[0].reset();
                    url = "<%=basePath%>role/addRole";
                }
            });
        }

        //打开修改用户弹出框
        function openUpdRole(data) {
            mainModel = layer.open({
                type: 1
                , title: '修改角色信息'
                ,maxmin: true       //最大化/最小化
                , content: $("#saveOrUpdateDiv")
                , area: ['550px', '300px']     //弹窗宽高
                ,success:function (index) {         //弹窗成功后回调
                    //在弹出层加载成功后的回调方法中去掉最小化按钮；
                    index.find('.layui-layer-min').remove();
                    //给lay-filter="dataForm"的表单赋值,name相同可以直接赋值
                    form.val("dataForm",data);
                    url = "<%=basePath%>role/updateRole";
                }
            });
        }

        //批量删除
        function batchDelete(){
            //得到选中的数据ID
            var checkStatus = table.checkStatus('dataTable')
                list="";
            for(var i=0;i<checkStatus.data.length;i++){
                list+=','+checkStatus.data[i].roleid;
            }
            list = list.substr(1);
            alert(list);
            layer.confirm('确定删除选中角色吗？', function(index){
                layer.close(index);
                //向服务端发送删除指令
                $.post("<%=basePath%>role/batchDeleteRole",{"ids":list},function (result) {
                    layer.msg(result.msg);
                    // 刷新数据表格
                    tableIns.reload();      //在不刷新页面的情况刷新表格数据
                })
            });
        }

        //角色分配权限菜单
        function openMenuTree(data){
            var menuTree;
            mainModel = layer.open({
                type: 1
                ,title:'为角色['+data.rolename+']分配权限'
                ,content:$("#selectMenuTree")
                ,maxmin: true       //最大化/最小化
                ,area: ['400px','500px']     //弹窗宽高
                ,btnAlign: 'c'
                ,btn: ['<div class="layui-icon layui-icon-ok">保存</div>', '<div class="layui-icon layui-icon-close">关闭</div>']
                ,yes: function(index, layero){
                    //获取复选框选中值
                    var nodes = dtree.getCheckbarNodesParam("menuTree");
                    var roleid = data.roleid;
                    var params = "roleid="+roleid;
                    $.each(nodes,function (i, item) {
                        params+="&ids="+item.nodeId;
                    })
                    $.post("<%=basePath%>roleMenu/editRoleMenu",params,function (obj) {
                        layer.msg(obj.msg);
                    })
                }
                ,success:function (index) {
                    //在弹出层加载成功后的回调方法中去掉最小化按钮；
                    index.find('.layui-layer-min').remove();
                    //初始化树
                    menuTree = dtree.render({
                        elem: "#menuTree",  //页面容器
                        url: "<%=basePath%>menu/roleMenuTree?roleid="+data.roleid,
                        dataStyle: "layuiStyle",  //使用layui风格的数据格式
                        dataFormat: "list",  //配置data的风格为list
                        response:{message:"msg",statusCode:0},  //修改response中返回数据的定义
                        checkbar: true,
                        checkbarType: "no-all",// 默认就是all上下级联，其他的值为： no-all半选  p-casc   self  only
                        skin: "layui"
                    });
                }
            });
        }

        //保存数据，监听submit
        form.on('submit(doSubmit)', function(obj) {
            //序列化表单数据
            var params = $("#dataForm").serialize();
            $.post(url,params,function (obj) {
                layer.msg(obj.msg);
                //关闭弹窗
                layer.close(mainModel);
                // 刷新数据表格
                tableIns.reload();      //在不刷新页面的情况刷新表格数据
            })
        })

    });

    function reloadTable(id){
        tableIns.reload({
            url:"<%=basePath%>role/roleList?id="+id
        });
    }

</script>

</html>
