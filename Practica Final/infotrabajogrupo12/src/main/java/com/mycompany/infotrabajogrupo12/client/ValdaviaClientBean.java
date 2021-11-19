/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.infotrabajogrupo12.client;

import com.mycompany.infotrabajogrupo12.json.ValdaviaReader;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.SessionScoped;
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
@SessionScoped
public class ValdaviaClientBean implements Serializable{
    
    Client client;
    WebTarget target;
    @PostConstruct
    
    public void init() {
        client = ClientBuilder.newClient();
        target = client.target("http://valdavia.infor.uva.es:8080/pagos/webresources/usuarios");
    }
    @PreDestroy
    public void destroy() {
        client.close();
    }
    
    public ValdaviaEJB getEmail(String email) {
        return target
        .register(ValdaviaReader.class)
        .path("{email}")
        .resolveTemplate("email", email)
        .request(MediaType.APPLICATION_JSON)
        .get(ValdaviaEJB.class);
    }
    
}
