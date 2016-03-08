/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function callToserver(chatBoxId,password,RoomName){
    var roomBox = "chatBox" + RoomName;
    var roomContent = "content" + RoomName;
    var roomInput = "Input" + RoomName;
    var roomButton = "Button" + RoomName;
    var credential = {
         chatId:chatBoxId,
         passWord:password,
        
       
         
        };
        console.log("chatBox Id "+chatBoxId +" password "+password);
        $.ajax({
       
        url:'http://localhost:8080/sampleHospital/chatBoxAuthentication',
        type:'POST',
        data:credential,
        success: function(data){
            if (data == "true"){
          
     
        
       
     
        window["socket" + RoomName] = new WebSocket(serviceLocation + RoomName);
        var chatDiv = $('#eachchatbox').html();
        $('#chatbox').append('<div id="' + roomBox + '"></div>');


        $("#" + roomBox).html(chatDiv);

        $('#' + roomBox).find("#message-area").attr("id", roomContent);
        $('#' + roomBox).find("#input-area").attr("id", roomInput);


        $('#' + roomBox).find('#button-append').append("<button id='" + roomButton + "' onClick=\" sendRoomMessage('" + RoomName + "'); return false;\">send message</button>");

    window["socket" + RoomName].onmessage = function (evt) {
        var msg = JSON.parse(evt.data); // native API
        var $messageLine = $('<tr><td class="received">' + msg.received
                + '</td><td class="user label label-info">' + msg.sender
                + '</td><td class="message badge">' + msg.message
                + '</td></tr>');
        $('#content' + RoomName).append($messageLine);
    }

    } else{
        window.alert("wrong credential");
    }
   

                
            },
          
          
        
        error:function(){
            alert("error");
            
        }
        
    });
  
}
function sendRoomMessage(RoomName) {
    $message = $("#Input" + RoomName).val();
    console.log($message);

    var msg = '{"message":"' + $message + '", "sender":"'
            + $nickName + '", "received":""}';
    window["socket" + RoomName].send(msg);
    $('#Input' + RoomName).val('');

}