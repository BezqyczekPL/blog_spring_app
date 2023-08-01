/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.projektPAI.services;

import com.mycompany.projektPAI.dto.PostDto;
import com.mycompany.projektPAI.entities.Post;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Sort;

/**
 *
 * @author dawid
 */
public interface PostService {
    List<PostDto> findAllPosts(Sort sort);
    List<PostDto> findAllByOrderByIdDesc();
    Post zapiszPost(PostDto postDto);

    PostDto findPostById(Long postId);

    void edytujPost(PostDto post);

    public void usunPost(Long postId);
}
