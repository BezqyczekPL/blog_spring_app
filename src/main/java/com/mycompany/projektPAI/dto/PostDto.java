/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projektPAI.dto;

import com.mycompany.projektPAI.entities.UserEntity;
import java.time.LocalDateTime;
import java.util.List;
import javax.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/**
 *
 * @author dawid
 */
@Data
@Builder
public class PostDto {
    private Long id;
    @NotEmpty(message = "Tytuł nie może być pusty")
    private String tytul;
    @NotEmpty(message = "Link do zdjęcia nie może być pusty")
    private String urlZdjecia;
    @NotEmpty(message = "Treść posta nie może być pusta")
    private String tresc;
    private LocalDateTime stworzono;
    private LocalDateTime edytowano;
    private List<KomentarzDto> komentarze;
    private UserEntity createdBy;
}
