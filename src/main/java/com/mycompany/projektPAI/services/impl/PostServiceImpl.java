/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projektPAI.services.impl;

import com.mycompany.projektPAI.dto.PostDto;
import com.mycompany.projektPAI.entities.Post;
import com.mycompany.projektPAI.entities.UserEntity;
import static com.mycompany.projektPAI.mappers.PostMapper.mapToPost;
import static com.mycompany.projektPAI.mappers.PostMapper.mapToPostDto;
import com.mycompany.projektPAI.repositories.PostRepository;
import com.mycompany.projektPAI.repositories.UserRepository;
import com.mycompany.projektPAI.security.SecurityUtil;
import com.mycompany.projektPAI.services.PostService;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 *
 * @author dawid
 */
@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;
    private UserRepository userRepository;
    
    @Autowired
    public PostServiceImpl(PostRepository postRepository, UserRepository userRepository)
    {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }
    
    @Override
    public List<PostDto> findAllPosts(Sort sort) {
        List<Post> posty = postRepository.findAll(sort);
        return posty.stream().map((post) -> mapToPostDto(post)).collect(Collectors.toList());
    }

   

    @Override
    public Post zapiszPost(PostDto postDto) {
        String email = SecurityUtil.getSessionUser();
        UserEntity user = userRepository.findByEmail(email);
        Post post = mapToPost(postDto);
        post.setCreatedBy(user);
        return postRepository.save(post);
    }

    @Override
    public PostDto findPostById(Long postId) {
        Post post = postRepository.findById(postId).get();
        return mapToPostDto(post);
    }

    @Override
    public void edytujPost(PostDto postDto) {
        String email = SecurityUtil.getSessionUser();
        UserEntity user = userRepository.findByEmail(email);
        Post post = mapToPost(postDto);
        post.setCreatedBy(user);
        postRepository.save(post);
    }

    @Override
    public void usunPost(Long postId) {
        postRepository.deleteById(postId);
    }

    @Override
    public List<PostDto> findAllByOrderByIdDesc() {
        List<Post> posty = (List<Post>) postRepository.findAllByOrderByIdDesc().get();
        return posty.stream().map((post) -> mapToPostDto(post)).collect(Collectors.toList());}
    
}
