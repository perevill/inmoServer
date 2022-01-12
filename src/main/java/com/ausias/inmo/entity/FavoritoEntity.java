/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ausias.inmo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author a023849364c
 */
@Entity
@Table(name = "favoritos")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

public class FavoritoEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private int cantidad;
    private double precio;

    @ManyToOne
    @JoinColumn(name = "idvivienda")
    private ViviendaEntity vivienda;

    @ManyToOne
    @JoinColumn(name = "idusuario")
    private UsuarioEntity usuario;

    public FavoritoEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    

    public ViviendaEntity getVivienda() {
        return vivienda;
    }

    public void setVivienda(ViviendaEntity vivienda) {
        this.vivienda = vivienda;
    }

    public UsuarioEntity getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioEntity usuario) {
        this.usuario = usuario;
    }
}

