;
/*
导航栏初始化
*/
function showNavigator() {
    let str2 = "<ul class=\"layui-nav layui-nav-tree layui-bg-blue\" lay-filter=\"test\">\n" +
        "<!-- 侧边导航: <ul class=\"layui-nav layui-nav-tree layui-nav-side\"> -->\n" +
        "  <li class=\"layui-nav-item\">\n" +
        "    <a href=\"javascript:;\" onclick='goToMyWork()'>我的工作</a>\n" +
        "  </li>\n" +
        "  <li class=\"layui-nav-item\">\n" +
        "    <a href=\"javascript:;\" onclick='goToMyPosted()'>我的发布</a>\n" +
        "  </li>\n" +
        "  <li class=\"layui-nav-item\">\n" +
        "    <a href=\"javascript:;\" onclick='goToWorkFlowManager()'>工作流管理</a>\n" +
        "  </li>\n" +
        "  <li class=\"layui-nav-item\">\n" +
        "    <a href=\"javascript:;\" onclick='goToAddWorkFlow()'>导入工作流</a>\n" +
        "  </li>\n" +
        "</ul>";

    $("#navigator").html(str2);
};

function goToMyWork() {
    window.location.href = "/wf/goToMyWork";
};

function goToAddWorkFlow() {
    window.location.href = "/wf/goToAddWorkFlow";
};

function goToMyPosted() {
    window.location.href = "/wf/goToMyPosted";
};

function goToWorkFlowManager() {
    window.location.href = "/wf/goToWorkFlowManager";
};

