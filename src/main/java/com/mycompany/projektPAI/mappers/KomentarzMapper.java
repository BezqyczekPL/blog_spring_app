/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projektPAI.mappers;

import com.mycompany.projektPAI.dto.KomentarzDto;
import com.mycompany.projektPAI.entities.Komentarz;

/**
 *
 * @author dawid
 */
public class KomentarzMapper {
    public static Komentarz mapToKomentarz(KomentarzDto komentarzDto)
    {
        return Komentarz.builder()
                .id(komentarzDto.getId())
                .tresc(komentarzDto.getTresc())
                .dataWyslania(komentarzDto.getDataWyslania())
                .dataAktualizacji(komentarzDto.getDataAktualizacji())
                .createdBy(komentarzDto.getCreatedBy())
                .post(komentarzDto.getPost())
                .build();
    }
    
    public static KomentarzDto mapToKomentarzDto(Komentarz komentarz)
    {
        return KomentarzDto.builder()
                .id(komentarz.getId())
                .tresc(komentarz.getTresc())
                .dataWyslania(komentarz.getDataWyslania())
                .dataAktualizacji(komentarz.getDataAktualizacji())
                .createdBy(komentarz.getCreatedBy())
                .post(komentarz.getPost())
                .build();
    }
}
