/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

 var $nickName;
$(document).ready(function () {
   $nickName = $("#getSessionName").val(); 
   $nickID = $("#getSession").val(); 
   
   
   
});



function callToserver(chatBoxId,password,RoomName){
    
    var roomBox = "chatBox" + RoomName;
    var roomContent = "content" + RoomName;
    var roomInput = "Input" + RoomName;
    var roomButton = "Button" + RoomName;
    var ImageInput  = "imageInput"+RoomName;
    var credential = {
         chatId:chatBoxId,
         passWord:password,
        
       
         
        };
        
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
        $('#' + roomBox).find("#username").append(RoomName);

   $('#' + roomBox).find('#button-append').append("<input id='"+ImageInput+"' type='file'><button id='" + roomButton + "' onClick=\" sendRoomMessage('" + RoomName + "'); return false;\">send message</button>");

    window["socket" + RoomName].onmessage = function (evt) {
        var msg = JSON.parse(evt.data); // native API
       
        $messageSteam =$('<a class="pull-left" href="#"><h6>'+msg.sender+'</h6><img src="https://upload.wikimedia.org/wikipedia/commons/7/7b/Yonina_Tulip.jpg"class="img-circle" alt="Cinque Terre" width="30" height="30"></a><div class="media-body"><img style="max-width:150px" src="'+msg.image+'"/><p>'+msg.message+'</p><h5><span class="small pull-right" id="time">'+msg.received+'</span></h5></div></div>');
       
      
      
        $('#content' + RoomName).append($messageSteam);
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
    var base64 = "";
    $message = $("#Input" + RoomName).val();
   
    var fileInput = document.getElementById("imageInput"+RoomName+"");
 
    var fileSelected = fileInput.files;
    if(!fileSelected){
        
    }
    if(fileSelected[0]){
     var fileToLoad = fileSelected[0];
      var fileReader = new FileReader();
    fileReader.onload = function(fileLoadedEvent){
     var srcData = fileLoadedEvent.target.result;
     var msg = '{"message":"' + $message + '",  "image":"'
            + srcData + '","sender":"'
            + $nickName + '", "received":""}';
    
     window["socket" + RoomName].send(msg);
    $("#imageInput"+RoomName).val('');
    };
    fileReader.readAsDataURL(fileToLoad);
    }else{
        
    
   
   
    var msg =  '{"message":"' + $message + '",  "image":"'
            + ""+ '","sender":"'
            + $nickName + '", "received":""}';
    window["socket" + RoomName].send(msg);
     $("#Input" + RoomName).val('').focus(); 
      }
}       