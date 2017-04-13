# Oracle Database#


- 是甲骨文公司的一款关系数据库管理系统
	- 关系型数据
		- 基于实例的关系模型提出来的数据库
	- 关系模型
		- 用二维表,用行和列的二维表方式保存数据的模型
- Oracle Database的基本概念
	- Oracle 服务器
		- 是一个数据管理系统(RDBMS),它提供开发的,全面的,近乎完整的信息管理
		- 一个有**Oracle数据库**和多个**Oracle实例**组成 
	- Oracle数据库和 Oracle实例
		- Oracle数据库: 
			- 位于硬盘上实际存放数据的文件,这些文件组织在一起,成为一个逻辑整体,即为Oracle数据库,因此在Oracle看来,数据库是指硬盘上文件的逻辑集合,必须要与内存里实例合作,才能对外提供数据管理服务
		- Oracle实例:
			- 位于物理内存里的数据结构,它由一个共享的内存池和多个后台进程组成,共享的内存池可以被所有的进程访问,用户如果要存取数据库(也就是硬盘上的文件)里的数据,必须通过实例才能实现,不能直接读取硬盘上的文件
		- 区别
			- 实例可以操作数据库,在任何时刻一个实例只能与一个数据库关联,大多数情况下,一个数据库上只有一个实例对齐进行操作
	- Oracle的体系结构
		- 客户端 >> ([PGA >> SGA]`[同在内存中]`) >> 数据文件
		- 多个客户端对应多个PGA,多个PGA对应同一个SGA,多个PGA将数据写入到SGA中,最终SGA统一写入到文件之中.称为二阶段提交.
	- 表空间(users)和数据文件
		- 表空间由多个数据文件组成
		- 数据文件只能属于一个表空间
		- 表空间为逻辑概念,数据文件为物理概念
