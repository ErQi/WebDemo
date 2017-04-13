# jsp #
也就是java服务器页面
- 作用
	- 将内容的生成和信息的展示进行分离,运行在服务器端,本质上就是一个servlet,产生的java文件和class文件保留在tomcat目录中的word目录下.
- jsp脚本
	- <% ... %> java代码片段
	- <%= .. %> 输出表达式
	- <%! ... %> 声明成员
- jsp的指令
	- 作用:声明jsp页面的属性和动作
	- 格式
		- <%@ 指令名称 属性 = "值" 属性 = "值">
	- jsp指令分类
		- page:声明jsp页面属性的一些属性
		- include:静态包含
		- taglib:导入标签库
	- 注意
		- 一个页面中可以出现多个指令
		- 指令可以放在任意位置,一般放在jsp页面最上方
	- page指令
		- 重要属性
			- contentType:设置相应流的变法,及通知浏览器用什么编码打开,设置文件mimetype
			- pageEncoding:设置页面的编码
			- import:导入所需要的包
			- **contentType和pageEncoding的联系**:
				- 若两者都出现的时候,各自使用各自的编码
				- 若只出现一者,两个都是用出现的编码
				- 若都不出现,使用服务器默认的编码
		- 了解属性
			- language:当前jsp页面里可以嵌套的语言(java vb)
			- buffer:设置jsp页面流的缓冲区大小
			- autoFlush:是否自动刷新
			- extends:声明当前jsp的页面继承与哪个类,必须是继承httpservlet及其子类.
			- isElIgnored:是否忽略el表达式
			- errorPage:当前jsp页面出现异常的时候要跳转的jsp页面
			- isErrorPage:当前jsp页面是否是一个错误页面
				- 设置为true,可以使用jsp页面的内置对象和exception.
	- include指令:
		- 静态包含,就是将其他页面或者servlet的内容包含进来,一起进行编译运行,生成java文件
		- 格式:
			- <%@include file = "相对路径或者绝对路径" %>
		- 路径:
			- 相对路径:
				- ./或者不写直接当前路径
				- 上一级路径../
			- 绝对路径:
				- 带协议和主机的绝对路径
				- 不带协议和主机的绝对路径如 /项目名/访问资源
			- 内部路径
				- 不打I协议和主机的绝对路径去掉项目名.
				- 使用场景
					- 请求转发
					- 静态包含
					- 动态包含
	- taglib指令:
		- 导入标签库
		- 格式:
			- <%@taglib prefix="前缀名" uri="名称空间">
		- 使用格式
			- <前缀名:标签 ...>

- jsp的内置对象
	- 在jsp页面中可以直接使用的对象也就是jsp的内置对象,共有九个

|内置对象|类型|
|--|--|
|out		|		JspWriter|
|request	|		HttpServletRequest|
|response	|	HttpServletResponse|
|session	|		HttpSession|
|exception	|	Throwable|
|page		|	Servlet(this)|
|config		|	ServletConfig|
|application|		ServletContext|
|pageContext|		PageContext	|

-  jsp的域对象
	-  Application		整个项目
	-  session			一次会话
	-  request			一次请求
	-  pageContext		一个页面
	-  pageContext的作用
		1. 域对象的基本存储操作
		2. 操作其他域对象
			```
				xxxAttribute(...,int scope);
					scope取值:
					APPLICATION_SCOPE 
					SESSION_SCOPE 
					REQUEST_SCOPE 
					PAGE_SCOPE 
			```
		1. 获取其他内置对象: getXxx();
		2. 便捷查找;findAttribute(String key):
			- 依次从pageContext , request , session , Application一个域中查找对应属性,若查不到返回null
			


- jsp的动作标签
	- <jsp:forward>:请求转发,相当于java中 request.getRequestDispatcher(...).forward(...)
	- <jsp:include>:动态包含

# el #
jsp的内置表达式语言,从jsp2.0开始,用来代替 <%= ... %>
- 作用
	1. 获取域中数据
	2. 执行运算
	3. 获取常见web对象
	4. 调用java方法
- 格式
	- ${el表达式}

- 获取域中数据
	- 注意:
		- 若属性名中出现了 "**. | + | -**"等特殊符号,需要使用scope获取
	- 获取简单数据
		- ${pageScope|requestScope|sessionScope|applicationScope.属性名}
			- 若不指定查找域,直接写属性名会依次从pageContext,request,session,application中查找指定值.
	- 获取其他数据
		- 获取数组中数据
			- ${域中名称[index]}
		- 获取list中的数据
			- ${域中名称[index]}
		- 获取map中的数据
			- ${域中名称.键名}
		- 获取javabean数据
			- ${域中名称.bean属性}

- 执行运行
	- el中可以执行四则运算 关系,逻辑
	- 注意:
		- +:只能进行加法运算,不能向java中一样的执行拼接操作.
		- empty:可以用来判断容器的长队是否为0,还可以判断一个对象是否为null.

- el的内置对象
	- el内置对象一共11个
		- pageScope
		- requestScope
		- sessionScope
		- applicationScope
		- param
		- paramValues
		- header
		- headerValues
		- initParam
		- cookie
		- pageContext
	- 注意:
		- 除了pageContext之外的对象获取的全是map集合
	- 和参数相关的内置对象
		- param
		- paramValues
	- 和请求头相关的内置对象
		- header
		- headerValues
	- 和全局初始化参数相关的内置对象 (wen.xml中的init配置)
		- initParam
	- cookie内置对象
		- cookie
	- pageContext
		- 相当于jsp当中的pageContext内置对象,通常用于在jsp页面获取项目名${pageContext.request.contextPath}

# jst1 #
jsp标准的标签库语言,apache开发的,用来代替java脚本

- 使用步骤
	1. 导入jar包 (jst1.jar和standard.jar)
	2. 在页面上导入标签库
		```
			<%@taglib prefix="" uri=""%>
			例如:
			<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
		```
- jst1的分类
	- core:核心类库
	- fmt:格式化|国际化
- core中标签
	- 主要标签
		- c:if
		- c:forEach
	- 其他标签
		- c:set
		- c:choose
		- c:when
		- c:otherwise
	- c:if 判断标签
		- `<`c:if test= "${el表达式}" `>` 满足的时候输出的内容 `<`/C:if`>`
	- c:forEach 循环标签
		1. 基础循环
			```
				<c:forEach begin="从那里开始" end="到那里结束" step="步长" var="给变量起个名字" varStatus="循环状态变量">
					${i }--${vs.count }--${vs.current }<br>
			 	</c:forEach>
			```
		1. 容器遍历
			```
				//遍历list
				<c:forEach items="${list }" var="n">
					${n }
				</c:forEach>
				
				//遍历map
				<c:forEach items="${map }" var="en">
					${en.key }-- ${en.value }<br/>
				</c:forEach>
		
			```
	