/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.infotrabajogrupo12.json;

import com.mycompany.infotrabajogrupo12.client.ValdaviaEJB;
import com.mycompany.infotrabajogrupo12.entities.Solicitar;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
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
public class SolicitarReader implements MessageBodyReader<Solicitar>{
    
    @Override
    public boolean isReadable(Class<?> type, Type type1, Annotation[] antns, MediaType mt) {
        return Solicitar.class.isAssignableFrom(type);
    }

    @Override
    public Solicitar readFrom(Class<Solicitar> type, Type genericType, Annotation[] annotations, MediaType mediaType,MultivaluedMap<String, String> httpHeaders, InputStream entityStream)
        throws IOException, WebApplicationException {
        Solicitar solicitar = new Solicitar();
        JsonParser parser = Json.createParser(entityStream);
        while (parser.hasNext()) {
            switch (parser.next()) {
                case KEY_NAME:
                    String key = parser.getString();
                    parser.next();
                    switch (key) {
                        case "email":
                            solicitar.setEmail(parser.getString());
                            break;
                        case "ofertaId":
                            solicitar.setOferta(parser.getInt());
                            break;
                        case "solicitarId":
                            solicitar.setId(parser.getInt());
                            break;
                        default:
                            break;
                    }
                    break;
                default:
                    break;
            }
        }
        return solicitar;
    }
    
}