- 拓展
	- 实时应用集群(RAC)
		- 有两台或者两台以上同构计算机及共享存储设备构成，可提供强大的数据库处理能力,这时称为集群
		- 特点
			- 负载均衡
				- 将服务器的压力平均分配到集群中的每一太设备,减轻了服务器的压力
			- 失败迁移
				- 当设备出现错误时,继续当前进度到另一台设备上去,无需回滚
	- SQL优化的原则
		1. 查询时尽量使用列名
			- 因为*号要先解析星号代表多少列.
		1. where解析顺序 右 -->> 左
			- 将重要的判断条件放在右边
		1. where和having都能使用时,尽量使用where
		2. 子查询和多表查询都能使用时,尽量优先使用多表查询,子查询在大部分情况下会将子查询转换成多表查询.
		3. 尽量使用union all,因为union = union all + distion
		4. 尽量不要使用集合运算
		5. 生成执行计划
					
					explan plan for selecct * from emp where deptno = 10;
					select * from table( dhms_xplan.display );	 
		 			可以查看查询过程顺序时间等

	- SQL中null值的问题
		1. 包含null的表达式都为null
		2. null永远!=null
		3. 如果集合中有null不能使用not in,但可以使用in
		4. 排序时null值会默认当做最大值进行排序
		5. 组函数(多行函数),自动过滤空值,可以嵌套滤空函数来屏蔽滤空功能
	- oracle中关键字
		- **distinct**
			- 去掉重复记录,对关键字之后所有列都有用,后面列组合起来判断是否重复
		- **dual**
			- 在Oracle中dual是专有名词,是伪表,只为了满足sql语句
		- **||**
			- 连接符,用于进行字符串的连接,作用于结果上进行拼接.
		- **:=**
			- 赋值运算符
		- between and
			- 区间运算符,表示在某某之间
				1. 含有边界
				2. 小值在前,大值在后
				```
					select * from emp where sal between 1000 and 2000;
				```
		- **in / not in**
			- 在某某之中,也就是集合之中,或者not in集合之外的
				```
					select * from emp where deptno in (10,20)
				```
		- **like**
			- 模糊查询有%(任意字符,任意长度) _(一个长度,任意字符)
				```
					select * from emp where ename like '____';
				```
			- 在查询%和_相关的数据时,使用\进行转义,表示查本身.
		- **order by**
			- 排序关键字,oder by后面可以 列,表达式,别名,序号
				- 列:即指定列进行排序
				- 表达式:根据表达式进行排序
				- 别名:表达式太长,或其他原因,可以使用别名来进行代替列,和表达式
				- 序号:可以使用序号指定查询列中的指定列进行排序
			- 多列排序
				- 当排序条件有多个时,先针对最右侧条件进行排序,然后在作为其他列排序,最后在默认所有列升序,在使用降序时,desc只针对离得最近的列.
			- null值排序
				- 升序排序时无问题,但降序排序时null默认排序在前了,改良方法在desc后添加 **nulls last**;
			- is
				- null值不能用等号判断可以使用 is 或者is not来判断 
	- sqlplus中关键字
		- spool
			- 录屏指令,可以将sqlplus中所有输入输出信息保存起来存放在指定位置
		- /
			- 执行上一次执行的sql命令
		- ed
			- 将上一条sql语句复制到默认文本编辑器里面
	- SQL中的注意点
		- 字符串
			- 字符串可以使select列表中的字符,数字,日期
			- 单引号表示日期和字符.
			- 双引号表示别名
			- 字符串的大小敏感
		- 日期格式
			- 日期格式有严格要求,必须满足其要求,默认的格式是`DD-MON-RR`
			- 修改日期格式
				- alter session|system set NLS_DATE_FORMAT=`yyyy-mm-dd`
				- session 表示在此次会话,system表示在系统,需要使用系统管理员权限修改
		- 查询数据字典
			- select * from v$nls_parameters
			- 可以查询系统的日期,语言,相关符号设置
	- sql和sqlplus
		- sql
			- 一种语言
			- ANSI标准
			- 关键词不能缩写
			- 适用于巨控制数据库中的表的定义信息和表中的数据
		- sql*plus
			- 一种环境
			- Oracle的特性之一
			- 关键字可以缩写
			- 命令不能改变数据库中数据的值
			- 集中运行
		- isql*plus
			- sqlplus的网页版...
			- 
- oracle中的函数
	- 单行函数
		- 字符函数
			- 大小写控制函数
				- LOWER(小写)
				- UPPER(大写)
				- INITCAP(首字母大写)
			- 字符控制函数
				- concat
					- 拼接函数
				- substr
					- 截取函数,共两种形式
						- substr(String a,int b)
						- substr(String a,int b,int c)
				- length/lengthb
					- 字符数/字节数
				- instr(a,b)
					- 在a中查找b出现的位置
				- lpad/rpad
					- lpad(String a,int b,String c)
					- 将a字符串扩展为指定b位,其余用c填充,l再左边填充,r在右边填充
				- trim
					- trim(String a,from String b)
					- 在b字符串中去掉第一个和最后一个a字符串
				- replace
					- replace(String a,String b,String c)
					- 在a字符串中的b字符使用c字符来进行替换
		- 数字函数
			- round(a,b)
				- 以a的b为进行四舍五入
			- trunc(a,b)
				- 以a截断b的指定位数
			- mod(a,b)
				- 对a以b进行求余
		- 日期函数	
			- sysdate
				- sysdate当前时间,可以使用减法运算,结果仍未时间
			- months_between
				- 两个日期详查的月数
			- add_months
				- 在日期上加上指定月 
			- next_day
				- 指定日期的下一天 next_day(sysdate,"星期几")
			- last_day
				- 本月的最后一天
			- round
				- 日期的四舍五入
			- trunc
				- 日期的截断
		- 转换函数
			- 隐式转换
				- oracle自动完成转化成为隐式转换例如:
					- varchar2 or char -->> number
					- varchar2 or char -->> date
					- number -->> varchar2
					- date -->> varchar2 
			- 显示转换
				- to_number
				- to_char
					- 以b的格式将系统当前时间格式化(yyyy-mm-dd hh24:mi:ss)
				- to_date
		- 通用函数
			- nvl(a,b)
				- 若a的值为null,则返回B值
			- nvl2(a,b,c)
				- 若a的值为null,则返回c否则返回b
			- nullif(a,b)
				- 若a,b相等返回空,不然返回a
			- coalesce(a,b,c...n)
				- 从左往右,找到一个不为空的数
	- 多行函数
		- 分组函数
			- avg:求平均
			- count:统计个数
			- max:最大值
			- min:最小值
			- sum:求和
		- 分组数据
			- group by
				- select 列 ... ,组函数 ,from table ,group by 列.
				- 出现在select后面的列必须出现在group by后面,但是group by之后的列不需要一定出现在select中.
				- 分组时先按照左侧分组,在往后继续分组
			- having
				- 在已经分组的情况下,进行过滤
