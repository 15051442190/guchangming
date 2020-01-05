package cn.kgc.controller;

import cn.kgc.domain.District;
import cn.kgc.service.DemoService;
import cn.kgc.util.PageUtil;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/")
public class DistrictController {
    @Autowired
    private DemoService demoService;
    @RequestMapping("getDistrict")
    public Map<String,Object> getDistrict(PageUtil pageUtil){
        //调用业务层
        PageInfo<District> pageInfo = demoService.getDistrict(pageUtil);
        //返回datagrid需要的total，rows的数据
        Map<String,Object> map=new HashMap<>();
        map.put("total",pageInfo.getTotal());
        map.put("rows",pageInfo.getList());
        return map;
    }
    @RequestMapping("addDistrict")
    public Map<String,Object> addDistrict(District district){
        Map<String,Object> map=new HashMap<>();
        try{
            //调用业务层
            int i = demoService.addDistrict(district);
            //返回datagrid需要的total，rows的数据

            map.put("i",i);

        }catch (Exception e){
            e.printStackTrace();
    }
    return map;
  }
    @RequestMapping("updateDistrict")
    public District updateDistrict(Integer id){
        try {
            //调用业务层
            District districtById = demoService.getDistrictById(id);
            return districtById;
        }catch (Exception e){
            e.printStackTrace();
        }
       return null;
    }
    @RequestMapping("updateDistrictByid")
    public Map<String,Object> updateDistrictByid(District district){
        Map<String,Object> map=new HashMap<>();
        try {
            //调用业务层
            int i = demoService.updateDistrict(district);
            map.put("i",i);
        }catch (Exception e){
            e.printStackTrace();
        }
        return map;
    }
    @RequestMapping("delDistrictByid")
    public Map<String,Object> delDistrictByid(Integer id){
        Map<String,Object> map=new HashMap<>();
        try {
            //调用业务层
            int i = demoService.deletDistrict(id);
            map.put("i",i);
        }catch (Exception e){
            e.printStackTrace();
        }
        return map;
    }
    //批量删除区域
    //前台传递数据的格式:ids=1,2,3,4     后台: String ids变量接收数据
    //前台传递数据的格式:ids=1&ids=2&ids=3     后台: Integer []ids变量接收数据
    @RequestMapping("delMoreDistrict")
    public String delMoreDistrict(String ids){
        try {
            //将字符串转化为数据组
            String[] split = ids.split(",");
            Integer[] splitlist=new Integer[split.length];
            for (int i=0;i<splitlist.length;i++){
                splitlist[i]=new Integer(split[i]);
            }
            //调用业务层
            int i = demoService.delectDistrict(splitlist);
            return "{\"result\":"+i+"}";   //拼接的json
        }catch (Exception e){
            e.printStackTrace();
            return "{\"result\":-1}";
        }
    }

}
