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
    <title>车辆出租</title>
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
    <style type="text/css">
        .thumbBox {
            height: 190px;
            overflow: hidden;
            border: 1px solid #e6e6e6;
            border-radius: 2px;
            cursor: pointer;
            position: relative;
            text-align: center;
            line-height: 153px;
        }

        .thumbBox:after {
            position: absolute;
            width: 100%;
            height: 100%;
            line-height: 170px;
            z-index: -1;
            text-align: center;
            font-size: 20px;
            content: "上传车辆图片";
            left: 0;
            top: 0;
            color: #9F9F9F;
        }
    </style>
</head>
<body>
<div class="childrenBody">
    <div style="padding:10px 20px 1px 20px;">

        <!--搜索栏-->
        <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
            <legend>查询条件</legend>
        </fieldset>

        <form class="layui-form" method="post" id="searchData">
            <div class="layui-row">
                <div class="layui-form-item layui-col-md6 layui-col-md-offset3">
                    <div class="layui-inline">
                        <label class="layui-form-label">身份证号：</label>
                        <div class="layui-input-inline">
                            <input type="text" name="identity" id="identity" class="layui-input">
                        </div>
                    </div>

                    <div class="layui-inline">
                        <button type="button" class="layui-btn layui-btn-normal layui-icon layui-icon-search"
                                id="doSearch">
                            查询
                        </button>
                        <button type="reset" id="dataFormReset"
                                class="layui-btn layui-btn-warm layui-icon layui-icon-refresh">重置
                        </button>
                    </div>
                </div>
            </div>
        </form>

        <!--数据表格-->
        <div id="content" style="display: none;padding-top: 5px">
            <table id="dataTable" lay-filter="dataTable"></table>
        </div>
        <!--数据编辑删除栏-->
        <div id="dataToolBar" style="display: none">
            <a class="layui-btn layui-btn-warm layui-btn-sm" lay-event="rentCar">出租汽车</a>
            <a class="layui-btn layui-btn-normal layui-btn-sm" lay-event="viewCar">查看大图</a>
        </div>

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
                            <input type="text" name="begindate" id="begindate" readonly="readonly" placeholder="请选择起租时间" class="layui-input">
                        </div>

                        <label class="layui-form-label">还车时间:</label>
                        <div class="layui-input-inline">
                            <input type="text" name="returndate" id="returndate" readonly="readonly" placeholder="请选择还车时间" class="layui-input">
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

        <!-- 查看大图弹出的层 开始 -->
        <div id="viewCarImageDiv" style="display: none;text-align: center;">
            <img alt="车辆图片" width="580" height="780" id="view_carimg">
        </div>
        <!-- 查看大图弹出的层 结束 -->

    </div>
</div>

