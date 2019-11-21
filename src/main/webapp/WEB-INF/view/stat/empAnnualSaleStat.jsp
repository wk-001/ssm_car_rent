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
    <title>业务员年销售额统计</title>
    <link rel="stylesheet" href="<%=basePath%>layuicms2.0/layui/css/layui.css" media="all" />
    <link rel="stylesheet" href="<%=basePath%>layuicms2.0/css/public.css" media="all" />
    <script type="text/javascript" src="<%=basePath%>layuicms2.0/layui/layui.js"></script>
    <script type="text/javascript" src="${ctx}/echarts/echarts.min.js"></script>
</head>

<body style="height: 100%; margin: 0">

<!--搜索栏-->
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
    <legend>查询条件</legend>
</fieldset>

<form class="layui-form" method="post" id="searchData">
    <div class="layui-row">
        <div class="layui-form-item layui-col-md6 layui-col-md-offset3">
            <div class="layui-inline">
                <label class="layui-form-label">选择年份：</label>
                <div class="layui-input-inline">
                    <input type="text" name="year" id="year" readonly class="layui-input">
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

<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
<div id="container" style="height: 550px;width: 98%"></div>

</body>

<script type="text/javascript">

    layui.use(['jquery','laydate'], function() {
        var $ = layui.jquery
            , laydate = layui.laydate

        //渲染时间组件
        laydate.render({
            elem: "#year",
            type: "year",
            value: new Date(),       //默认日期
            change : function(value) {//点击年份直接选中
                $("#year").val(value);
                $(".layui-laydate").remove();
            }
        });

        $("#doSearch").click(function () {
            getDate();
        });

        function getDate() {
            var year = $("#year").val();
            if(year===""){
                year = new Date().getFullYear();
            }
            $.post("<%=basePath%>stat/empAnnualSaleStat",{year:year},function (data) {
                var dom = document.getElementById("container");
                var myChart = echarts.init(dom);
                var app = {};
                option = null;
                option = {
                    title: {
                        text: '销售额年度统计',
                        subtext: '真实有效',
                        x: 'center'
                    },
                    color: ['#3398DB'],
                    tooltip : {
                        trigger: 'axis',
                        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                        }
                    },
                    grid: {
                        left: '3%',
                        right: '4%',
                        bottom: '3%',
                        containLabel: true
                    },
                    xAxis : [
                        {
                            type : 'category',
                            data : data.name,
                            axisTick: {
                                alignWithLabel: true
                            }
                        }
                    ],
                    yAxis : [
                        {
                            type : 'value'
                        }
                    ],
                    series : [
                        {
                            name:'销售额',
                            type:'bar',
                            barWidth: '60%',
                            data:data.value
                        }
                    ]
                };
                ;
                if (option && typeof option === "object") {
                    myChart.setOption(option, true);
                }
            })
        }

        //进入页面默认加载一次
        getDate();

    });
</script>
</html>
