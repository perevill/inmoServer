package com.ausias.inmo.repository;

import com.ausias.inmo.entity.TipoViviendaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoViviendaRepository extends JpaRepository<TipoViviendaEntity, Long> {    

    public Page<TipoViviendaEntity> findByNombreIgnoreCaseContaining(String strFilter, Pageable oPageable);

    public Page<TipoViviendaEntity> findById(Long longTipoProducto, Pageable oPageable);

    public Page<TipoViviendaEntity> findByIdAndNombreIgnoreCaseContaining(Long longTipoProducto, String strFilter, Pageable oPageable);

}
