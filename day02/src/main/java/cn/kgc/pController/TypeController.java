package cn.kgc.pController;

import cn.kgc.domain.Type;
import cn.kgc.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller("typeController2")
@RequestMapping("/page/")
public class TypeController {
    @Autowired
   private TypeService typeService;
    @RequestMapping("getALLType")
    @ResponseBody
    public List<Type> getAllType(){
        List<Type> typeList = typeService.getAllType();
        return typeList;
    }
}
