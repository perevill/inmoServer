/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ausias.inmo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author a023849364c
 */
@Entity
@Table(name = "zona")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ZonaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    private String nombre;
    private String codpostal;
    

    @ManyToOne
    @JoinColumn(name = "idcomarca")
    private ComarcaEntity comarca;

    /*@OneToMany(mappedBy = "producto")
    private List<CarritoEntity> carritos = new ArrayList<>();
    
    @OneToMany(mappedBy = "producto")
    private List<CompraEntity> compras = new ArrayList<>();*/

    public ZonaEntity() {
    }

    public ZonaEntity(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodpostal() {
        return codpostal;
    }

    public void setCodpostal(String codpostal) {
        this.codpostal = codpostal;
    }   

   /* public int getCarritos() {
        return this.carritos.size();
    }

    public int getCompras() {
        return compras.size();
    } */

    public ComarcaEntity getComarca() {
        return comarca;
    }

    public void setComarca(ComarcaEntity comarca) {
        this.comarca = comarca;
    }

}

