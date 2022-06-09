package com.codegym.controller;

import com.codegym.model.Cart;
import com.codegym.model.Product;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;
import java.util.Optional;

@Controller
public class ShoppingCartController {

    @ModelAttribute("cart")
    public Cart setupCart() {
        return new Cart();
    }

    @GetMapping("/shopping-cart")
    public ModelAndView showCart(@SessionAttribute("cart") Cart cart) {
        ModelAndView modelAndView = new ModelAndView("/cart");
        modelAndView.addObject("cart", cart);
        return modelAndView;
    }


    @GetMapping("/delete/{id}")
    public String delete(@SessionAttribute("cart") Cart cart, @PathVariable int id) {
        Map.Entry<Product, Integer> product = cart.findByIdInCart(id);
        if (product.getKey() != null) {
            cart.deleteProduct(product.getKey());
            return "redirect:/shopping-cart";
        } else {
           return  "/error.404";
        }
    }
//    @PostMapping("/delete-blog")
//    public String deleteBlog(@ModelAttribute("blog") Blog blog) {
//        blogService.remove(blog.getId());
//        return "redirect:blogs";
//    }
}