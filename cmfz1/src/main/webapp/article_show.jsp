<%@page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" pageEncoding="UTF-8" %>
<%--标签页--%>
<div>
    <!-- Nav tabs -->
    <ul class="nav nav-tabs" role="tablist">
        <li role="presentation" class="active"><a href="#" aria-controls="home" role="tab" data-toggle="tab">展示文章</a>
        </li>
        <li role="presentation"><a href="#" aria-controls="profile" role="tab" data-toggle="tab"
                                   onclick="openModal('add')">添加文章</a></li>
    </ul>
</div>
<table id="t4"></table>
<div id="pg4"></div>
<%--模态框--%>
<div class="modal fade" role="dialog" id="article_modal">
    <div class="modal-dialog" style="width: 702px">
        <div class="modal-content" role="document">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">添加文章</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal">
                    <input type="hidden" name="id" id="article_id"/>
                    <input type="hidden" name="oper" id="article_oper"/>
                    <div class="form-group">
                        <label for="article_title" class="col-sm-2 control-label">标题:</label>
                        <div class="col-sm-10">
                            <input type="text" name="title" class="form-control" id="article_title" placeholder="Title">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="article_author" class="col-sm-2 control-label">作者:</label>
                        <div class="col-sm-10">
                            <input type="text" name="author" class="form-control" id="article_author"
                                   placeholder="Author">
                        </div>
                    </div>
                    <div class="form-group">
                       <textarea id="editor_id" name="content" style="width:700px;height:300px;">
                       </textarea>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-success" onclick="delSave()">保存</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div>
<!-- /.modal -->
<script>
    $(function () {
        $('#t4').jqGrid({
            url: '${pageContext.request.contextPath}/article/allArticle',//  查询数据表格加载的路径
            datatype: 'json',
            colNames: ['编号', '标题', '内容', '日期', '作者', '操作'],// 定义列的名字
            editurl: '${pageContext.request.contextPath}/article/change',
            /*prmNames: {id: 'id'}, 自定义默认jqgrid发送请求参数key的名字*/
            colModel: [ // 定义每列匹配远程数据的规则相关操作
                {
                    name: 'id',
                    key: true  //指定当前列为主键id列，有且只有一个
                },
                {
                    name: 'title',
                    editable: true,
                    edittype: 'text'
                },
                {
                    name: 'content',
                    editable: true,
                    edittype: 'text'
                },
                {
                    name: 'create_date',
                },
                {
                    name: 'author',
                    editable: true,
                    edittype: 'text'
                },
                {
                    name: 'aaa', formatter: function (value, option, rows) {
                        return "<a class='btn btn-info' onclick=\"openModal('edit','" + rows.id + "')\">修改文章</a>"
                    }
                }
            ],
            height: '400px',
            styleUI: 'Bootstrap',
            autowidth: true,// 根据父容器自动计算列宽，以适应父容器大小
            pager: "#pg4",//指定构建分页工具栏的div
            rowList: [5, 10, 15, 20],//每页展示多少条
            rowNum: 5,//默认每页展示的条数，要值必须来自于rowList求中的一个
            page: 1,//默认展示第几页
            viewrecords: true,//显示信息的总条数
            caption: "文章展示",
            rownumbers: true
        }).navGrid("#pg4", {add: false, edit: false, del: true, search: false, refresh: true},
            {   //控制编辑的参数
            },
            {/*控制添加*/}, {/*控制删除*/}, {/*控制搜索*/}, {/*控制pView*/}
        )
    })

    function openModal(oper, id) {
        KindEditor.html("#editor_id", "");
        var article = $("#t4").jqGrid("getRowData", id);
        //文章的数据赋值给form表单
        $("#article_id").val(article.id);
        $("#article_title").val(article.title);
        $("#article_author").val(article.author);
        KindEditor.html("#editor_id", article.content);
        $("#article_oper").val(oper);
        $("#article_modal").modal("show")
    }

    KindEditor.create('#editor_id', {
        allowFileManager: true,
        uploadJson: "${pageContext.request.contextPath}/kindeditor/upload",//
        filePostName: "img",
        fileManagerJson: "${pageContext.request.contextPath}/kindeditor/getAll",//指定浏览远程图片的服务器端程序
        resizeType: 1,
        //将富文本的内容提交到form表单中
        afterBlur: function () {
            this.sync();
        }
    });

    function delSave() {
        $.ajax({
            url: "${pageContext.request.contextPath}/article/change",
            type: "post",
            data: {
                id: $("#article_id").val(),
                title: $("#article_title").val(),
                author: $("#article_author").val(),
                oper: $("#article_oper").val(),
                content: $(document.getElementsByTagName('iframe')[0].contentWindow.document.body).html()
            },
            datatype: "json",
            success: function () {
                $("#article_modal").modal("hide");
                $("#t4").trigger("reloadGrid");
            }
        })
    }
</script>

