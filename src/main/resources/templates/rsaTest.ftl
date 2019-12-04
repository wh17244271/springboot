<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/html">
<head lang="en">
    <meta charset="UTF-8"/>
    <title></title>
    <#assign path="${request.getContextPath()}">
</head>
<body>
<p style="color:blue;">这是RSA测试页面:
</p>
<div>
    <span>输入需要加密的字符串:</span><input type="text" id="myStr" /><br><br>
    <button onclick="a();">开始加密</button>
    <br><br>
    <span>加密后:</span><span id="jiami" style="color:orangered;"></span>
    <br><br>
    <button onclick="b();">开始解密</button>
    <br><br>
    <span>解密后:</span><span id="jiemi" style="color:green;"></span>
</div>
</body>
</html>
<script type="text/javascript" src="${path}/js/jquery.min.js"></script>
<script type="text/javascript" src="${path}/js/jsencrypt.min.js"></script>
<script>
    function a() {
        var publicKey = "${publicKey!}";
        var myStr = $("#myStr").val();

        var encrypt = new JSEncrypt();

        encrypt.setPublicKey(publicKey);
        var encryptMyStr = encrypt.encrypt(myStr);
        $("#jiami").text(encryptMyStr);

    }

    function b() {
        var encryptMyStr = $("#jiami").text();
        <#--var privateKey = "${privateKey!}";-->
        <#--var encrypt = new JSEncrypt();-->
        <#--encrypt.setPrivateKey(privateKey);-->
        <#--var jieMiHou = encrypt.decrypt(encryptMyStr);-->
        <#--$("#jiemi").text(jieMiHou);-->
        $.ajax({
            url: "/encryptMyStr",
            data: {
                "encryptMyStr": encryptMyStr,
            },
            dataType: "json",
            type: "get",
            success: function (data) {
                $("#jiemi").text(data.data);
            }
        });
    }

</script>