# jdbc #
jdbc是oracle公司指定的一套用于java操作数据库的规范(接口).
- jdbc的作用
	1. 连接数据库
	2. 发送sql语句
	3. 处理结果

- 连接池
	- 为了提高jdbc的操作效率.
	- 必须实现 java.sql.DataSource 接口
		- 获取连接:	getConnection();
		- 归还连接:  conn.close();
- 常用的连接池:
	- DBCP:apache组织
		- 使用步骤:
			1. 1.导入jar包(commons-dbcp-1.4.jar和commons-pool-1.5.6.jar)
			2. 使用api
				- a.硬编码
				```
					//创建连接池
					BasicDataSource ds = new BasicDataSource();
					
					//配置信息
					ds.setDriverClassName("com.mysql.jdbc.Driver");
					ds.setUrl("jdbc:mysql:///day07");
					ds.setUsername("root");
					ds.setPassword("1234");
				```
				- b.配置文件
				```
					实现编写一个properties文件
					//存放配置文件
					Properties prop = new Properties();
					prop.load(new FileInputStream("src/dbcp.properties"));
					//设置
					//prop.setProperty("driverClassName", "com.mysql.jdbc.Driver");
					
					//创建连接池
					DataSource ds = new BasicDataSourceFactory().createDataSource(prop);
				```
	- C3P0:(有自动回收空闲连接的功能)
		- 使用步骤:
			1. 导入jar包(c3p0-0.9.1.2.jar)
			2. 使用api
				- a.硬编码(不推荐)
				```
					new ComboPooledDataSource()
				```
				- b.配置文件
				```
					配置文件的名称:c3p0.properties 或者 c3p0-config.xml
					配置文件的路径:src下
				
					编码只需要一句话
						new ComboPooledDataSource()//使用默认的配置
						new ComboPooledDataSource(String configName)//使用命名的配置 若配置的名字找不到,使用默认的配置
				```

- dbutils:(是apache组织的一个工具类,jdbc的框架,更方便我们使用)
	- 使用步骤:
		1. 导入jar包(commons-dbutils-1.4.jar)
		2. 创建一个queryrunner类
			- queryrunner作用:操作sql语句
			- 构造方法: new QueryRunner(Datasource ds);
		3. 编写sql
		4. 执行sql
			- query(..):执行r操作
			- update(...):执行cud操作
			
	- 核心类或接口
		- QueryRunner
			- 作用:操作sql语句
			- 构造器: new QueryRunner(Datasource ds);
		- 注意:
			- 底层帮我们创建连接,创建语句执行者 ,释放资源.
		- 常用方法:
			- query(..):
			- update(..):
	
		- DbUtils:释放资源,控制事务 类
			- closeQuietly(conn):内部处理了异常
			- commitAndClose(Connection conn):提交事务并释放连接
			
		- ResultSetHandler:封装结果集 接口
			1. ArrayHandler, 将查询结果的第一条记录封装成数组,返回
			2. ArrayListHandler, 将查询结果的每一条记录封装成数组,将每一个数组放入list中返回
			3. BeanHandler, 将查询结果的第一条记录封装成指定的bean对象,返回
			4. BeanListHandler, 将查询结果的每一条记录封装成指定的bean对象,将每一个bean对象放入list中 返回.
			5. ColumnListHandler, 将查询结果的指定一列放入list中返回 
			6. MapHandler, 将查询结果的第一条记录封装成map,字段名作为key,值为value 返回
			7. MapListHandler, 将查询结果的每一条记录封装map集合,将每一个map集合放入list中返回
			8. ScalarHandler,针对于聚合函数 例如:count(*) 返回的是一个Long值