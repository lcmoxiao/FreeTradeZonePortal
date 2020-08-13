;

function showNavigator() {
    let tmp = "<div class=\"layui-layout layui-layout-admin\">\n" +
        "    <div class=\"layui-header\">\n" +
        "        <div class=\"layui-logo\">门户网站管理</div>\n" +
        "        <!-- 头部区域（可配合layui已有的水平导航） -->\n" +
        "        <ul class=\"layui-nav layui-layout-left\">\n" +
        "            <li class=\"layui-nav-item\"><a href='/myWork'>我的工作</a></li>\n" +
        "            <li class=\"layui-nav-item\">\n" +
        "                <a href=\"\">工作管理</a>\n" +
        "                <dl class=\"layui-nav-child\">\n" +
        "                    <dd><a href='/workflowManagement'>工作流管理</a></dd>\n" +
        "                    <dd><a href='/myPost'>已发布工作</a></dd>\n" +
        "                </dl>\n" +
        "            </li>\n" +
        "            <li class=\"layui-nav-item\"><a href='/userManagement'>用户管理</a></li>\n" +
        "            <li class=\"layui-nav-item\"><a href='/roleManagement'>角色管理</a></li>\n" +
        "            <li class=\"layui-nav-item\"><a href='/permissionManagement'>权限管理</a></li>\n" +
        "            <li class=\"layui-nav-item\"><a href='/modelManagement'>模板管理</a></li>\n" +
        "        </ul>\n" +
        "        <ul class=\"layui-nav layui-layout-right\">\n" +
        "            <li class=\"layui-nav-item\"><a href=\"/logout\">注销</a></li>\n" +
        "        </ul>\n" +
        "    </div>\n" +
        "</div>";
    $("#navigator").html(tmp);
}

function flushLayUi() {
    layui.use('form', function () {
        layui.form.render();
    });
}

function closeTmpWindow() {
    layer.close(index);
}