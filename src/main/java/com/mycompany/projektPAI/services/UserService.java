/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.projektPAI.services;

import com.mycompany.projektPAI.dto.RegistrationDto;
import com.mycompany.projektPAI.entities.UserEntity;

/**
 *
 * @author dawid
 */
public interface UserService {
    void saveUser(RegistrationDto registrationDto);

    public UserEntity findByEmail(String email);

    public UserEntity findByUsername(String username);
}
