/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projektPAI.mappers;

import com.mycompany.projektPAI.dto.PostDto;
import com.mycompany.projektPAI.entities.Post;
import static com.mycompany.projektPAI.mappers.KomentarzMapper.mapToKomentarzDto;
import java.util.stream.Collectors;

/**
 *
 * @author dawid
 */
public class PostMapper {

    public static Post mapToPost(PostDto post) {
        Post postDto = Post.builder()
                .id(post.getId())
                .tytul(post.getTytul())
                .urlZdjecia(post.getUrlZdjecia())
                .tresc(post.getTresc())
                .createdBy(post.getCreatedBy())
                .stworzono(post.getStworzono())
                .edytowano(post.getEdytowano())
                .build();
        return postDto;
    }

    public static PostDto mapToPostDto(Post post) {
        PostDto postDto = PostDto.builder()
                .id(post.getId())
                .tytul(post.getTytul())
                .urlZdjecia(post.getUrlZdjecia())
                .tresc(post.getTresc())
                .stworzono(post.getStworzono())
                .edytowano(post.getEdytowano())
                .createdBy(post.getCreatedBy())
                .komentarze(post.getKomentarze().stream().map((komentarz) -> mapToKomentarzDto(komentarz)).collect(Collectors.toList()))
                .build();
        return postDto;
    }
}
