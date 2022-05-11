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
