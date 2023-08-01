/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projektPAI.controllers;

import com.mycompany.projektPAI.dto.RegistrationDto;
import com.mycompany.projektPAI.entities.UserEntity;
import com.mycompany.projektPAI.services.UserService;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author dawid
 */
@Controller
public class AuthController {

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    private UserService userService;

    @GetMapping("/rejestracja")
    public String formularzRejestracji(Model model) {
        RegistrationDto user = new RegistrationDto();
        model.addAttribute("user", user);
        return "rejestracja";
    }

    @PostMapping("/rejestracja/zapisz")
    public String zarejestruj(@Valid @ModelAttribute("user") RegistrationDto user, BindingResult result, Model model) {
        UserEntity istniejacyEmail = userService.findByEmail(user.getEmail());
        if (istniejacyEmail != null && istniejacyEmail.getEmail() != null && !istniejacyEmail.getEmail().isEmpty()) {
            return "redirect:/rejestracja?fail";
        }
        UserEntity istniejacyLogin = userService.findByUsername(user.getUsername());
        if (istniejacyLogin != null && istniejacyLogin.getUsername() != null && !istniejacyLogin.getUsername().isEmpty()) {
            return "redirect:/rejestracja?fail";
        }
        if (result.hasErrors()) {
            model.addAttribute("user", user);
            return "rejestracja";
        }
        userService.saveUser(user);
        return "redirect:/posty?success";
    }
    
    @GetMapping("/logowanie")
    public String logowanie()
    {
        return "logowanie";
    }
}
