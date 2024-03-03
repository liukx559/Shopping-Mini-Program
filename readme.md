# 扫盲笔记
## lombok
作用：提高开发效率，通过注解形式使javabean生成get、set、有参数、无参数、toString等方法，无需手动实现。
## Mybatis
数据库管理简化开发工具。sql和java编码分开，功能边界清晰，一个专注业务，一个专注数据
### 使用
#### 1搭建数据库

#### 2新建项目——添加依赖

#### 3配置Mybatis——xml文件

#### 4编写Mybatis工具类

#### 5实体类

#### 6Dao接口
就是Mapper.java,添加扫描到mapper
## 报错
### If you want an embedded database (H2, HSQL or Derby), please put it on the classpath. 
![img.png](img/img.png)
### If you have database settings to be loaded from a particular profile you may need to activate it (no profiles are currently active).
将yml文件换成application.properties文件才能激活。
### mybatis plus报Invalid bound statement (not found)
参考 https://blog.csdn.net/wwrzyy/article/details/86034458 保留一个配置就可以了，这俩配置冲突。

### MySQL JDBC Error: Public Key Retrieval is not allowed
参考 https://www.codejava.net/java-se/jdbc/fix-error-public-key-retrieval-is-not-allowed 

jdbc:mysql://localhost:3306/xxx?allowPublicKeyRetrieval=true&useSSL=false