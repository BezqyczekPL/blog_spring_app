/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projektPAI.repositories;

import com.mycompany.projektPAI.entities.Post;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author dawid
 */
public interface PostRepository extends JpaRepository<Post, Long> {
    Optional<Post> findByTytul(String tytul);
    Optional<Post> findAllByOrderByIdDesc();
}
