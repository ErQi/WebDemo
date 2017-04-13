# CSS #
让页面更加的美观,层叠渲染.
- 作用
	- 渲染
	- 提高工作效率
- 格式
	- 选择器{属性:值,属性1:值1;}
- 后缀名
	- .css 独立的css(样式)文件
- 和html元素整合
	1. 内联样式表:通过标签内的style属性设置样式
	2. 内部样式表:在当前页面中使用样式 例如head中直接书写样式
	2. 外部样式表:导入独立的css文件 例如在head中通过link导入样式
- 选择器
	- id选择器
		-  要求:
			-  html元素必须有id属性且有值 ` <xxx id="id1"></xxx>`
			-  css中通过"#"引入,后面加上id的值  `#id1{...}`
	- class选择器
		- 要求:
			- html元素必须要有class属性且有值 ` <xxx class="cls1"/>`
			- css中通过"."引入,后面加上class的值  `.cls1{...}`
	- 元素选择器
		- 直接用元素(标签)名即可   `xxx{...}`
- 派生选择器
	- 属性选择器: 一种特殊的元素选择器,在元素的基础上进行筛选
		- 要求
		- html元素必须有一个属性不论属性是什么且有值  `<xxx nihao="wohenhao"/>`
		- css中通过下面的方式使用: 元素名[属性="属性值"]{....} 例如:
		- `xxx[nihao="wohenhao"]{....}`
	- 后代选择器
		- 选择器 后代选择器{...}   在满足第一个选择器的条件下找后代的选择器,给满足条件的元素添加样式 例如 `div span{xx}`
- 了解的选择器
	- 锚伪类选择器 修改超链接的属性,属性顺序不能乱
		```
			a:link {color: #FF0000}	   /* 未访问的链接 */
			a:visited {color: #00FF00}	/* 已访问的链接 */
			a:hover {color: #FF00FF}      /* 鼠标移动到链接上 */
			a:active {color: #0000FF}     /* 选定的链接 */
		```
- 选择器使用小结
	- id选择器:针对一个元素(标签)
	- class选择器:一个类的选择器
	- 元素选择器:针对一种元素(标签)
	- 属性选择器:元素选择器的特殊用法
- 注意
	- 同一个选择器,不同样式作用于同一个元素.
		1. 不同的样式会叠加.
		2. 相同的样式最近选择.
	- 多个选择器,作用于同一个元素
		1. 越特殊优先级越高 id优先级最高

- 属性
	- 字体
		- font-family:设置字体(隶书) 设置字体家族
		- font-size:设置字体大小
		- font-style:设置字体风格
	- 文本:改变文本的颜色、字符间距，对齐文本，装饰文本，对文本进行缩进
		- color:文本颜色
		- line-height:设置行高
		- text-decoration: 向文本添加修饰。 none underline
		- text-align:对齐文本
	- 列表:
		- list-style-type:设置列表项的类型 例如:a 1  实心圆 
		- list-style-image:设置图片最为列表项类型 使用的时候使用 url函数  url("/i/arrow.gif");
	- 背景:
		- background-color:设置背景颜色
		- background-image:设置图片作为背景 url
	- 尺寸:
		- width:
		- height:
	- 浮动:
		- float: 可选值 left right
	- 分类:
		- clear:设置元素的两边是否有其他的浮动元素
			- 值为:both 两边都不允许有浮动元素
		- display:设置是否及如何显示元素
			- none 此元素不会被显示
			- block 此元素将显示为块级元素，此元素前后会带有换行符
			- inline 默认。此元素会被显示为内联元素，元素前后没有换行符

- 框模型:
	- 一个元素外面有padding(内边距) border(边框) margin(外边距)
		- padding:元素和边框的距离
		- margin:元素最外层的空白
	- 上面这三个属性都有简写的属性
		- 若设置大小的时候 四个值:顺序 上右下左
			- padding:10px 10px 10px 10px
			- 若只写一个的话 代表四个边使用同一个值  padding:10px
			- 若只写两个个的话 上下用第一个 左右用第二个 padding:10px 20px
			- 若只写三个个的话 上对应10,右对应20,下对应30,左对应20 padding:10px 20px 30px
			- 按照顺序取值,没有值用对面的值 即 上-下 左-右
	- border(边框)
		- 还可以设置颜色 风格
		- 简写属性:
			- border:宽度  风格  颜色;
			- border:5px solid red;
			- solid:实线
			- dashed:虚线
			- double:双实线

# JavaScript #
JavaScript一种直译式脚本语言，是一种动态类型、弱类型、基于原型的语言，内置支持类型。它的解释器被称为JavaScript引擎，为浏览器的一部分

- 组成部分
	- ECMAScript:js基础语法(关键字,运算符,语句,函数等等)
	- BOM:游览器对象模型(弹出窗口,移动关闭和更改游览器窗口等)
	- DOM:文档对象模型(将html/xml转换成DOM树,然后提供操作的接口)
- 作用
	- 修改html页面
	- 修改html样式
	- 完成html界面的事件
- 和html整合方式
	1. 在页面中直接写,代码放在`<script></script>`标签中,该标签一般放在head标签中
	2. 写在独立的js文件中,通过script标签的src属性导入.
- 变量声明
	- var 变量名 = 初始化值;
	- var 变量名;
> var可以省略,但不建议,最后一个变量的分号可以省略,但不建议
	
- 数据类型
	- 原始类型
		1. Null
		2. String
		3. Number
		4. Boolean
		5. Undefined
		
		可以用过**typeof**运算符来判断值或变量的类型,.
		typeof的返回值如下
			```
			undefined - 如果变量是 Undefined 类型的 
			boolean - 如果变量是 Boolean 类型的 
			number - 如果变量是 Number 类型的 
			string - 如果变量是 String 类型的 
			object - 如果变量是一种引用类型或 Null 类型的 
			```
	- 引用类型
- 函数
	- 函数定义格式
	```
		方式1:
			function 函数名(参数){
				函数体;
			}
		方式2:
			var 函数名 = function(参数){
				函数体;
			}
		函数不用声明返回值类型 
		参数不需要加类型
	```
- 事件
	- 基于DOM的常见事件:
		1. onclick: 点击事件
		2. onsubmit: 表单提交事件,加在form表单标签上 onsubmit = "return 函数名()" 函数返回类型为boolean类型,表示是否可以提交.
		3. onload: 元素加载完毕事件.
- 事件和函数的交互
	1. 通过标签事件属性调用函数`<xxx onclick="函数名(参数)"></xxx>`
	2. 通过DOM获取标签对象设置事件函数`var obj=documnet.getElementById("id值");`
- 获取元素的值
	- obj.value;
- 获取标签体中内容
	- obj.innerHTML;
- 运算符
	- 基本等同于java,略有点小不同如 `==` `===`
		- == : 只判断值是否相同.
		- ===: 不仅判断值,同时判断类型.
- 语句
	- 与java基本相同.