/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.infotrabajogrupo12.pedir;

import com.mycompany.infotrabajogrupo12.client.ValdaviaClientBean;
import com.mycompany.infotrabajogrupo12.client.ValdaviaEJB;
import com.mycompany.infotrabajogrupo12.entities.Oferta;
import com.mycompany.infotrabajogrupo12.jaas.LoginView;
import com.mycompany.infotrabajogrupo12.entities.Solicitar;
import com.mycompany.infotrabajogrupo12.rest.OfertaFacadeREST;
import com.mycompany.infotrabajogrupo12.rest.SolicitarFacadeREST;
import java.io.Serializable;
import java.util.Date;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.faces.flow.FlowScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;


/**
 *
 * @author Juand
 */
@Named
@FlowScoped("pedir")
public class Pedir implements Serializable{
    
    private int ofertaId;
    private String nombre= "";
    String descripcion= "";
    private Date FechaVigen;
    private Date FechaIncor;
    private String EmailEmpresa= "";
    private String Estudios="";
    private String otros="";
    private int solicitudes;
    private Oferta oferta=new Oferta();
    private Solicitar solicitar =new Solicitar();
    @Inject LoginView login;
    @EJB private OfertaFacadeREST p;
    @EJB private SolicitarFacadeREST s;
    
    ValdaviaClientBean client;
    
        @PersistenceContext
    EntityManager em;

        public int getOfertaDescripcion() {
        try {
            oferta= em.createNamedQuery("Oferta.findById", Oferta.class)
            .setParameter("id", ofertaId)
            .getSingleResult();
            return ofertaId;
        } catch (NoResultException e) {
            return -1;
        }
    }
        
        public void updateOferta() {
            
            oferta.setDescripcion(oferta.getDescripcion());
            oferta.setEmailEmpresa(oferta.getEmailEmpresa());
            oferta.setEstudios(oferta.getEstudios());
            oferta.setFechIncor(oferta.getFechIncor());
            oferta.setFechVigen(oferta.getFechVigen());
            oferta.setOtros(oferta.getOtros());
            oferta.setId(ofertaId);
            oferta.setSolicitudes(oferta.getSolicitudes()+1);
            oferta.setNombre(oferta.getNombre());
            
            solicitar.setEmail(login.getAuthenticatedUser().getEmail());
            solicitar.setOferta(oferta.getId());
            solicitar.setId(1);
            
            p.edit(oferta);
            s.edit(solicitar);
            
                 
    }
        
    public String validatePago(){
        
        
        ValdaviaEJB valdavia = new ValdaviaEJB();
        ValdaviaClientBean clientBean = new ValdaviaClientBean();
        clientBean.init();
            
        String email =login.getAuthenticatedUser().getEmail();
        String pago;

        
        try{
                valdavia = clientBean.getEmail(email);
                pago=valdavia.getPago();
                
            }catch(javax.ws.rs.NotFoundException e){
                return "cuentano";
            }
        
        //if(pago.equals("si") || pago.equals("no")){
            //System.out.println("HOLA");
            if(pago.equals("no")){
                return "pagonook";
            }
        //}
        return "pagook";
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

    public Oferta getOferta() {
        return oferta;
    }

    public void setOferta(Oferta oferta) {
        this.oferta = oferta;
    }

    public OfertaFacadeREST getP() {
        return p;
    }

    public void setP(OfertaFacadeREST p) {
        this.p = p;
    }
    
    
    
    
}
