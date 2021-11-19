/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.infotrabajogrupo12.client;

import com.mycompany.infotrabajogrupo12.entities.GruposUsuarios;
import com.mycompany.infotrabajogrupo12.entities.Users;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Juand
 */

@Stateless
public class EmpresaEJB {
    
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    EmpresaBackingBean bean;

    public void deleteEmpresa(String email) {
        //Users user = findUsuarioByEmail(email);
        GruposUsuarios group = findGrupoByEmail(email);
 
        //em.remove(user);
        em.remove(group);
        
                
        bean.init();
        bean.getEmpresas();

    }

    public Users findUsuarioByEmail(String email) {
        TypedQuery<Users> query = em.createNamedQuery("Usuarios.findByEmail", Users.class);
        query.setParameter("email", email);
        
        Users user = null;
        try {
            user = query.getSingleResult();
        } catch (Exception e) {
        }
        return user;
    }
    
    public GruposUsuarios findGrupoByEmail(String email) {
        TypedQuery<GruposUsuarios> query = em.createNamedQuery("GruposUsuarios.findByEmail", GruposUsuarios.class);
        query.setParameter("email", email);
        GruposUsuarios group = null;
        try {
            group = query.getSingleResult();
        } catch (Exception e) {
        }
        return group;
    }
    
    
    
    public GruposUsuarios findGrupoByTipo(String Nombregrupo) {
        TypedQuery<GruposUsuarios> query = em.createNamedQuery("GruposUsuarios.findByTipo", GruposUsuarios.class);
        query.setParameter("nombregrupo", Nombregrupo);
        GruposUsuarios group = null;
        try {
            group = query.getSingleResult();
        } catch (Exception e) {
        }
        return group;
    }
}
