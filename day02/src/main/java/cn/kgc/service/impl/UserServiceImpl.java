package cn.kgc.service.impl;

import cn.kgc.domain.Users;
import cn.kgc.domain.UsersExample;
import cn.kgc.mapper.UsersMapper;
import cn.kgc.service.UserService;
import cn.kgc.util.MD5Utils;
import cn.kgc.util.PageUtil;
import cn.kgc.util.UserCondition;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UsersMapper usersMapper;
    @Override
    public PageInfo<Users> getUser(UserCondition userCondition) {
        PageHelper.startPage(userCondition.getPage(),userCondition.getRows());
        //查询所有
        UsersExample usersExample=new UsersExample();
        //封装条件
        UsersExample.Criteria criteria=usersExample.createCriteria();
        //一个属性一个判断
        if (userCondition.getName()!=null){
            criteria.andNameLike("%"+userCondition.getName()+"%");
        }
        if (userCondition.getTel()!=null){
            criteria.andTelephoneLike("%"+userCondition.getTel()+"%");
        }
        List<Users> usersList = usersMapper.selectByExample(usersExample);
        PageInfo<Users> pageInfo=new PageInfo<>(usersList);

        return pageInfo;
    }

    @Override
    public boolean checkname(String name) {
        UsersExample usersExample=new UsersExample();
        UsersExample.Criteria criteria = usersExample.createCriteria();
        //添加条件
        criteria.andIsadminEqualTo(new Integer(0));//保证是房东用户
        criteria.andNameEqualTo(name);
        List<Users> usersList = usersMapper.selectByExample(usersExample);
            if (usersList!=null&&usersList.size()!=0){
                return false;
            }
        return true;
    }

    @Override
    public int addUser(Users users) {
        //出于系统用户安全考虑_对密码进行加密码
        //使用MD5工具类对密码加密
        String newPassword= MD5Utils.md5Encrypt(users.getPassword());
        users.setPassword(newPassword);
        //设置户东用户
        users.setIsadmin(0);
        int i = usersMapper.insertSelective(users);
        return i;
    }

    @Override
    public Users login(String username, String password) {
        UsersExample usersExample=new UsersExample();
        UsersExample.Criteria criteria = usersExample.createCriteria();
        //添加条件
        criteria.andIsadminEqualTo(0); //保证是房东用户
        criteria.andNameEqualTo(username);//用户名
        criteria.andPasswordEqualTo(MD5Utils.md5Encrypt(password));//密码
        List<Users> usersList = usersMapper.selectByExample(usersExample);
        if (usersList!=null&&usersList.size()>0){
            return usersList.get(0);//返回登录的人

        }
        return null;
    }
}
