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
    <title>客户管理</title>
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
                    <label class="layui-form-label">客户姓名:</label>
                    <div class="layui-input-inline">
                        <input type="text" name="custname"  autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">身份证号:</label>
                    <div class="layui-input-inline">
                        <input type="text" name="identity"  autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">客户地址:</label>
                    <div class="layui-input-inline">
                        <input type="text" name="address"  autocomplete="off" class="layui-input">
                    </div>
                </div>
            </div>

            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">客户电话:</label>
                    <div class="layui-input-inline">
                        <input type="text" name="phone"  autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">客户职位:</label>
                    <div class="layui-input-inline">
                        <input type="text" name="career"  autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">性别:</label>
                    <div class="layui-input-inline">
                        <input type="radio" name="sex" value="1" title="男">
                        <input type="radio" name="sex" value="0" title="女">
                    </div>
                </div>
            </div>
            <div class="layui-form-item" style="padding-left:950px">
                <div class="layui-inline">
                    <button type="button" class="layui-btn layui-btn-normal layui-icon layui-icon-search" id="doSearch">查询</button>
                    <button type="reset" class="layui-btn layui-btn-warm layui-icon layui-icon-refresh">重置</button>
                </div>
            </div>
        </form>

        <!--工具栏按钮-->
        <div id="topToolBar" style="display: none">
            <button type="button" class="layui-btn layui-btn-sm layui-btn-normal" lay-event="add">增加</button>
            <button type="button" class="layui-btn layui-btn-sm layui-btn-danger" lay-event="batchDelete">批量删除</button>
            <button type="button" class="layui-btn layui-btn-sm layui-btn-normal" lay-event="getSelect">获取选中行</button>
            <button type="button" class="layui-btn layui-btn-sm layui-btn-normal" lay-event="refreshTable">刷新表格</button>
        </div>

        <!--数据表格-->
        <table class="layui-hide" id="dataTable" lay-filter="dataTable"></table>

        <!--数据编辑删除栏-->
        <div id="dataToolBar" style="display: none">
            <a class="layui-btn layui-btn-sm" lay-event="edit">编辑</a>
            <a class="layui-btn layui-btn-danger layui-btn-sm" lay-event="del">删除</a>
        </div>

        <!-- 添加和修改的弹出层开始 -->
        <div style="display: none;padding: 20px" id="saveOrUpdateDiv" >
            <form class="layui-form"  lay-filter="dataForm" id="dataForm">
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">客户姓名:</label>
                        <div class="layui-input-inline">
                            <input type="text" name="custname" lay-verify="required"   placeholder="请输入客户姓名" autocomplete="off"
                                   class="layui-input">
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">客户职位:</label>
                        <div class="layui-input-inline">
                            <input type="text" name="career" lay-verify="required"  placeholder="请输入客户职位" autocomplete="off"
                                   class="layui-input">
                        </div>
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">身份证号:</label>
                        <div class="layui-input-inline">
                            <input type="text" name="identity"  placeholder="请输入客户身份证号" autocomplete="off"
                                   class="layui-input">
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">客户地址:</label>
                        <div class="layui-input-inline">
                            <input type="text" name="address"  placeholder="请输入客户地址" autocomplete="off"
                                   class="layui-input">
                        </div>
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">客户电话:</label>
                        <div class="layui-input-inline">
                            <input type="text" name="phone" lay-verify="required|phone"  placeholder="请输入客户电话" autocomplete="off"
                                   class="layui-input">
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">客户性别:</label>
                        <div class="layui-input-inline">
                            <input type="radio" name="sex" value="1" checked="checked" title="男">
                            <input type="radio" name="sex" value="0" title="女">
                        </div>
                    </div>
                </div>
                <div class="layui-form-item" style="padding-left:160px">
                    <div class="layui-input-block">
                        <button type="button" class="layui-btn layui-btn-normal layui-btn-sm layui-icon layui-icon-release" lay-filter="doSubmit" lay-submit="">提交</button>
                        <button type="reset" class="layui-btn layui-btn-warm layui-btn-sm layui-icon layui-icon-refresh" >重置</button>
                    </div>
                </div>
            </form>

        </div>
        <!-- 添加和修改的弹出层结束 -->

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
            elem: '#dataTable'      //渲染目标对象 数据表格对应ID
            ,height: 'full-280'            //数据表格高度 可用高度-指定高度
            ,method: 'post'
            ,url: "<%=basePath%>customer/customerList"
            ,page: true //开启分页
            ,toolbar:"#topToolBar"     //引用表头工具栏 topToolBar是div的ID
            ,defaultToolbar: ['filter', 'print', 'exports']     //修改默认工具栏的功能和顺序
            ,even: true             //开启隔行背景
            //,totalRow:true	    //开启合并行
            ,text: {
                none: '暂无相关数据' //无数据时显示的内容 默认：无数据。
            }
            ,cols: [[ //表头
                {type:'checkbox', fixed: 'left'}    //fixed冻结列的位置
                ,{field:'custname', title:'客户姓名',align:'center'}
                ,{field:'identity', title:'身份证号',align:'center'}
                ,{field:'phone', title:'客户电话',align:'center'}
                ,{field:'address', title:'客户地址',align:'center'}
                ,{field:'career', title:'客户职位',align:'center'}
                ,{field:'sex', title:'性别',align:'center',templet:function(d){
                        return d.sex=='1'?'<span style="color: blue;">男</span>':'<span style="color: red;">女</span>';
                    }}
                ,{field:'createtime', title:'注册时间',align:'center'}
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
                    openAddCustomer();
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

        //客户模糊查询
        $("#doSearch").click(function () {
            var params = $("#searchData").serialize();
            console.log(params);
            tableIns.reload({
                url:"<%=basePath%>customer/customerList?"+params,
                page:{curr:1}           //每次查询从第一页开始
            });
        })

        //监听工具条  注：tool 是工具条事件名，dataTable 是 table 原始容器的属性 lay-filter="对应的值"
        table.on('tool(dataTable)', function(obj){
            var data = obj.data; //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
            var tr = obj.tr; //获得当前行 tr 的 DOM 对象（如果有的话）

            if(layEvent === 'del'){ //删除
                layer.confirm('确定删除客户['+data.custname+']吗？', function(index){
                    obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
                    layer.close(index);
                    //向服务端发送删除指令
                    $.post("<%=basePath%>customer/deleteById",{"id":data.identity},function (result) {
                        layer.msg(result.msg);
                        // 刷新数据表格
                        tableIns.reload();      //在不刷新页面的情况刷新表格数据
                    })
                });
            } else if(layEvent === 'edit'){ //编辑
                openUpdCustomer(data);      //修改当前行数据
            }
        });

        var url = "";       //区分添加和修改提交的URL
        var mainModel = "";     //弹窗对象
        //打开添加客户弹出框
        function openAddCustomer(){
            mainModel = layer.open({
                type: 1
                ,title:'添加客户'
                ,content:$("#saveOrUpdateDiv")
                ,maxmin: true       //最大化/最小化
                ,area: ['750px','380px']     //弹窗宽高
                ,success:function (index) {
                    //在弹出层加载成功后的回调方法中去掉最小化按钮；
                    index.find('.layui-layer-min').remove();
                    //打开弹窗清空整个form表单,jquery对象获取的是所有对象的数组，数组中是dom对象，dom对象才有reset();方法
                    $("#dataForm")[0].reset();
                    url = "<%=basePath%>customer/addCustomer";
                }
            });
        }

        //打开修改客户弹出框
        function openUpdCustomer(data) {
            mainModel = layer.open({
                type: 1
                , title: '修改客户信息'
                ,maxmin: true       //最大化/最小化
                , content: $("#saveOrUpdateDiv")
                , area: ['750px', '380px']     //弹窗宽高
                ,success:function (index) {         //弹窗成功后回调
                    //在弹出层加载成功后的回调方法中去掉最小化按钮；
                    index.find('.layui-layer-min').remove();
                    //给lay-filter="dataForm"的表单赋值,name相同可以直接赋值
                    form.val("dataForm",data);
                    url = "<%=basePath%>customer/updateById";
                }
            });
        }

        //批量删除
        function batchDelete(){
            //得到选中的数据ID
            var checkStatus = table.checkStatus('dataTable')
            list="";
            for(var i=0;i<checkStatus.data.length;i++){
                list+=','+checkStatus.data[i].identity;
            }
            list = list.substr(1);
            alert(list);
            layer.confirm('确定删除选中客户吗？', function(index){
                layer.close(index);
                //向服务端发送删除指令
                $.post("<%=basePath%>customer/batchDelete",{"ids":list},function (result) {
                    layer.msg(result.msg);
                    // 刷新数据表格
                    tableIns.reload();      //在不刷新页面的情况刷新表格数据
                })
            });
        }


        //保存数据，监听submit
        form.on('submit(doSubmit)', function(obj) {
            //序列化表单数据
            var params = $("#dataForm").serialize();
            console.log(params);
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
            url:"<%=basePath%>customer/customerList?id="+id
        });
    }

</script>

</html>
