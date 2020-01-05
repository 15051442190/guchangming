package cn.kgc.pController;

import cn.kgc.domain.House;
import cn.kgc.domain.Users;
import cn.kgc.service.HouseService;
import cn.kgc.util.FileUploadUtil;
import cn.kgc.util.PageUtil;
import cn.kgc.util.SearchCondition;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;

@Controller("HouseController2")
@RequestMapping("/page/")
public class HouseController {
    @Autowired
    private HouseService houseService;

    @RequestMapping("addHouse")
    //一个表单对象对应一个参数或者实体
    //一个文件域对象与一个CommonsMultipartFile对象对应
    public String addHouse(HttpSession session,House house, @RequestParam(value = "pfile",required = false) CommonsMultipartFile pfile){
        //1.实现文件上传
       /* System.out.println("上传文件名称:"+pfile.getOriginalFilename());
        System.out.println("上传文件大小:"+pfile.getSize());
        System.out.println("上传文件的类型:"+pfile.getContentType());*/
        //上传
        //获取文件的扩展名

        try {

            String saveFileName = FileUploadUtil.upload(pfile);
            //2.将数据添加到数据库
            //修改house实体，添加额外的属性值
            //设置编号
            house.setId(System.currentTimeMillis()+"");
            //设置所属用户   重点理解
            Users users =(Users) session.getAttribute("loginInfo");
            house.setUserId(users.getId());
            //设置图片路径
            house.setPath(saveFileName);
            //调用业务实现添加
            houseService.addHours(house);
            return "fabu";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "error";
    }
    //显示出租房的信息
    @RequestMapping("getHouseByUserid")
    public String getHouseByUserid(PageUtil pageUtil, HttpSession session, Model model){
            //设置页大小
        pageUtil.setRows(2);
        //调用业务层获取数据
        //获取用户id
        Users users =(Users) session.getAttribute("loginInfo");
        //Integer userid=1007;
        PageInfo<House> pageInfo = houseService.getHouseByUserid(users.getId(), pageUtil);
        //将数据填充到作用域
       model.addAttribute("pageInfo",pageInfo);

        return "forward:guanli.jsp";
    }
    //显示修改出租房信息
    @RequestMapping("showHouse")
    public String showHouse(String id,Model model){
        /*调用业务*/
        House house = houseService.getHouse(id);
        model.addAttribute("house",house);
        return "upfabu";
    }
    @RequestMapping("upHouse")
    //一个表单对象对应一个参数或者实体
    //一个文件域对象与一个CommonsMultipartFile对象对应
    public String upHouse(House house,String oldPath, @RequestParam(value = "pfile",required = false) CommonsMultipartFile pfile){
        try {
            //1.判断图片有没有选择   :如果选中图片上传， 否则不上传
            if (!pfile.getOriginalFilename().equals("")){
                //System.out.println("上传图片");
                String saveFileName = FileUploadUtil.upload(pfile);
                //设置修改实体图片的路径
                house.setPath(saveFileName);
                //删除旧图
                // File file=new File("d:\\images\\"+oldPicPath);
                //file.delete();
                FileUploadUtil.deleteFile(oldPath);
            }
            //2.修改数据库信息
            //调用业务实现添加
            houseService.updateHouse(house);

            return "redirect:getHouseByUserid";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "error";
    }
    //删除出租房信息
    @RequestMapping("deletHouse")
    public String deletHouse(String id) {
        try {
            /*调用业务*/
            int i = houseService.deleleHouse(id, 1);
            return "redirect:getHouseByUserid";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "error";
    }
    @RequestMapping("getBrowsHouses")
    public String getBrowsHouses(SearchCondition searchCondition,Model model){
        //设置页大小
        searchCondition.setRows(3);
        //调用业务
        PageInfo<House> pageInfo = houseService.getBrowHouses(searchCondition);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("searchCondition",searchCondition);
        return "list";
    }
}
