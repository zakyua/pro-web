1.top.html页面显示登录者昵称、判断是否是自己的空间
  1）显示登录者呢称：${session.userBasic.nickName}
  2) 判断是否是自己的空间：${session.userBasic.id != session.friend.id}
     如果不是期望的效果，首先考虑将两者的id都显示出来

2.点击左侧的好友连接，进入好友空间
  1）根据id获取指定userBasic信息，查询这个userBasic的topicList，然后覆盖friend对应的value
  2）main页面应该展示friend中的topicList而不是userBasic中topicList
  3）跳转后，在左侧(left)中显示整个index页面
     -问题：在left页面显示整个index布局
     -解决：给超链接添加target属性 ：target="_top" 保证在顶层页面显示整个index页面
  4）top.html页面需要修改："欢迎进入${session.friend}"
     top.html页面的返回自己空间的超链接需要修改：
     <a th:href="@{|/user.do?operate=friend&id=${session.userBasic.id} target="_top"}"></a>

3.日志详情页面实现
  1)已知topic的id，需要根据topic的id获取特定的topic
  2）获取这个topic关联的所有回复
  3) 如果某个回复中有主人回复，需要查询出来
        -在TopicController中获取指定的topic
        -具体这个topic中关联多个个Reply，有ReplyService内部实现
  4)获取到这个topic中的author只有id，那么需要在topicService的getTopic方法中封装，在查询topic本身信息时，同时调用userBasicService中的
    获取userBasic方法，给author属性赋值
  5）同理，在reply类中也有author，而且这个author也只有id，那么我们也需要根据id查询得到author，最后设置关联

4.添加回复


5.删除回复
  1)如果回复中有关联的主人回复，需要先删除主人回复
  2）删除回复
     我们在删除回复表记录时，发现删除失败，原因时：在主人回复表中仍然有记录应用待删除的回复这条记录
     如果需要删除主表数据，需要首先删除子表数据

6.删除日志
  1）删除日志，首先要考虑是否有关联的回复
  2）删除回复，首先要考虑是否有关联的主人回复
  3）另外，如果不是自己的空间，则不能删除日志




1.日期和字符串之间的格式化
    /*
    // String-> java.util.Date
    String dateStr = "2012-12-30 12:59:59";
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    try{
    Date date = sdf.parse(dateStr);
    }catch(ParseException e){
    e.printStackTrace();
    }

    //Date->String
    Date date = new Date();
    String str = sdf.format(date);
    */

2.thymeleaf使用#dates这个内置对象
   ${#dates.format(topic.topicDate,'yyyy-MM-dd')}

3.系统启动时，我们访问的页面是：http://localhost:8080/pro23/page.do?operate=page&page=login
  为什么不是  http://localhost:8080/pro23/login.html   ?
  答： 如果是后者，那么属于直接访问静态页面。那么页面上的thymeleaf表达式(标签)浏览器式不能识别的
       我们访问前者的目的其实就是执行 ViewBaseService中的processTemplate()

4.http://localhost:8080/pro23/page.do?operate=page&page=login 访问这个URL，执行的过程是怎么样的？
  答：
  http://   localhost:       8080    /pro23          /page.do                    ?operate=page&page=login
  协议       ServiceIP       port     context root   request.getServletPath()     query String
 1) DispathcherServlet -> urlPattern : *.do  拦截 /page.do
 2) request.getServletPath(); -> /page.do
 3) 解析字符串，将/page.do->page
 4) 拿到page这个字符串，然后去ioc容器(BeanFactory) 中寻找id=page的那个bean对象 ->PageController.java
 5) 获取这个operate的值->page 因此得知，应该执行 PageController中的page()方法
 6) PageController中的page方法定义如下
    public String page(String page){
    return page;
    }
  7) 在queryString：?operate=page&page=login 中获取请求参数，参数名是page，参数值是login
     因此page方法的参数page值会被赋上"login"
     然后return page; , return 给谁？
  8) 因为PageController的page方法是DispathcherServlet通过反射调用的
     method.invoke(....);
     因此，字符串"login"返回给DispathcherServlet
  9)DispathcherServlet接收到返回值，然后处理视图
     当前处理处理视图有两种：1.带前缀redirect:  2.不带前缀
     当前返回"login",不带前缀
     那么执行  super.processTemplate("login",request,response);
  10)此时ViewBaseServlet中的processTemplate方法会执行，效果是
     在"login"上加上前缀"/" (其实就是配置文件中的view-prefix支配的值)
     在"login"上加上后缀".html"(其实就是配置文件中的view-suffix配置的值)
     最后进行服务器的转发


