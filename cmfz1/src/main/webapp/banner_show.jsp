<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" pageEncoding="UTF-8" %>

<div style="height: 80%;">
    <table id="t1"></table>
    <div id="pg1" style="height: 50px"></div>
</div>
<!--引入jquery的js文件-->
<script src="${pageContext.request.contextPath}/statics/boot/js/jquery-2.2.1.min.js"></script>
<!--引入bootStrap的js文件-->
<script src="${pageContext.request.contextPath}/statics/boot/js/bootstrap.min.js"></script>
<!--引入jqGrid的js文件-->
<script src="${pageContext.request.contextPath}/statics/jqgrid/js/ajaxfileupload.js"></script>
<script src="${pageContext.request.contextPath}/statics/jqgrid/js/trirand/i18n/grid.locale-cn.js"></script>
<script src="${pageContext.request.contextPath}/statics/jqgrid/js/trirand/jquery.jqGrid.min.js"></script>
<script>
    $(function () {
        $('#t1').jqGrid({
            url: '${pageContext.request.contextPath}/banner/allBanner',//  查询数据表格加载的路径
            datatype: 'json',
            colNames: ['ID', 'NAME', 'COVER', 'DESCRIPTION', 'STATUS', 'CREATE_DATE'],// 定义列的名字
            cellEdit: true, // 开启数据表格的编辑模式
            //cellurl: '${pageContext.request.contextPath}/banner/change',// 单元格编辑后提交的路径
            editurl: '${pageContext.request.contextPath}/banner/change',
            /*prmNames: {id: 'id'}, 自定义默认jqgrid发送请求参数key的名字*/
            colModel: [ // 定义每列匹配远程数据的规则相关操作
                {
                    name: 'id',
                    key: true  //指定当前列为主键id列，有且只有一个
                },
                {
                    name: 'name',
                    // 开启单元格编辑模式,如果需要针对单元格进行单独编辑，还必须保证cellEdit为true
                    editable: true,
                    edittype: 'text'// 指定单元格编辑类型，默认是text, 还支持 select checkbox  file 等
                },
                {
                    name: 'cover',
                    editable: true,
                    edittype: 'file',
                    formatter: function (value, options, row) {
                        return '<img style="height: 50px;width:70px" src="${pageContext.request.contextPath}/' + 'image/' + value + '"/>';
                    }
                },
                {
                    name: 'description',
                    editable: true,
                    edittype: 'text',
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
                    //editable: true,
                    name: 'create_date',
                    //formatter: "date"

                },

            ],
            height: '400px',
            styleUI: 'Bootstrap',
            autowidth: true,// 根据父容器自动计算列宽，以适应父容器大小
            pager: "#pg1",//指定构建分页工具栏的div
            rowList: [5, 10, 15, 20],//每页展示多少条
            rowNum: 5,//默认每页展示的条数，要值必须来自于rowList求中的一个
            page: 1,//默认展示第几页
            viewrecords: true,//显示信息的总条数
            caption: "轮播图展示",
            rownumbers: true
        }).navGrid("#pg1", {add: true, edit: true, del: true, refresh: true},
            {   //控制编辑的参数
                closeAfterEdit: true,//编辑提交后关闭窗口
                beforeShowForm: function (frm) {
                    frm.find("#cover").attr("disabled", true);
                }

            }, {
                //控制添加的参数
                closeAfterAdd: true,//添加提交后关闭窗口
                editData: {id: ''},//当前请求发送的额外参数，如果请求参数的key与表单提交数据的key冲突，会覆盖掉表单提交的
                afterSubmit: function (response) {
                    var status = response.responseJSON.status;//返回的map中的status
                    var data = response.responseJSON.data;	//返回的map中的message，保存的是插入的banner的id
                    console.log(response.responseJSON + "0000000");
                    console.log(status);
                    if (status) {
                        console.log("进入上传的方法！！！！！！！！！！！！！");
                        // 文件上传		ajaxFileUpload
                        $.ajaxFileUpload({
                            url: "${pageContext.request.contextPath}/banner/upload",
                            type: "post",
                            fileElementId: "cover",
                            data: {id: data},
                            success: function () {
                                //刷新页面
                                $("#t1").trigger("reloadGrid");
                            }
                        })
                    }
                    return "finish";
                }
            }, {/*控制删除*/}, {/*控制搜索*/}, {/*控制pView*/}
        )
    })


</script>
