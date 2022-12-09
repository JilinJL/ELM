---
title: 饿了么项目接口实现_更新ing
author: jilin
tags: 
  - java
  - javaweb
categories: 
- [JavaWeb]

---



# 饿了么项目接口实现

## 0.初始化

**设置Tomcat地址**

![tomcat](https://gitee.com/jilinJL/my-pictures/raw/master/img/202212032201499.png)

**indexServlet** 一个统一servlet容器

```java
@WebServlet(name="indexServlet", urlPatterns = "/")
public class indexServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        start(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        start(req, resp);
    }

    private void start(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        PrintWriter writer = resp.getWriter();
        //反射获取[path]路径
        //获取路径字符串
        String path = req.getServletPath();
        //获取类名称
        String className = path.substring(1, path.lastIndexOf("/"));
        //获取方法名称
        String methodName = path.substring(path.lastIndexOf("/") + 1);
        System.out.println(className + "," + methodName);
        //通过反射调用类的方法
        try {
            Class<?> clazz = Class.forName("com.jilin.controller." + className);
            //创建类的对象 相当于new了一个
            Object instance = clazz.getConstructor().newInstance();
            Method method = clazz.getMethod(methodName,HttpServletRequest.class);
            //调用方法
            Object object = method.invoke(instance, req);
            writer.write(JSON.toJSONString(object));
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
```

**CORSFilter** 设置跨域过滤器

```java
@WebFilter("/*")
public class CORSFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        // 响应标头指定 指定可以访问资源的URI路径
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        //响应标头指定响应访问所述资源到时允许的一种或多种方法
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE");
        //设置 缓存可以生存的最大秒数
        response.setHeader("Access-Control-Max-Age", "3600");
        //设置  受支持请求标头
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
        // 指示的请求的响应是否可以暴露于该页面。当true值返回时它可以被暴露
        response.setHeader("Access-Control-Allow-Credentials","true");
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
```

**mainFilter** 防止乱码的过滤器,设置utf8

```java
@WebFilter(filterName = "mainFilter", urlPatterns = "*")
public class mainFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }
    public void destroy() {
    }
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest)request;
        HttpServletResponse  resp= (HttpServletResponse)response;
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        chain.doFilter(req, resp);
    }
}
```





## 1.用户操作

接口:

> ### 注册
>
> |  接口地址  | UserController/saveUser                                      |
> | :--------: | ------------------------------------------------------------ |
> |  **参数**  | user:{<br/>	userId:'',        //账号<br/>	password:'',      //密码<br/>	userName:'',      //用户名称<br/>	userSex:1       //性别<br/>} |
> | **返回值** | **int** 影响的行数                                           |
>
> 
>
> ### 账号是否存在
>
> |  接口地址  | UserController/getUserById       |
> | :--------: | -------------------------------- |
> |  **参数**  | userId                           |
> | **返回值** | **int** 账号在数据库中存在的行数 |
>
> ### 登录
>
> |  接口地址  | UserController/getUserByIdByPass                             |
> | :--------: | ------------------------------------------------------------ |
> |  **参数**  | {<br/>    userId:this.userId, //账号<br/>	password:this.password //密码<br/>} |
> | **返回值** | **User对象** <br />Class User {<br/>	private String userId;<br/>	private String password;<br/>	private String userName;<br/>	private Integer userSex;<br/>	private String userImg;<br/>	private Integer delTag;<br/>} |

### 1.1结构

![结构1](https://gitee.com/jilinJL/my-pictures/raw/master/img/202212032159943.png)

**UserController**

```java
public class UserController {

    //注册
    public int saveUser(HttpServletRequest request)
    {
        return 0;
    }
    //账号是否存在
    public int getUserById(HttpServletRequest request)
    {
        return 0;
    }
    //登录
    public User getUserByIdByPass(HttpServletRequest request)
    {
        return null;
    }
}
```

***UserDao*** 接口

```java
public interface UserDao {
    //注册
    public int addUser(User user);
    //账号是否存在
    public int getUserById(User user);
    //登录
    public User userLogin(String userId,String password);
}
```

**UserDaoImpl** 接口实现

```java
public class UserDaoImpl implements UserDao {
    @Override
    public int addUser(User user) {
        return 0;
    }
    @Override
    public int getUserById(User user) {
        return 0;
    }
    @Override
    public User userLogin(String userId, String password) {
        return null;
    }
}
```

***User*** 实体类

```java
package com.jilin.entity;

public class User {
    private String userId;
    private String password;
    private String userName;
    private Integer userSex;
    private String userImg;
    private Integer delTag;

    public User(String userId, String password, String userName, Integer userSex, String userImg, Integer delTag) {
        this.userId = userId;
        this.password = password;
        this.userName = userName;
        this.userSex = userSex;
        this.userImg = userImg;
        this.delTag = delTag;
    }
    public User(){

    }
	//get() and set() 过于冗长 这里不展示
    // ...
}

```

***UserService*** 接口

```java
public interface UserService {

    //注册
    public int addUser(User user);
    //账号是否存在
    public int getUserById(User user);
    //登录
    public User userLogin(String userId,String password);
}
```

**UserServiceImpl** 接口实现

```java
public class UserServiceImpl implements UserService {
    @Override
    public int addUser(User user) {
        return 0;
    }

    @Override
    public int getUserById(User user) {
        return 0;
    }

    @Override
    public User userLogin(String userId, String password) {
        return null;
    }
}
```

### 1.2业务实现

**UserController**

```java
public class UserController {

    //注册
    public int saveUser(HttpServletRequest request)
    {
        User user = new User();
        user.setUserId(request.getParameter("userId"));
        user.setPassword(request.getParameter("password"));
        user.setUserName(request.getParameter("userName"));
        user.setUserSex(Integer.parseInt(request.getParameter("userSex")));
        UserService usersService = new UserServiceImpl();
        return usersService.addUser(user);
    }

    //账号是否存在
    public int getUserById(HttpServletRequest request)
    {
        String userId = request.getParameter("userId");
        UserService usersService = new UserServiceImpl();
        return usersService.getUserById(userId);
    }

    //登录
    public User getUserByIdByPass(HttpServletRequest request)
    {
        String userId = request.getParameter("userId");
        String password = request.getParameter("password");
        UserService usersService = new UserServiceImpl();
        return usersService.userLogin(userId, password);
    }
}
```

**UserDaoImpl**

```java
public class UserDaoImpl implements UserDao {
    @Override
    public int addUser(User user) {
        int ret = 0;
//        1. 导入jar包
//        2. 注册驱动
        try {
//        3. 创建连接
            Connection con = MySqlUtil.getConn();
//        4. 定义SQL语句
//        5. 执行SQL并处理结果
            String sql = "INSERT INTO user(userId, password, userName, userSex)values(?,?,?,?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, user.getUserId());
            pst.setString(2, user.getPassword());
            pst.setString(3, user.getUserName());
            pst.setInt(4, user.getUserSex());
            ret = pst.executeUpdate();
//        6. 释放资源
            MySqlUtil.close(pst,con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ret;
    }

    @Override
    public int getUserById(String userId) {

        try {
            Connection con = MySqlUtil.getConn();
            String sql = "SELECT COUNT(*) as count FROM user where userId = '"+userId+"'";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()) {
                int count = rs.getInt("count");
                return count;
            }
//        6. 释放资源
            MySqlUtil.close(rs,stmt,con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public User userLogin(String userId, String password) {
        try {
            Connection con = MySqlUtil.getConn();
            String sql = "select * from user where userId='"+userId+"' and password='"+password+"'";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()) {
                User user = new User();
                user.setUserId(rs.getString("userId"));
                user.setPassword(rs.getString("password"));
                user.setUserName(rs.getString("userName"));
                user.setUserSex(rs.getInt("userSex"));
                user.setUserImg(rs.getString("userImg"));
                user.setDelTag(rs.getInt("delTag"));

                return user;
            }
//        6. 释放资源
            MySqlUtil.close(rs,stmt,con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
```

**UserServiceImpl**

```java
public class UserServiceImpl implements UserService {
    @Override
    public int addUser(User user) {
        UserDao dao = new UserDaoImpl();
        return dao.addUser(user);
    }

    @Override
    public int getUserById(String userId) {
        UserDao dao = new UserDaoImpl();
        return dao.getUserById(userId);
    }

    @Override
    public User userLogin(String userId, String password) {
        UserDao dao = new UserDaoImpl();
        return dao.userLogin(userId, password);
    }
}
```



<hr>