<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8"/>
    <title></title>
    <#assign path="${request.getContextPath()}">
</head>
<body>
<p style="color:blue;">wode页面:
<h1 style="color:blue;">${username!}</h1>

<h1 style="color:blue;">${message!}</h1>
</p>
<div>
    <input type="text" id="xss" value="变化之前">
    <button type="button" onclick="a();">点击</button>
</div>
<div>
    <form action="/xss" method="get">
        <input type="text" id="aa" name='username' value="第一个输入框script">
        <input type="text" id="bb" name='username' value="第script二个">
        <button type="submit">提交</button>
    </form>
</div>
</body>
</html>
<script type="text/javascript" src="${path}/js/jquery.min.js"></script>
<script>
    function a() {
        $.ajax({
            url: "/xss",
            data: {
                "username": ["命运的烟尘script", "第二个参数script"],
                "password": "123456scriptscrdscript"
            },
            dataType: "json",
            type: "get",
            success: function (data) {

            }
        });
    }

</script>