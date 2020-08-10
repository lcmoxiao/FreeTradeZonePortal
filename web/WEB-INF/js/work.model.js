;
/*
导航栏初始化
*/
function showNavigator() {
    let s = "<ul class=\"layui-nav\" id='navigator_ul'>\n" +
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
        "    <a href=\"javascript:;\" onclick='goToHome()'>返回功能中心</a>\n" +
        "  </li>\n" +
        "</ul>";


    $("#navigator").html(s);
};

function goToMyWork() {
    window.location.href = "/workManagement";
};

function goToMyPosted() {
    window.location.href = "/myPost";
};

function goToWorkFlowManager() {
    window.location.href = "/workflowManagement";
};

function goToHome() {
    window.location.href = "/home";
};
