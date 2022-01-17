/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ausias.inmo.repository;

import com.ausias.inmo.entity.ViviendaEntity;
import com.ausias.inmo.entity.ZonaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author a023849364c
 */
public interface ViviendaRepository extends JpaRepository<ViviendaEntity, Long> {
    
        public Page<ViviendaEntity> findByUbicacionIgnoreCaseContaining(String strFilter, Pageable oPageable);

}
