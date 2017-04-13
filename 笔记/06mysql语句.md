# 数据库 #
本质上就是一个文件系统,通过标准的sql语句对数据进行crud操作.

- 数据库的分类
	- 关系型数据库
		- 存放实体与实体之间的关系的数据库
	- 非关系型数据库
		- 存放的是对象(redis),并非实体关系NO-sql(not only sql)
- 常见的数据库

| 软件名 | 厂商 | 特点 |
| ---- | :----: | :----: |
|mysql|oracle|开源数据库|
|oracle|oracle|大型的收费数据库|
|DB2|IBM|大型收费数据库|
|sqlserver|微软|中大型收费数据库|
|sybase|sybase(powerdesigner)|...|

- sql
> 结构化查询语句,用于管理数据库
- sql的分类
	- DDL:数据定义语言(操作数据库和表)
	- DML:数据操作语言(操作表中记录)
	- DQL:数据查询语言(非官方)
	- DCL:数据控制语言(操作用户,事物,权限)
- sql中的数据类型

|java|mysql|存储大小|
| -- | :--: |:--:|
|byte|tinyint|0-255|
|short|smallint|-2^15 ~ 2^15-1|
|int|int|-2^31 ~ 2^31-1|
|long|bigint|-2^63 ~ 2^63-1|
|char/string|varchar(n) / char(n)|手动设定|
|float/double|float(n1,n2)/double(n1,n2)|n1表述数字长度,n2表示小数点占多少位|
|java.sql.data|data日期| ~ |
|java.sql.time|time时间| ~ |
|java.sql.timestamp|timestamp|时间戳,若值为null会自动保存服务器时间|
|java.sql.clob(长文本)| text(mysql特有)|~|
|java.sql.blob(二进制)| blob|~|
	- varchar和char的区别
		- varchar是可变长度,例如varchar(20),字段只保存了一个'abc',那么只占'abc'所需要的空间,而char(20)不同,char是定长,在创建时就占用了20的空间.

- sql中的查询操作的关键字

|关键字|作用|用法|扩展|
|--|--|--|
|distinct|结果去重复|`select distinct xxx`|~|
| order by (asc/desc)|排序用asc升序,desc降序| `order by 列名 asc/desc`|~|
|where|判断语句,后面接上列和条件|`where 列名 条件 值`|~|
|like|模糊匹配语句| `列名 like '%XX%'` %表示省略一个或多个|~|
|sum()|求和| `select sum(price) from...` 会忽略null值|~|
|avg()|求平均|`select avg(price) from...`  会忽略null值|~|
|avg()|求最大值|`select max(price) from...`  会忽略null值|~|
|avg()|求最小值|`select min(price) from...`  会忽略null值|~|
|count()|求次数|`select count(price) from...`  会忽略null值|~|
|round(i,n)|保留小数|`select round(avg(price),2) from...` 会将值保留两位小数|~|
|gorup by|分组| `gorup by 列名` 将数据根绝列名值进行分组|~|
|having|对分组后的数据进行整理,和where用法相同|`gorup by 列名 having 条件;`
|between|在某某区间|`where xxx BETWEEN 较小值 and 较大值`|~|


- DDL:数据定于语言
	- 操作数据库
		- **create** **database** 数据库名称 `(创建)`
		- **drop** **database** 数据库名称 `(删除)`
		- **show** **database** `(查看所有数据库)`
	- 操作表
		- 创建表
			- **create** **table** 表名 (字段描述, 字段类型 [约束]); 
			```
				create table user ( 
					id int primary key auto_increment, 
					username varchar(20) 
				);
			```
		- 修改表
			- **alter** **table** 表名 ...
			- 修改表名
				- **alter** **table** 旧表名 **rename to** 新表名
				```
					alter table user rename to user10;
				```
			- 添加字段
				- **alter table **表名 **add **[column] 字段描述;
				```	
					// 添加单条
					alter table user10 add sex int;
					
					// 添加多条
					alter table user10 add (
						age int, 
						house varchar(20) );
				```
			- 修改字段名
				- **alter table **表名 **change **字段名称 新字段名称 新字段描述;
				```
					alter table user10 change name username varchar(20);
				```
			- 修改字段描述
				- **alter table** 表名 **modify **字段名 字段类型 [约束]
				```
					alter table user10 modify name int not null;
				```
			- 删除字段
				- **alter table **表名 **drop **字段名;
				```
					alter table user10 drop age;
				```
		- 删除表
			- **drop table **表名;
		- 常用命令
			- **use** 数据库名称: `切换或进入数据库`
			- **show tables** : `查看当前数据库下所有表`
			- **desc **表名 : `查看表结构`
			- **show create table **表名: `查看建表语句`
