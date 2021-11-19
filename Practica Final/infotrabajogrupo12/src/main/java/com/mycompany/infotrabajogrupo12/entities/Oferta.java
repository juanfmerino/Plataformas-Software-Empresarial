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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "oferta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Oferta.findAll", query = "SELECT o FROM Oferta o"),
    @NamedQuery(name = "Oferta.findById", query = "SELECT o FROM Oferta o WHERE o.id = :id"),
    @NamedQuery(name = "Oferta.findByNombre", query = "SELECT o FROM Oferta o WHERE o.nombre = :nombre"),
    @NamedQuery(name = "Oferta.findByDescripcion", query = "SELECT o FROM Oferta o WHERE o.descripcion = :descripcion"),
    @NamedQuery(name = "Oferta.findByFechIncor", query = "SELECT o FROM Oferta o WHERE o.fechIncor = :fechIncor"),
    @NamedQuery(name = "Oferta.findByFechVigen", query = "SELECT o FROM Oferta o WHERE o.fechVigen = :fechVigen"),
    @NamedQuery(name = "Oferta.findByEmailEmpresa", query = "SELECT o FROM Oferta o WHERE o.emailEmpresa = :emailEmpresa"),
    @NamedQuery(name = "Oferta.findByEstudios", query = "SELECT o FROM Oferta o WHERE o.estudios = :estudios"),
    @NamedQuery(name = "Oferta.findByOtros", query = "SELECT o FROM Oferta o WHERE o.otros = :otros"),
    @NamedQuery(name = "Oferta.findBySolicitudes", query = "SELECT o FROM Oferta o WHERE o.solicitudes = :solicitudes")})
public class Oferta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fech_incor")
    @Temporal(TemporalType.DATE)
    private Date fechIncor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fech_vigen")
    @Temporal(TemporalType.DATE)
    private Date fechVigen;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "email_empresa")
    private String emailEmpresa;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "estudios")
    private String estudios;
    @Size(max = 255)
    @Column(name = "otros")
    private String otros;
    @Basic(optional = false)
    @NotNull
    @Column(name = "solicitudes")
    private int solicitudes;

    public Oferta() {
    }

    public Oferta(Integer id) {
        this.id = id;
    }

    public Oferta(Integer id, String nombre, String descripcion, Date fechIncor, Date fechVigen, String emailEmpresa, String estudios, int solicitudes) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fechIncor = fechIncor;
        this.fechVigen = fechVigen;
        this.emailEmpresa = emailEmpresa;
        this.estudios = estudios;
        this.solicitudes = solicitudes;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechIncor() {
        return fechIncor;
    }

    public void setFechIncor(Date fechIncor) {
        this.fechIncor = fechIncor;
    }

    public Date getFechVigen() {
        return fechVigen;
    }

    public void setFechVigen(Date fechVigen) {
        this.fechVigen = fechVigen;
    }

    public String getEmailEmpresa() {
        return emailEmpresa;
    }

    public void setEmailEmpresa(String emailEmpresa) {
        this.emailEmpresa = emailEmpresa;
    }

    public String getEstudios() {
        return estudios;
    }

    public void setEstudios(String estudios) {
        this.estudios = estudios;
    }

    public String getOtros() {
        return otros;
    }

    public void setOtros(String otros) {
        this.otros = otros;
    }

    public int getSolicitudes() {
        return solicitudes;
    }

    public void setSolicitudes(int solicitudes) {
        this.solicitudes = solicitudes;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Oferta)) {
            return false;
        }
        Oferta other = (Oferta) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.infotrabajogrupo12.entities.Oferta[ id=" + id + " ]";
    }
    
}
