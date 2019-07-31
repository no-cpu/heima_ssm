package com.itheima.ssm.controller;

import com.itheima.ssm.domain.Role;
import com.itheima.ssm.domain.UserInfo;
import com.itheima.ssm.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.WebParam;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {


    @Autowired
    private IUserService userService;

    @RequestMapping("/save.do")
    @PreAuthorize("authentication.principal.username == 'tom'")
    //添加用户
    public String save(UserInfo userInfo) throws Exception{
       userService.save(userInfo);
       return "redirect:findAll.do";
    }

    @RequestMapping("/findAll.do")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    //用户查询
    public ModelAndView findAll() throws Exception {
        ModelAndView mv = new ModelAndView();
        List<UserInfo> userList = userService.findAll();
        mv.addObject("userList", userList);
        mv.setViewName("user-list");
        return mv;
    }
   @RequestMapping("/findById.do")
    public ModelAndView findById(@RequestParam(name = "id",required = true)String id) throws Exception {
         ModelAndView mv = new ModelAndView();
         UserInfo user=userService.findById(id);
          mv.addObject("user",user);
          mv.setViewName("user-show");

          return mv;
   }
   @RequestMapping("/findUserByIdAndAllRole.do")

    public ModelAndView  findUserByIdAndAllRole(@RequestParam(name ="id",required = true) String userId)throws Exception{
           ModelAndView mv = new ModelAndView();
            //1.根据用户id查询用户
       UserInfo userInfo = userService.findById(userId);
       //2.根据用户id查询可以添加的角色
       List<Role> otherRoles=userService.findOtherRoles(userId);
       mv.addObject("user",userInfo);
       mv.addObject("roleList",otherRoles);
        mv.setViewName("user-role-add");
       return mv;
   }

   @RequestMapping("/addRoleToUser.do")
    public String addRoleToUser(@RequestParam(name = "userId",required = true) String userId,@RequestParam(name = "ids",required = true) String[]roleIds){

         userService.addRoleToUser(userId,roleIds);
         return "redirect:findAll.do";

    }

}
