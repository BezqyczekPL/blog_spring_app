/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projektPAI.services.impl;

import com.mycompany.projektPAI.dto.KomentarzDto;
import com.mycompany.projektPAI.dto.PostDto;
import com.mycompany.projektPAI.entities.Komentarz;
import com.mycompany.projektPAI.entities.Post;
import com.mycompany.projektPAI.entities.UserEntity;
import com.mycompany.projektPAI.repositories.KomentarzRepository;
import com.mycompany.projektPAI.repositories.PostRepository;
import com.mycompany.projektPAI.services.KomentarzService;
import static com.mycompany.projektPAI.mappers.KomentarzMapper.mapToKomentarz;
import static com.mycompany.projektPAI.mappers.KomentarzMapper.mapToKomentarzDto;
import static com.mycompany.projektPAI.mappers.PostMapper.mapToPost;
import static com.mycompany.projektPAI.mappers.PostMapper.mapToPostDto;
import com.mycompany.projektPAI.repositories.UserRepository;
import com.mycompany.projektPAI.security.SecurityUtil;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author dawid
 */
@Service
public class KomentarzServiceImpl implements KomentarzService {

    private KomentarzRepository komentarzRepository;
    private PostRepository postRepository;
     private UserRepository userRepository;
    
    
    @Autowired
    public KomentarzServiceImpl(KomentarzRepository komentarzRepository, PostRepository postRepository, UserRepository userRepository) {
        this.komentarzRepository = komentarzRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }
    
    @Override
    public void stworzKomentarz(Long postId, KomentarzDto komentarzDto) {
        String email = SecurityUtil.getSessionUser();
            Post post = postRepository.findById(postId).get();
            Komentarz komentarz = mapToKomentarz(komentarzDto);
            UserEntity user = userRepository.findByEmail(email);
            komentarz.setPost(post);
            komentarz.setCreatedBy(user);
            komentarzRepository.save(komentarz);
    }       

    @Override
    public List<KomentarzDto> findAllKomentarze() {
        List<Komentarz> komentarze = komentarzRepository.findAll();
        return komentarze.stream().map(komentarz -> mapToKomentarzDto(komentarz)).collect(Collectors.toList());
    }

    @Override
    public KomentarzDto findKomentarzById(Long komentarzId) {
        Komentarz komentarz = komentarzRepository.findById(komentarzId).get();
        return mapToKomentarzDto(komentarz);
    }

    @Override
    public void edytujKomentarz(KomentarzDto komentarzDto) {
        String email = SecurityUtil.getSessionUser();
        Komentarz komentarz = mapToKomentarz(komentarzDto);
        UserEntity user = userRepository.findByEmail(email);
        komentarz.setCreatedBy(user);
        komentarzRepository.save(komentarz);
    }

    @Override
    public void usunKomentarz(Long komentarzId) {
        komentarzRepository.deleteById(komentarzId);
    }
        
    
    
    

}