- 多表查询
	- 等值连接
		- 两张表的条件用=
	- 不等值连接
		- 两张表连接条件不是=
	- 外链接
		- where a(+) = b(+)
		- 在条件中加(+)即时外链接,以指定+的的所有信息去匹配b表中数据
	- 自连接
		- 将一张表其多个别名,然后进行匹配
- 层次查询
	- 用于处理单表查询中的重复查询问题
	- 会将结果形成一棵树,需要使用伪列
	- 格式
		```
			select level,列
				from 表
				connect by prior 前条件 = 后条件
				start with 后条件 =/is 起始位置;
		```
- 子查询
	- 不能一步解决的问题,用子查询解决,将上一次查询的结果作为一个表参与查询
	- 注意
		1. 合理的书写风格
		2. 括号
		3. 可以在主查询的where select having from后面使用子查询
		4. 不可以在group by后面使用子查询
		5. 强调from后面的子查询
	 	6. 主查询和子查询可以不是同一张表；只要子查询返回的结果 主查询可以使用即可
		7. 一般不在子查询排序；但top-n分析问题中，必须对子查询排序
		8. 一般先执行子查询，再执行主查询；但相关子查询例外
		9. 单行子查询只能使用单行操作符；多行子查询只能使用多行操作符
SQL> 10. 子查询中的null
SQL> */
- 条件语句
	```
		case 参数 where 条件 then 处理
			      where 条件 then 处理
			end;
		
		case where 判断条件 then 处理
			...
			end;
	```
- 条件函数
	- decode(a,[b,b1],[c,c1]...def)
		- 参数a满足条件b时返回b1,不满足继续向下查找,类似于swith
- 异地容灾
	- 在不同的地域部署服务器,进行数据备份
- 伪列
	- rownum
		- 伪列,显示内容为行号
	- 注意点:
		1. 行号永远按照默认的顺序
		2. 只能使用<  <= 不能使用 > >=
			- oracle的行号是从小到大的,先查找第一行,然后依次往下查,> 和 >= 直接到了指定行,没有索引开头,无法往下查找.
```
SQL> --设置行宽
SQL> set linesize  150
SQL> --设置列宽
SQL> col ename format a8
SQL> col sal for 9999
SQL> /
```

- in 和 not in问题
	- 在in可以看做any也就是or,只要满足一个即可,所以null值不影响判断,
	- 在not in中可以看做all也就是and,需要满足所有,而与null值判断结果都是FALSE,所以不能使用not in
- 什么叫数据的完整性
	- 指的表的约束
- delete和truncate区别
	1. delete是逐条删除,truncate是先摧毁表,再重建
	2. delete是DML(可以回滚)truncate是DDL(不可以回滚)
	3. delete不会释放空间 truncate会
	4. delete可以闪回(flashback) truncate不可以
	5. delete会产生碎片,truncate不会
	- 在mysql中 delete删除比truncate慢
	- 在oracle中 delete删除比truncate快
		- 因为oracle存在undo(数据还原),delete并没有删除数据,只是删除了数据的引用,而truncate是真实删除了数据,所以delete更快
