# http #
超文本传输协议:规定数据的格式
- 浏览器往服务器发送 ---- 请求
- 服务器往浏览器回写 ---- 响应

- request
	- 组成部分
		- 请求行
		- 请求头
		- 请求体
	- 请求行:报文第一行
		- 格式:请求方式	访问的资源  		协议/版本
			- GET 	  /day0801/1.html 	HTTP/1.1
		- 请求方式
			- get/post
				- get请求会把参数放在url后面,而post不会
				- get参数大小(url)大小有限制,post没有
				- get没有请求体  post有请求体,请求参数放置在请求体
	- 请求头
		- 格式:key/value (value可以是多个值)
		- 常见请求头:
			- Accept: text/html,image/bmp			--支持数据类型    text/html text/css text/javascript 大类型/小类型 mime类型
			- Accept-Charset: ISO-8859-1			--字符集
			- Accept-Encoding: gzip					--支持压缩
			- Accept-Language:zh-cn 				--语言环境
			- Host: www.itcast.cn:80				--访问主机
			- If-Modified-Since: Tue, 11 Jul 2000 18:23:51 GMT	  --缓存文件的最后修改时间
			- Referer: http://www.itcast.com/index.jsp	 --来自哪个页面、防盗链
			- User-Agent: Mozilla/4.0 (compatible; MSIE 5.5; Windows NT 5.0)	--硬件信息
			- Cookie:xxxxxx				--身份标识
			- Connection:Keep-Alive   	--链接状态,http短链接会被保存一小段时间,这里标识其状态
	- 请求体
		- 格式 key=value&key=value..
		- 只有post才有请求体,  get请求参数 http://xxxx?key=value&...
- response
	- 组成部分
		- 响应行
		- 响应头
		- 响应体
	- 响应行：报文第一行
		- 格式：协议/版本		状态码		状态码说明
			- HTTP/1.1	200			OK
			- 状态码
				- 200: 正常响应成功
				- 302: 重定向
				- 304: 读取缓存
				- 404: 访问资源不存在
				- 500: 服务器内部异常
	- 响应头:从响应信息的第二行到空行结
		- 格式:key/value (value可以是多个值)
		- 常见头
			- Location: http://www.it315.org/index.jsp 	--跳转方向 和302一起使用的
			- Server:apache tomcat			--服务器型号
			- Content-Encoding: gzip 			--数据压缩
			- Content-Length: 80 			--数据长度
			- Content-Language: zh-cn 		--语言环境
			- Content-Type: text/html; charset=GB2312 		--数据类型
			- Last-Modified: Tue, 11 Jul 2000 18:23:51 GMT	--最后修改时间
			- Refresh: 1;url=http://www.it315.org		--定时刷新
			- Content-Disposition: attachment; filename=aaa.zip	--下载
			- Set-Cookie:SS=Q0=5Lb_nQ; path=/search
			- Expires: -1					--缓存
			- Cache-Control: no-cache  			--缓存
			- Pragma: no-cache   				--缓存
			- Connection: Keep-Alive   			--连接
# servlet #
动态的web开发技术,本质就是一个类,运行在服务器端的一个java小程序,用于处理业务逻辑,生成动态web内容.
- servlet体系结构
	- Servlet:		(接口)
	- GenericServlet:	(抽象类)
	- HttpServlet:		(抽象类,但是没有未实现的抽象方法)
- servlet声明周期
	- void init(ServletConfig config):初始化
		 * 初始化方法
		 * 执行者:服务器
		 * 执行次数:一次
		 * 执行时机:默认第一次访问的时候
 	- void service(ServletRequest request,ServletResponse response):服务 处理业务逻辑
		 * 服务
		 * 执行者:服务器
		 * 执行次数:请求一次执行一次
		 * 执行时机:请求来的时候
	- void destroy():销毁
		 * 销毁
		 * 执行者:服务器
		 * 执行次数:只执行一次
		 * 执行时机:当servlet被移除的时候或者服务器正常关闭的时候

> serlvet是单实例多线程
> 默认第一次访问的时候,服务器创建servlet,并调用init实现初始化操作.并调用一次service方法
> 每当请求来的时候,服务器创建一个线程,调用service方法执行自己的业务逻辑
> 当serlvet被移除的时候服务器正常关闭的时候,服务器调用servlet的destroy方法实现销毁操作.

- url-pattern配置
	- 匹配模式
		1. 完全匹配:  以**/**开始,例如**/hello**,**/a****/b**等.
		2. 目录匹配:	 以**/**开始,以**`*`**结尾, 例如 **/a/`*`**  **/`*`**
		3. 后缀名匹配:以**`*`**开始,以字符结尾 例如**`*`.jsp**  **`*`.do**
	- 优先级
		- 完全匹配 > 目录匹配 >后缀匹配
- load-on-startup
	- 作用:修改servlet的初始化时机(正常在第一次被访问时初始化)
	- 取值:值与优先级成反比

- ServletConfig: servlet配置对象
	- 作用
		1. 获取当前servlet名称
		2. 获取当前servlet初始化参数
		3. 获取ServletContext对象
	- 常用方法
		- String getServletName():获取当前servlet的名称(web.xml配置的servlet-name)
		- String  getInitParameter(String key):通过名称获取指定的参数值
		- Enumeration getInitParameterNames() :获取所有的参数名称
> servletconfig是由服务器创建的,在创建servlet的同时也创建了它,通过servlet的init(ServletConfig config)将config对象
	传递给servlet,由servlet的getServletConfig方法获取
- ServletContext
	- 上下文(域对象)
		- 当web项目启动的时候被创建,单例唯一,可以在多个servlet中做信息传递的桥梁,当web项目关闭或移除时被销毁.
		- 其设置属性的方法是加锁安全的,可以放心使用
	- 作用
		1. 获取全局的初始化参数
		2. servlet间共享信息
		3. 获取文件mime类型
	- 常用方法
		- setAttribute(String key,Object value);//设置值
		- Object getAttribute(String key);//获取值
		- removeAttribute(String key)://移除值