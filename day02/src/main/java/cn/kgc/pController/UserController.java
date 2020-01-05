package cn.kgc.pController;

import cn.kgc.domain.Users;
import cn.kgc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.nio.cs.US_ASCII;

import javax.servlet.http.HttpSession;

@Controller(value = "userController2")
@RequestMapping("/page/")
public class UserController{
    @Autowired
    private UserService userService;
    @RequestMapping("checkname")
    @ResponseBody
    public String checkname(String name){
        //调用service
        boolean b = userService.checkname(name);
        return "{\"result\":"+b+"}";
    }
    @RequestMapping("reg")
    public String regUser(Users users){
        //调用service
        int i = userService.addUser(users);
        if (i>0){
            return "redirect:login.jsp";
        }else {
            return "redirect:regs.jsp";
        }
    }
    @RequestMapping("login")
    public String login(HttpSession session,String username, String password){
       //调用业务
        Users login = userService.login(username, password);
        if (login==null){
            return "redirect:login.jsp";
        }else {
            //只要登入，肯定使用cookie或者session保存登入者的信息
            //使用session保存登入的者的信息
            session.setAttribute("loginInfo",login);
            //设置有效期
            session.setMaxInactiveInterval(20*60);
            return "redirect:getHouseByUserid";
        }
    }
}
