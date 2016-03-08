/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.samplehospital.WebSocket;

import java.io.IOException;
import javax.websocket.MessageHandler;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.RemoteEndpoint;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 *
 * @author sandeshpoudel
 */
@ServerEndpoint("/websocket/echoa")
public class EndPoint {

    @OnOpen
    public void onOpen(Session session) {
        RemoteEndpoint.Basic remoteEndpointBasic = session.getBasicRemote();
        session.addMessageHandler(new EchoMessageHandler(remoteEndpointBasic));

    }

    private static class EchoMessageHandler implements MessageHandler.Whole<String> {

        private final RemoteEndpoint.Basic remoteEndpointBasic;

        private EchoMessageHandler(RemoteEndpoint.Basic remoteEndpointBasic) {
            this.remoteEndpointBasic = remoteEndpointBasic;
        }

    @OnMessage
        public void onMessage(String message) {

            try {
                if (remoteEndpointBasic != null) {
                    remoteEndpointBasic.sendText("System: " + message);
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        }
        

    }
}
