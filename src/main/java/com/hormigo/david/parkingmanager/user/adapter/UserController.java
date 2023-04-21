package com.hormigo.david.parkingmanager.user.adapter;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.hormigo.david.parkingmanager.user.UserAlreadyExistsException;
import com.hormigo.david.parkingmanager.user.domain.*;
import com.hormigo.david.parkingmanager.user.service.UserService;

import jakarta.validation.Valid;

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
    public String showUserCreateForm(final @Valid @ModelAttribute("userDao") UserDao userDao,final BindingResult bindingResult, final Model model) {
        
        if (bindingResult.hasErrors()) {
            model.addAttribute("userDao", userDao);
            return "user/createform";
        }
        try {
        this.userService.register(userDao);
    }
    catch (UserAlreadyExistsException exception){
        bindingResult.rejectValue("email","userData.email","Ya existe un usuario con el correo");
        model.addAttribute("userDao", userDao);
        return "user/createform";
    }
        return "redirect:/users";
    }   
}
