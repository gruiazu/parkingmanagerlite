package com.hormigo.david.parkingmanager.user.adapter;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.hormigo.david.parkingmanager.user.domain.*;
import com.hormigo.david.parkingmanager.user.service.UserService;

@Controller
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String displayUsers(Model model) {
        model.addAttribute("users", this.userService.getAll());
        return "user/list";
    }

    @GetMapping("/newUser")
    public String showUserCreateForm(Model model) {
        List<Role> roles = Arrays.asList(Role.values());
        model.addAttribute("roles", roles);
        UserDao userDao = new UserDao();
        model.addAttribute("userDao", userDao);
        return "user/createform";
    }    

    @PostMapping("/newUser")
    public String showUserCreateForm(@ModelAttribute("userDao") UserDao userDao) {
        
        this.userService.register(userDao);
        return "redirect:/users";
    }   
}
