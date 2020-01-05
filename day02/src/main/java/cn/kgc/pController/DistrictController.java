package cn.kgc.pController;

import cn.kgc.domain.District;
import cn.kgc.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller("DistrictController2")
@RequestMapping("/page/")
public class DistrictController {
    @Autowired
    private DemoService demoService;

    @RequestMapping("getAllDistrict")
    @ResponseBody
    public List<District> getAllDistrict(){
        List<District> districtList = demoService.getAllDistrict();
        return districtList;
    }
}
