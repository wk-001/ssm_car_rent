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
    <title>客户地区统计</title>
    <script type="text/javascript" src="${ctx}/echarts/echarts.min.js"></script>
    <script type="text/javascript" src="${ctx}/echarts/jquery-3.4.1.min.js"></script>
</head>

<body style="height: 100%; margin: 0">
<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
<div id="container" style="height: 650px;width: 98%"></div>

</body>

<script type="text/javascript">
    var dom = document.getElementById("container");
    var myChart = echarts.init(dom);
    var app = {};
    option = null;

    $(function () {
        initCustomerAreaCharts();
    });

    function initCustomerAreaCharts() {
        $.post("<%=basePath%>stat/initCustomerAreaCharts",function (data) {
            option = {
                title: {
                    text: '客户地区统计',
                    subtext: '真实有效',
                    x: 'center'
                },
                tooltip: {
                    trigger: 'item',
                    formatter: "{a} <br/>{b} : {c} ({d}%)"
                },
                legend: {
                    orient: 'vertical',
                    left: 'left',
                    data: data
                },
                series: [
                    {
                        name: '客户数量(占比)',
                        type: 'pie',
                        radius: '55%',
                        center: ['50%', '60%'],
                        data: data,
                        itemStyle: {
                            emphasis: {
                                shadowBlur: 10,
                                shadowOffsetX: 0,
                                shadowColor: 'rgba(0, 0, 0, 0.5)'
                            }
                        }
                    }
                ]
            };
            ;
            if (option && typeof option === "object") {
                myChart.setOption(option, true);
            }
        })
    }
</script>
</html>
