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
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author a023849364c
 */
public interface ViviendaRepository extends JpaRepository<ViviendaEntity, Long> {
    
    
    
    @Query(
            value = "SELECT * FROM vivienda WHERE idzona=?1",
            nativeQuery = true)
    Page<ViviendaEntity> findbyZona(Long lZona,Pageable oPageable);
    
    @Query(
            value = "SELECT * FROM vivienda WHERE idzona IN (SELECT id from zona where idcomarca=?1)",
            nativeQuery = true)
    Page<ViviendaEntity> findbyComarca(Long lComarca,Pageable oPageable);
    
    @Query(
            value = "SELECT * FROM vivienda WHERE idzona IN (SELECT id from comarca  where idciudad IN (SELECT id from ciudad where id=?1))",
            nativeQuery = true)
    Page<ViviendaEntity> findbyCiudad(Long lCiudad,Pageable oPageable);
    
    @Query(
            value = "SELECT * FROM vivienda WHERE alquilar=true AND idzona IN (SELECT id from comarca  where idciudad IN (SELECT id from ciudad where id=?1))",
            nativeQuery = true)
    Page<ViviendaEntity> findbyAlquilarCiudad(Long lCiudad,Pageable oPageable);
   
    @Query(
            value = "SELECT * FROM vivienda WHERE alquilar=true AND idzona IN (SELECT id from zona where idcomarca=?1)",
            nativeQuery = true)
    Page<ViviendaEntity> findbyAlquilarComarca(Long lComarca,Pageable oPageable);
    
    @Query(
            value = "SELECT * FROM vivienda WHERE alquilar=true AND idzona=?1",
            nativeQuery = true)
    Page<ViviendaEntity> findbyAlquilarZona(Long lZona,Pageable oPageable);
    
     @Query(
            value = "SELECT * FROM vivienda WHERE comprar=true AND idzona IN (SELECT id from comarca  where idciudad IN (SELECT id from ciudad where id=?1))",
            nativeQuery = true)
    Page<ViviendaEntity> findbyComprarCiudad(Long lCiudad,Pageable oPageable);
   
    @Query(
            value = "SELECT * FROM vivienda WHERE comprar=true AND idzona IN (SELECT id from zona where idcomarca=?1)",
            nativeQuery = true)
    Page<ViviendaEntity> findbyComprarComarca(Long lComarca,Pageable oPageable);
    
    @Query(
            value = "SELECT * FROM vivienda WHERE comprar=true AND idzona=?1",
            nativeQuery = true)
    Page<ViviendaEntity> findbyComprarZona(Long lZona,Pageable oPageable);
    
     @Query(
            value = "SELECT * FROM vivienda WHERE obranueva=true AND idzona IN (SELECT id from comarca  where id IN (SELECT id from ciudad where id=?1))",
            nativeQuery = true)
    Page<ViviendaEntity> findbyObranuevaCiudad(Long lCiudad,Pageable oPageable);
   
    @Query(
            value = "SELECT * FROM vivienda WHERE obranueva=true AND idzona IN (SELECT id from comarca where id=?1)",
            nativeQuery = true)
    Page<ViviendaEntity> findbyObranuevaComarca(Long lComarca,Pageable oPageable);
    
    @Query(
            value = "SELECT * FROM vivienda WHERE obranueva=true AND idzona=?1",
            nativeQuery = true)
    Page<ViviendaEntity> findbyObranuevaZona(Long lZona,Pageable oPageable);
    
    
    
    
        public Page<ViviendaEntity> findByUbicacionIgnoreCaseContaining(String strFilter, Pageable oPageable);
        
         @Query(
            value = "SELECT * FROM vivienda WHERE comprar = ?1 AND alquilar=?2 AND obranueva=?3 AND idtipovivienda =?4 AND conservacion LIKE '?5' AND habitaciones=?6 AND antiguedad<=?7 AND idzona=?8 AND idanunciante IN (SELECT id FROM usuario WHERE profesional = ?9)",
            nativeQuery = true)
    Page<ViviendaEntity> findByCompraAlquilarObranuevaAndTipoViviendaAndConservacionAndHabitacionesAndAntiguedadAndProfesionalAndZona(Boolean comprar,Boolean alquilar,Boolean obranueva, Long lTipovivienda, String conservacion, Integer habitaciones, Integer antiguedad, Long lZona, Boolean profesional,Pageable oPageable);
    
    @Query(
            value = "SELECT * FROM vivienda WHERE comprar = ?1 AND alquilar=?2 AND obranueva=?3 AND idtipovivienda =?4 AND conservacion LIKE '?5' AND habitaciones=?6 AND antiguedad<=?7 AND idzona IN (SELECT id from comarca where id=?8) AND idanunciante IN (SELECT id FROM usuario WHERE profesional = ?9)",
            nativeQuery = true)
    Page<ViviendaEntity> findByCompraAlquilarObranuevaAndTipoViviendaAndConservacionAndHabitacionesAndAntiguedadAndProfesionalAndComarca(Boolean comprar,Boolean alquilar,Boolean obranueva, Long lTipovivienda, String conservacion, Integer habitaciones, Integer antiguedad, Long lComarca, Boolean profesional,Pageable oPageable);
    
    @Query(
            value = "SELECT * FROM vivienda WHERE comprar = ?1 AND alquilar=?2 AND obranueva=?3 AND idtipovivienda =?4 AND conservacion LIKE '?5' AND habitaciones=?6 AND antiguedad<=?7 AND idzona IN (SELECT id from comarca  where id IN (SELECT id from ciudad where id=?8)) AND idanunciante IN (SELECT id FROM usuario WHERE profesional = ?9)",
            nativeQuery = true)
    Page<ViviendaEntity> findByCompraAlquilarObranuevaAndTipoViviendaAndConservacionAndHabitacionesAndAntiguedadAndProfesionalAndCiudad(Boolean comprar,Boolean alquilar,Boolean obranueva, Long lTipovivienda, String conservacion, Integer habitaciones, Integer antiguedad, Long lCiudad, Boolean profesional,Pageable oPageable);
    
    @Query(
            value = "SELECT * FROM vivienda WHERE comprar = ?1 AND alquilar=?2 AND obranueva=?3 AND idtipovivienda =?4 AND conservacion LIKE '?5' AND habitaciones=?6 AND antiguedad<=?7 AND idanunciante IN (SELECT id FROM usuario WHERE profesional = ?8)",
            nativeQuery = true)
    Page<ViviendaEntity> findByCompraAlquilarObranuevaAndTipoViviendaAndConservacionAndHabitacionesAndAntiguedadAndProfesional(Boolean comprar,Boolean alquilar,Boolean obranueva, Long lTipovivienda, String conservacion, Integer habitaciones, Integer antiguedad,  Boolean profesional,Pageable oPageable);

}
