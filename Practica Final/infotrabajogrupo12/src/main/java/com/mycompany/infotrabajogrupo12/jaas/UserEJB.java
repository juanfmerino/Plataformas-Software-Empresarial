/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.infotrabajogrupo12.jaas;

import com.mycompany.infotrabajogrupo12.entities.GruposUsuarios;
import com.mycompany.infotrabajogrupo12.entities.Solicitar;
import com.mycompany.infotrabajogrupo12.entities.Users;
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
public class UserEJB {
    @PersistenceContext
    private EntityManager em;
    
    public Users createUser(Users user, String tipo) {
        try {
             user.setPassword(AuthenticationUtils.encodeSHA256(user.getPassword()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        GruposUsuarios group = new GruposUsuarios();
        group.setEmail(user.getEmail());
        group.setNombregrupo(tipo);
        em.persist(user);
        em.persist(group);
        return user;
    }
    
    
    public Users findByEmail(String email) {
        TypedQuery<Users> query = em.createNamedQuery("Users.findByEmail",Users.class);
        query.setParameter("email", email);
        Users users = null;
        
        try {
            users = query.getSingleResult();
        } catch (Exception e) {
        }
        return users;
    }
    
    public List<Users> findGrupo(String nombregrupo) {
        TypedQuery<Users> query = em.createNamedQuery("Users.findByGroupname", Users.class);
        query.setParameter("nombregrupo", nombregrupo);
        List<Users> empresas = null;
        try {
            empresas = query.getResultList();
        } catch (Exception e) {
        }
        return empresas;
    }

}
