<#import "spring.ftl" as spring />
<!DOCTYPE html>
<html xmlns="" xmlns="">
<head lang="en">
    <meta charset="UTF-8"/>
    <title></title>
    <#assign path="${request.getContextPath()}">
    <#assign aaa="${request.getContextPath()}">
</head>
<body>
<form id="myForm" action="/index" method="post">
    <p>用户名</p>
    <input id="username" name="username" type="text">
    <p>密码</p>
    <input id="password" name="password" type="password">
    <button type="submit">from登陆</button>
    <p>
        <button id="sub" type="button">ajax登陆</button>
    </p>
</form>
<img src="${request.contextPath }/img/1.jpg" width="300px">
<img src="${path}/img/2.jpg" width="300px">
<img src="${aaa}/img/3.jpg" width="300px">
<img src="<@spring.url'/img/4.jpg'/>" width="300px">
</body>
</html>
    <script type="text/javascript" src="${path}/js/jquery.min.js"></script>
<script>

    $("#sub").click(function () {
        $.ajax({
            type: "POST",   //提交的方法
            url: "/index", //提交的地址
            data: $('#myForm').serialize(),// 序列化表单值
            async: false,
            dateType: "json",
            error: function (request) {  //失败的话
                 // alert("Connection error");
            },
            success: function (data) {  //成功
                window.location.href = "/index"
            }
        });
    });

    function book() {
        studen();
    };
    function studen() {
        alert('sdf');
    }

</script>

