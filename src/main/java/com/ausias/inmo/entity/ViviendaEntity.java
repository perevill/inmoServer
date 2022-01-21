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
@Table(name = "vivienda")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ViviendaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    private String ubicacion;
    private Double precio;
    private Integer nplanta;
    private Double m2utiles;
    private Double m2construidos;
    private Integer habitaciones;
    private Boolean exterior;
    private String descripcion;
    private String conservacion;
    private Integer antiguedad;
    private Boolean comprar;
    private Boolean alquilar;
    private Boolean obranueva;


    @ManyToOne
    @JoinColumn(name = "idtipovivienda")
    private TipoViviendaEntity tipovivienda;
    
    @ManyToOne
    @JoinColumn(name = "idzona")
    private ZonaEntity zona;
    
    @ManyToOne
    @JoinColumn(name = "idanunciante")
    private UsuarioEntity usuario;

    @OneToMany(mappedBy = "vivienda")
    private List<FavoritoEntity> favoritos = new ArrayList<>();
    
    @OneToMany(mappedBy = "vivienda")
    private List<FileEntity> imagenes = new ArrayList<>();
    
    public ViviendaEntity() {
    }
            
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Integer getNplanta() {
        return nplanta;
    }

    public void setNplanta(Integer nplanta) {
        this.nplanta = nplanta;
    }

    public Double getM2utiles() {
        return m2utiles;
    }

    public void setM2utiles(Double m2utiles) {
        this.m2utiles = m2utiles;
    }

    public Double getM2construidos() {
        return m2construidos;
    }

    public void setM2construidos(Double m2construidos) {
        this.m2construidos = m2construidos;
    }

    public Integer getHabitaciones() {
        return habitaciones;
    }

    public void setHabitaciones(Integer habitaciones) {
        this.habitaciones = habitaciones;
    }

    public Boolean getExterior() {
        return exterior;
    }

    public void setExterior(Boolean exterior) {
        this.exterior = exterior;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getConservacion() {
        return conservacion;
    }

    public void setConservacion(String conservacion) {
        this.conservacion = conservacion;
    }

    public Integer getAntiguedad() {
        return antiguedad;
    }

    public void setAntiguedad(Integer antiguedad) {
        this.antiguedad = antiguedad;
    }

    public Boolean getComprar() {
        return comprar;
    }

    public void setComprar(Boolean comprar) {
        this.comprar = comprar;
    }

    public Boolean getAlquilar() {
        return alquilar;
    }

    public void setAlquilar(Boolean alquilar) {
        this.alquilar = alquilar;
    }

    public Boolean getObranueva() {
        return obranueva;
    }

    public void setObranueva(Boolean obranueva) {
        this.obranueva = obranueva;
    }

    public TipoViviendaEntity getTipovivienda() {
        return tipovivienda;
    }

    public void setTipovivienda(TipoViviendaEntity tipovivienda) {
        this.tipovivienda = tipovivienda;
    }

    public ZonaEntity getZona() {
        return zona;
    }

    public void setZona(ZonaEntity zona) {
        this.zona = zona;
    }

    public UsuarioEntity getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioEntity usuario) {
        this.usuario = usuario;
    }
    public int getFavoritos() {
        return favoritos.size();
    }
   public int getImagenes() {
        return imagenes.size();
    }

}