- DML:数据操作语言
	- 数据插入
		- **insert into **表名 **values**(字段值1,字段值2....);
		```
			insert into user values(1,'er');
		```
			1.  默认插入全部字段
			2.  需要保证values中内容顺序和表结构顺序一致
			3.  字段类型非数字需要加上单引号;
		- **insert into **表名 (字段名1,字段名2....) **values**(字段值1,字段值2....)
		```
			insert into user (id, username) values (2, 'qi');
		``` 
			1. 必须保证values内容顺序和表名后的字段顺序相对应.
	- 数据修改
		-  **update **表名 **set **字段名1=字段值1, 字段名2=字段值2 **where **条件;
		```
			update user set username = 'le' ,id = 928 where id = 1;
		```
	- 数据删除
		- **dalete from **表名 **where **条件;
		```
			delete from user where id = 928;
		```
- DQL:数据查询语言(非官方)
	- 格式
		- select ... from 表名 where 条件 group by 分组字段 having 条件 order by 排序条件 ase|desc
	- 简单查询
	```
		CREATE TABLE products(
			pid INT PRIMARY KEY AUTO_INCREMENT,
			pname VARCHAR(20),
			price DOUBLE,
			pnum INT,
			cno INT,
			pdate TIMESTAMP
		);
		
		INSERT INTO products VALUES (NULL,'泰国大榴莲',98,12,1,NULL);
		INSERT INTO products VALUES (NULL,'新疆大枣',38,123,1,NULL);
		INSERT INTO products VALUES (NULL,'新疆切糕',68,50,2,NULL);
		INSERT INTO products VALUES (NULL,'十三香',10,200,3,NULL);
		INSERT INTO products VALUES (NULL,'老干妈',20,180,3,NULL);
		INSERT INTO products VALUES (NULL,'豌豆黄',20,120,2,NULL);
	```
		- 查询所有
			- select * from products;
		- 只查询商品名和商品价格
			- select pname,price from products;
		- 查询所有商品有哪些价格
			- select distinct price from products;
		- 给所有价格+10元显示
			- select price+10 '特价' from products;
			- 在显示列`price` 后面空格 可以接上别名 '特价'
			- 在确定需要显示的列如price可以直接进行需要的操作.
	- 条件查询
		- 商品按照价格排序
			- select * from products order by price desc;
		- 查询有'新'字的商品,并按价格降序排序
			- select * from products where pname like '%新%' order by price desc;
	- 聚合函数
		- 获得所有商品的价格的总和：
			- select sum(price) from products;
		- 获得商品表中价格的平均数：
			- select round(avg(price),2) from products;
		- 获得商品表中有多少条记录：
			- select count(*) from products;
	- 分组查询
		- 根据cno字段分组，分组后统计商品的个数.
			- select cno, count(*) from products gorup by cno;
		- 根据cno分组，分组统计每组商品的总数量，并且总数量> 200;
- 约束
	- 为了保证数据的有效性和完整性
	- 常用约束有:主键约束(**primary key**) 唯一约束(**unique**)  非空约束(**not null**)
	- 主键约束:被修饰字段唯一非空,且一张表只能有一个主键,这个主键可以包含多个字段
		1. 建表时字段后直接约束(`只能约束一个`)
			```
				create table user (
					id int primary key,
					name varchar(20)
				);
			```
		1. 建表时约束区约束
			```
				create table user (
					id int,
					name varchar(20),
					primary key (id,name)
				);
			```
		1. 建表之后通过修改表结构添加约束
			```
				create table user (
					id int,
					name varchar(20)
				);
				alter table user add primary key (id, name);
			```

		- 当有多个字段使用主键约束时,称为联合主键此时需要所有主键字段均重复才算重复,单一字段重复并不算重复
	- 唯一约束:被修饰字段唯一,对null不起作用
		1. 建表时直接约束
		    ```
				create table user (
					id int unique,
					name varchar(20) unique
				);
			```
		1. 建表时约束区约束
			```
	 			create table user (
					id int,
					name varchar(20),
					unique(id,name)
				);
			```
		1. 建表之后通过修改表结构添加约束
			```
				alter table user add unique (id); // 单一约束
				alter table user add unique(id, name); // 联合约束
			```
		- 如2,在约束区一次添加两个字段和建表之后一次约束两个字段的都成为联合唯一,只有当多个联合字段都相同时才算唯一冲突.
	- 非空约束:被修饰的字符不能为空
			```
				create table user (
					id int not null,
					name varchar(20)
				);
			```
