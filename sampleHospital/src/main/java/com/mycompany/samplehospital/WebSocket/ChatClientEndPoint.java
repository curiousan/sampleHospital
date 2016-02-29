/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.samplehospital.WebSocket;

/**
 *
 * @author sandeshpoudel
 */
import java.io.IOException;
import java.io.StringWriter;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonWriter;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
@ServerEndpoint("/chatClientEndPoint")
public class ChatClientEndPoint {
 static Set<Session> chatRoomUser = Collections.synchronizedSet(new HashSet<Session>());
   @OnOpen 
 
 public void handOpen(Session userSession) throws IOException{
     chatRoomUser.add(userSession);
     
        
    }
 @OnMessage
 public void handleMessage(String message ,Session userSession) throws IOException{

     String username=(String) userSession.getUserProperties().get("username");

       if (username == null){

        userSession.getUserProperties().put("username", message);
        userSession.getBasicRemote().sendText(buildJsonData("System","you are now connected as "+message));
     }else{
         Iterator<Session> it = chatRoomUser.iterator();
         while (it.hasNext()){
        	 
             it.next().getBasicRemote().sendText(buildJsonData(username,message));
         }
     }
 }
 @OnClose
 public void handleClose(Session userSession){
     chatRoomUser.remove(userSession);
     
 }
 private String buildJsonData(String username,String message){
 JsonObject jsonObject = Json.createObjectBuilder().add("message",username+" :"+message).build();   
   StringWriter stringWriter = new StringWriter();
   try(
       JsonWriter jsonWriter = Json.createWriter(stringWriter)){
      jsonWriter.write(jsonObject);
      return stringWriter.toString();
   }
   }
 }
 
   /* 
    Session user session = null ;
    private Message Handler message handler;
 
    public ChatClientEndpoint (URI endpointURI) {
        try {
            WebSocketContainer container = ContainerProvider
                    .getWebSocketContainer ();
            container.connectToServer ( this , endpointURI);
        } Catch (Exception e) {
            throw new RuntimeException (e);
        }
    }
 
    / **
     * Call Back hook for Connection open events.
     *
     *param User session
     * Which the user session is opened.
     * /
    OnOpen
    public void OnOpen (Session user session) {
        this .userSession = user session;
    }
 
    / **
     * Call Back Connection hook for close events.
     *
     *param User session
     * The user session Which is getting closed.
     *param Reason
     * The reason for the close connection
     * /
    OnClose
    public void onClose (Session user session, Close Reason reason) {
        this .userSession = null ;
    }
 
    / **
     * Call Back hook for Message Events. This method will be invoked when a
     * Client send a message.
     *
     *param Message
     * The text message
     * /
    OnMessage
    public void onMessage (String message) {
        if ( this .messageHandler! = null )
            this .messageHandler.handleMessage (message);
    }
 
    / **
     * Register message handler
     *
     *param Message
     * /
    public void addMessageHandler (Message Handler msgHandler) {
        this .messageHandler = msgHandler;
    }
 
    / **
     * Send a message.
     *
     *param User
     *param Message
     * /
    public void SendMessage (String message) {
        this .userSession.getAsyncRemote () sendText (message).;
    }
 
    / **
     * Message Handler.
     *
     * /
    public static interface Message Handler {
        public void handleMessage (String message);
    */
