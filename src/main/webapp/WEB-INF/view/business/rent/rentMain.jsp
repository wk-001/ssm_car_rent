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
    <title>出租单管理</title>
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

                <label class="layui-form-label">出租单号:</label>
                <div class="layui-input-inline">
                    <input type="text" name="rentid" autocomplete="off" class="layui-input">
                </div>

                <label class="layui-form-label">身份证号:</label>
                <div class="layui-input-inline">
                    <input type="text" name="identity" autocomplete="off" class="layui-input">
                </div>

                <label class="layui-form-label">车牌号:</label>
                <div class="layui-input-inline">
                    <input type="text" name="carnumber" autocomplete="off" class="layui-input">
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">开始时间：</label>
                <div class="layui-input-inline">
                    <input type="text" name="startTime" id="startTime" readonly="readonly" class="layui-input">
                </div>

                <label class="layui-form-label">结束时间：</label>
                <div class="layui-input-inline">
                    <input type="text" name="endTime" id="endTime" readonly="readonly" class="layui-input">
                </div>

                <label class="layui-form-label">出租状态:</label>
                <div class="layui-input-inline">
                    <input type="radio" name="rentflag" value="1" title="已归还">
                    <input type="radio" name="rentflag" value="0" title="未归还">
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
        <script type="text/html" id="dataToolBar">

            {{#  if(d.rentflag == 0){ }}
                <a class="layui-btn layui-btn-sm" lay-event="edit">编辑</a>
                <a class="layui-btn layui-btn-normal layui-btn-sm" lay-event="export">导出</a>
                <a class="layui-btn layui-btn-danger layui-btn-sm" lay-event="del">删除</a>
            {{#  } else { }}
                <a class="layui-btn layui-btn-normal layui-btn-sm" lay-event="export">导出</a>
            {{#  } }}


        </script>

        <!-- 添加和修改的弹出层开始 -->
        <div style="display: none;padding: 20px" id="saveOrUpdateDiv">
            <form class="layui-form" lay-filter="dataForm" id="dataForm">
                <div class="layui-form-item">
                    <label class="layui-form-label">出租单号:</label>
                    <div class="layui-input-block">
                        <input type="text" name="rentid" lay-verify="required" readonly="readonly" placeholder="请输入出租单号" autocomplete="off"
                               class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">身份证号:</label>
                    <div class="layui-input-block">
                        <input type="text" name="identity" lay-verify="required" readonly="readonly" placeholder="请输入客户身份证号"
                               autocomplete="off"
                               class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">车牌号:</label>
                    <div class="layui-input-inline">
                        <input type="text" name="carnumber" lay-verify="required" readonly="readonly" placeholder="请输入车牌号"
                               autocomplete="off"
                               class="layui-input">
                    </div>

                    <label class="layui-form-label">出租价格:</label>
                    <div class="layui-input-inline">
                        <input type="text" name="price" lay-verify="required|number" placeholder="请输入出租价格"
                               autocomplete="off"
                               class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">起租时间:</label>
                    <div class="layui-input-inline">
                        <input type="text" name="begindate" id="begindate" lay-verify="required" readonly="readonly" placeholder="请选择起租时间" class="layui-input">
                    </div>

                    <label class="layui-form-label">还车时间:</label>
                    <div class="layui-input-inline">
                        <input type="text" name="returndate" id="returndate" lay-verify="required" readonly="readonly" placeholder="请选择还车时间" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">操作员:</label>
                    <div class="layui-input-inline">
                        <input type="text" name="opername" lay-verify="required" readonly="readonly" placeholder="请输入操作员"
                               autocomplete="off" class="layui-input">
                    </div>
                </div>

                <div class="layui-form-item" style="padding: 13px 0px 0px 160px">
                    <div class="layui-input-block">
                        <button type="button"
                                class="layui-btn layui-btn-normal layui-btn-sm layui-icon layui-icon-release"
                                lay-filter="doSubmit" lay-submit="">提交
                        </button>
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

        //渲染时间组件
        laydate.render({
            elem: "#begindate",
            type: "datetime"
            ,trigger: 'click'       //解决绑定事件控件一闪而过的问题
        });
        laydate.render({
            elem: "#returndate",
            type: "datetime"
            ,trigger: 'click'
        });

        //渲染数据表格
        tableIns = table.render({
            elem: '#dataTable'      //渲染目标对象 数据表格对应ID
            , height: 'full-280'            //数据表格高度 可用高度-指定高度
            , method: 'post'
            , url: "<%=basePath%>rent/rentList"
            , page: true //开启分页
            , even: true             //开启隔行背景
            , text: {
                none: '暂无相关数据' //无数据时显示的内容 默认：无数据。
            }
            , cols: [[ //表头
                 {field: 'rentid', title: '出租单号', align: 'center'}
                , {field: 'identity', title: '身份证号', align: 'center'}
                , {field: 'carnumber', title: '车牌号', align: 'center'}
                , {field: 'begindate', title: '起租时间', align: 'center'}
                , {field: 'returndate', title: '归还时间', align: 'center'}
                , {field: 'price', title: '出租价格', align: 'center'}
                , {field: 'opername', title: '操作员', align: 'center'}
                , {
                    field: 'rentflag', title: '出租单状态', align: 'center', templet: function (d) {
                        return d.rentflag == '1' ? '<span style="color: blue;">已归还</span>' : '<span style="color: red;">未归还</span>';
                    }
                }
                , {field: 'createtime', title: '注册时间', align: 'center'}
                , {fixed: 'right', title: '操作',width:200, toolbar: '#dataToolBar', align: 'center'}
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

        //监听头部工具栏事件 dataTable:数据表格的ID
        table.on('toolbar(dataTable)', function (obj) {
            switch (obj.event) {
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
                    console.log(checkStatus.isAll) //表格是否全选
                    break;
            }
            ;
        });

        //出租单模糊查询
        $("#doSearch").click(function () {
            var params = $("#searchData").serialize();
            console.log(params);
            tableIns.reload({
                url: "<%=basePath%>rent/rentList?" + params,
                page: {curr: 1}           //每次查询从第一页开始
            });
        })

        //监听工具条  注：tool 是工具条事件名，dataTable 是 table 原始容器的属性 lay-filter="对应的值"
        table.on('tool(dataTable)', function (obj) {
            var data = obj.data; //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
            var tr = obj.tr; //获得当前行 tr 的 DOM 对象（如果有的话）

            if (layEvent === 'del') { //删除
                layer.confirm('确定删除出租单[' + data.rentid + ']吗？', function (index) {
                    obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
                    layer.close(index);
                    //向服务端发送删除指令
                    $.post("<%=basePath%>rent/deleteById",
                        {"rentid": data.rentid,"carnumber":data.carnumber},
                        function (result) {
                        layer.msg(result.msg);
                        // 刷新数据表格
                        tableIns.reload();      //在不刷新页面的情况刷新表格数据
                    })
                });
            } else if (layEvent === 'edit') { //编辑
                openUpdRent(data);      //修改当前行数据
            }else if (layEvent === 'export') {
                //导出出租单 文件上传下载如果用ajax需要特殊处理
                location.href="<%=basePath%>file/exportRent?rentid="+data.rentid;
            }
        });

        var url = "";       //区分添加和修改提交的URL
        var mainModel = "";     //弹窗对象

        //打开修改出租单弹出框
        function openUpdRent(data) {
            mainModel = layer.open({
                type: 1
                , title: '修改出租单信息'
                , maxmin: true       //最大化/最小化
                , content: $("#saveOrUpdateDiv")
                , area: ['705px', '415px']     //弹窗宽高
                , success: function (index) {         //弹窗成功后回调
                    //在弹出层加载成功后的回调方法中去掉最小化按钮；
                    index.find('.layui-layer-min').remove();
                    //给lay-filter="dataForm"的表单赋值,name相同可以直接赋值
                    form.val("dataForm", data);
                    $("#identity").attr("readonly", "readonly");    //车牌号作为主键不能修改
                    url = "<%=basePath%>rent/updateById";
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
            url: "<%=basePath%>rent/rentList?id=" + id
        });
    }

</script>

</html>
