/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projektPAI.dto;

import com.mycompany.projektPAI.entities.Post;
import com.mycompany.projektPAI.entities.UserEntity;
import java.time.LocalDateTime;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/**
 *
 * @author dawid
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KomentarzDto {
    private Long id;
    @NotEmpty(message = "Komentarz nie może być pusty")
    private String tresc;
    private LocalDateTime dataWyslania;
    private LocalDateTime dataAktualizacji;
    private Post post;
    private UserEntity createdBy;
}
