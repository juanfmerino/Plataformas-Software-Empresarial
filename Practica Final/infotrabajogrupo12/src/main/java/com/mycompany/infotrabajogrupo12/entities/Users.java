/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.infotrabajogrupo12.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Juand
 */
@Entity
@Table(name = "users")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Users.findByGroupname", query = "SELECT u FROM Users u, GruposUsuarios g WHERE u.email = g.email AND g.nombregrupo = :nombregrupo"),
    @NamedQuery(name = "Users.findAll", query = "SELECT u FROM Users u"),
    @NamedQuery(name = "Users.findByEmail", query = "SELECT u FROM Users u WHERE u.email = :email"),
    @NamedQuery(name = "Users.findByNombre", query = "SELECT u FROM Users u WHERE u.nombre = :nombre"),
    @NamedQuery(name = "Users.findByPassword", query = "SELECT u FROM Users u WHERE u.password = :password"),
    @NamedQuery(name = "Users.findByFechNac", query = "SELECT u FROM Users u WHERE u.fechNac = :fechNac"),
    @NamedQuery(name = "Users.findByTelefono", query = "SELECT u FROM Users u WHERE u.telefono = :telefono"),
    @NamedQuery(name = "Users.findByTarjetaCredito", query = "SELECT u FROM Users u WHERE u.tarjetaCredito = :tarjetaCredito")})
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "password")
    private String password;
    @Column(name = "fech_nac")
    @Temporal(TemporalType.DATE)
    private Date fechNac;
    @Size(max = 9)
    @Column(name = "telefono")
    private String telefono;
    @Size(max = 64)
    @Column(name = "tarjeta_credito")
    private String tarjetaCredito;

    public Users() {
    }

    public Users(String email) {
        this.email = email;
    }
    
    public Users(String email, String nombre, String password,String telefono,String tarjeta,Date fecha) {
        this.email = email;
        this.nombre = nombre;
        this.password = password;
        this.fechNac= fecha;
        this.tarjetaCredito=tarjeta;
        this.telefono=telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getFechNac() {
        return fechNac;
    }

    public void setFechNac(Date fechNac) {
        this.fechNac = fechNac;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getTarjetaCredito() {
        return tarjetaCredito;
    }

    public void setTarjetaCredito(String tarjetaCredito) {
        this.tarjetaCredito = tarjetaCredito;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (email != null ? email.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Users)) {
            return false;
        }
        Users other = (Users) object;
        if ((this.email == null && other.email != null) || (this.email != null && !this.email.equals(other.email))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.infotrabajogrupo12.Users[ email=" + email + " ]";
    }
    
}
