package com.example.springsecuritycsrf.controller;

import com.example.springsecuritycsrf.dto.UserDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserMvcController {

    // 1.er paso: Obtener el formulario en el Frontend
    @GetMapping("/")
    public String getForm(Model model){
        model.addAttribute("user",new UserDTO());
        return "user-form";
    }

    // 2.do paso: Recibir el formulario con los datos rellenos
    @PostMapping("/users")
    public String save(@ModelAttribute("user") UserDTO user, Model model){
        System.out.println(user);
        model.addAttribute("nombreUsuario",user.getUsername());
        model.addAttribute("email",user.getEmail());
        return "congratulations";
    }
}
