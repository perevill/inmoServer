/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ausias.inmo.repository;

import com.ausias.inmo.entity.CiudadEntity;
import com.ausias.inmo.entity.TipoViviendaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author a023849364c
 */
public interface ComarcaRepository extends JpaRepository<CiudadEntity, Long>{
        public Page<CiudadEntity> findByNombreIgnoreCaseContaining(String strFilter, Pageable oPageable);

    public Page<CiudadEntity> findById(Long longCiudad, Pageable oPageable);

    public Page<CiudadEntity> findByIdAndNombreIgnoreCaseContaining(Long longCiudad, String strFilter, Pageable oPageable);
    
}
