package cn.kgc.controller;

import cn.kgc.domain.House;
import cn.kgc.service.HouseService;
import cn.kgc.util.PageUtil;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController("HouseController1")
@RequestMapping("/admin/")
public class HouseController {
    @Autowired
    private HouseService houseService;

    @RequestMapping("getAllHouse")
    public Map<String,Object> getAllHouse(PageUtil pageUtil){
        PageInfo<House> allByuser = houseService.getAllByuser(pageUtil);
        Map<String,Object> map=new HashMap<>();
        map.put("total",allByuser.getTotal());
        map.put("rows",allByuser.getList());
        return map;
    }
    //更新出租房的审核状态
    @RequestMapping("updatePassState")
    public Map<String,Object> updatePassState(String id,Integer state){
        //调用业务获取数据
        int temp=houseService.passHouse(id,state);
        //封装返回数据
        Map<String,Object> map=new HashMap<>();
        map.put("result",temp);
        return map;
    }
}
