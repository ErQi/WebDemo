# xml #
xml是可扩展的标签语言,最初是用于替换不严谨的html文件,但是毫无新特性,替换成本大,最终胎死腹中.
而因为其书写严谨最终变成了常用的配置文件.

- 书写规范
	1. 区分大小写
	2. 有且只有一个根标签
	3. 标签必须关闭
	4. 属性必须用引号包裹
	5. 标签体中的空格或者换行等特殊的字符都是作为数据内容存在的
	6. 特殊字符必须转义
	
- xml组成部分
	- 声明
		- 作用:告诉对方我是xml文件
		- 格式: <?xml .... ?>
		- 要求:必须写在xml文件的第一行,顶格写不能空行
	- 元素
		- 格式: `<`xxx`>` `<`/xxx`>`,`<`xx/`>`
		- 要求:必须关闭,且标签名不能xml Xml XML等等开头,并且标签名中不能出现 空格 或者 : 等特殊字符
	- 属性
		- 格式:`<`xx 属性名="属性值"/`>`
		- 要求:属性值必须用引号包裹
	- 注释
		- 和html一样 `<`!-- 注释内容 --`>`
	- CDATA
		- xml文件的特殊字符必须转义,通过CDATA可以保证数据原样输出
		- 格式: <![CDATA[ 要输出的内容 ]]>
- xml的解析
	- 解析方式
		- sax:逐行解析,只能查询
		- dom:一次性加载文档,形成一个dom树,可以对dom树进行crud操作
	- 解析技术
		- JAXP:sun公司提供支持DOM和SAX开发包
		- JDom:dom4j兄弟
		- jsoup:一种处理html特定解析开发包
		- dom4j:常用的解析包
	- 解析步骤
		- dom4j
			1. 导入jar包
			2. 创建一个核心对象 SAXReader reader = new();
			3. 加载xml文件 Document doc = reader.read(文件);
			4. 获取根节点  Element root=doc.getRootElement();
			5. 根据根节点就可以获取其他的结点了(文本节点,属性结点,元素节点)
				- 获取所有子元素 list<Ekement> list = root.elements();
				- 获去元素的指定属性 String value = root.attributeValue("属性名");
				- 获取标签体 String value = ele.elementText();
		- xpath(依赖于dom4j)
			1. 导入jar包
			2. 加载xml文件
			3. 使用api
			```
				selectNode("表达式");
				selectSingNode("表达式");
			```
				- 表达式写法
				- 标签体匹配
				```
					/根标签/子标签/子标签....
					按照顺序写出标签即可,可以使用/取代上级
					//子标签/子标签...
				```
				- 元素匹配
				```
					元素名[@属性名='属性值']
				```
- XML约束
	- 规定XML中标签出现的顺序,可以出现的属性等,可分为两类
	- DTD约束
		- 格式
			1. <!DOCTYPE 根元素名 [dtd语法]>
			2. <!DOCTYPE 根元素名 SYSTEM "约束文件的位置">
			3. <!DOCTYPE 根元素名 PUBLIC "约束文件的名称" "约束文件的位置">
		- 语法
			- 元素
				- 格式
					- <!DOCTYPE `根元素名` PUBLIC `"约束文件的名称"` `"约束文件的位置"`>
					- 数据类型:
						- `#PCDATA`:普通文本 使用的时候一般用()引起来
					- 包含内容:
						- 该元素下可以出现那些元素 用()引起来
				- 符号
					1. *: 出现任意次
					2. ?: 出现1次或者0次
					3. +: 出现至少1次
					4. |: 或者
					5. (): 分组
					6. ,: 顺序
			- 属性
				- 格式
					- <!ATTLIST 元素名 属性名 属性类型 属性是否必须出现>
				- 属性类型
					- ID:唯一
					- CDATA:普通文本
				- 是否必须出现
					- REQUIRED:必须出现
					- IMPLIED:可以不出现
			- 一个xml文档中只能有一个DTD约束
		- SCHEMA约束
			- 格式:
				- <根标签 xmlns="..." ...>
				- <根标签 xmlns:别名="..." ...>
			- 名称空间:
				- 关联约束文件
				- 规定元素是来源于那个约束文件的
			- 一个xml中可以有多个SCHEMA约束,但是只有一个能不带别名,其余都带别名.SCHEMA本身也是xml文件

# 服务器 #
常见的web服务区

|服务器名称 | 厂商 |特点|
|--|--|--|
|weblogic|oracle|大型收费,支持所有javaee规范|
|webspere|IBM|大型收费,支持所有javaee规范|
|tomcat|apache|中小型免费,支持servlet和jsp规范|

- tomcat目录结构
	- conf:配置文件
	- lib:存放的是tomcat和项目运行时需要的jar包
	- logs:日志 注意:catalina
	- temp:临时文件
	- webapps:存放项目的目录
	- work:存放jsp文件在运行时产生的java和class文件
- 常见web项目结构
	- 项目名称
		- html  css  js  image等目录或者文件
		- WEB-INF
			- lib
			- classes
			- web.xml
- IDEA用Gradle构建结构
	- src 
		- main 
			- bin 脚本库 
			- java java源代码文件 
			- resources 资源库，会自动复制到classes目录里 
			- filters 资源过滤文件 
			- assembly 组件的描述配置（如何打包） 
			- config 配置文件 
			- webapp web应用的目录。WEB-INF、css、js等 
		- test 
			- java 单元测试java源代码文件 
			- resources 测试需要用的资源库 
			- filters 测试资源过滤库 