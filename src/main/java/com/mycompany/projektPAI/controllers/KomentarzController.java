/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projektPAI.controllers;

import com.mycompany.projektPAI.dto.KomentarzDto;
import com.mycompany.projektPAI.dto.PostDto;
import com.mycompany.projektPAI.entities.Komentarz;
import com.mycompany.projektPAI.entities.UserEntity;
import com.mycompany.projektPAI.services.KomentarzService;
import com.mycompany.projektPAI.services.UserService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author dawid
 */
@Controller
public class KomentarzController {
    private KomentarzService komentarzService;
    private UserService userService;
    
    @Autowired
    public KomentarzController(KomentarzService komentarzService, UserService userService) {
        this.komentarzService = komentarzService;
        this.userService = userService;
    }
    
    @GetMapping("/komentarze/{postId}/stworz")
    public String formularzKomentarza(@PathVariable("postId") Long postId, Model model)
    {
        Komentarz komentarz = new Komentarz();
        model.addAttribute("postId", postId);
        model.addAttribute("komentarz", komentarz);
        return "stworzKomentarz";
    }
    
    @PostMapping("/komentarze/{postId}")
    public String stworzKomentarz(@PathVariable("postId") Long postId, @Valid @ModelAttribute("komentarz") KomentarzDto komentarzDto, BindingResult result, Model model)
    {
        if(result.hasErrors())
        {
            model.addAttribute("komentarz", komentarzDto);
            return "stworzKomentarz";
        }
        UserEntity user = new UserEntity();
        
        model.addAttribute("user",user);
        komentarzService.stworzKomentarz(postId, komentarzDto);
        return "redirect:/posty/"+postId;
    }
    
    @GetMapping("/komentarze/{komentarzId}/edytuj")
    public String formularzEdycji (@PathVariable("komentarzId") long komentarzId, Model model)
    {
        KomentarzDto komentarz = komentarzService.findKomentarzById(komentarzId);
        model.addAttribute("komentarz", komentarz);
        return "edytujKomentarz";
    }
    
    @PostMapping("/komentarze/{komentarzId}/edytuj")
    public String edytujKomentarz (@PathVariable("komentarzId") Long komentarzId, @Valid @ModelAttribute("komentarz") KomentarzDto komentarz, BindingResult result, Model model)
    {
        if(result.hasErrors())
        {
            model.addAttribute("komentarz", komentarz);
            return "edytujKomentarz";
        }
        KomentarzDto komentarzDto = komentarzService.findKomentarzById(komentarzId);
        komentarz.setId(komentarzId);
        komentarz.setPost(komentarzDto.getPost());
        Long id = komentarzDto.getPost().getId();
        komentarzService.edytujKomentarz(komentarz);
        return "redirect:/posty/"+id;
    }
    
    @GetMapping("/komentarze/{komentarzId}/delete")
    public String usunKomentarz(@PathVariable("komentarzId") Long komentarzId)
    {
        KomentarzDto komentarzDto = komentarzService.findKomentarzById(komentarzId);
        Long id = komentarzDto.getPost().getId();
        komentarzService.usunKomentarz(komentarzId);
        return "redirect:/posty/"+id;
    }
}
