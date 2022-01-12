package com.ausias.inmo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "usuario")
@JsonIgnoreProperties({"hibernateLazyInitialize", "handler"})
public class UsuarioEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String dni;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private String login;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    private String email;
    private boolean profesional;
    private String tlf;
    private String codpostal;
    private String direccion;
    

    /**@JsonIgnore
    private String token;*/

    private boolean validado;

    @ManyToOne
    @JoinColumn(name = "idtipousuario")
    private TipoUsuarioEntity tipousuario;

    @OneToMany(mappedBy = "usuario")
    private List<FavoritoEntity> favoritos = new ArrayList<>();
   
    public UsuarioEntity() {
    }

    public UsuarioEntity(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean getProfesional() {
        return profesional;
    }

    public void setProfesional(boolean profesional) {
        this.profesional = profesional;
    }

    /*public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }*/

    public boolean isValidado() {
        return validado;
    }

    public void setValidado(boolean validado) {
        this.validado = validado;
    }

    public String getTlf() {
        return tlf;
    }

    public void setTlf(String tlf) {
        this.tlf = tlf;
    }

    public String getCodpostal() {
        return codpostal;
    }

    public void setCodpostal(String codpostal) {
        this.codpostal = codpostal;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    

    /*public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }*/

    public TipoUsuarioEntity getTipousuario() {
        return tipousuario;
    }

    public void setTipousuario(TipoUsuarioEntity tipousuario) {
        this.tipousuario = tipousuario;
    }

    public int getFavoritos() {
        return favoritos.size();
    }

   
}
