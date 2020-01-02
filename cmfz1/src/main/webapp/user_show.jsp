<%@page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" pageEncoding="UTF-8" %>
<%--标签页--%>
<div>
    <!-- Nav tabs -->
    <ul class="nav nav-tabs" role="tablist">
        <li role="presentation" class="active"><a href="#" aria-controls="home" role="tab" data-toggle="tab">展示用户</a>
        </li>
        <li role="presentation"><a href="#" aria-controls="profile" role="tab" data-toggle="tab" onclick="UserOut()">数据导出</a>
        </li>
        <li role="presentation"><a href="#" aria-controls="profile" role="tab" data-toggle="tab" onclick="echarts()">注册人数</a>
        </li>
    </ul>
</div>
<table id="t3"></table>
<div id="pg3"></div>
<script>
    $(function () {
        $('#t3').jqGrid({
            url: '${pageContext.request.contextPath}/user/allUser',//  查询数据表格加载的路径
            datatype: 'json',
            colNames: ['编号', '用户名', '法号', '省份', '城市', '签名', '性别', '头像', '状态', '电话', '日期'],// 定义列的名字
            editurl: '${pageContext.request.contextPath}/user/change',
            /*prmNames: {id: 'id'}, 自定义默认jqgrid发送请求参数key的名字*/
            colModel: [ // 定义每列匹配远程数据的规则相关操作
                {
                    name: 'id',
                    key: true  //指定当前列为主键id列，有且只有一个
                },
                {
                    name: 'username',
                },
                {
                    name: 'dharma',
                },
                {
                    name: 'province',
                },
                {
                    name: 'city',
                },
                {
                    name: 'sign',
                },
                {
                    name: 'sex',
                },
                {
                    name: 'photo',
                    formatter: function (value, options, row) {
                        return '<img style="height: 50px;width:70px" src="${pageContext.request.contextPath}/' + 'image/' + value + '"/>';
                    }
                },
                {
                    name: 'status',
                    editable: true,
                    formatter: function (rowObject) {
                        console.log(rowObject);
                        var level;
                        if (rowObject == 1) {
                            level = "<option>激活</option>";
                        } else if (rowObject == 2) {
                            level = "<option>禁用</option>";
                        }
                        return level;
                    },
                    //单元格修改状态
                    edittype: 'select',
                    editoptions: {value: "1:激活; 2:禁用"}
                },
                {
                    name: 'phone',
                },
                {
                    name: 'create_date',
                },

            ],
            height: '400px',
            styleUI: 'Bootstrap',
            autowidth: true,// 根据父容器自动计算列宽，以适应父容器大小
            pager: "#pg3",//指定构建分页工具栏的div
            rowList: [5, 10, 15, 20],//每页展示多少条
            rowNum: 5,//默认每页展示的条数，要值必须来自于rowList求中的一个
            page: 1,//默认展示第几页
            viewrecords: true,//显示信息的总条数
            caption: "用户展示",
            rownumbers: true
        }).navGrid("#pg3", {add: false, edit: true, del: false, search: false, refresh: true},
            {   //控制编辑的参数
                closeAfterEdit: true,//编辑提交后关闭窗口
                beforeShowForm: function (frm) {
                    frm.find("#cover").attr("disabled", true);
                }

            }, {}, {/*控制删除*/}, {/*控制搜索*/}, {/*控制pView*/}
        )
    })

    function UserOut() {
        window.location.href = "${pageContext.request.contextPath}/user/daochu";
    }

    function echarts() {
        window.location.href = "${pageContext.request.contextPath}/echarts/echarts.jsp";
    }

</script>