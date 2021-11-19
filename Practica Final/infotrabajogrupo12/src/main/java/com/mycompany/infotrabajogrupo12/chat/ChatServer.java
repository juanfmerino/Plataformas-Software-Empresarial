/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.infotrabajogrupo12.chat;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 *
 * @author Juand
 */
@ServerEndpoint("/websocket")

public class ChatServer {
    private static final Set<Session> peers = Collections.synchronizedSet(new HashSet<Session>());

    /**
     *
     * @param peer
     */
    @OnOpen
    
    public void onOpen(Session peer) {
        peers.add(peer);
    }

    /**
     *
     * @param peer
     */
    @OnClose
    
    public void onClose(Session peer) {
    peers.remove(peer);
    }
    
    /**
     *
     * @param message
     * @param client
     * @throws IOException
     * @throws EncodeException
     */
    @OnMessage
    public void message(String message, Session client) throws IOException, EncodeException {
        for (Session peer : peers) {
            peer.getBasicRemote().sendText(message);
        }
    }
}