5.目前我们进行javaweb项目开发的”套路“是这样的：
  1.拷贝jar包(myssm)
  2.新建配置文件applicationContext.xml或者可以不叫这个名字，在web.xml中指定文件名
  3.在web.xml文件中配置
    1) 配置前缀和后缀，这样thymeleaf引擎可以根据我们饭就字符串的拼接，在跳转
    <context-param>
            <param-name>view-prefix</param-name>
            <param-value>/</param-value>
    </context-param>
    <context-param>
            <param-name>view-suffix</param-name>
            <param-value>.html</param-value>
    </context-param>

    2)配置监听器要读取的参数，目的是加载IOC容器的配置文件(也就是applicationContext.xml)
      <context-param>
              <param-name>contextConfigLocation</param-name>
              <param-value>applicationContext.xml</param-value>
      </context-param>

4.开发业务的模块
  1)一个具体的业务模块纵向上由几个部分组成：
    -html页面
    -POJO类
    -DAO接口及其实现类
    -Service接口及其实现类
    -Controller控制器组件

  2)如果html页面由thymeleaf表达式，一定不能直接访问，必须要经过PageController
  3)在applicationContext.xml配置DAO、Service、Controller，以及三者之间的依赖关系
  4)DAO实现类中，继承BaseDAO，然后实现具体的接口，需要注意，BaseDAO后面的泛型不能写错
    例如：
    public class UserDAOImpl extends BaseDAO<User> implements UserDAO{}
  5)Service是业务控制类，这一层我们只需要记住一点：
          -业务逻辑我们都封装在Service这一层，不要分散在Controller层，也不要出现在DAO层(保证DAO层的单精度特性)
          -当某一个业务功能需要实现其他的业务模块的时候，尽量去调用别人的Service，而不是深入到其他模块的DAO细节

  6) Controller类编写规则
    ①在applicationContext.xml中配置Controller
    <bean id="user" class="com.atguigu.qqzone.controller.UserController"/>
    那么，用户在前端发请求的时，对应的servletPath就是 /user.do,其中user就是对应此处的bean的id值
    ②在Controller中设计方法时，需要和operate方法名一致
    public String login(String login,String pwd,HttpSession session){
      return "index";
    }
    因此我们需要验证的表单如下：
    <from th:action="@{/user.do}" method="post">
          <input type="hidden" name="operate" value="login"/>
    </from>
    ③在表单中，组件的name属性和Controller中的方法的参数名一致
     <input type="text" name="longId"/>
     public String login(String login,String pwd,HttpSession session)
    ④另外需要强调的是：Controller中的方法的参数不一定是通过请求获取的
    if("request".equals...)else if("response".equals...)else if("session".equals...){
    直接赋值
    }else{
    此处才是从request中请求参数中获取
    request.getParameter("loginId");
     }
  7) DispatcherServlet中的步骤大概分为：
     0.从application作用域中获取IOC容器
     1.解析servletPath,在IOC容器中寻找对应的Controller组件
     2.准备operate指定的方法所要求的参数
     3.调用operate指定的方法
     4.接收到指定operate方法的返回值，对返回值进行处理-视图处理

  8)为什么DispatcherServlet能够从application作用域中获取IOC容器
    ContextLoaderListener在容器启动的时候会执行初始化方法，而它的操作就是：
    1.解析IOC容器的配置文件，创建一个一个的组件，并完成组件之间的依赖关系的注入
    2.将IOC容器保存到application作用域




























