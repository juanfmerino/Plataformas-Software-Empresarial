/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.infotrabajogrupo12.json;

import com.mycompany.infotrabajogrupo12.client.ValdaviaEJB;
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
public class ValdaviaReader implements MessageBodyReader<ValdaviaEJB>{
    
    @Override
    public boolean isReadable(Class<?> type, Type type1, Annotation[] antns, MediaType mt) {
        return ValdaviaEJB.class.isAssignableFrom(type);
    }

    @Override
    public ValdaviaEJB readFrom(Class<ValdaviaEJB> type, Type genericType, Annotation[] annotations, MediaType mediaType,MultivaluedMap<String, String> httpHeaders, InputStream entityStream)
        throws IOException, WebApplicationException {
        ValdaviaEJB valdavia = new ValdaviaEJB();
        JsonParser parser = Json.createParser(entityStream);
        while (parser.hasNext()) {
            switch (parser.next()) {
                case KEY_NAME:
                    String key = parser.getString();
                    parser.next();
                    switch (key) {
                        case "email":
                            valdavia.setEmail(parser.getString());
                            break;
                        case "estadopago":
                            valdavia.setPago(parser.getString());
                            break;
                        default:
                            break;
                    }
                    break;
                default:
                    break;
            }
        }
        return valdavia;
    }
}
