/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.infotrabajogrupo12.client;


import com.mycompany.infotrabajogrupo12.entities.Oferta;
import com.mycompany.infotrabajogrupo12.entities.Solicitar;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Juand
 */

@Stateless
public class OfertaEJB {
    
     @PersistenceContext
    private EntityManager em;
     
     public List<Oferta> findEmpresa(String emailEmpresa) {
        TypedQuery<Oferta> query = em.createNamedQuery("Oferta.findByEmailEmpresa", Oferta.class);
        query.setParameter("emailEmpresa", emailEmpresa);
        List<Oferta> ofertas = null;
        try {
            ofertas = query.getResultList();
        } catch (Exception e) {
        }
        return ofertas;
    }
     
     public List<Solicitar> findCandidato(String email) {
        TypedQuery<Solicitar> query = em.createNamedQuery("Solicitar.findByEmail", Solicitar.class);
        query.setParameter("email", email);
        List<Solicitar> solicitudes = null;
        try {
            solicitudes = query.getResultList();
        } catch (Exception e) {
        }
        return solicitudes;
    }
    
    
}
