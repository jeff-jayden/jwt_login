# jwt_login

实现的大概思路：
1.前端传用户名密码，后端接收查询用户，使用 userId和password 生成jwt返回给前端
2.前端存储到localstorage中，访问其他敏感数据，将token放到header请求头中请求
3.后端在拦截器中拿到jwt，进行解码拿到userId 用其查询数据库有没有该用户，有将得到的用户密码加密与前端传来的 token 对比校验，通过就放行
