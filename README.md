<hr>



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





## 1.用户-User

### 1.1接口

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

### 

![结构1](https://gitee.com/jilinJL/my-pictures/raw/master/img/202212032159943.png)

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

	//constructor()
	//get() and set() 过于冗长 这里不展示
    // ...
}

```

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

#### **UserController**

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

#### **UserDaoImpl**

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

#### **UserServiceImpl**

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

## 2.商家-Business

### 2.1接口

> ### 查询商家
>
> |  接口地址  | BusinessController/listBusinessByOrderTypeId |
> | :--------: | -------------------------------------------- |
> |  **参数**  | orderTypeId:this.orderTypeId //点餐分类      |
> | **返回值** | **List<Business\>** 商家集合                 |
>
> 
>
> ### 根据businessId查询商家信息
>
> |  接口地址  | BusinessController/getBusinessById  |
> | :--------: | ----------------------------------- |
> |  **参数**  | businessId:this.businessId //商家Id |
> | **返回值** | **Business**对象                    |

### 2.2业务实现

***Business***实体

```java
package com.jilin.entity;

public class Business {
    private Integer businessId;
    private String businessName;
    private String businessAddress;
    private String businessExplain;
    private String businessImg;
    private Integer orderTypeId;
    private Double starPrice;
    private Double deliveryPrice;
    private String remarks;
    
     //constructor()
	//get() and set()
}
```



#### **BusinessController**

```java
public class BusinessController {
    //查询商家
    public List<Business> listBusinessByOrderTypeId(HttpServletRequest request){
        BusinessService BusServ = new BusinessServiceImpl();
        int type = Integer.parseInt(request.getParameter("orderTypeId"));
        List<Business> list = BusServ.listBusiness(type);
        return list;
    }
    //根据id查询商家信息
    public Business getBusinessById(HttpServletRequest request){
        int Id = Integer.parseInt(request.getParameter("businessId"));
        BusinessService BusServ = new BusinessServiceImpl();
        return BusServ.getBusinessById(Id);
    }

}
```

#### *BusinessDao*

```java
public interface BusinessDao {

    //查询商家列表
    List<Business> listBusiness(Integer orderTypeId);

    //根据id查询商家信息
    Business getBusinessById(Integer businessId);
}
```

#### **BusinessDaoImpl**

```java
public class BusinessDaoImpl implements BusinessDao {