- truncate:清空表
	- truncate 表名:干掉表,并从新创建表
	- 和delete from区别
		- delete 属于DML语句,truncate属于DDL语句
		- delete 逐条删除 truncate直接删除表
		- delete 清空表内部计数不重置 truncate重置计数
- auto_increment:自增
	1. 被修饰字段需要支持自增,一般int
	2. 被修饰的字段必须是一个key 一般是 primary key

- 数据库建立
	- 关系类型
		- 一对多
			- 在开发中,在多表的一方添加一个外键,外键的名称一般为主表的名称_id,字段类型一般和主表的主键的类型保持一致.为了保证数据的有效性和完整性,
			- 在多表的一方添加外键约束(不是必须的,也可以通过java程序来控制)
		- 多对多:
			- 在开发中,一般引入一个中间表,在中间表中存放另外两张表的主键.这样就可以将多对多的关系拆分两个一对多的关系为了保证数据的有效性和完整性,
			- 在中间表上添加两个外键约束(不是必须的,也可以通过java程序来控制)
		- 一对一
			- 在开发中,例如:person idcard
				- 思路1:将两个实体合二为一
				- 思路2:在一个表上将这个表的主键设置成外键且添加外键约束.
	- ER逻辑图
	- ![ER图](https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1489695066766&di=88e26a899267d0e9d8dda6859d12be7d&imgtype=0&src=http%3A%2F%2Fimages2015.cnblogs.com%2Fblog%2F834545%2F201603%2F834545-20160308221519554-120556001.png)
		- 实体用矩形表示
		- 属性用椭圆表示
		- 关系用菱形表示
- 多表查询
	- 内连接
		- 显示内连接
			```
				select a.*,b.* from a join b on 连接条件;
			```
		- 隐士内连接
			```
				select a.*,b.* from a,b where 连接条件;
			```
	- 外连接
		- 左外连接
			```
				select a.*,b.* from a left join b on 连接条件;
			```
			- 展示a表所有数据,根据条件关联查询b表,满足条件展示,不满足null值展示
	- 子查询
		- 将一个查询的结果作为一个临时表
			```
				select a.*,tmp.* from a,(select * from b where 条件) as tmp where 条件;
			```
			- 表 [as] 别名 方式,可以起别名,如上查询结果就是别名 tmp的临时表


