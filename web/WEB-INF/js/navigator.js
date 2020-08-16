;

function showNavigator() {
    $.ajax({
        url: "/permission/mine",
        method: "get",
        success: function (res) {
            layui.use('element', function () {
                var element = layui.element;
            });
            let ps = "";
            for (let i = 0; i < res.length; i++) {
                ps += res[i]['pSrc'];
            }
            console.log(ps);
            let tmp = "<div class=\"layui-layout layui-layout-admin\">\n" +
                "    <div class=\"layui-header\">\n" +
                "        <div class=\"layui-logo\">门户网站管理</div>\n" +
                "        <!-- 头部区域（可配合layui已有的水平导航） -->\n" +
                "        <ul class=\"layui-nav layui-layout-left\">\n";

            if (ps.indexOf("/work") >= 0)
                tmp += "            <li class=\"layui-nav-item\"><a href='/workOfMine'>我的工作</a></li>\n";
            if (ps.indexOf("/workflow") >= 0)
                tmp += "            <li class=\"layui-nav-item\">\n" +
                    "                <a href=\"javascript:;\">工作管理</a>\n" +
                    "                <dl class=\"layui-nav-child\">\n" +
                    "                    <dd><a href='/workflowManagement'>工作流管理</a></dd>\n" +
                    "                    <dd><a href='/workOfMyPost'>已发布工作</a></dd>\n" +
                    "                </dl>\n" +
                    "            </li>\n";
            if (ps.indexOf("/user") >= 0)
                tmp += "            <li class=\"layui-nav-item\"><a href='/userManagement'>用户管理</a></li>\n";
            if (ps.indexOf("/role") >= 0) tmp += "            <li class=\"layui-nav-item\"><a href='/roleManagement'>角色管理</a></li>\n";
            if (ps.indexOf("/permission") >= 0) tmp += "            <li class=\"layui-nav-item\"><a href='/permissionManagement'>权限管理</a></li>\n";
            if (ps.indexOf("/model") >= 0) tmp += "            <li class=\"layui-nav-item\"><a href='/modelManagement'>模板管理</a></li>\n";
            if (ps.indexOf("/main") >= 0) tmp += "            <li class=\"layui-nav-item\"><a href='/main'>内容管理</a></li>\n";
            if (ps.indexOf("/statistic") >= 0) tmp += "            <li class=\"layui-nav-item\"><a href='/statisticOfLogin'>访问分析</a></li>\n";
            tmp +=
                "        </ul>\n" +
                "        <ul class=\"layui-nav layui-layout-right\">\n" +
                "            <li class=\"layui-nav-item\"><a href=\"/logout\">注销</a></li>\n" +
                "        </ul>\n" +
                "    </div>\n" +
                "</div>";
            $("#navigator").html(tmp);
            flushLayUi();
        }
    });

}

function flushLayUi() {
    layui.use('form', function () {
        layui.form.render();
    });
}

function closeTmpWindow() {
    layer.close(index);
}

function dateToStr(date) {
    var time = new Date(date);
    var y = time.getFullYear();
    var M = time.getMonth() + 1;
    M = M < 10 ? ("0" + M) : M;
    var d = time.getDate();
    d = d < 10 ? ("0" + d) : d;
    var h = time.getHours();
    h = h < 10 ? ("0" + h) : h;
    var m = time.getMinutes();
    m = m < 10 ? ("0" + m) : m;
    return y + "-" + M + "-" + d + " " + h + ":" + m;
}