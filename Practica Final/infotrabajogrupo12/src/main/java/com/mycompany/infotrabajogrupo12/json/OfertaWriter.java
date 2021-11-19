/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.infotrabajogrupo12.json;

import com.mycompany.infotrabajogrupo12.entities.Oferta;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.json.Json;
import javax.json.stream.JsonGenerator;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author Juand
 */

@Provider
@Produces(MediaType.APPLICATION_JSON)

public class OfertaWriter implements MessageBodyWriter<Oferta>{

    @Override
    public boolean isWriteable(Class<?> type, Type type1, Annotation[] antns, MediaType mt) {
       return Oferta.class.isAssignableFrom(type);
    }

    @Override
    public long getSize(Oferta t, Class<?> type, Type type1, Annotation[] antns, MediaType mt) {
        return -1;
    }

    @Override
   public void writeTo(Oferta t, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream) throws IOException, WebApplicationException {
        
       JsonGenerator gen = Json.createGenerator(entityStream);
        
        DateFormat fechaFormat = new SimpleDateFormat("yyyy-MM-dd");
        String fechaIncor = fechaFormat.format(t.getFechIncor());
        String fechaVigen = fechaFormat.format(t.getFechVigen());
        
        gen.writeStartObject()
            .write("paqueteId", t.getId())
            .write("emailEmpresa", t.getEmailEmpresa())
            .write("descripcion", t.getDescripcion())
            .write("fechVigen", fechaVigen)
            .write("fechIncor", fechaIncor)
            .write("estudios", t.getEstudios())
            .write("otros", t.getOtros())
            .write("solicitudes", t.getSolicitudes())
            .write("nombre", t.getNombre())
            .writeEnd();
        gen.flush();

    }
    
    
    
}
