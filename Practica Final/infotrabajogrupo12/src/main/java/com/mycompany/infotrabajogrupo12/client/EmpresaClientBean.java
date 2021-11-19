/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.infotrabajogrupo12.client;

import com.mycompany.infotrabajogrupo12.entities.Users;
import com.mycompany.infotrabajogrupo12.jaas.UserEJB;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

/**
 *
 * @author Juand
 */

@Named 
@RequestScoped
public class EmpresaClientBean {
    
Client client;
    WebTarget target;

    @Inject
    EmpresaBackingBean bean;
    
    FacesContext context = FacesContext.getCurrentInstance();
    HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
    
    @Inject
    private EmpresaEJB empresaEJB;
    
    @Inject private UserEJB userEJB;
        

    @PostConstruct
    public void init() {
        client = ClientBuilder.newClient();
        target = client.target("http://localhost:8080/infotrabajogrupo12/webresources/com.mycompany.infotrabajogrupo12.users");
        
    }

    @PreDestroy
    public void destroy() {
        client.close();
    }

    public Users[] getEmpresas() {
        
            return target
                .request()
                .get(Users[].class);
        
    }

    public Users getEmpresa() {

           Users e = target
                    .path("{empresaId}")
                    .resolveTemplate("empresaId", bean.getEmpresaId())
                    .request()
                    .get(Users.class);
            return e;

    }
    
   /* public List<Users> getUsers(){
    
        List<Users> u= new ArrayList<Users>();
        
        u=userEJB.findGrupo("empresas");
        u =  (List<Users>) target
                    .path("{empresaId}")
                    .resolveTemplate("empresaId", bean.getEmpresaId())
                    .request()
                    .get(Users.class);
            return u;
        
    }*/

    public void deleteEmpresa() {
        
        target.path("{empresaId}")
                .resolveTemplate("empresaId", bean.getEmpresa().getEmail())
                .request()
                .delete();
        empresaEJB.deleteEmpresa(bean.getEmpresa().getEmail());

        
    }
    
    
}
