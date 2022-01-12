/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ausias.inmo.repository;

import com.ausias.inmo.entity.FavoritoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author a023849364c
 */
public interface FavoritoRepository extends JpaRepository<FavoritoEntity, Long> {
    
}
