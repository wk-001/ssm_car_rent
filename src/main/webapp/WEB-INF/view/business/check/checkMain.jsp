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
    <title>检查单管理</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta http-equiv="Access-Control-Allow-Origin" content="*">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <link rel="icon" href="<%=basePath%>layuicms2.0/favicon.ico">
    <link rel="stylesheet" href="<%=basePath%>layuicms2.0/layui/css/layui.css" media="all"/>
    <link rel="stylesheet" href="<%=basePath%>layuicms2.0/css/public.css" media="all"/>
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

                <label class="layui-form-label">检查单号:</label>
                <div class="layui-input-inline">
                    <input type="text" name="checkid" autocomplete="off" class="layui-input">
                </div>

                <label class="layui-form-label">出租单号:</label>
                <div class="layui-input-inline">
                    <input type="text" name="rentid" autocomplete="off" class="layui-input">
                </div>

                <label class="layui-form-label">车辆问题:</label>
                <div class="layui-input-inline">
                    <input type="text" name="problem" autocomplete="off" class="layui-input">
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">问题描述:</label>
                <div class="layui-input-inline">
                    <input type="text" name="checkdesc" autocomplete="off" class="layui-input">
                </div>

                <label class="layui-form-label">开始时间：</label>
                <div class="layui-input-inline">
                    <input type="text" name="startTime" id="startTime" readonly="readonly" class="layui-input">
                </div>

                <label class="layui-form-label">结束时间：</label>
                <div class="layui-input-inline">
                    <input type="text" name="endTime" id="endTime" readonly="readonly" class="layui-input">
                </div>

            </div>

            <div class="layui-form-item" style="padding-left:950px">
                <div class="layui-inline">
                    <button type="button" class="layui-btn layui-btn-normal layui-icon layui-icon-search" id="doSearch">
                        查询
                    </button>
                    <button type="reset" class="layui-btn layui-btn-warm layui-icon layui-icon-refresh">重置</button>
                </div>
            </div>
        </form>

        <!--数据表格-->
        <table class="layui-hide" id="dataTable" lay-filter="dataTable"></table>

        <!--数据编辑删除栏-->
        <div id="dataToolBar" style="display: none">
            <a class="layui-btn layui-btn-sm" lay-event="edit">编辑</a>
        </div>

        <!-- 添加和修改的弹出层开始 -->
        <div style="display: none;padding: 20px" id="saveOrUpdateDiv">
            <form class="layui-form" lay-filter="dataForm" id="dataForm">
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">检查时间:</label>
                        <div class="layui-input-inline">
                            <input type="text" name="checkdate"  id="checkdate" readonly="readonly" placeholder="请输入起租时间"  autocomplete="off"
                                   class="layui-input">
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">操作员:</label>
                        <div class="layui-input-inline">
                            <input type="text" name="opername" lay-verify="required"  readonly="readonly" placeholder="请输入操作员" autocomplete="off"
                                   class="layui-input">
                        </div>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">检查单号:</label>
                    <div class="layui-input-block">
                        <input type="text" name="checkid"  id="checkid" readonly="readonly" placeholder="请输入检查单号"  autocomplete="off"
                               class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">出租单号:</label>
                    <div class="layui-input-block">
                        <input type="text" name="rentid" lay-verify="required"  readonly="readonly"  placeholder="请输入出租单号" autocomplete="off"
                               class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">赔付金额:</label>
                        <div class="layui-input-inline">
                            <input type="text" name="paymoney" lay-verify="required"   placeholder="请输入车牌号" autocomplete="off"
                                   class="layui-input">
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">存在问题:</label>
                        <div class="layui-input-inline">
                            <input type="text" name="problem" lay-verify="required"   placeholder="请输入存在问题" autocomplete="off"
                                   class="layui-input">
                        </div>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">问题描述:</label>
                    <div class="layui-input-block">
                        <input type="text" name="checkdesc" lay-verify="required"   placeholder="请输入问题描述" autocomplete="off"
                               class="layui-input">
                    </div>
                </div>

                <div class="layui-form-item" style="padding-left: 160px">
                    <div class="layui-input-block">
                        <button type="button" class="layui-btn layui-btn-normal layui-btn-sm layui-icon layui-icon-release"
                                lay-filter="doSubmit" lay-submit="">提交</button>
                        <button type="reset" class="layui-btn layui-btn-warm layui-btn-sm layui-icon layui-icon-refresh" >重置</button>
                    </div>
                </div>
            </form>

        </div>
        <!-- 添加和修改的弹出层结束 -->

    </div>
