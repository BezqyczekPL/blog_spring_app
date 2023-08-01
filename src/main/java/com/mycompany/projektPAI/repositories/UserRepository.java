/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.projektPAI.repositories;

import com.mycompany.projektPAI.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author dawid
 */
public interface UserRepository extends JpaRepository<UserEntity,Long>{
    UserEntity findByEmail(String email);
    UserEntity findByUsername (String username);

    UserEntity findFirstByUsername(String username);
}
