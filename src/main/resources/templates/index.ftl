<!DOCTYPE html>
<html>
<head lang="en">
<meta charset="UTF-8" />
<title></title>
</head>
<body>
	<p style="color:blue;">欢迎回来: 
		<h1 style="color:blue;">${username!}</h1>
		
		<h1 style="color:blue;">${message!}</h1>
		
	</p>
	<a href="wode" >wode_GET</a>
	
	<a href="javascript:void(0);"  onclick="document.getElementById('myform').submit();">wode_POST</a>
	<form id="myform" method="post" action="/wode">
	</form>
</body>
</html>