/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.infotrabajogrupo12.client;

import com.mycompany.infotrabajogrupo12.entities.Oferta;
import com.mycompany.infotrabajogrupo12.jaas.LoginView;
import com.mycompany.infotrabajogrupo12.json.OfertaReader;
import com.mycompany.infotrabajogrupo12.json.OfertaWriter;
import com.mycompany.infotrabajogrupo12.rest.OfertaFacadeREST;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;


/**
 *
 * @author Juand
 */
@Named
@RequestScoped

public class OfertaClientBean {
    
    Client client;
    
    @Inject
    OfertaBackingBean bean;
    
    @EJB private OfertaFacadeREST p;
    
    
    WebTarget target;
    
    @Inject LoginView login;
    
    
    @PostConstruct
    
    public void init() {
    client = ClientBuilder.newClient();
    target =
    client.target("http://localhost:8080/infotrabajogrupo12/webresources/com.mycompany.entities.oferta");
    }
    
    @PreDestroy
    public void destroy() {
    client.close();
    }
    
    public Oferta[] getOfertas() {
        return target
        .request()
        .get(Oferta[].class);
    }
    
    public Oferta getOferta() {
        
        Oferta o = target
        .register(OfertaReader.class)
        .path("{paqueteId}")
        .resolveTemplate("paqueteId", bean.getOferta().getId())
        .request(MediaType.APPLICATION_JSON)
        .get(Oferta.class);
        
        bean.setEmailEmpresa(o.getEmailEmpresa());
        bean.setDescripcion(o.getDescripcion());
        bean.setEstudios(o.getEstudios());
        bean.setFechaIncor(o.getFechIncor());
        bean.setFechaVigen(o.getFechVigen());
        bean.setNombre(o.getNombre());
        bean.setOtros(o.getOtros());
        return o;
    }
    
        public Oferta getOferta1() {
        
        Oferta o = target
        .register(OfertaReader.class)
        .path("{paqueteId}")
        .resolveTemplate("paqueteId", bean.getSolicitud().getOferta())
        .request(MediaType.APPLICATION_JSON)
        .get(Oferta.class);
        
        bean.setEmailEmpresa(o.getEmailEmpresa());
        bean.setDescripcion(o.getDescripcion());
        bean.setEstudios(o.getEstudios());
        bean.setFechaIncor(o.getFechIncor());
        bean.setFechaVigen(o.getFechVigen());
        bean.setNombre(o.getNombre());
        bean.setOtros(o.getOtros());
        return o;
    }
    
     public List<Oferta> getByEmpresa(){
        return target
        .register(OfertaReader.class)
        .path("{emailEmpresa}")
        .resolveTemplate("emailEmpresa", login.getAuthenticatedUser().getEmail())
        .request(MediaType.APPLICATION_JSON)
        .get(List.class);
    }
    
    public void deleteOferta() {
        target.path("{paqueteId}")
        .resolveTemplate("paqueteId", bean.getOferta().getId())
        .request()
        .delete();
        
        bean.init();
   
    }
    
    public void addOferta() {
        
        
        
        Oferta o = new Oferta();
        o.setId(1);
        o.setDescripcion(bean.getDescripcion());
        o.setEmailEmpresa(login.getAuthenticatedUser().getEmail());
        o.setEstudios(bean.getEstudios());
        o.setFechIncor(bean.getFechaIncor());
        o.setFechVigen(bean.getFechaVigen());
        o.setNombre(bean.getNombre());
        o.setOtros(bean.getOtros());
        o.setSolicitudes(0);
        
        
        
        target.register(OfertaWriter.class)
        .request()
        .post(Entity.entity(o, MediaType.APPLICATION_JSON));
        
        bean.init();
        bean.setOfertaId(0);
        bean.setDescripcion("");
        bean.setEmailEmpresa("");
        bean.setEstudios("");
        bean.setFechaIncor(null);
        bean.setFechaVigen(null);
        bean.setNombre("");
        bean.setOtros("");
        bean.setSolicitudes(0);
    }
    
    public void actualizarOferta() {
        
        Oferta o = new Oferta();
        
        
        o.setId(bean.getOferta().getId());
        o.setDescripcion(bean.getDescripcion());
        o.setEmailEmpresa(bean.getEmailEmpresa());
        o.setEstudios(bean.getEstudios());
        o.setFechIncor(bean.getFechaIncor());
        o.setFechVigen(bean.getFechaVigen());
        o.setNombre(bean.getNombre());
        o.setOtros(bean.getOtros());
        o.setSolicitudes(bean.getSolicitudes());


        target.register(OfertaWriter.class)
                .path("{id}")
                .resolveTemplate("id", bean.getOferta().getId())
                .request()
                .put(Entity.entity(o, MediaType.APPLICATION_JSON));
        
        bean.setOfertaId(0);
        bean.setDescripcion("");
        bean.setEmailEmpresa("");
        bean.setEstudios("");
        bean.setFechaIncor(null);
        bean.setFechaVigen(null);
        bean.setNombre("");
        bean.setOtros("");
        bean.setSolicitudes(0);
        
    }
    
    public Date getMin() {
	    Calendar currentDate = Calendar.getInstance();
	    //logger.info("Min Age: "+currentDate.get(Calendar.MONTH)+"/"+currentDate.get(Calendar.DATE)+"/"+currentDate.get(Calendar.YEAR));
	    return currentDate.getTime();
    }

    
     /*public void actualizarEnvio() {
        Oferta p = new Oferta();
        
        p=target.path("{id}")
                .resolveTemplate("id", bean.getPaqueteId())
                .request()
                .get(Oferta.class);
    
        p.setId(bean.getPaqueteId());
        p.setDni(p.getDni());
        p.setNombre(p.getNombre());
        p.setNombEmpresa(p.getNombEmpresa());
        p.setFechEntrega(p.getFechEntrega());
        p.setDescripcion(p.getDescripcion());
        p.setPeso(p.getPeso());
        p.setEstado("Entregado");
        p.setEmail(null);


        target.register(OfertaWriter.class)
                .path("{id}")
                .resolveTemplate("id", bean.getPaqueteId())
                .request()
                .put(Entity.entity(p, MediaType.APPLICATION_JSON));
        
    }*/
}
