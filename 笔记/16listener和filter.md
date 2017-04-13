# listener #
也就是监听器,用于监听各种变化
- 作用:
	- 监听web中的域对象 `ServletContext` `ServletRequest` `HttpSession`
- 监听内容
	- 域对象的创建和销毁
	- 域对象属性的变化
	- 监听session中javabean的状态 

- 域对象创建和销毁的监听
	- ServletContextListener
	- ServletRequestListener
	- HttpSessionListener
	- 使用方式
		- 实现了接口,然后在web.xml中进行配置,即可监听指定域对象的创建于销毁,web3.1 未知...无网.
- 域对象属性变化(添加 替换 删除)
	- ServletContextAttributeListener
	- ServletRequestAttributeListener
	- HttpSessionAttributeListener
	- 使用方式
		- 同上,实现接口然后在web.xml中配置即可
- session中javabean的监听
	- HttpSessionBindingListener
		- 作用:
			- 监听对应的bean添加到session或从session中移除
		- 使用方式
			- 对应的bean实现该接口即可
	- HttpSessionActivationListener
		- 作用
			- 监听对应的bean从session中序列化到磁盘和从磁盘读取到内存
		- 使用方式
			- 对应的bean实现该接口接口,同时需要实现序列化,不然无法从磁盘中恢复
		- 扩展
			- 存在session中的对象可以序列化到磁盘中减低服务器内存压力,在需要的时候在加载到内存中.
			- 配置方式
				- 在项目下/meta-info下建立一个context.xml配置如下内容
				```
					<Context>
							<!--
								maxIdleSwap	:1分钟 如果session不使用就会序列化到硬盘.
								directory	:directory 序列化到硬盘的文件存放的位置.
							-->
						<Manager className="org.apache.catalina.session.PersistentManager" maxIdleSwap="1">
							<Store className="org.apache.catalina.session.FileStore" directory="directory"/>
							</Manager>
					</Context>
				```

# filter #
也就是过滤器,用于对服务器的各种servlet进行过滤操作.
可以拦截每个请求根据对应的信息作出对应配置,在发送到对应的servlet进行后续处理.

- 常见作用
	- 自动登录
	- 统一编码
	- 过滤关键字
	- ...


- 实现步骤
	1. 实现filter接口
	2. 注册,配置路径
		- web.xml中的配置顺序决定filter的多匹配调用循序,web3.1不使用配置文件时,调用顺序暂未知.


- 生命周期
	- filter和servlet一样,是单实例多线程.
	- 在项目被加载的时候创建filter并调用init实现初始化
	- 请求到达时根据路径调用dofilter方法.
	- 当filter被移除的时候,或服务器关闭时调用destory方法进行销毁

- filterConfig
	- 过滤器配置对象
	- 作用
		- 获取全局管理者
		- 获取当前filter名称
		- 获取当前filter初始化参数

- filter配置
	- url-pattern
		- 拦截路径,和servlet配置一样
	- servlet-name
		- 顾名思义用于针对servlet的name进行匹配
	- dispatcher
		- 针对某种请求
		- 默认是REQUEST,一点配置了,就以配置为准
		- 可用参数:
			- REQUEST:从游览器发送过的来的请求
			- FORWARD:转发过来的请求
			- ERROR:服务器错误发送过来的请求
			- INCLUDE:包含而发送过来的请求
		- 扩展
			- 若不配置dispatcher那么正常的请求可以拦截到,但是转发的请求是无法拦截到的.