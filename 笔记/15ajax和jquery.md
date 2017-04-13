# AJAX #
AJAX是一种用于创建快速动态网页的技术,通过在后台与服务器进行少量的数据交换,AJAX可以使网页实现异步更新,这意味着可以再不重新加载整个网页的情况下,对网页的某部分进行更新,传统的网页(不使用AJAX)如果需要更新内容,必须重载整个网页.

- AJAX-api
	- 常用属性
		- onreadystatechange:检测readyState状态改变的时候
		- readyState:ajax核心对象的状态
			- 0:核心对象创建
			- 1:调用了open方法
			- 2:调用了send方法
			- 3:部分响应已经生成(没有意思)
			- 4:响应已经完成(使用的是这个状态)
		- status:状态码
			```
				if(xmlhttp.readyState==4 && xmlhttp.status==200){
				}
			```
		- responseText:响应回来的文本
	- 常用方法
		- open("**请求方式**","**请求路径**",["**是否异步**"]):设置请求的方式和请求的路径\
		- send(["**参数**"]):发送请求 参数是请求方式为post的时候的参数
		- setRequestHeader("**content-type**","**form表单enctype属性**"):设置post请求的参数的类型 必须放在send方法之前.

# jquery中的AJAX #
- get请求方式
	- 格式 
		- $.get(**url**,**params**,**function(数据){}**,**type**);
	- 参数
		- url:请求的路径
		- params:请求的参数 参数为**key**/**value**的形式 key=value  {"":"","":""}
		- fn:回调函数 参数就是服务器发送回来的数据
		- type:返回内容格式，xml, html, script, json, text, _default。通常使用"json".
- post请求方式
	- 格式
		- $.post(**url**,**params**,**function(数据){}**,**type**);
	- 参数
		- url:请求的路径
		- params:请求的参数 参数为**key**/**value**的形式 key=value  {"":"","":""}
		- fn:回调函数 参数就是服务器发送回来的数据
		- type:返回内容格式，xml, html, script, json, text, _default。通常使用"json".