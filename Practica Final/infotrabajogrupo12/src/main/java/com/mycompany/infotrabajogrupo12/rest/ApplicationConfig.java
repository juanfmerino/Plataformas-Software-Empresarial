/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.infotrabajogrupo12.rest;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author Juand
 */
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(com.mycompany.infotrabajogrupo12.json.OfertaReader.class);
        resources.add(com.mycompany.infotrabajogrupo12.json.OfertaWriter.class);
        resources.add(com.mycompany.infotrabajogrupo12.json.SolicitarReader.class);
        resources.add(com.mycompany.infotrabajogrupo12.json.ValdaviaReader.class);
        resources.add(com.mycompany.infotrabajogrupo12.rest.GruposUsuariosFacadeREST.class);
        resources.add(com.mycompany.infotrabajogrupo12.rest.OfertaFacadeREST.class);
        resources.add(com.mycompany.infotrabajogrupo12.rest.SolicitarFacadeREST.class);
        resources.add(com.mycompany.infotrabajogrupo12.rest.UsersFacadeREST.class);
    }
    
}
