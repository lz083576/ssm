package com.itheima.ssm.controller;

import com.itheima.ssm.domain.Product;
import com.itheima.ssm.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private IProductService productService;
    @RequestMapping("/findAll.do")
    @RolesAllowed("ADMIN")
    public ModelAndView findAll() throws Exception {
       ModelAndView mv = new ModelAndView();
        try {
            List<Product> ps = productService.findAll();
            mv.addObject("productList",ps );
            mv.setViewName("product-list");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mv;
   }


    @RequestMapping("/save.do")
    public String save(Product product)throws Exception {
        try {
            productService.save(product);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:findAll.do";
    }


}
