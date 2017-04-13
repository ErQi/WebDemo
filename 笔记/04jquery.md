# jQuery #
jquert是js的类库,是对常见的方法和对象进行了封装,方便开发.

- jquery和html的整合
	- 通过script标签的src属性导入即可.
- 获取jquery对象
	- $("选择器")：　常用方式，若＄被使用可以使用下一种方式
	- jquery("选择器"):
- dom对象和jquery对象的转换
	- dom >> jquery
		- ${dom对象}
	- jquery >> dom
		- jquery对象[index]
		- jquery对象.get(index)
- 页面加载
	- window.onload = function(){};一个页面中只能使用一次,以最后一个为准
	- $(function(){}) 或 $(document).ready(function(){}),可以在页面中多次使用,是对window.onload的封装.
- 派发事件
	- $("选择器").事件(function(){}),事件与js一样,去掉on即可.
- jquery中的动画效果
	- 隐藏或展示
		- show(毫秒数) , hide(毫秒数)  `从左原点到右原点的缩放效果`
	- 滑入或滑出
		- slideDown(毫秒数), slideUp(毫秒数)
	- 淡入或淡出
		- fadeIn(毫秒数), fadeOut(毫秒数)
- jquery中的选择器
	- 基本选择器
		- $("#id值")
		- $(".class值")
		- $("标签名")
		- 多选,讲上述类型用逗号隔开即可,例如${"#id值,.class值,标签名"}
	- 层次选择器
		- a b:a标签下的所有b标签
		- a>b:a标签先的直属b标签
		- a+b:a标签下的第一个b标签
		- a~b:a标签下的所有b标签
	- 基本过滤选择器
		- :frist 第一个
		- :last 最后一个
		- :odd 索引奇数
		- :even 索引偶数
		- :eq(index) 指定索引
		- :gt(index) 大于指定索引
		- :lt(index) 小于指定索引
	- 内容过滤
		- :has("选择器") 配合其他选择器使用,限定包含指定选择器的元素
	- 可见过滤
		- hidden 不可见源于,一般指 input type = hidden 和样式中的display:none
		- visible
	- 属性过滤
		- [属性名]
		- [属性名="值"]
	- 表单过滤
		- :input  所有表单的子标签
- 属性和css操作
	- 对属性的操作
		- attr():设置或获取元素的属性
			- attr("属性名称") 获取属性值
			- attr("属性名称","值") 设置属性值
			- attr({
				"属性1":"值1",
				"属性2":"值2",
				....	
			  })  设置多个属性及其值.
		- removeAttr("属性名称"):移除指定属性
		- addClass removeClass 添加或移除指定的class属性
		- 在1.6版本之后使用prop替代attr,因为attr有些属性不兼容,例如选择框的checked属性等.
	- 对Css操作:操作元素的style属性:
		- css():获取或设置css样式
			- css("属性名") 获取
			- css("属性名","值") 设置
			- attr({
				"属性1":"值1",
				"属性2":"值2",
				....	
			  })  设置多个属性
	- 获取元素的尺寸
		- width();
		- height();
- 零碎点
	- 数组的遍历
		- jquery对象.each(function(){});
		- $.each(jquery对象,function(){})
	- 标签体内容操作
		- html() 获取或设置html内容,获取的是带标签`<a xxxx>xxx</a>`的源码,设置的时候会把设置的进行html解析
		- text() 只获取标签中的内容,纯本文格式,设置同理.
- 文档操作
	- 内部插入
		- a.append(b)  将b插入到a标签体内末尾
		- a.prepend(b) 将b插入到a标签体内开头
		- a.appendto(b) 将a插入到b标签体内末尾
		- a.prependto(b) 将a插入到b标签体内开头
	- 外部插入
		- a.after(b) 将b放在a标签后面
		- a.before(b) 将b放在a标签前面
		- a.insertAfter(b) 将a插入到b标签后面
		- a.insertBefore(b) 将a放到b标签前面
	- 删除
		- empty() 清空元素
		- remove() 删除元素
- jquery中创建元素
	- $("<标签名>").prop(属性).html(内容);