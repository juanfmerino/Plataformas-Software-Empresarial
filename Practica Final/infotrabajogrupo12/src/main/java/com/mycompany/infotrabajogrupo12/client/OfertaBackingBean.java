/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.infotrabajogrupo12.client;


import com.mycompany.infotrabajogrupo12.entities.Oferta;
import com.mycompany.infotrabajogrupo12.entities.Solicitar;
import com.mycompany.infotrabajogrupo12.entities.Users;
import com.mycompany.infotrabajogrupo12.jaas.LoginView;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

import javax.inject.Named;

/**
 *
 * @author Juand
 */

@Named 
@SessionScoped

public class OfertaBackingBean implements Serializable{
    
    private int ofertaId;
    private String nombre= "";
    String descripcion= "";
    private Date FechaVigen;
    private Date FechaIncor;
    private String EmailEmpresa= "";
    private String Estudios="";
    private String otros="";
    private int solicitudes;
    
    private List<Oferta> ofertas;
    private Oferta oferta;
    
    @Inject private OfertaEJB ofertaEJB;
    @Inject LoginView login;
    
    private Solicitar solicitud;
    
    
    @PostConstruct
    public void init() {
        ofertas = ofertaEJB.findEmpresa(login.getAuthenticatedUser().getEmail());
    }

    public Solicitar getSolicitud() {
        return solicitud;
    }

    public void setSolicitud(Solicitar solicitud) {
        this.solicitud = solicitud;
    }

    
    
    public List<Oferta> getOfertas() {
        return ofertas;
    }

    public void setOfertas(List<Oferta> ofertas) {
        this.ofertas = ofertas;
    }

    public Oferta getOferta() {
        return oferta;
    }

    public void setOferta(Oferta oferta) {
        this.oferta = oferta;
    }

    public OfertaEJB getOfertaEJB() {
        return ofertaEJB;
    }

    public void setOfertaEJB(OfertaEJB ofertaEJB) {
        this.ofertaEJB = ofertaEJB;
    }

    
    public int getOfertaId() {
        return ofertaId;
    }

    public void setOfertaId(int ofertaId) {
        this.ofertaId = ofertaId;
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

    public Date getFechaVigen() {
        return FechaVigen;
    }

    public void setFechaVigen(Date FechaVigen) {
        this.FechaVigen = FechaVigen;
    }

    public Date getFechaIncor() {
        return FechaIncor;
    }

    public void setFechaIncor(Date FechaIncor) {
        this.FechaIncor = FechaIncor;
    }

    public String getEmailEmpresa() {
        return EmailEmpresa;
    }

    public void setEmailEmpresa(String EmailEmpresa) {
        this.EmailEmpresa = EmailEmpresa;
    }

    public String getEstudios() {
        return Estudios;
    }

    public void setEstudios(String Estudios) {
        this.Estudios = Estudios;
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
    
    
}
