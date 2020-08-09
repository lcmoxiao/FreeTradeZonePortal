;
/*
导航栏初始化
*/
function showNavigator() {
    let s = "<ul class=\"layui-nav\" lay-filter=\"\">\n" +
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
        "  <li class=\"layui-nav-item\">\n" +
        "    <a href=\"javascript:;\" onclick='goToHome()'>返回功能中心</a>\n" +
        "  </li>\n" +
        "</ul>";


    $("#navigator").html(s);
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

function goToHome() {
    window.location.href = "/home";
};
