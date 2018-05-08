# Register接口文档

## 接口名称

英文名称：registor

**url**：http://54.201.190.180:8080/wishesmap/register

## 接口含义

用户注册时，系统提取**用户名**和**密码**，若**用户名**不与现有用户名冲突则返回**true**，否则返回**false**。



## 接收数据格式

json字符串，其格式为

```json
{
	"user_name":xxx,
	"user_pass":xxx
}
```

换行与空格对结果无影响

## 返回数据格式

（从postman复制的）

```json
{"success":"false"}
```

## 接口测试用例及结果

**后台未检测json数据格式的正确性！！！**如**{"user_ID":3**，没有}会抛异常。

users数据表

![users数据](/users数据.PNG)

| 用例ID |                           发送数据                           |    预期返回数据     |    实际返回数据     |
| :----: | :----------------------------------------------------------: | :-----------------: | :-----------------: |
|   1    |      {"user_name":"tomcat",<br />"user_pass":"123456"}       | {"success":"false"} | {"success":"false"} |
|   2    |       {"user_name":"java",<br />"user_pass":"123456"}        | {"success":"true"}  | {"success":"true"}  |
|   3    | //第二次发送<br />{"user_name":"java",<br />"user_pass":"123456"} | {"success":"false"} | {"success":"false"} |