- 什么是碎片
	- oracle中数据插入的位置根据HWM来决定的,HWM是单向线性的,以保存的数据被delete之后占用的位置还在,就会影响查询速度,占用存储空间
- 去掉碎片
	1. alter table 表名 move;
	2. 数据的导出和导入
- oracle中事务的标志
	1. 起始标志:事务中第一条DML语句
	2. 结束标志:
		- 提交
			- 显示:commit
			- 隐式:正常退出exit,DDL,DCL
		- 回滚
			- 显示:rollback
			- 隐式:非正常退出,掉电,宕机
- 事务的控制
	- savepoint:保存点
	- 当事务只是一两个结点出错时,可以使用保存点回滚,不必回滚整个事务.
- oracle中隔离级别
	- oracle中只有三个级别
		- READ COMMITED 读已提交
		- SERIAZABLE	串行化
		- READ ONLY 	只读
- oracle的回收站
	- 查看回收站
		- show recyclebin
	- 清空回收站
		- purge recylebin
	- purge
		- 不经过回收站,直接彻底删除数据
	- 恢复回收站表
		- fiashback table 原表名or**recylebin name** to before drop;
	- 同名表的恢复
		- 当删除了同名的表多次,优先回复最后删除的
	- 改名恢复
		- fiashback table 原表名 to before drop rename to 新表名;
	- **鹳狸猿没有回收站**
- oracle鹳狸猿登陆
	- sqlplus xxxx/xxxx as sysbda
	- 当主机认证完成之后可以输入错误的账号密码同样可以登陆
- oracle的约束级别
	- 列级约束
	- 表级约束
- check约束
	- 限定列的输入值
		- sex number(1) constraint 约束名 check (sex in (0,1))
- foreign key
	- 在子表中定义了一个表级的约束,当需要删除主表数据时
		- 直接删除子表
		- 将子表的依赖外键值置为null
			- `on delete set null`
- 视图 (VIEW)
	- 视图是一种虚表
	- 视图建立在已有表的基础上,视图依赖以建立的这些表称为基表
	- 向视图提供数据内容的语句为SELECT语句,可以将视图ulijiewei存储起来的SELECT语句
	- 视图向用户提供基表数据的另一种表现形式
	- **视图不能提高性能**
	- 优点
		- 简化复杂查询
		- 同样的数据,可以有不同的显示方式
		- 提供数据的相互独立
		- 限制数据访问
- 序列
	- 和mysql的auto increment一样能够进行自增,方便查询
	- 序列在下列情况下会出现裂缝
		- 回滚
		- 系统异常
		- 多表使用同一序列
- 索引
	- 索引的定义
		- 一种独立于表的模式对象,可以储存在与表不同的磁盘或表空间中
		- 索引被删除或损坏,不会对表产生影响,其影响的只是查询速度
		- 索引一旦建立,oracle管理系统会对其进行自动维护,而且由oracle管理系统决定何时使用索引,用户不用再查询语句中指定使用哪个索引
		- 在删除一个表时,所有基于该表的索引会自动被删除
		- 通过指针加速oracle服务器的查询速度
		- 通过快速定位数据的方法,减少磁盘I/O
	- 创建索引的情景
		- 列中数值分布范围很广
		- 列经常在where子句或连接条件中出现
		- 表经常被访问而且数据量很大,访问的数据大概占数据总量的2%到4%
	- 不适合创建索引的情景
		- 表数据小
		- 不经常出现在where子句或连接条件中
		- 查询的数据大于2%到4%
		- 表经常更新(索引目录基于列经常更新)
	- 基于表创建索引
		- create index 索引名 on 表名(列名)
	- oracle索引类型
		- B树索引(默认)
		- 位图索引(矩)