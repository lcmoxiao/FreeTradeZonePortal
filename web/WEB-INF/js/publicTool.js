;
/*
导航栏初始化
*/
function showNavigator() {

    let str = "<ul>\n" +
        "            <li>\n" +
        "                <button onclick='goToMyWork()'>代办业务</button>\n" +
        "            </li>\n" +
        "            <li>\n" +
        "                <button onclick='goToWorkFlowManager()'>工作流管理</button>\n" +
        "            </li>\n" +
        "            <li>\n" +
        "                <button onclick='goToAddWorkFlow()'>导入工作流</button>\n" +
        "            </li>\n" +
        "            <li>\n" +
        "                <button onclick='goToMyPosted()'>我的发布</button>\n" +
        "            </li>\n" +
        "        </ul>";

    $("#navigator").html(str);
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

