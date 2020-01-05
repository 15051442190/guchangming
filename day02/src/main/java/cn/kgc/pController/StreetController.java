package cn.kgc.pController;

import cn.kgc.domain.District;
import cn.kgc.domain.Street;
import cn.kgc.service.DemoService;
import cn.kgc.service.StreetService;
import cn.kgc.util.PageUtil;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller("streetController2")
@RequestMapping("/page/")
public class StreetController {

    //调用业务
    @Autowired
    private StreetService streetService;

    @RequestMapping("getStreetByDid")
    @ResponseBody
    public List<Street> getStreetByDid(Integer did){
        return streetService.getStreetByDistrict(did);
    }

}
