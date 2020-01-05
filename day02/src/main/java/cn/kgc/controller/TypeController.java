package cn.kgc.controller;

import cn.kgc.domain.Type;
import cn.kgc.service.TypeService;
import cn.kgc.util.PageUtil;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin/")
public class TypeController {
    @Autowired
    private TypeService typeService;
    @RequestMapping("getType")
    public Map<String,Object> getType(PageUtil pageUtil){
        //调用业务层
        PageInfo<Type> pageInfo = typeService.getType(pageUtil);
        //返回datagrid需要的total，rows的数据
        Map<String,Object> map=new HashMap<>();
        map.put("total",pageInfo.getTotal());
        map.put("rows",pageInfo.getList());
        return map;
    }
    @RequestMapping("addType")
    public Map<String,Object> addType(Type type){
        Map<String,Object> map=new HashMap<>();
        try{
            //调用业务层
            int i = typeService.addType(type);
            //返回datagrid需要的total，rows的数据

            map.put("i",i);

        }catch (Exception e){
            e.printStackTrace();
    }
    return map;
  }
    @RequestMapping("updateType")
    public Type updateType(Integer id){
        try {
            //调用业务层
            Type typeById = typeService.getTypeById(id);
            return typeById;
        }catch (Exception e){
            e.printStackTrace();
        }
       return null;
    }
    @RequestMapping("updateTypeByid")
    public Map<String,Object> updateTypeByid(Type type){
        Map<String,Object> map=new HashMap<>();
        try {
            //调用业务层
            int i = typeService.updateType(type );
            map.put("i",i);
        }catch (Exception e){
            e.printStackTrace();
        }
        return map;
    }
    @RequestMapping("delTypeByid")
    public Map<String,Object> delTypeByid(Integer id){
        Map<String,Object> map=new HashMap<>();
        try {
            //调用业务层
            int i = typeService.deletType(id);
            map.put("i",i);
        }catch (Exception e){
            e.printStackTrace();
        }
        return map;
    }
    //批量删除区域
    //前台传递数据的格式:ids=1,2,3,4     后台: String ids变量接收数据
    //前台传递数据的格式:ids=1&ids=2&ids=3     后台: Integer []ids变量接收数据
    @RequestMapping("delMoreType")
    public String delMoreType(String ids){
        try {
            //将字符串转化为数据组
            String[] split = ids.split(",");
            Integer[] splitlist=new Integer[split.length];
            for (int i=0;i<splitlist.length;i++){
                splitlist[i]=new Integer(split[i]);
            }
            //调用业务层
            int i = typeService.delectType(splitlist);
            return "{\"result\":"+i+"}";   //拼接的json
        }catch (Exception e){
            e.printStackTrace();
            return "{\"result\":-1}";
        }
    }

}