## 练习题 ##
```
	CREATE DATABASE mysqltest2;
	USE mysqltest2;

	-- 部门表
	
	CREATE TABLE DEPT(
	    DEPTNO INT PRIMARY KEY,  -- 部门编号
	    DNAME VAR`dept``emp`CHAR(14) ,  -- 部门名称
	    LOC VARCHAR(13) ) ; -- 部门地址

	INSERT INTO DEPT VALUES (10,'ACCOUNTING','NEW YORK');
	INSERT INTO DEPT VALUES (20,'RESEARCH','DALLAS');
	INSERT INTO DEPT VALUES (30,'SALES','CHICAGO');
	INSERT INTO DEPT VALUES (40,'OPERATIONS','BOSTON');
	
	-- 员工表
	
	CREATE TABLE EMP
	    (
		EMPNO INT  PRIMARY KEY,  -- 员工编号
	    ENAME VARCHAR(10),  -- 员工名称
	    JOB VARCHAR(9), -- 工作
	    MGR DOUBLE, -- 直属领导编号
	    HIREDATE DATE,  -- 入职时间
	    SAL DOUBLE, -- 工资
	    COMM DOUBLE, -- 奖金
	    DEPTNO INT, -- 部门号
	    FOREIGN KEY(DEPTNO) REFERENCES DEPT(DEPTNO));
	    
	SELECT * FROM emp;
	    
	  INSERT INTO EMP VALUES
	(7369,'SMITH','CLERK',7902,'1980-12-17',800,NULL,20);
	INSERT INTO EMP VALUES
	(7499,'ALLEN','SALESMAN',7698,'1981-02-20',1600,300,30);
	INSERT INTO EMP VALUES
	(7521,'WARD','SALESMAN',7698,'1981-02-22',1250,500,30);
	INSERT INTO EMP VALUES
	(7566,'JONES','MANAGER',7839,'1981-04-02',2975,NULL,20);
	INSERT INTO EMP VALUES
	(7654,'MARTIN','SALESMAN',7698,'1981-09-28',1250,1400,30);
	INSERT INTO EMP VALUES
	(7698,'BLAKE','MANAGER',7839,'1981-05-01',2850,NULL,30);
	INSERT INTO EMP VALUES
	(7782,'CLARK','MANAGER',7839,'1981-06-09',2450,NULL,10);
	INSERT INTO EMP VALUES
	(7788,'SCOTT','ANALYST',7566,'1987-07-13',3000,NULL,20);
	INSERT INTO EMP VALUES
	(7839,'KING','PRESIDENT',NULL,'1981-11-17',5000,NULL,10);
	INSERT INTO EMP VALUES
	(7844,'TURNER','SALESMAN',7698,'1981-09-08',1500,0,30);
	INSERT INTO EMP VALUES
	(7876,'ADAMS','CLERK',7788,'1987-07-13',1100,NULL,20);
	INSERT INTO EMP VALUES
	(7900,'JAMES','CLERK',7698,'1981-12-03',950,NULL,30);
	INSERT INTO EMP VALUES
	(7902,'FORD','ANALYST',7566,'1981-12-03',3000,NULL,20);
	INSERT INTO EMP VALUES
	(7934,'MILLER','CLERK',7782,'1982-01-23',1300,NULL,10); 
	    
	
	
	-- 工资等级表
	CREATE TABLE SALGRADE
	      ( GRADE INT,  -- 工资等级
	    LOSAL DOUBLE, -- 最低工资
	    HISAL DOUBLE ); -- 最高工资
	INSERT INTO SALGRADE VALUES (1,700,1200);
	INSERT INTO SALGRADE VALUES (2,1201,1400);
	INSERT INTO SALGRADE VALUES (3,1401,2000);
	INSERT INTO SALGRADE VALUES (4,2001,3000);
	INSERT INTO SALGRADE VALUES (5,3001,9999);
```

1. 返回拥有员工的部门名、部门号。(dept,emp)	
	```
		SELECT DISTINCT d.deptno,d.dname FROM dept d,emp e WHERE e.deptno=d.deptno;
	```

2. 工资多于smith的员工信息。
	```
		SELECT e.* FROM emp e, (SELECT * FROM emp WHERE ename = 'smith') a WHERE e.sal > a.sal;
	```
3. 返回员工和其所属领导的姓名。
	```
		SELECT e.ename 员工,l.ename 领导 FROM emp e,emp l WHERE e.mgr=l.empno;
	```
4. 返回雇员的雇佣日期早于其领导雇佣日期的员工及其领导姓名。(在日期类型可以直接比较)
	```
		SELECT e.ename 员工,l.ename 领导 FROM emp e,emp l WHERE e.mgr=l.empno AND e.hiredate < l.hiredate;
	```

5. 返回员工姓名及其所在的部门名称。
	```
	SELECT e.ename 员工,d.dname 部门 FROM emp e, dept d WHERE e.deptno = d.deptno;
	```

6. 返回从事clerk工作的员工姓名和所在部门名称
	```
		SELECT e.ename 员工,d.dname 部门 FROM emp e, dept d WHERE e.deptno = d.deptno AND e.job = 'clerk';
	```
7. 返回部门号及其本部门的最低工资。
	```
		SELECT deptno,MIN(sal) FROM emp GROUP BY deptno;
	```
8. 返回销售部(sales)所有员工的姓名。
	```
		SELECT deptno FROM dept WHERE dname = 'sales';
		SELECT ename FROM emp WHERE deptno = 30;
		SELECT ename 姓名 FROM emp e,(SELECT deptno FROM dept WHERE dname = 'sales') d WHERE e.deptno = d.deptno;
	```
