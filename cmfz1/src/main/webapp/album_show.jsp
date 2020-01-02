<%@page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" pageEncoding="UTF-8" %>
<table id="t2"></table>
<div id="pg2"></div>
<script>
    $(function () {
        $("#t2").jqGrid({
            url: '${pageContext.request.contextPath}/album/allAlbum',
            datatype: "json",
            height: 100,
            cellEdit: true, // 开启数据表格的编辑模式
            editurl: '${pageContext.request.contextPath}/album/change',
            colNames: ['ID', 'TITLE', 'COVER', 'AUTHOR', 'BEAM', 'SCORE', 'COUNT', 'INTRO', 'CREATE_DATE'],
            colModel: [
                {name: 'id', key: true},  //指定当前列为主键id列，有且只有一个
                {name: 'title', editable: true, edittype: 'text'},
                {
                    name: 'cover',
                    editable: true,
                    edittype: 'file',
                    formatter: function (value, options, row) {
                        return '<img style="height: 50px;width:70px" src="${pageContext.request.contextPath}/' + 'image/' + value + '"/>';
                    }
                },
                {name: 'author', editable: true, edittype: 'text'},
                {name: 'beam', editable: true, edittype: 'text'},
                {name: 'score', editable: true, edittype: 'text'},
                {name: 'count', editable: false},
                {name: 'intro', editable: true, edittype: 'text'},
                {name: 'create_date'}
            ],
            height: '400px',
            autowidth: true,// 根据父容器自动计算列宽，以适应父容器大小
            styleUI: 'Bootstrap',
            rowNum: 5,
            rowList: [5, 10, 15],
            pager: '#pg2',
            page: 1,//默认展示第几页
            viewrecords: true,
            caption: "专辑展示",
            rownumbers: true,
            subGrid: true,
            caption: "专辑展示",
            subGridRowExpanded: function (subgrid_id, row_id) {//1.当前父容器的id 2.当前专辑的id
                var subgrid_table_id = subgrid_id + "_t";
                var pager_id = "p_" + subgrid_table_id;
                $("#" + subgrid_id).html(
                    "<table id='" + subgrid_table_id + "' class='scroll'></table><div id='" + pager_id + "' class='scroll'></div>");
                jQuery("#" + subgrid_table_id).jqGrid(
                    {
                        url: "${pageContext.request.contextPath}/chapter/allChapter?album_id=" + row_id,
                        datatype: "json",
                        cellEdit: true, // 开启数据表格的编辑模式
                        editurl: '${pageContext.request.contextPath}/chapter/change?album_id=' + row_id,
                        colNames: ['ID', 'TITLE', 'SIZE', 'DURATION', 'COVER', 'CREATE_DATE', '在线播放'],
                        colModel: [
                            {name: "id", key: true},
                            {name: "title", editable: true, edittype: 'text'},
                            {name: "size"},
                            {name: "duration"},
                            {
                                name: "cover",
                                editable: true,
                                edittype: 'file',
                            },
                            {name: "create_date"},
                            {
                                name: "aaa", width: 300, formatter: function (value, options, rows) {
                                    return "<audio controls loop>\n" +
                                        "  <source src='${pageContext.request.contextPath}/music/" + rows.cover + "' type=\"audio/ogg\">\n" +
                                        "  <source src=\"horse.mp3\" type=\"audio/mpeg\">\n" +
                                        "</audio> "
                                }
                            }

                        ],
                        autowidth: true,// 根据父容器自动计算列宽，以适应父容器大小
                        styleUI: 'Bootstrap',
                        pager: pager_id,
                        height: 100,
                        styleUI: 'Bootstrap',
                        rowNum: 5,
                        rowList: [5, 10, 15],
                        page: 1,//默认展示第几页
                        viewrecords: true,
                        caption: "章节展示",
                        rownumbers: true,

                    });
                jQuery("#" + subgrid_table_id).jqGrid('navGrid', "#" + pager_id, {
                        edit: true,
                        add: true,
                        del: true,
                        refresh: true
                    },
                    {
                        //控制修改
                        closeAfterEdit: true,//编辑提交后关闭窗口
                        beforeShowForm: function (frm) {
                            frm.find("#cover").attr("disabled", true);
                        }
                    },
                    {
                        //控制添加的参数
                        closeAfterAdd: true,//添加提交后关闭窗口
                        editData: {id: ''},//当前请求发送的额外参数，如果请求参数的key与表单提交数据的key冲突，会覆盖掉表单提交的
                        afterSubmit: function (response) {
                            var status = response.responseJSON.status;//返回的map中的status
                            var data = response.responseJSON.data;	//返回的map中的message，保存的是插入的banner的id
                            console.log(status);
                            console.log(data);
                            if (status) {
                                console.log("进入上传的方法！！！！！！！！！！！！！");
                                // 文件上传		ajaxFileUpload
                                $.ajaxFileUpload({
                                    url: "${pageContext.request.contextPath}/chapter/upload",
                                    type: "post",
                                    fileElementId: "cover",
                                    data: {id: data},
                                    success: function () {
                                        //刷新页面
                                        //$("#t2").trigger("reloadGrid");
                                        $("#" + subgrid_table_id).trigger("reloadGrid");
                                    }
                                })
                            }
                            return "finish";
                        }
                    }, {/*控制删除*/}, {/*控制搜索*/}
                );
            }
        }).jqGrid('navGrid', '#pg2', {add: true, edit: true, del: true, refresh: true},
            {
                //控制修改
                closeAfterEdit: true,//编辑提交后关闭窗口
                beforeShowForm: function (frm) {
                    frm.find("#cover").attr("disabled", true);
                }
            },
            {
                //控制添加的参数
                closeAfterAdd: true,//添加提交后关闭窗口
                editData: {id: ''},//当前请求发送的额外参数，如果请求参数的key与表单提交数据的key冲突，会覆盖掉表单提交的
                afterSubmit: function (response) {
                    var status = response.responseJSON.status;//返回的map中的status
                    var data = response.responseJSON.data;	//返回的map中的message，保存的是插入的banner的id
                    console.log(status);
                    console.log(data);
                    if (status) {
                        console.log("进入上传的方法！！！！！！！！！！！！！");
                        // 文件上传		ajaxFileUpload
                        $.ajaxFileUpload({
                            url: "${pageContext.request.contextPath}/album/upload",
                            type: "post",
                            fileElementId: "cover",
                            data: {id: data},
                            success: function () {
                                //刷新页面
                                $("#t2").trigger("reloadGrid");
                            }
                        })
                    }
                    return "finish";
                }

            }, {/*控制删除*/}, {/*控制搜索*/}
        );
    })
</script>