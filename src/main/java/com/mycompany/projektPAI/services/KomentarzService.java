/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.projektPAI.services;

import com.mycompany.projektPAI.dto.KomentarzDto;
import com.mycompany.projektPAI.dto.PostDto;
import java.util.List;

/**
 *
 * @author dawid
 */
public interface KomentarzService {
    void stworzKomentarz(Long postId, KomentarzDto komentarz);

    List<KomentarzDto> findAllKomentarze();
    KomentarzDto findKomentarzById(Long komentarzId);

    public void edytujKomentarz(KomentarzDto komentarzDto);

    public void usunKomentarz(Long komentarzId);
}
