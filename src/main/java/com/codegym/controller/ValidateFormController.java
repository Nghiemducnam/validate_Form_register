package com.codegym.controller;

import com.codegym.model.User;
import com.codegym.service.IUserService;
import com.codegym.validation.UserValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class ValidateFormController {
    @Autowired
    IUserService iUserService;
    @GetMapping("/")
    public String showForm(Model model){
    model.addAttribute("user", new User());
    return "home";
    }
    @PostMapping("/register")
    public String checkRegister(@Valid @ModelAttribute ("user") User user, BindingResult bindingResult, Model model){
        new UserValidation().validate(user, bindingResult);
        if(bindingResult.hasFieldErrors()){
            return "home";
        }else {
            iUserService.save(user);
            model.addAttribute("user", new User());
            model.addAttribute("message", "New customer created successfully");
           return "home";
        }
    }

}
