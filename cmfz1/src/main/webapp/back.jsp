<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>持明法洲后台管理系统</title>

    <!--引入bootStrap的css样式文件-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/boot/css/bootstrap.css" type="text/css">
    <!--引入bootStrap的css样式文件-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/jqgrid/css/trirand/ui.jqgrid-bootstrap.css"
          type="text/css">
    <!--引入jquery的js文件-->
    <script src="${pageContext.request.contextPath}/statics/boot/js/jquery-3.3.1.min.js"></script>
    <!--引入bootStrap的js文件-->
    <script src="${pageContext.request.contextPath}/statics/boot/js/bootstrap.min.js"></script>
    <!--引入jqGrid的js文件-->
    <script src="${pageContext.request.contextPath}/statics/jqgrid/js/ajaxfileupload.js"></script>
    <script src="${pageContext.request.contextPath}/statics/jqgrid/js/trirand/i18n/grid.locale-cn.js"></script>
    <script src="${pageContext.request.contextPath}/statics/jqgrid/js/trirand/jquery.jqGrid.min.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/kindeditor/themes/default/default.css"/>
    <script charset="utf-8" src="${pageContext.request.contextPath}/kindeditor/kindeditor-all.js"></script>
    <script charset="utf-8" src="${pageContext.request.contextPath}/kindeditor/lang/zh-CN.js"></script>
</head>
<body>
<!--顶部导航-->
<div class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="row">
            <div class="navbar-header col-md-10">
                <button class="navbar-toggle collapsed" data-toggle="collapse" data-target="#cc">
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a href="" class="navbar-brand">持明法洲后台管理系统</a>
            </div>
            <div class="navbar-collapse collapse">
                <ul class="nav navbar-nav">
                    <li><a href="">欢迎：${loginAdmin.username}</a></li>
                    <li><a href="">安全退出</a></li>
                </ul>
            </div>
        </div>
    </div>
</div>
<!--主体内容-->
<div class="navbar">
    <div class="row">
        <!--手风琴-->
        <div class="col-md-2">
            <div class="panel-group" id="acc">
                <div class="panel panel-danger">
                    <div class="panel-heading" style="text-align: center">
                        <a href="#pc1" class="panel-title" data-toggle="collapse" data-parent="#acc">轮播图管理</a>
                    </div>
                    <div class="panel-collapse collapse" id="pc1">
                        <div class="panel-body" style="text-align: center">
                            <div>
                                <a href="javascript:$('#content').load('${pageContext.request.contextPath}/banner_show.jsp')"
                                   class="btn btn-info" style="width: 80%">展示轮播图</a>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="panel panel-info">
                    <div class="panel-heading" style="text-align: center">
                        <a href="#pc2" class="panel-title" data-toggle="collapse" data-parent="#acc">专辑管理</a>
                    </div>
                    <div class="panel-collapse collapse" id="pc2">
                        <div class="panel-body" style="text-align: center">
                            <div>
                                <a href="javascript:$('#content').load('${pageContext.request.contextPath}/album_show.jsp')"
                                   class="btn btn-warning" style="width: 80%">展示专辑信息</a>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="panel panel-default">
                    <div class="panel-heading" style="text-align: center">
                        <a href="#pc3" class="panel-title" data-toggle="collapse" data-parent="#acc">文章管理</a>
                    </div>
                    <div class="panel-collapse collapse" id="pc3">
                        <div class="panel-body" style="text-align: center">
                            <div>
                                <a href="javascript:$('#content').load('${pageContext.request.contextPath}/article_show.jsp')"
                                   class="btn btn-danger" style="width: 80%">展示文章信息</a>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="panel panel-warning">
                    <div class="panel-heading" style="text-align: center">
                        <a href="#pc4" class="panel-title" data-toggle="collapse" data-parent="#acc">用户管理</a>
                    </div>
                    <div class="panel-collapse collapse" id="pc4">
                        <div class="panel-body" style="text-align: center">
                            <div>
                                <a href="javascript:$('#content').load('${pageContext.request.contextPath}/user_show.jsp')"
                                   class="btn btn-success" style="width: 80%">展示用户信息</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%--巨幕和图片--%>
        <div class="col-md-10" id="content">
            <div class="jumbotron">
                <h3>欢迎来到持明法洲后台管理系统!</h3>
            </div>
            <img src="${pageContext.request.contextPath}/image/shouye.jpg">
        </div>
    </div>
</div>
<div class="pannel-footer panel panel-info  text-center" style="position: absolute;width: 100%">
    <h5>持明法洲后台管理系统@百知教育2019.7.8</h5>
</div>


</body>
</html>
