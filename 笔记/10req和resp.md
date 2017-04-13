# response #
- 作用
	- 响应请求,返回数据
- 组成部分
	- 响应行		响应头		响应体
- 响应行
	- 格式
		- 协议/版本		状态码		状态码说明
	- 状态码
		- 1xx:已发送请求
		- 2xx:已完成响应
		- 3xx:还需游览器进一步操作
		- 4xx:用户操作错误
			1. 404:访问资源不存在
			2. 405:访问的方法不存在
		- 5xx:服务器错误
	- 常用方法
		- setStatus(int 状态码):针对1xx,2xx,3xx
		- sendError(int 状态码):针对4xx,5xx
- 响应头
	- 格式
		- key/value(value可以使多值)
	- 常用方法
		- setHeader(String key,String value);
		- setIntHeader(String key, int value);
		- setDataHeader(String key, Data value);
		- 更常用的还有addxxxx方法,set方法是针对单值,add方法在无值时会设置,有值时会作为多值追加
	- 常用的响应头
		- location: 重定向
		- refresh: 定时刷新
		- content-type:设置文件的mime类型,设置响应流的编码告诉游览器解析的编码
		- content-disposition:文件下载
		- 重定向的使用
			- 方式1:
				- response.sendReadirect("路径");
			- 方式2:
				- response.setStatus(302);
				- response.setHeader("location","路径")
		- 定时刷新的使用:
			- 方式1:
				- respooen.setHeader("refresh","秒数;url=跳转的路径");
			- 方式2:
				- <meta http-equiv="refresh" content="秒数;url=路径">
	- 响应体
		- 常用方法:
			- Writer getWriter():字符流
			- ServletOutputStream getOutputStream() :字节流
		- 处理中文乱码
			- response.setContentType("text/html;charset=utf-8");
			- response.setHeader("content-type", "text/html;charset=utf-8");
	- 注意:
		- 在同一个响应中两个输出流是互斥的,同一时间只能存在一个互斥流,close方法可以不写,在响应完成之后服务器会判断是否关闭流,未关闭会做关闭操作.
- 文件下载
	- 下载方式
		1. 超链接下载
			- `<`a href="文件路径">xxxx`<`/a>
		1. 编码下载
			1. 设置文件类型
			```
				String mimeType=context.getMimeType(文件名)
				response.setContentType(mimeType);
			```
			1. 设置下载头信息
			```
				response.setHeader("content-disposition", "attachment;filename="+文件名称);
			```
				- 最后的文件名称就是游览器获取的文件名称,但是游览器支持的编码格式并不一样会导致文件名乱码的问题
			1. IO流互拷
			```
				IOUtils.copy(is,os);
			```


# request #
- 作用
	- 携带请求数据发送到服务器
- 组成部分
	- 请求行		请求头		请求体
- 请求行
	- 格式
		- 请求方式		请求资源		协议/版本
	- 常用方法
		- String getMethod():获取请求方式
		- String getRemoteAddr():获取ip地址
		- String getContextPath() :在java中获取项目名称  (/day10)
		- getRequestURI():获取的是 从项目名到参数之前的内容  /day10/regist
		- getRequestURL():获取的带协议的完整路径   http://localhost/day10/regist
		- String getQueryString():get请求的所有参数   username=tom&password=123
		- String getProtocol():获取协议和版本
- 请求头
	- 格式
		- key/value(value可以是多值)
	- 常用方法
		- String getHeader(String key):通过key获取指定的value (一个)
		- Enumeration getHeaders(String name) :通过key获取指定的value(多个)
		- Enumeration getHeaderNames() :获取所有的请求头的名称
		- int getIntHeader(String key):获取整型的请求头
		- long getDateHeader(String key):获取时间的请求头
	- 重要的请求头参数
		- user-agent:游览器,系统相关信息
		- referer:请求来自哪里
- 请求体
	- 格式
		- key=value&key=valuexxxx
	- 常用方法
		- String getParameter(String key):获取一个值
		- String[] getParameterValues(String key):通过一个key获取多个值
		- Map<String,String[]> getParameterMap():获取所有的参数名称和值
	- 请求的中文乱码
		- tomcat8.5未设置request编码,但是有默认的编码.如get中文默认不乱码.post中文默认乱码一样
		- 应对方式:直接先设置request编码为utf-8即可.
		```
			 request.setCharacterEncoding("utf-8");
		```
- 域对象
	- request
		- 创建:请求生成时
		- 销毁:响应生成时
		- 作用:携带数据进行请求
	- 为什么是域对象
		- 在请求转发时完成了跨域操作,两个域都是同一个request对象,所以算是域对象

- 请求转发和重定向区别:
	- 重定向发送两次请求,请求转发一次请求
	- 重定向地址栏发生该表,请求转发不变
	- 重定向是从浏览器发送,请求转发是服务器内部
	- 重定向不存在request域对象,请求转发可以使用request域对象
	- 重定向是response的方法,请求转发是request的方法
	- 重定向可以请求站外资源,请求转发不可以
			