9. 返回工资多于平均工资的员工。
	```
		SELECT AVG(sal) FROM emp;
		SELECT ename 姓名 FROM emp WHERE sal > (SELECT AVG(sal) FROM emp);
	```
10. 返回与SCOTT从事相同工作的员工。
	```
		SELECT ename 姓名 FROM emp WHERE job = (SELECT job FROM emp WHERE ename = 'scott') AND ename != 'scott';
	```
11. 返回与30部门员工工资相同的员工姓名与工资。
	```
		SELECT sal FROM emp WHERE deptno=30; 
		SELECT ename 姓名,sal 工资 FROM emp WHERE sal IN(SELECT sal FROM emp WHERE deptno=30) AND deptno!=30;
	```
12. 返回工资高于30部门所有员工工资水平的员工信息
	```
		SELECT MAX(sal) FROM emp WHERE deptno = 30;
		SELECT * FROM emp WHERE sal > (SELECT MAX(sal) FROM emp WHERE deptno = 30);
	```
13. 返回部门号、部门名、部门所在位置及其每个部门的员工总数。
	```
		SELECT deptno 部门,COUNT(ename) 人数 FROM emp GROUP BY deptno;
		SELECT d.*,e.total FROM dept d LEFT JOIN (SELECT deptno ,COUNT(*) total FROM emp GROUP BY deptno) e ON d.deptno = e.deptno;
	```
14. 返回员工的姓名、所在部门名及其工资。
	```
		SELECT e.ename 姓名, e.sal 工资, d.dname 部门 FROM emp e LEFT JOIN dept d ON e.deptno = d.deptno;
	```
15. 返回员工的详细信息。(包括部门名)
	```
		SELECT e.*, d.dname FROM  emp e LEFT JOIN dept d ON e.deptno = d.deptno;
	```
16. 返回员工工作及其从事此工作的最低工资。
	```
		SELECT job ,MIN(sal) FROM emp GROUP BY job;
	```
17. 返回不同部门经理的最低工资。
	```
		SELECT deptno,MIN(sal) FROM emp WHERE job='manager' GROUP BY deptno;
	```
18.计算出员工的年薪，并且以年薪排序。
	```
		SELECT ename,sal*12+IFNULL(comm,0) yearsal FROM emp ORDER BY yearsal;
	```
19. 返回工资处于第四级别的员工的姓名。
	```
		SELECT * FROM salgrade WHERE grade = 4;
		SELECT e.ename 姓名 FROM emp e ,(SELECT * FROM salgrade WHERE grade = 4) s WHERE e.sal >= s.losal AND e.sal <= s.hisal;

		SELECT e.ename FROM emp e,salgrade s WHERE s.grade=4 AND e.sal BETWEEN s.losal AND s.hisal  
	```


20. 返回工资为二等级的职员名字、部门所在地、和二等级的最低工资和最高工资
	```
		SELECT e.* FROM emp e,salgrade s WHERE s.grade=2 AND e.sal BETWEEN s.losal AND s.hisal  
		SELECT e.ename, d.loc FROM emp e , dept d ,salgrade s WHERE s.grade=2 AND e.sal BETWEEN s.losal AND s.hisal AND e.deptno = d.deptno;

		SELECT e.ename,d.loc,s.losal,s.hisal FROM emp e,salgrade s,dept d WHERE s.grade=2 AND  e.deptno=d.deptno AND e.sal BETWEEN s.losal AND s.hisal  ;

		SELECT e.ename,d.loc,s.losal,s.hisal 
			FROM emp e
			JOIN salgrade s 
				ON s.grade=2 AND e.sal BETWEEN s.losal AND s.hisal 
			JOIN dept d
				ON e.deptno = d.deptno
	```
21. 工资等级多于smith的员工信息。
	```
		SELECT hisal FROM salgrade s, emp e WHERE e.ename ='smith' AND e.sal BETWEEN s.losal AND s.hisal;

		SELECT * FROM emp WHERE sal>1200;

		SELECT e.* FROM emp e ,(SELECT hisal hi FROM salgrade s, emp e WHERE e.ename ='smith' AND e.sal BETWEEN s.losal AND s.hisal) s WHERE sal>s.hi;

		SELECT * FROM emp WHERE sal >(SELECT s.hisal FROM emp e,salgrade s WHERE e.ename='smith' AND e.sal BETWEEN s.losal AND s.hisal)
	```