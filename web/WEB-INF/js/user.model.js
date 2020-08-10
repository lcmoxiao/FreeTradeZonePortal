;

/*
导航栏初始化
*/
function showNavigator() {
    let s = "<ul class=\"layui-nav\" id='navigator_ul'>\n" +
        "<!-- 侧边导航: <ul class=\"layui-nav layui-nav-tree layui-nav-side\"> -->\n" +
        "  <li class=\"layui-nav-item\">\n" +
        "    <a href=\"javascript:;\" onclick='goUserManager()'>用户管理</a>\n" +
        "  </li>\n" +
        "  <li class=\"layui-nav-item\">\n" +
        "    <a href=\"javascript:;\" onclick='goRoleManager()'>角色管理</a>\n" +
        "  </li>\n" +
        "  <li class=\"layui-nav-item\">\n" +
        "    <a href=\"javascript:;\" onclick='goPermissionManager()'>权限管理</a>\n" +
        "  </li>\n" +
        "  <li class=\"layui-nav-item\">\n" +
        "    <a href=\"javascript:;\" onclick='goToHome()'>返回功能中心</a>\n" +
        "  </li>\n" +
        "</ul>";

    $("#navigator").html(s);
};

function goUserManager() {
    window.location.href = "/userManagement";
};

function goRoleManager() {
    window.location.href = "/roleManagement";
};

function goPermissionManager() {
    window.location.href = "/permissionManagement";
};

function goToHome() {
    window.location.href = "/home";
};

function flushLayUi() {
    layui.use('form', function () {
        let form = layui.form;
        form.render();
    });
}