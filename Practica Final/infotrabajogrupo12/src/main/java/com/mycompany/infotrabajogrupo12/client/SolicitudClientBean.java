/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.infotrabajogrupo12.client;

import com.mycompany.infotrabajogrupo12.entities.Oferta;
import com.mycompany.infotrabajogrupo12.entities.Solicitar;
import com.mycompany.infotrabajogrupo12.jaas.LoginView;
import com.mycompany.infotrabajogrupo12.json.OfertaReader;
import com.mycompany.infotrabajogrupo12.json.SolicitarReader;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Juand
 */

@Named
@RequestScoped
public class SolicitudClientBean {
    
    Client client;
        @Inject
    SolicitudBackingBean bean;
    WebTarget target;
    
    @Inject LoginView login;
    
    
    @PostConstruct
    
    public void init() {
    client = ClientBuilder.newClient();
    target =
    client.target("http://localhost:8080/infotrabajogrupo12/webresources/com.mycompany.infotrabajogrupo12.entities.solicitar");
    }
    
    @PreDestroy
    public void destroy() {
    client.close();
    }
    
    public Solicitar[] getSolicitudes() {
        return target
        .request()
        .get(Solicitar[].class);
    }
    
    public Oferta getSolicitud() {
        
        /*Solicitar s = target
        .register(SolicitarReader.class)
        .path("{solicitarId}")
        .resolveTemplate("solicitarId", bean.getSolicitud().getId())
        .request(MediaType.APPLICATION_JSON)
        .get(Solicitar.class);*/
        
        Oferta o = target
                .register(OfertaReader.class) 
                .path("{paqueteId}")
                .resolveTemplate("paqueteId", bean.getSolicitud().getOferta())
                .request(MediaType.APPLICATION_JSON)
                .get(Oferta.class);
        
        return o;
    }
    
    
}
