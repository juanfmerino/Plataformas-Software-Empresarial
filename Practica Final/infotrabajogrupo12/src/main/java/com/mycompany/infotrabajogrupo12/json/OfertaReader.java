/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.infotrabajogrupo12.json;

import com.mycompany.infotrabajogrupo12.entities.Oferta;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.stream.JsonParser;
import javax.ws.rs.Consumes;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author Juand
 */
@Provider
@Consumes(MediaType.APPLICATION_JSON)

public class OfertaReader implements MessageBodyReader<Oferta>{
    
    @Override
    public boolean isReadable(Class<?> type, Type type1, Annotation[] antns, MediaType mt) {
            return Oferta.class.isAssignableFrom(type);
    }

    @Override
    public Oferta readFrom(Class<Oferta> type, Type genericType,Annotation[] annotations,MediaType mediaType,MultivaluedMap<String, String> httpHeaders,InputStream entityStream) throws IOException, WebApplicationException {
        Oferta paquete = new Oferta();
        JsonParser parser = Json.createParser(entityStream);
        
        while (parser.hasNext()) {
        switch (parser.next()) {
        case KEY_NAME:
        String key = parser.getString();
        parser.next();
        switch (key) {
                case "paqueteId":
                paquete.setId(parser.getInt());
            break;
            
            case "emailEmpresa":
                paquete.setEmailEmpresa(parser.getString());
            break;
            
            case "descripcion":
                paquete.setDescripcion(parser.getString());
            break;
            
            case "fechIncor":
                String fechaString = parser.getString();
                Date fecha = new Date();
                try {
                    fecha = new SimpleDateFormat("yyyy-MM-dd").parse(fechaString);
                } catch (ParseException ex) {
                    Logger.getLogger(OfertaReader.class.getName()).log(Level.SEVERE, null, ex);
                }
                paquete.setFechIncor(fecha);
            break;
            
            case "fechVigen":
                String fechaString1 = parser.getString();
                Date fecha1 = new Date();
                try {
                    fecha1 = new SimpleDateFormat("yyyy-MM-dd").parse(fechaString1);
                } catch (ParseException ex) {
                    Logger.getLogger(OfertaReader.class.getName()).log(Level.SEVERE, null, ex);
                }
                paquete.setFechVigen(fecha1);
            break;
            
            case "estudios":
                paquete.setEstudios(parser.getString());
            break;
            
            case "otros":
                paquete.setOtros(parser.getString());
            break;
            
            case "solicitudes":
            paquete.setSolicitudes(parser.getInt());
            break;
            
            case "nombre":
                paquete.setNombre(parser.getString());
            break;
            
                default:
            break;
        }
        break;
        default:
        break;
        }
        }
        return paquete;
    }
    
}
