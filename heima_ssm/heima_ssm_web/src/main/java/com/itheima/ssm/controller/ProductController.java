package com.itheima.ssm.controller;

import com.itheima.ssm.domain.Product;
import com.itheima.ssm.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private IProductService productService;

    @RequestMapping("/save.do")
    public String save(Product product){
        productService.save(product);
        return "redirect:findAll.do";
    }



    @RequestMapping("/findAll.do")
    @RolesAllowed("ADMIN")
    //查询所有的产品信息 ，未分页
    public ModelAndView findAll() throws Exception {
        ModelAndView mv = new ModelAndView();
        List<Product> ps = productService.findAll();
        mv.addObject("productList", ps);
        mv.setViewName("product-list");
        return mv;

    }
       /*  @RequestMapping("/findAll.do")
         //查询所有的产品信息，分页
         public ModelAndView findAll(@RequestParam(name="page",required = true,defaultValue ="1")int page,
                                     @RequestParam(name="size",required = true,defaultValue = "2")int size) throws Exception {
             ModelAndView mv = new ModelAndView();
             List<Product> productList = productService.findAll(page,size);

             PageInfo pageInfo = new PageInfo(productList);
             mv.addObject("pageInfo", pageInfo);
             mv.setViewName("product-page-list");
             return mv;

         }*/
}
