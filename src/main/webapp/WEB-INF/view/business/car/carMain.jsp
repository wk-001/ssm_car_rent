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
    <title>车辆管理</title>
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

            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">车牌号：</label>
                    <div class="layui-input-inline">
                        <input type="text" name="carnumber" class="layui-input">
                    </div>
                </div>

                <div class="layui-inline">
                    <label class="layui-form-label">车辆类型：</label>
                    <div class="layui-input-inline">
                        <input type="text" name="cartype" class="layui-input">
                    </div>
                </div>

                <div class="layui-inline">
                    <label class="layui-form-label">车辆颜色：</label>
                    <div class="layui-input-inline">
                        <input type="text" name="color" class="layui-input">
                    </div>
                </div>

                <div class="layui-inline">
                    <label class="layui-form-label">车辆描述：</label>
                    <div class="layui-input-inline">
                        <input type="text" name="description" class="layui-input">
                    </div>
                </div>

                <div class="layui-inline">
                    <label class="layui-form-label">是否出租:</label>
                    <div class="layui-input-inline">
                        <input type="radio" name="isrenting" value="1" title="已出租">
                        <input type="radio" name="isrenting" value="0" title="未出租">
                    </div>
                </div>

                <div class="layui-inline">
                    <button type="button" class="layui-btn layui-btn-normal layui-icon layui-icon-search" id="doSearch">
                        查询
                    </button>
                    <button type="reset" id="dataFormReset"
                            class="layui-btn layui-btn-warm layui-icon layui-icon-refresh">重置
                    </button>
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
            <a class="layui-btn layui-btn-normal layui-btn-sm" lay-event="viewCar">查看大图</a>
            <a class="layui-btn layui-btn-danger layui-btn-sm" lay-event="del">删除</a>
        </div>

        <!-- 添加和修改的弹出层开始 -->
        <div style="display: none;padding: 20px" id="saveOrUpdateDiv">
            <form class="layui-form layui-row layui-col-space10" lay-filter="dataForm" id="dataForm">
                <div class="layui-col-md12 layui-col-xs12">
                    <div class="layui-row layui-col-space10">
                        <div class="layui-col-md9 layui-col-xs7">
                            <div class="layui-form-item magt3">
                                <label class="layui-form-label">车牌号:</label>
                                <div class="layui-input-block">
                                    <input type="text" name="carnumber" id="carnumber" class="layui-input"
                                           lay-verify="required" placeholder="请输入车牌号">
                                </div>
                            </div>
                            <div class="layui-form-item">
                                <label class="layui-form-label">车辆类型:</label>
                                <div class="layui-input-block">
                                    <input type="text" name="cartype" class="layui-input" lay-verify="required"
                                           placeholder="请输入车辆类型">
                                </div>
                            </div>
                            <div class="layui-form-item">
                                <label class="layui-form-label">车辆颜色:</label>
                                <div class="layui-input-block">
                                    <input type="text" name="color" class="layui-input" lay-verify="required"
                                           placeholder="请输入车辆颜色">
                                </div>
                            </div>
                            <div class="layui-form-item">
                                <label class="layui-form-label">车辆描述:</label>
                                <div class="layui-input-block">
                                    <input type="text" name="description" class="layui-input" lay-verify="required"
                                           placeholder="请输入车辆描述">
                                </div>
                            </div>
                        </div>
                        <div class="layui-col-md3 layui-col-xs5">
                            <div class="layui-upload-list thumbBox" id="carimgDiv">
                                <!-- 显示上传的图片 -->
                                <img class="layui-upload-img thumbImg" id="showCarImg">
                                <!-- 保存当前显示图片的地址 -->
                                <input type="hidden" name="carimg" id="carimg">
                            </div>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <label class="layui-form-label">购买价格:</label>
                            <div class="layui-input-inline">
                                <input type="text" name="price" class="layui-input" lay-verify="required|number"
                                       placeholder="请输入购买价格">
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label">出租价格:</label>
                            <div class="layui-input-inline">
                                <input type="text" name="rentprice" class="layui-input" lay-verify="required|number"
                                       placeholder="请输入出租价格">
                            </div>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <label class="layui-form-label">出租押金:</label>
                            <div class="layui-input-inline">
                                <input type="text" name="deposit" class="layui-input" lay-verify="required|number"
                                       placeholder="请输入出租押金">
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label">出租状态:</label>
                            <div class="layui-input-inline">
                                <input type="radio" name="isrenting" value="1" title="已出租">
                                <input type="radio" name="isrenting" value="0" title="未出租" checked="checked">
                            </div>
                        </div>
                    </div>

                    <div class="layui-form-item" style="padding-left:160px">
                        <div class="layui-input-block">
                            <button type="button"
                                    class="layui-btn layui-btn-normal layui-btn-sm layui-icon layui-icon-release"
                                    lay-filter="doSubmit" lay-submit="">提交
                            </button>
                            <button type="reset"
                                    class="layui-btn layui-btn-warm layui-btn-sm layui-icon layui-icon-refresh">重置
                            </button>
                        </div>
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
<style>
    /*解决数据表格复选框位置不居中的问题*/
    .layui-table-cell .layui-form-checkbox[lay-skin="primary"] {
        top: 50%;
        transform: translateY(-50%);
    }
