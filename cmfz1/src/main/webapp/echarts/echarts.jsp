<%@page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" pageEncoding="UTF-8" %>
<html lang="en">
<head>
    <title>Document</title>
    <!--引入jquery的js文件-->
    <script src="${pageContext.request.contextPath}/statics/boot/js/jquery-3.3.1.min.js"></script>
    <!-- 引入 ECharts 文件 -->
    <script src="echarts.min.js"></script>
    <script type="text/javascript" src="https://cdn.goeasy.io/goeasy-1.0.3.js"></script>
</head>
<body>
<!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
<div id="main" style="width: 600px;height:400px;"></div>
<script type="text/javascript">
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('main'));

    // 指定图表的配置项和数据
    var option = {
        title: {
            text: '用户注册数量'
        },
        tooltip: {},
        legend: {
            data: ['男生', '女生']
        },
        xAxis: {
            data: ["一周", "二周", "三周"]
        },
        yAxis: {},
        series: [{
            name: '男生',
            type: 'bar'
        }, {
            name: '女生',
            type: 'bar'
        }],
    };

    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
    setTimeout(function () {
        $.ajax({
            url: "${pageContext.request.contextPath}/echarts/getAll",
            type: 'get',
            datatype: 'json',
            success: function (data) {
                console.log("ok");
                myChart.setOption({
                    series: [{
                        data: data[0]
                    }, {
                        data: data[1]
                    }]
                });
            }
        })
    }, 10000);


    var goEasy = new GoEasy({
        host: 'hangzhou.goeasy.io',//应用所在的区域地址，杭州：hangzhou.goeasy.io，新加坡：singapore.goeasy.io
        appkey: "BS-4f37197f96e743ebab7e25ad31960707",
        forceTLS: false, //如果需要使用HTTPS/WSS，请设置为true，默认为false
        onConnected: function () {
            console.log('连接成功！')
        },
        onDisconnected: function () {
            console.log('连接断开！')
        },
        onConnectFailed: function (error) {
            console.log('连接失败或错误！')
        }
    });
    setTimeout(function () {

    })
    goEasy.subscribe({
        channel: "aaa", onMessage: function (message) {
            alert("Channel:" + message.channel + " content:" + JSON.parse(message.content));
        }
    });
</script>
</body>
</html>