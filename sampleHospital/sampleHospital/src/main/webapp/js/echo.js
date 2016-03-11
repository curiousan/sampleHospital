/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


"use strict";
var Echo = Echo|| {};
Echo.sendMessage = function(){
    var echo = $('#echo');
    var message = echo.val();
    if(message != ''){
       Echo.socket.send(message);
        echo.val.empty
    }
    }
    

Echo.connect = function(host){
    
     Echo.socket = new WebSocket("ws://localhost:8080/sampleHospital/websocket/echoa");
     console.log(" info: connection attemp.")

    
  
    
    Echo.socket.onopen = function(){
        console.log('info: connection opned');
        $('#echo').keydown(function(evt){
            if(evt.keyCode ==13){
                Echo.sendMessage();
                console.log("info :send message ");
            }
            
        });
    };
    Echo.socket.onclose = function(){
        console.log("info: connection closed");
        
    };
    Echo.socket.onmessage = function(message){
        console.log("message: "+message.data);
        
        $('#echoBack').Text=("<h2>"+message.data+"</h2>");
    };

};

Echo.initialize = function(){
    var ep ='/sampleHospital/websocket/chat';

    Echo.connect('ws://'+ window.location.host+ep)
    console.log("info: windows ws protocol");

};
Echo.initialize();