</body>
<script type="text/javascript">
    var tableIns;       //定义全局变量 使layui方法块外也可以调用表格
    layui.use(['jquery', 'element', 'form', 'layer', 'table', 'laydate'], function () {
        var $ = layui.jquery
            , element = layui.element
            , form = layui.form
            , layer = layui.layer
            , table = layui.table
            , laydate = layui.laydate

        //渲染时间组件
        laydate.render({
            elem: "#begindate",
            type: "datetime"
            ,trigger: 'click'       //解决绑定事件控件一闪而过的问题
        })
        laydate.render({
            elem: "#returndate",
            type: "datetime"
            ,trigger: 'click'
        })

        function initCarTable() {
            //渲染数据表格
            tableIns = table.render({
                elem: '#dataTable'      //渲染目标对象 数据表格对应ID
                , height: 'full-180'            //数据表格高度 可用高度-指定高度
                , method: 'post'
                , url: "<%=basePath%>busCar/carList?isrenting=0"
                , page: true //开启分页
                , even: true             //开启隔行背景
                , text: {
                    none: '暂无相关数据' //无数据时显示的内容 默认：无数据。
                }
                , cols: [[ //表头
                    {field: 'carnumber', title: '车牌号', align: 'center'}
                    , {field: 'cartype', title: '车辆类型', align: 'center'}
                    , {field: 'color', title: '车辆颜色', align: 'center'}
                    , {field: 'price', title: '车辆价格', align: 'center'}
                    , {field: 'rentprice', title: '出租价格', align: 'center'}
                    , {field: 'deposit', title: '押金', align: 'center'}
                    , {field: 'description', title: '车辆描述', align: 'center'}
                    , {field: 'createtime', title: '录入时间', align: 'center'}
                    , {
                        field: 'isrenting', title: '出租状态', align: 'center', templet: function (d) {
                            return d.isrenting == '1' ? '<span style="color: blue;">已出租</span>' : '<span style="color: red;">未出租</span>';
                        }
                    }
                    , {
                        field: 'carimg', title: '缩略图', align: 'center', templet: function (d) {
                            return "<img width=40 height=30 src=<%=basePath%>file/downloadShowFile?path=" + d.carimg + " />";
                        }
                    }
                    , {fixed: 'right', title: '操作', width: 220, toolbar: '#dataToolBar', align: 'center'}
                ]]
            });
        }

        //车辆模糊查询
        $("#doSearch").click(function () {
            var params = $("#searchData").serialize();
            $.post("<%=basePath%>customer/getById", params, function (result) {
                if (result.code == 0) {
                    $("#content").show();
                    initCarTable();
                } else {
                    $("#content").hide();
                    layer.msg("该客户不存在，请检查身份证号！");
                }
            })
        })

        //监听工具条  注：tool 是工具条事件名，dataTable 是 table 原始容器的属性 lay-filter="对应的值"
        table.on('tool(dataTable)', function (obj) {
            var data = obj.data; //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
            var tr = obj.tr; //获得当前行 tr 的 DOM 对象（如果有的话）

            if (layEvent === 'rentCar') { //出租汽车
                openRentCar(data);
            } else if (layEvent === 'viewCar') { //查看车辆图片
                openViewCar(data);
            }
        });

        var url = "";       //区分添加和修改提交的URL
        var mainModel = "";     //弹窗对象
        //打开添加客户弹出框
        function openRentCar(data) {
            mainModel = layer.open({
                type: 1
                , title: '添加出租单'
                , content: $("#saveOrUpdateDiv")
                , area: ['705px', '415px']     //弹窗宽高
                , success: function (index) {
                    //打开弹窗清空整个form表单,jquery对象获取的是所有对象的数组，数组中是dom对象，dom对象才有reset();方法
                    $("#dataForm")[0].reset();
                    var identity = $("#identity").val();
                    var price = data.rentprice;
                    var carnumber = data.carnumber;
                    $.get(
                        "<%=basePath%>rent/initRent",
                        {identity:identity,price:price,carnumber:carnumber},
                        function (result) {
                            //弹窗回显
                            form.val("dataForm",result);
                        }
                    )
                    url = "<%=basePath%>rent/addRent";
                }
            });
        }

        //查看车辆图片
        function openViewCar(data) {
            mainIndex = layer.open({
                type: 1,
                title: "【" + data.carnumber + '】的车辆图片',
                content: $("#viewCarImageDiv"),
                maxmin: true,       //最大化/最小化
                area: ['600px', '800px'],
                success: function (index) {
                    //在弹出层加载成功后的回调方法中去掉最小化按钮；
                    index.find('.layui-layer-min').remove();
                    $("#view_carimg").attr("src", "<%=basePath%>file/downloadShowFile?path=" + data.carimg);
                }
            });
        }

        //保存数据，监听submit
        form.on('submit(doSubmit)', function (obj) {
            //序列化表单数据
            var params = $("#dataForm").serialize();
            $.post(url, params, function (obj) {
                layer.msg(obj.msg);
                //关闭弹窗
                layer.close(mainModel);
                // 隐藏表格
                $("#content").hide();
            })
        })

    });

    function reloadTable(id) {
        tableIns.reload({
            url: "<%=basePath%>busCar/carList?id=" + id
        });
    }

</script>

</html>
