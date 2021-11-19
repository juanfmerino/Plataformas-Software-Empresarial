/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.infotrabajogrupo12.client;

import com.mycompany.infotrabajogrupo12.entities.GruposUsuarios;
import com.mycompany.infotrabajogrupo12.entities.Users;
import com.mycompany.infotrabajogrupo12.jaas.UserEJB;
import com.mycompany.infotrabajogrupo12.rest.GruposUsuariosFacadeREST;
import com.mycompany.infotrabajogrupo12.rest.UsersFacadeREST;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Juand
 */

@Named
@SessionScoped
public class EmpresaBackingBean implements Serializable{
    
    String empresaId;
    String grupo;
    private List<Users> empresas;
    private Users empresa;
     
    @Inject private UserEJB userEJB;
    @Inject private EmpresaEJB empresaEJB;
    
        
    
    @PersistenceContext
    private EntityManager em;

    @PostConstruct
    public void init() {
        empresas = userEJB.findGrupo("empresas");        
    }

    public Users getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Users empresa) {
        this.empresa = empresa;
    }

    public UserEJB getUserEJB() {
        return userEJB;
    }

    public void setUserEJB(UserEJB userEJB) {
        this.userEJB = userEJB;
    }

    public List<Users> getEmpresas() {
        return empresas;
    }

    public void setEmpresas(List<Users> empresas) {
        this.empresas = empresas;
    }
    
    
    public String getEmpresaId() {
            return empresaId;
    }

    public void setEmpresaId(String empresaId) {
        this.empresaId = empresaId;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }
    
}