    //获取分类商家
    @Override
    public List<Business> listBusiness(Integer orderTypeId) {
        //System.out.println("获取列表成功");
        List<Business> busList = new ArrayList<>();
        try{
            Connection con = MySqlUtil.getConn();
            String sql = "Select * from business where orderTypeId = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, String.valueOf(orderTypeId));
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Business bus = new Business();
                bus.setBusinessId(rs.getInt("orderTypeId"));
                bus.setBusinessName(rs.getString("businessName"));
                bus.setBusinessAddress(rs.getString("businessAddress"));
                bus.setBusinessExplain(rs.getString("businessExplain"));
                bus.setBusinessImg(rs.getString("businessImg"));
                bus.setStarPrice(rs.getDouble("starPrice"));
                bus.setDeliveryPrice(rs.getDouble("deliveryPrice"));
                bus.setRemarks(rs.getString("remarks"));
                busList.add(bus);
            }
            MySqlUtil.close(rs,pst,con);
        }catch (Exception e){
            e.printStackTrace();

        }
        return busList;
    }

    //根据id获取信息
    @Override
    public Business getBusinessById(Integer businessId) {
        try {
        //System.out.println("搜索成功");
        Connection con = MySqlUtil.getConn();
        String sql = "SELECT * FROM business where businessId = ?";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setString(1, String.valueOf(businessId));
        ResultSet rs = pst.executeQuery();
        while(rs.next()) {
            Business bus = new Business();
            bus.setBusinessId(rs.getInt("orderTypeId"));
            bus.setBusinessName(rs.getString("businessName"));
            bus.setBusinessAddress(rs.getString("businessAddress"));
            bus.setBusinessExplain(rs.getString("businessExplain"));
            bus.setBusinessImg(rs.getString("businessImg"));
            bus.setStarPrice(rs.getDouble("starPrice"));
            bus.setDeliveryPrice(rs.getDouble("deliveryPrice"));
            bus.setRemarks(rs.getString("remarks"));
            return bus;
        }

            MySqlUtil.close(rs,pst,con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
```

#### *BusinessService* 

```java
public interface BusinessService {
    //根据id查询商家信息
    List<Business> listBusiness(Integer type);
    //查询商家
    Business getBusinessById(Integer id);
}
```

#### **BusinessServiceImpl**

```java
public class BusinessServiceImpl implements BusinessService {

    @Override
    public List<Business> listBusiness(Integer type) {
        BusinessDao dao = new BusinessDaoImpl();
        return dao.listBusiness(type);
    }

    @Override
    public Business getBusinessById(Integer id) {
        BusinessDao dao = new BusinessDaoImpl();
        return dao.getBusinessById(id);
    }
}

```

## 3.购物车-Cart

### 3.1接口

> ### 添加到购物车
>
> |  接口地址  | CartController/saveCart                                      |
> | :--------: | ------------------------------------------------------------ |
> |  **参数**  | {<br />businessId:this.businessId, //商家id<br />userId:this.user.userId,  //用户id<br />foodId:this.foodArr[index].foodId  //食品id<br />} |
> | **返回值** | **int** 影响的行数                                           |
>
> ### 删除购物车
>
> |  接口地址  | CartController/removeCart                                    |
> | :--------: | ------------------------------------------------------------ |
> |  **参数**  | {<br />businessId:this.businessId, //商家id<br />userId:this.user.userId,  //用户id<br />foodId:this.foodArr[index].foodId  //食品id<br />} |
> | **返回值** | **int** 影响的行数                                           |
>
> ### 修改购物车
>
> |  接口地址  | CartController/updateCart                                    |
> | :--------: | ------------------------------------------------------------ |
> |  **参数**  | {<br />businessId:this.businessId, //商家id<br />userId:this.user.userId,  //用户id<br />foodId:this.foodArr[index].foodId  //食品id<br />quantity:this.foodArr[index].quantity+num //数量<br />} |
> | **返回值** | **int** 影响的行数                                           |
>
> ### 查询购物车
>
> |  接口地址  | CartController/listCart         |
> | :--------: | ------------------------------- |
> |  **参数**  | userId：this.user.userId //账号 |
> | **返回值** | **List<Cart\>** 购物车列表      |

### 3.2业务实现

***cart***实体类

```java
public class Cart {
    private Integer cartId;
    private Integer foodId;
    private Integer businessId;
    private String userId;
    private Integer quantity;
    private Food food;
    private Business business;
	//constructor()
    //get() & set()
}
```

#### **CartController**

```java
public class CartController {

    //购物车列表
    public List<Cart> listCart(HttpServletRequest request){
        String userId = request.getParameter("userId");
        CartService cartserv = new CartServiceImpl();
        return cartserv.listCart(userId);

    }
    //添加到购物车
    public int saveCart(HttpServletRequest request){
        Cart cart = new Cart();
        cart.setCartId(Integer.valueOf(request.getParameter("cartId")));
        cart.setFoodId(Integer.valueOf(request.getParameter("foodId")));
        cart.setBusinessId(Integer.valueOf(request.getParameter("businessId")));
        cart.setUserId(request.getParameter("userId"));
        CartService cartserv = new CartServiceImpl();
        return cartserv.addCart(cart);
    }
    //修改购物车
    public int updateCart(HttpServletRequest request){
        Cart cart = new Cart();
        cart.setFoodId(Integer.valueOf(request.getParameter("foodId")));
        cart.setBusinessId(Integer.valueOf(request.getParameter("businessId")));
        cart.setUserId(request.getParameter("userId"));
        cart.setQuantity(Integer.valueOf(request.getParameter("quantity")));
        CartService cartserv = new CartServiceImpl();
        return cartserv.updateCart(cart);
    }
    //删除购物车
    public int removeCart(HttpServletRequest request){
        Cart cart = new Cart();
        cart.setFoodId(Integer.valueOf(request.getParameter("foodId")));
        cart.setBusinessId(Integer.valueOf(request.getParameter("businessId")));
        cart.setUserId(request.getParameter("userId"));
        CartService cartserv = new CartServiceImpl();
        return cartserv.updateCart(cart);
    }
}
```

#### *CartDao*

```java
public interface CartDao {
    //查询购物车
    List<Cart> listCart(String userId);
    //添加到购物车
    int addCart(Cart cart);
    //修改购物车
    int updateCart(Cart cart);
    //删除购物车
    int removeCart(Cart cart);
}
```

#### **CartDaoImpl**

```java
public class CartDaoImpl implements CartDao {
    @Override
    public List<Cart> listCart(String userId) {
        List<Cart> list = new ArrayList<>();
        try{
            Connection con = MySqlUtil.getConn();
            //cartQuery 多表查询
            String sql = "Select * from cart,food,business where cart.foodId = food.foodId and cart.businessId = business.businessId and cart.userId = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, userId);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                Cart cart = new Cart();
                cart.setCartId(rs.getInt("cartId"));
                cart.setUserId(rs.getString("userId"));
                cart.setQuantity(rs.getInt("quantity"));
                cart.setFoodId(rs.getInt("foodId"));
                cart.setBusinessId(rs.getInt("businessId"));

                //TODO 把Food和Business填上
                cart.setFood(new Food(rs.getInt("foodId"),rs.getString("foodName"),rs.getString("foodExplain"),rs.getString("foodImg"),rs.getDouble("foodPrice"),rs.getInt("businessId"),rs.getString("remarks")));
                cart.setBusiness(new Business(rs.getInt("businessId"),rs.getString("businessName"),rs.getString("businessAddress"),rs.getString("businessExplain"),rs.getString("businessImg"),rs.getInt("orderTypeId"),rs.getDouble("starPrice"),rs.getDouble("deliveryPrice"),rs.getString("remarks")));

                list.add(cart);
            }
        MySqlUtil.close(rs,pst,con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public int addCart(Cart cart) {
        int ret=0;
        try{
            Connection con = MySqlUtil.getConn();
            String sql ="INSERT INTO cart(businessId, userId, foodId)values(?,?,?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, String.valueOf(cart.getBusinessId()));
            pst.setString(2, cart.getUserId());
            pst.setString(3, String.valueOf(cart.getFoodId()));
            ret = pst.executeUpdate();

            MySqlUtil.close(pst, con);
            return ret;


        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int updateCart(Cart cart) {
        int ret;
        try{
            Connection con = MySqlUtil.getConn();
            String sql = "UPDATE cart SET quantity = ? where businessId = ? and userId = ? and foodId =? and quantity = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1,cart.getQuantity());
            pst.setInt(2,cart.getBusinessId());
            pst.setString(3,cart.getUserId());
            pst.setInt(4,cart.getFoodId());
            ret = pst.executeUpdate();

            MySqlUtil.close(pst, con);
            return ret;

        }catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int removeCart(Cart cart) {
        int ret;
        try{
            Connection con = MySqlUtil.getConn();
            String sql = "DELETE FROM cart WHERE businessId = ? and userId = ? and foodId = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1,cart.getBusinessId());
            pst.setString(2, cart.getUserId());
            pst.setInt(3,cart.getFoodId());
            ret = pst.executeUpdate();

            MySqlUtil.close(pst, con);
            return ret;

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
```

#### *CartService*

```java
public interface CartService {
    //查询购物车
    List<Cart> listCart(String userId);
    //添加到购物车
    int addCart(Cart cart);
    //修改购物车
    int updateCart(Cart cart);
    //删除购物车
    int removeCart(Cart cart);
}
```

#### **CartServiceImpl**

```java
public class CartServiceImpl implements CartService {
    @Override
    public List<Cart> listCart(String userId) {
        CartDao dao = new CartDaoImpl();
        return dao.listCart(userId);
    }
    @Override
    public int addCart(Cart cart) {
        CartDao dao = new CartDaoImpl();
        return dao.addCart(cart);
    }
    @Override
    public int updateCart(Cart cart) {
        CartDao dao = new CartDaoImpl();
        return dao.addCart(cart);
    }
    @Override
    public int removeCart(Cart cart) {
        CartDao dao = new CartDaoImpl();
        return dao.removeCart(cart);
    }
}
```

<hr>

## 4.食品-Food

### 4.1接口

> ### 根据businessId查询所属食品信息
>
> |  接口地址  | FoodController/listFoodByBusinessId |
> | :--------: | ----------------------------------- |
> |  **参数**  | businessId:this.businessId //商家Id |
> | **返回值** | **List<Food\>** 食品集合            |

### 4.2业务实现

***Food***实体类

```java
public class Food {
    private Integer foodId;
    private String foodName;
    private String foodExplain;
    private String foodImg;
    private Double foodPrice;
    private Integer businessId;
    private String remarks;
	//constructor
    //get() & set()
}
```

#### **FoodController**

```java
public class FoodController {
    public List<Food> listFoodByBusinessId(HttpServletRequest request){
        String businessId = request.getParameter("businessId");
        FoodService service = new FoodServiceImpl();
        return service.listFoodByBusinessId(businessId);
    }
}
```

#### *FoodDao*

```java
public interface FoodDao {
    List<Food> listFoodByBusinessId(String businessId);
}
```

#### **FoodDaoImpl**

```java
public class FoodDaoImpl implements FoodDao {
    @Override
    public List<Food> listFoodByBusinessId(String businessId) {
        List<Food> list = new ArrayList<>();
        try{
            Connection con = MySqlUtil.getConn();
            //cartQuery
            String sql = "Select * from food where businessId = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, businessId);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                Food food = new Food();
                food.setFoodId(rs.getInt("foodId"));
                food.setFoodName(rs.getString("foodName"));
                food.setFoodExplain(rs.getString("foodExplain"));
                food.setFoodImg(rs.getString("foodImg"));
                food.setFoodPrice(rs.getDouble("foodPrice"));
                food.setBusinessId(Integer.valueOf(businessId));
                food.setRemarks(rs.getString("remarks"));

                list.add(food);
            }
            MySqlUtil.close(rs,pst,con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
```

#### *FoodService*

```java
public interface FoodService {
    List<Food> listFoodByBusinessId(String businessId);
}
```

#### **FoodServiceImpl**

```java
public class FoodServiceImpl implements FoodService {
    @Override
    public List<Food> listFoodByBusinessId(String businessId) {
        FoodDao dao = new FoodDaoImpl();
        return dao.listFoodByBusinessId(businessId);
    }
}
```

## 5.地址-Address