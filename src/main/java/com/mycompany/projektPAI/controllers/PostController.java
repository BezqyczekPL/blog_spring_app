/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projektPAI.controllers;

import com.mycompany.projektPAI.dto.KomentarzDto;
import com.mycompany.projektPAI.dto.PostDto;
import com.mycompany.projektPAI.entities.Post;
import com.mycompany.projektPAI.entities.UserEntity;
import com.mycompany.projektPAI.security.SecurityUtil;
import com.mycompany.projektPAI.services.KomentarzService;
import com.mycompany.projektPAI.services.PostService;
import com.mycompany.projektPAI.services.UserService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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
public class PostController {
    private PostService postService;
    private UserService userService;
    private KomentarzService komentarzService;
    
    @Autowired
    public PostController(PostService postService, UserService userService, KomentarzService komentarzService) {
        this.postService = postService;
        this.userService = userService;
        this.komentarzService = komentarzService;
    }
    
    @GetMapping("/posty")
    public String getPosty(Model model)
    {
        UserEntity user = new UserEntity();
        List<PostDto> posty = postService.findAllPosts(Sort.by(Sort.Direction.DESC, "id"));
        String email = SecurityUtil.getSessionUser();
        if (email != null)
        {
            user = userService.findByEmail(email);
            model.addAttribute("user", user);
        }
        model.addAttribute("user", user);
        model.addAttribute("posty", posty);
        return "posts-list";
    }
    
    @GetMapping("/posty/stworz")
    public String formularzPosta (Model model)
    {
        Post post = new Post();
        model.addAttribute("post", post);
        return "stworzPost";
    }
    
    @PostMapping("/posty/stworz")
    public String zapiszPost (@Valid @ModelAttribute("post") PostDto postDto, BindingResult result, Model model)
    {
        if(result.hasErrors())
        {
            model.addAttribute("post", postDto);
            return "stworzPost";
        }
        postService.zapiszPost(postDto);
        return "redirect:/posty";
    }
    
    @GetMapping("/posty/{postId}/edytuj")
    public String formularzEdycji (@PathVariable("postId") long postId, Model model)
    {
        PostDto post = postService.findPostById(postId);
        model.addAttribute("post",post);
        return "edytujPost";
    }
    
    @PostMapping("/posty/{postId}/edytuj")
    public String edytujPost (@PathVariable("postId") Long postId, @Valid @ModelAttribute("post") PostDto post, BindingResult result, Model model)
    {
        if(result.hasErrors())
        {
            model.addAttribute("post", post);
            return "edytujPost";
        }
        post.setId(postId);
        postService.edytujPost(post);
        return "redirect:/posty";
    }
    
    @GetMapping("/posty/{postId}")
    public String post(@PathVariable("postId") Long postId, Model model)
    {
        UserEntity user = new UserEntity();
        PostDto postDto = postService.findPostById(postId);
     
        String email = SecurityUtil.getSessionUser();
        if (email != null)
        {
            user = userService.findByEmail(email);
            model.addAttribute("user", user);
        }
 
        model.addAttribute("user", user);
        model.addAttribute("post", postDto);
        return "trescPosta";
    }
    
    @GetMapping("/posty/{postId}/delete")
    public String usunPost(@PathVariable("postId") Long postId)
    {
        postService.usunPost(postId);
        return "redirect:/posty";
    }
}
