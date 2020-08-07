;
/*
导航栏初始化
*/
function showNavigator() {
    let str2 = "<ul class=\"layui-nav layui-nav-tree layui-bg-blue\" lay-filter=\"test\">\n" +
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
        "</ul>";

    $("#navigator").html(str2);
};

function goUserManager() {
    window.location.href = "/u/goUserManager";
};

function goRoleManager() {
    window.location.href = "/u/goRoleManager";
};

function goPermissionManager() {
    window.location.href = "/u/goPermissionManager";
};