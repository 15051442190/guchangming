package cn.kgc.controller;

import cn.kgc.domain.Type;
import cn.kgc.domain.Users;
import cn.kgc.service.TypeService;
import cn.kgc.service.UserService;
import cn.kgc.util.PageUtil;
import cn.kgc.util.UserCondition;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin/")
public class UsersController {
    @Autowired
    private UserService userService;
    @RequestMapping("getUser")
    public Map<String,Object> getType(UserCondition userCondition){
        //调用业务层
        PageInfo<Users> pageInfo = userService.getUser(userCondition);
        //返回datagrid需要的total，rows的数据
        Map<String,Object> map=new HashMap<>();
        map.put("total",pageInfo.getTotal());
        map.put("rows",pageInfo.getList());
        return map;
    }
}