</div>

</body>
<script type="text/javascript">
    var tableIns;       //定义全局变量 使layui方法块外也可以调用表格
    layui.use(['jquery', 'element', 'form', 'layer', 'table','laydate'], function () {
        var $ = layui.jquery
            , element = layui.element
            , form = layui.form
            , layer = layui.layer
            , table = layui.table
            ,laydate = layui.laydate

        //渲染时间组件
        laydate.render({
            elem:"#startTime",
            type:"datetime"
        });
        laydate.render({
            elem:"#endTime",
            type:"datetime"
        });
        laydate.render({
            elem:'#checkdate',
            type:'datetime'
        });

        //渲染数据表格
        tableIns = table.render({
            elem: '#dataTable'      //渲染目标对象 数据表格对应ID
            , height: 'full-280'            //数据表格高度 可用高度-指定高度
            , method: 'post'
            , url: "<%=basePath%>check/checkList"
            , page: true //开启分页
            , even: true             //开启隔行背景
            , text: {
                none: '暂无相关数据' //无数据时显示的内容 默认：无数据。
            }
            , cols: [[ //表头
                {field:'checkid', title:'检查单号',align:'center'}
                ,{field:'rentid', title:'出租单号',align:'center'}
                ,{field:'checkdate', title:'检查时间',align:'center'}
                ,{field:'problem', title:'存在问题',align:'center'}
                ,{field:'checkdesc', title:'问题描述',align:'center'}
                ,{field:'paymoney', title:'赔付金额',align:'center'}
                ,{field:'opername', title:'操作员',align:'center'}
                ,{field:'createtime', title:'录入时间',align:'center'}
                , {fixed: 'right', title: '操作', toolbar: '#dataToolBar', align: 'center'}
            ]]
            , done: function (res, curr, count) {
                //如果当前页面数据全部删除，并且不是第一页的情况，就跳转到前一页
                if (res.data.length == 0 && curr != 1) {
                    tableIns.reload({
                        page: {curr: curr - 1}           //跳转到前一页
                    });
                }
            }
        });

        //检查单模糊查询
        $("#doSearch").click(function () {
            var params = $("#searchData").serialize();
            console.log(params);
            tableIns.reload({
                url: "<%=basePath%>check/checkList?" + params,
                page: {curr: 1}           //每次查询从第一页开始
            });
        })

        //监听工具条  注：tool 是工具条事件名，dataTable 是 table 原始容器的属性 lay-filter="对应的值"
        table.on('tool(dataTable)', function (obj) {
            var data = obj.data; //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
            var tr = obj.tr; //获得当前行 tr 的 DOM 对象（如果有的话）

            if (layEvent === 'edit') { //编辑
                openUpdCheck(data);      //修改当前行数据
            }
        });

        var url = "";       //区分添加和修改提交的URL
        var mainModel = "";     //弹窗对象

        //打开修改检查单弹出框
        function openUpdCheck(data) {
            mainModel = layer.open({
                type: 1
                , title: '修改检查单信息'
                , maxmin: true       //最大化/最小化
                , content: $("#saveOrUpdateDiv")
                , area: ['705px', '410px']     //弹窗宽高
                , success: function (index) {         //弹窗成功后回调
                    //在弹出层加载成功后的回调方法中去掉最小化按钮；
                    index.find('.layui-layer-min').remove();
                    //给lay-filter="dataForm"的表单赋值,name相同可以直接赋值
                    form.val("dataForm", data);
                    url = "<%=basePath%>check/updateById";
                }
            });
        }

        //保存数据，监听submit
        form.on('submit(doSubmit)', function (obj) {
            //序列化表单数据
            var params = $("#dataForm").serialize();
            console.log(params);
            $.post(url, params, function (obj) {
                layer.msg(obj.msg);
                //关闭弹窗
                layer.close(mainModel);
                // 刷新数据表格
                tableIns.reload();      //在不刷新页面的情况刷新表格数据
            })
        })

    });

    function reloadTable(id) {
        tableIns.reload({
            url: "<%=basePath%>check/checkList?id=" + id
        });
    }

</script>

</html>
