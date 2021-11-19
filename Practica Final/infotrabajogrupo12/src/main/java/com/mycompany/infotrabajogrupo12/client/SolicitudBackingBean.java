/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.infotrabajogrupo12.client;

import com.mycompany.infotrabajogrupo12.entities.Solicitar;
import com.mycompany.infotrabajogrupo12.jaas.LoginView;
import java.io.Serializable;
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
public class SolicitudBackingBean implements Serializable{
    
    String solicitarId;
    String oferta;
    String email;
    private List<Solicitar> solicitudes;
    private Solicitar solicitud;
    
    
    
    @Inject private OfertaEJB ofertaEJB;
    
    @Inject LoginView login;

    @PostConstruct
    public void init() {
        solicitudes = ofertaEJB.findCandidato(login.getAuthenticatedUser().getEmail());        
    }

    public String getSolicitarId() {
        return solicitarId;
    }

    public void setSolicitarId(String solicitarId) {
        this.solicitarId = solicitarId;
    }

    public String getOferta() {
        return oferta;
    }

    public void setOferta(String oferta) {
        this.oferta = oferta;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Solicitar> getSolicitudes() {
        return solicitudes;
    }

    public void setSolicitudes(List<Solicitar> solicitudes) {
        this.solicitudes = solicitudes;
    }

    public Solicitar getSolicitud() {
        return solicitud;
    }

    public void setSolicitud(Solicitar solicitud) {
        this.solicitud = solicitud;
    }

    public LoginView getLogin() {
        return login;
    }

    public void setLogin(LoginView login) {
        this.login = login;
    }
    
    
    
}
