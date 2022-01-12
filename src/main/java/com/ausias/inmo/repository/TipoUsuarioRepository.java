package com.ausias.inmo.repository;

import com.ausias.inmo.entity.TipoUsuarioEntity;
import com.ausias.inmo.entity.TipoViviendaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoUsuarioRepository extends JpaRepository<TipoUsuarioEntity, Long> {    

    public Page<TipoUsuarioEntity> findByNombreIgnoreCaseContaining(String strFilter, Pageable oPageable);

    public Page<TipoUsuarioEntity> findById(Long longTipoUsuario, Pageable oPageable);

    public Page<TipoUsuarioEntity> findByIdAndNombreIgnoreCaseContaining(Long longTipoUsuario, String strFilter, Pageable oPageable);

}
