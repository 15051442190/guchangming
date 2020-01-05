package cn.kgc.service;

import cn.kgc.domain.Type;
import cn.kgc.domain.Users;
import cn.kgc.util.PageUtil;
import cn.kgc.util.UserCondition;
import com.github.pagehelper.PageInfo;

public interface UserService {
   /**
    * 查询区域带分页
    *
    * @param userCondition 包含查询条件 page接收页码  rows接收页大小
    * @return
    */
   public PageInfo<Users> getUser(UserCondition userCondition);

   /**
    *
    * @param name
    * @return
    */
   public boolean checkname(String name);

   /** 注册用户
    *
    * @param users 用户实体
    * @return 影响行数
    */
   public int addUser(Users users);

   /**
    * 登录验证
    * @param username 用户名
    * @param password 密码
    * @return 返回实体
    */
   public Users login(String username,String password);
}
