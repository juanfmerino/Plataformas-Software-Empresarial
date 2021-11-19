/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.infotrabajogrupo12.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Juand
 */
@Entity
@Table(name = "grupos_usuarios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GruposUsuarios.findAll", query = "SELECT g FROM GruposUsuarios g"),
    @NamedQuery(name = "GruposUsuarios.findByEmail", query = "SELECT g FROM GruposUsuarios g WHERE g.email = :email"),
    @NamedQuery(name = "GruposUsuarios.findByNombregrupo", query = "SELECT g FROM GruposUsuarios g WHERE g.nombregrupo = :nombregrupo")})
public class GruposUsuarios implements Serializable {

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
    @Size(min = 1, max = 32)
    @Column(name = "nombregrupo")
    private String nombregrupo;

    public GruposUsuarios() {
    }

    public GruposUsuarios(String email) {
        this.email = email;
    }

    public GruposUsuarios(String email, String nombregrupo) {
        this.email = email;
        this.nombregrupo = nombregrupo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombregrupo() {
        return nombregrupo;
    }

    public void setNombregrupo(String nombregrupo) {
        this.nombregrupo = nombregrupo;
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
        if (!(object instanceof GruposUsuarios)) {
            return false;
        }
        GruposUsuarios other = (GruposUsuarios) object;
        if ((this.email == null && other.email != null) || (this.email != null && !this.email.equals(other.email))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.infotrabajogrupo12.GruposUsuarios[ email=" + email + " ]";
    }
    
}