</style>
<script type="text/javascript">
    var tableIns;       //定义全局变量 使layui方法块外也可以调用表格
    layui.use(['jquery', 'element', 'form', 'layer', 'table', 'laydate', 'upload'], function () {
        var $ = layui.jquery
            , element = layui.element
            , form = layui.form
            , layer = layui.layer
            , table = layui.table
            , laydate = layui.laydate
            , upload = layui.upload

        var editIndex;


        //渲染数据表格
        tableIns = table.render({
            elem: '#dataTable'      //渲染目标对象 数据表格对应ID
            , height: 'full-210'            //数据表格高度 可用高度-指定高度
            , method: 'post'
            , url: "<%=basePath%>busCar/carList"
            , page: true //开启分页
            , toolbar: "#topToolBar"     //引用表头工具栏 topToolBar是div的ID
            , defaultToolbar: ['filter', 'print', 'exports']     //修改默认工具栏的功能和顺序
            , even: true             //开启隔行背景
            , text: {
                none: '暂无相关数据' //无数据时显示的内容 默认：无数据。
            }
            , cols: [[ //表头
                {type: 'checkbox', fixed: 'left'}    //fixed冻结列的位置
                , {field: 'carnumber', title: '车牌号', align: 'center'}
                , {field: 'cartype', title: '车辆类型', align: 'center'}
                , {field: 'color', title: '车辆颜色', align: 'center'}
                , {field: 'price', title: '车辆价格', align: 'center'}
                , {field: 'rentprice', title: '出租价格', align: 'center'}
                , {field: 'deposit', title: '押金', align: 'center'}
                , {field: 'description', title: '车辆描述', align: 'center'}
                , {field: 'createtime', title: '录入时间', align: 'center'}
                , {field: 'isrenting', title: '出租状态', align: 'center', templet: function (d) {
                        return d.isrenting == '1' ? '<span style="color: blue;">已出租</span>' : '<span style="color: red;">未出租</span>';
                    }}
                ,{field:'carimg', title:'缩略图',align:'center',templet:function(d){
                        return "<img width=40 height=30 src=<%=basePath%>file/downloadShowFile?path="+d.carimg+" />";
                    }}
                , {fixed: 'right', title: '操作', width: 220, toolbar: '#dataToolBar', align: 'center'}
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
                case 'add':             //工具栏按钮lay-event="add"属性的值
                    openAddCar();
                    break;
                case 'batchDelete':
                    batchDelete();
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

        //车辆模糊查询
        $("#doSearch").click(function () {
            var params = $("#searchData").serialize();
            tableIns.reload({
                url: "<%=basePath%>busCar/carList?" + params,
                page: {curr: 1}           //每次查询从第一页开始
            });
        })

        //监听工具条  注：tool 是工具条事件名，dataTable 是 table 原始容器的属性 lay-filter="对应的值"
        table.on('tool(dataTable)', function (obj) {
            var data = obj.data; //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
            var tr = obj.tr; //获得当前行 tr 的 DOM 对象（如果有的话）

            if (layEvent === 'del') { //删除
                layer.confirm('确定删除车辆[' + data.carnumber + ']吗？', function (index) {
                    obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
                    layer.close(index);
                    //向服务端发送删除指令
                    $.post("<%=basePath%>busCar/deleteById", {"id": data.carnumber}, function (result) {
                        layer.msg(result.msg);
                        // 刷新数据表格
                        tableIns.reload();      //在不刷新页面的情况刷新表格数据
                    })
                });
            } else if (layEvent === 'edit') { //编辑
                openUpdCar(data);      //修改当前行数据
            } else if (layEvent === 'viewCar') { //查看车辆图片
                openViewCar(data);
            }
        });

        var url = "";       //区分添加和修改提交的URL
        var mainModel = "";     //弹窗对象
        //打开添加车辆弹出框
        function openAddCar() {
            mainModel = layer.open({
                type: 1
                , title: '添加车辆'
                , content: $("#saveOrUpdateDiv")
                , area: ['750px', '470px']     //弹窗宽高
                , success: function (index) {
                    //打开弹窗清空整个form表单,jquery对象获取的是所有对象的数组，数组中是dom对象，dom对象才有reset();方法
                    $("#dataForm")[0].reset();
                    //设置默认显示图片
                    $("#showCarImg").attr("src","<%=basePath%>file/downloadShowFile?path=images/defaultcarimage.jpg")
                    //设置默认图片路径
                    $("#carimg").val("images/defaultcarimage.jpg")
                    $("#carnumber").removeAttr("readonly");
                    url = "<%=basePath%>busCar/addCar";
                }
            });
        }

        //打开修改车辆弹出框
        function openUpdCar(data) {
            mainModel = layer.open({
                type: 1
                , title: '修改车辆信息'
                , content: $("#saveOrUpdateDiv")
                , area: ['750px', '470px']     //弹窗宽高
                , success: function (index) {         //弹窗成功后回调
                    //给lay-filter="dataForm"的表单赋值,name相同可以直接赋值
                    form.val("dataForm", data);
                    $("#showCarImg").attr("src","<%=basePath%>file/downloadShowFile?path="+data.carimg);
                    $("#carnumber").attr("readonly","readonly");    //车牌号作为主键不能修改
                    url = "<%=basePath%>busCar/updateById";
                }
            });
        }

        //批量删除
        function batchDelete() {
            //得到选中的数据ID
            var checkStatus = table.checkStatus('dataTable')
            list = "";
            for (var i = 0; i < checkStatus.data.length; i++) {
                list += ',' + checkStatus.data[i].carnumber;
            }
            list = list.substr(1);
            alert(list);
            layer.confirm('确定删除选中车辆吗？', function (index) {
                layer.close(index);
                //向服务端发送删除指令
                $.post("<%=basePath%>busCar/batchDelete", {"ids": list}, function (result) {
                    layer.msg(result.msg);
                    // 刷新数据表格
                    tableIns.reload();      //在不刷新页面的情况刷新表格数据
                })
            });
        }

        //上传图片
        upload.render({
            elem: '#carimgDiv',
            url: '<%=basePath%>file/uploadFile',
            method : "post",  //此处是为了演示之用，实际使用中请将此删除，默认用post方式提交
            acceptMime:'images/*',      //只能上传图片
            field:"mf",
            done: function(res, index, upload){
                $('#showCarImg').attr('src',"<%=basePath%>file/downloadShowFile?path="+res.data.src);
                $('#carimg').val(res.data.src);
                $('#carimgDiv').css("background","#fff");
            }
        });

        //查看车辆图片
        function openViewCar(data) {
            mainIndex=layer.open({
                type:1,
                title:"【"+data.carnumber+'】的车辆图片',
                content:$("#viewCarImageDiv"),
                maxmin: true,       //最大化/最小化
                area:['600px','800px'],
                success:function(index){
                    //在弹出层加载成功后的回调方法中去掉最小化按钮；
                    index.find('.layui-layer-min').remove();
                    $("#view_carimg").attr("src","<%=basePath%>file/downloadShowFile?path="+data.carimg);
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
                // 刷新数据表格
                tableIns.reload();      //在不刷新页面的情况刷新表格数据
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
