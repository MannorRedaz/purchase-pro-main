package cn.mono.purchase.controller;


import cn.mono.purchase.dto.Login;
import cn.mono.purchase.dto.Message;
import cn.mono.purchase.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author nihao
 * @time 2021/3/13
 */

@RestController
@RequestMapping("")
@CrossOrigin(origins = "*")
public class LoginController {
    @Autowired
    LoginService loginService;
    Message message;

    @RequestMapping(value = "supplierLogin",method = RequestMethod.POST)
    //@PostMapping("supplierLogin")
    @ResponseBody
    public Message supplierLogin(@RequestBody Login login){
        return  loginService.supplierLogin(login);
    }

    @RequestMapping(value = "administerLogin",method = RequestMethod.POST)
    @ResponseBody
    public Message administerLogin(@RequestBody Login login){
        return loginService.administerLogin(login);
    }


    /*
    public ModelAndView supplierLogin(@RequestBody Login supplier) throws IOException {
        System.out.println(supplier);
        ModelAndView mav = new ModelAndView();
        message =loginService.supplierLogin(supplier);
        System.out.println(message.getMsg());
        if (message.isSuccess()){
            mav.addObject("s", supplier);
            mav.setViewName("success");

            //out.print("<script language=\"javascript\">alert('登录成功！');window.location.href='success'</script>");
            return mav;
        }else {
            //resp.getWriter().print("<script language=\"javascript\">alert('登录失败！');window.location.href='index'</script>");
            mav.setViewName("Login");
            return mav;
        }

    }*/

    /**
     * 管理员登录
     * @return
     */
    /*
    @RequestMapping("administerLogin")
    public ModelAndView administerLogin(Login login){
        ModelAndView mav = new ModelAndView();
        if (loginService.presidentSelectByName(login.getName()))
        message =loginService.supplierSelectByName();
        System.out.println(message.getMsg());
        if (message.isSuccess()){
            mav.addObject("s", supplier);
            mav.setViewName("success");
            PrintWriter out = resp.getWriter();
            //out.print("<script language=\"javascript\">alert('登录成功！');window.location.href='success'</script>");
            return mav;
        }else {
            //resp.getWriter().print("<script language=\"javascript\">alert('登录失败！');window.location.href='index'</script>");
            mav.setViewName("Login");
            return mav;
        }
    }*/
/*
    public String checkLogin(@RequestParam("userName") String userName,
                             @RequestParam("password") String password,
                             HttpServletResponse response) throws IOException  {
　　　　//根据用户名userName和密码password查看是否是非法用户，此处代码略。。。。
　　　　response.setContentType("text/html;charset=gb2312");
　　　　PrintWriter out = response.getWriter();
　　　　if(非法用户){
    　　　　out.print("<script language=\"javascript\">alert('登录失败！');window.location.href='/你的工程名/login'</script>");
    　　　　return "/login";
　　　　}
　　　　return "/user/index";　
　}
*/

}
