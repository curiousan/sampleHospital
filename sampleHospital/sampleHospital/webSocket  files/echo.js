
var Echo = Echo|| {};
Echo.sendMessage = function(){
    var echo = $('#echo');
    var message = echo.val();
    if(message != ''){
        console.log("info: your message is "+message);
        Echo.socket.send(message);
        echo.val.empty
    }
    
};
Echo.connect = function(host){
    if('webSocket' in window){
        Echo.socket = new WebSocket(host);
        console.log("info:  window webSocket created");
    }else if('MozWebSocket' in window){
        Echo.socket = new MozWebSocket(host);
        console.log("info: mozilla web socket created ");
        
    }else{
        return;
    }
    Echo.socket.onopen = function(){
        console.log('info: connection opned');
        $('#echo').keydown(function(evt){
            if(evt.keyCode ==13){
                Echo.sendMessage();
                console.log("info: enter key is pressed");
            }
            
        });
    };
    Echo.socket.onclose = function(){
        console.log("info: connection closed");
        
    };
    Echo.socket.onmessage = function(message){
        console.log("message: "+message.data);
        $('#echoBack').text(message.data);
    };

};

Echo.initialize = function(){
    var ep ='/websocket/echoa';
if(window.location.protocol =='http'){
    Echo.connect('ws://'+window.location.host +ep);
    console.log("info: windows ws protocol");
} else{
    Echo.connect('wss://'+window.location.host +ep);
    console.log("info: window wss protocol");
        
    }
};
Echo.initialize();






