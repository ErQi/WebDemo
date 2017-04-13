# jsp #
- 什么是jsp
	- jsp是java server pages(java服务器页面)的简称.
	- 本质上jsp就是一个servlet,在html代码中嵌套java代码.
- jsp的作用
	- 在服务器端运行,处理请求,生成动态内容.
- 存放位置
	- 对应的java和class文件再tomcat目录下的work目录中.
- 生成文件的格式
	- 项目源码中 文件名.jsp  服务器端编译之后 文件名_jsp.class
	- jsp文件再第一次被访问的时候服务器生成,未访问之前不会生成class文件
- 执行流程
	1. 发送请求访问jsp
	2. 服务器接受请求,jspServlet查找对应的jsp文件
	3. 服务器将jsp翻译成一个集成servlet的java文件
	4. jvm将java文件编译成class文件
	5. 服务器运行class文件,生成动态内容
	6. 将内容发送给服务器
	7. 服务器组成响应信息,发送给浏览器
	8. 浏览器接受数据,解析展示.
- jsp的脚本
	- `<% .... %>`
		- java代码片段,生成在jsp的service方法中
	- `<%= ... %>`
		- 输出表达式,同样声称在service方法中,等同于调用out.print(..)
	- `<%! ... %>`
		- 声明成员变量,声称在文件的开头的,成员变量位置.


# cookie和session #
- 会话技术
	- 当用户打开浏览器是,访问不同资源,直到用户关闭浏览器,这一段时间可以看做为一次会话
	- 作用
		- http协议是一个无状态协议,它无法记录用户的上次访问内容等.用户在访问过程中难免会产生一些数据,这时候就可以用会话技术将其保存.
- 分类
	- cookie:浏览器段会话
	- session:服务器端会话技术

# cookie #
cookie是有服务器生成,通过response将cookie写会浏览器保存在浏览器上的,在下次浏览器根据一定的规则携带不同的cookie,服务器就可以根据cookie得到想要的数据
- cookie的数据类型
	- 创建
		- cookie是键值对形式的对象.  ` new Cookie(String key,String value) `
	- 保存
		- 通过response的addCookie方法将其发送到浏览器进行保存.
	- 获取
		- 通过request对象getCookie方法进行获取
	- 常用方法
		- setMaxAge(int 秒):设置cookie在浏览器的存活时间,以秒为单位,若设置0,则表示删除该cookie
		- setPatn(String path):设置cookie的路径,当浏览器进行访问的时候会进行路径匹配,将匹配上的cookie携带在request中进行请求
	- 注意
		- cookie不支持中文,可以通过编码的方式进行保存中文

# session #
session是服务端的会话技术,给需要的用户分配session对象,便于保存会话中产生的数据.
session也是依赖于cookie而产生的.

- session的声明周期
	- 创建
		- 在第一次调用request.getsession时创建.
	- 销毁
		- 服务器关闭
		- session超时
			- session有默认超时时间,tomcat中web.xml可以进行配置
			- 也可以通过setMaxInactiveInterval(int 秒)进行设置
		- session被销毁
			- 手动调用invalidate()进行销毁.
- session的会话过程
	- session是保存在服务器的,服务器给浏览器用户对应的session,并将其id保存到浏览器的cookie中,第一次访问服务器的时候,服务器获取session的id
		- 获取到id
			- 根据id在服务器中查找有无对应的session
				- 查到该session:
					- 进行对应操作,保存数据,将session的id再次返回
				- 未找到:
					- 创建一个session,将需要的数据保存,然后返回其id
		- 未获取到id
			- 创建一个session,将需要的数据保存,然后返回其id