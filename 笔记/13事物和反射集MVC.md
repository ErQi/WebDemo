# mvc #
mvc是一种编程思想,一种便于解耦合便于扩展修改的变成思想.
- web中的mvc
	- M:对数据的各种操作,也就是dao层
	- V:对信息的展示也就对应jsp页面
	- C:控制调用各种操作,核心控制,对应servlet

# 事务 #
- 就是将多个单元操作合并称为一个整体,整体不可分可,要么全部成功,要么全部失败.
- mysql中的事务
	- mysql中事务是默认自动提交,一条sql语句就是一个事务
	- 开启手动事务方式:
		- 关闭事务
			 `set autocommit = off `
		- 手动开启事务
			```
			start transaction; --开启事务
			commit; --提交事务
			rollback;--回滚事务
			```
- 事务的特点**ACID**
	- 原子性: 事务里的操作单元不可分割,要么全部成功,要么全部失败.
	- 一致性: 事务执行前后,业务状态和其他业务状态保持一致.(数据的准确)
	- 隔离性: 一个事务最好不要受到其他事务的影响
	- 持久性: 一旦事务提交或回滚,这个状态都要持久化到数据库中
- 隔离性可能导致的问题
	- 脏读: 在一个事务中读取到另一个事务没有提交的数据
	- 不可重复度: 在一个事务中,两次查询结果不一致(针对update操作)
	- 虚读(幻读): 在一个事务中,两次查询结果不一致(针对insert操作)
- 设置数据库隔离界别解决问题

|级别|内容|结果|
|--|--|--|
|read uncommitted  |	读未提交	|上面的三个问题都会出现
|read committed  	|读已提交	|可以避免脏读的发生
|repeatable read	|	可重复读	|可以避免脏读和不可重复读的发生
|serializable		|串行化		|可以避免所有的问题
- 四种隔离级别的效率
	- read uncommitted>read committed>repeatable read>serializable
- 四种隔离级别的安全性
	- read uncommitted<read committed<repeatable read<serializable
- 开发中决定不允许脏读情况
	- mysql中默认级别:repeatable read
	- oracle中默认级别:read committed