四种授权模式：

一、授权码模式
通过浏览器获取code，请求地址如下：
http://localhost:8843/oauth/authorize?client_id=client&response_type=code
其中client_id的值是数据库表oauth_client_details中的client_id的值

然后再根据code获取token， 可用postman请求，post请求如下：
http://client:secret@localhost:8843/oauth/token?grant_type=authorization_code&code=n7F5yc
其中client是client_id的值，secret对应是oauth_client_details表中client_secret的值,
grant_type表示授权模式，这里authorization_code是授权码模式，code的值是前面获取的code值

二、简化模式
通过浏览器访问
http://localhost:8080/oauth/authorize?response_type=token&client_id=client_3
注：client_3 为简化模式的client_id
得到https://www.baidu.com/#access_token=fe4f9999-db8c-4e24-abf4-9ae63c1052b3&token_type=bearer&expires_in=43199&scope=select
注：https://www.baidu.com/ 是自己配置的redirect_uri， access_token就是我们需要的token

三、客户端模式
通过postman，post请求
http://client_2:c123456@localhost:8843/oauth/token?grant_type=client_credentials

响应
{
    "access_token": "db8870b8-b92d-41e7-a213-ecc3dc03a555",
    "token_type": "bearer",
    "expires_in": 43199,
    "scope": "select"
}

四、密码模式
通过postman，post请求
http://client_1:p123456@localhost:8843/oauth/token?username=admin&password=admin&grant_type=password

{
    "access_token": "19106905-87ed-4df9-a8f3-db131f36a8de",
    "token_type": "bearer",
    "expires_in": 43199,
    "scope": "select"
}
