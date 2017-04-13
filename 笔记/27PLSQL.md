# PL/SQL #
- 为什么学习PL/SQL
	1. PL/SQL是对SQL语句扩展,操作oracle效率更高,速度更快.
	2. 储存过程,存储函数,触发器...等五个需要先学习PL/SQL
- 什么是PL/SQL
	- PLSQL是oracle对sql语言过程化扩展,指在sql命令语言中增加了过程处理语句(如分支,循环等),使SQL语言具有过程处理能力
- 语言结构
	- declare
		- 变量说明
			- 变量类型
				- char
				- varchar2
				- date
				- number
				- boolean
				- long
			- 引用型变量
				- **表名.列名%type**:表示该列什么类型该变量就是什么类型,列对应的类型改变,该变量类型同样改变
			- 记录行变量
				- **表名%rowtype**:理解为数组,对应表中的一行中所有列的数据,类似于将一行看做一个对象
			- 定义变量时变量名在前,类型在后
		- 光标申明
		- 例外说明
	- begin
		- DML语句
	- exception
		- 例外处理语句
	- end
- 变量的使用
	- select ename ,sal , into pename ,psal , from emp where empno = 7839;
	- 直接赋值在select语句后面,和查询列顺序对应
- if语句

			if 判断条件 then 操作
				elsif 判断条件 then 操作
				else 操作
			end if;
- 循环语句
			
			loop
				exit when 退出条件
			end loop;
- 光标/游标
	- 定义
		- cursor 光标 is (查询语句)select ename ,sal from emp;
	- 取值
		- fetch 光标 into 对应查询条件的变量
	- 使用
		- open 光标: 打开光标
		- fetch 光标: 从光标中取值
		- close 光标: 关闭光标
		- 光标%notfound: 判断光标内是否有数据
		- 光标%isopen: 判断是否打开
		- 光标%rowcount: 影响的行数
	- 参数
		- cursor 光标(参数 参数类型) is (查询语句)select ename ,sal from emp where deptno = 参数;
		- 打开光标时传入参数即可
			- open 光标(20);
- 异常/例外
	- 系统异常
		- no_data_found:未找到数据
		- too_many_rows:select ... into匹配多行
		- zero_value:被零除
		- value_error:算数或转换错误
		- timeout_on_resource:等待返回超时(分布式数据库)
	- 书写格式

			drclare
			xxxx
			begin
			xxxx
			exception
				when 异常名 then 操作
				when others(剩余所有异常) then 操作
			end;
	- 自定义异常
		- 定义方式
			- 异常名 exception;
		- 处理方式同上
- PMON
	- 进程监视器进程
		- 在用户进程失败时执行进程恢复
			- 清楚数据库缓冲区高速缓存
			- 释放该用户进程使用资源
		- 进食会话,查看是否发生空闲会话超时
		- 在监听程序中动态注册数据库服务
	- 在cursor使用时发生异常,没有finally语句,游标是否就不能正常close?
	- 因为PMON的存在,不用担心,它会去进行关闭释放操作
- 存储过程和存储函数
	- 定义
		- 值存储在数据库中供所有用户程序调用的子程序叫做存储过程.存储函数
	- 区别
		- 存储函数能通过return来返回一个结果值,而存储过程不能返回值
	- 存储过程
		- 用create procedure命令来创建存储过程
		- create [or replace] procedure 过程名(参数列表) AS PLSQL 子程序体
	- 调用方式
		- exec 过程名
		- 其他地方 直接调用过程名即可
	- 包结构
		- 用于返回集合对象,也就是cursor对象
	

				包头
				CREATE OR REPLACE PACKAGE MYPACKAGE AS 

  					type empcursor is ref cursor;
  					procedure queryEmpList(dno in number,empList out empcursor);

				END MYPACKAGE;


				包体
				CREATE OR REPLACE PACKAGE BODY MYPACKAGE AS

  					procedure queryEmpList(dno in number,empList out empcursor) AS
  					BEGIN
   
    					open empList for select * from emp where deptno=dno;
   
  					END queryEmpList;

				END MYPACKAGE;

- 触发器
	- 数据库触发器是一个与表关联的,存储PL/SQL程序,当指定特定的数据操作语句时(Insert,uodate,delete)会自动执行触发器定义的语句序列
	- 作用
		- 数据确认
		- 进行复杂的安全性检查
		- 做审计,跟踪表上所做的操作数据等
		- 数据的备份和同步
	- 语法
				
			create[or replace] trigger 触发器名
			{before|after}
			{delete|insert|update[or列名]}
			on 表名
			[for each row [when(条件)]]
			PLSQL块
		- for each row(行级触发器)