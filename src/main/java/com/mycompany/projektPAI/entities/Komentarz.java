/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projektPAI.entities;

import java.time.LocalDateTime;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import java.util.HashSet;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Entity
public class Komentarz {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String tresc;
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime dataWyslania;
    @UpdateTimestamp
    private LocalDateTime dataAktualizacji;
    
    @ManyToOne
    @JoinColumn(name = "created_by", nullable = false)
    private UserEntity createdBy;
    @ManyToOne
    @JoinColumn(name="post_id", nullable = false)
    private Post post;
}
