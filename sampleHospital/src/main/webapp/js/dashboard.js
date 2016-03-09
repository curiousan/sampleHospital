/* global id */

var baseUrl = "http://localhost:8080/sampleHospital/webapi/";
var LastMessageId = 0;
var wsocket;
var serviceLocation = "ws://localhost:8080/sampleHospital/chat/";
var $nickName = $('#getCurrentName').Text;
var myId = $('#getSession').val();
console.log($nickName)
var chatBoxDetails={};
  function checkAuthentication(RoomName){  
      var chatBoxId = chatBoxDetails[RoomName];
      var newDiv = $(document.createElement('div')); 
      
  
   
 
   var password =prompt("Please enter password", "eg pass");
    callToserver(chatBoxId,password,RoomName);
   
   
   
    
    }


function addRoom() {


    var roomName = $('#roomName').val();
    var password = $('#password').val();
    var chatRoom = $.parseXML('<chatBox><chatBoxId></chatBoxId><chatName></chatName><password></password></chatBox>');
    console.log(chatRoom);
    var ajaxRoom = $(chatRoom);
    ajaxRoom.find('chatName').append(roomName);
    ajaxRoom.find('password').append(password);

    $.ajax({
        url: 'webapi/chatbox',
        contentType: 'application/xml',
        processData: false,
        data: chatRoom,
        type: "POST",
        success: function () {
            alert('Room Added');
        },
        error: function () {
            alert('error adding room');
        }



    });
}

UsersidList=[];
function getUsers(data, status) {
    console.log("getting users list");
    console.log(data);
    //var userListBox = $(".userListDiv");
    //userListBox.empty();
    //userListBox.append('<ul class="usersList"></ul>');
    console.log('second call');
    $(data).find('user').each(function () {
        var name = $(this).find('fullName').text();
        var otherid = $(this).find('id').text();
        
        console.log('user Id lits:' +UsersidList);
            console.log('called once');
        var myId = $("#getSession").val();


        console.log('myid' + myId);
        console.log('otherid' + otherid);
        if (myId === otherid) {

        } else {
            if(UsersidList.indexOf(otherid)===-1){
            $('#group').append('<input  class="messageCheckbox" type="checkbox" id="'+otherid+'" value=' + name + '\');">' + name + '<a href="javaScript:void(0)"></option><br>');
            $('#userList').append('< input  class="messageCheckbox" type="checkbox"  id="'+otherid+'" value=' + name + '\');">' + name + '<a href="javaScript:void(0)"></option><br>');
            $('#listusers').append('<li class=' + name + ' onclick="requestMessage(\'' + myId + '\',\'' + otherid + '\',\'' + name + '\');"><a href="javaScript:void(0)">' + name + '</li>');
            UsersidList.push(otherid);
            }
            else{
            
            
           
           }

        }
        
    });

}
function getChatBox(data, status) {

    console.log("getting chatBOx list");
    console.log(data);
    var $chatBoxList = $("#groupChatBox");
    $(data).find('chatBox').each(function () {
        var name = $(this).find('chatName').text();
        var chatId = $(this).find('chatBoxId').text();
        var userList=[];
          $(this).find('users').find('users').each(function () {
          var Id = $(this).text();
           
 
              userList.push(Id);
            
              
          });
           
         var  sessionId = $("#getSession").val();
        
         if(isInArray(sessionId,userList)){
        
        
        chatBoxDetails[name] =chatId;


     $chatBoxList.append(' <li id="'+chatId+'" >' + name + '</li>');

         }
         console.log(chatId+" contains following user list"+userList);

    });
}
function isInArray(value, array) {
  return array.indexOf(value) > -1;
}


function sendMessage(senderId, recieverId) {
    console.log("send message is called");
    var Inputmessage = $('#messageInput' + senderId + recieverId + '').val();
    
    var message = $.parseXML('<message><date></date><id></id><senderID></senderID><recieverID></recieverID><content></content></message>');
    var $messageXml = $(message);
    $messageXml.find('content').append(Inputmessage);
    $messageXml.find('senderID').append(senderId);
    $messageXml.find('recieverID').append(recieverId);

    console.log(message);
    $('#messageInput' + senderId + recieverId).val('');
    $.ajax({
        url: baseUrl + "messages/",
        type: "POST",
        dataType: 'xml',
        data: message,
        processData: false,
        contentType: "application/xml",
        success: getMessage,
        error: function (xhr) {
            console.log(xhr.status);
            console.log("error!!!");
        }


    });

}
function requestMessage(senderID, recieverID,name) {


    var messageBox = 'messageBox' + senderID + recieverID + '';
    var messageContent = 'messageContent' + senderID + recieverID + '';
    var messageInput = 'messageInput' + senderID + recieverID + '';
    var messageButton = 'button' + senderID + recieverID + '';
    var messageFile= 'file' + senderID + recieverID + '';

    
    if ($("#" + messageBox).length) {



        $("#" + messageBox).toggle(100);





    } else {
        var chatDiv = $('#eachchatbox').html();
        $('#chatbox').append('<div id="' + messageBox + '"></div>');

        console.log("div has been created");
        $("#" + messageBox).html(chatDiv);
        $('#' + messageBox).find("#username").append(name);
        $('#' + messageBox).find("#message-area").attr("id", messageContent);
        $('#' + messageBox).find("#input-area").attr("id", messageInput);
        $('#' + messageBox).find("#file-area").attr("id", messageFile);
     


        $('#' + messageBox).find('#button-append').append("<button id='" + messageButton + "' onClick=\"sendMessage('" + senderID + "','" + recieverID + "'); return false;\">send message</button>");





    }



    setInterval(function () {
        $.ajax({
            url: baseUrl + "messages?firstUserId=" + recieverID + "&secondUserId" + senderID,
            method: "GET",
            datatype: "xml",
            success: getMessage,
            error: function (xhr, ajaxOptions, thrownError) {
                console.log(xhr.status);
                console.log(thrownError);
            }
        });

    }, 3000);
}

function getMessage(data, status) {
    var messageArrayList = [];
    var count;

    console.log("getting messages");
    console.log(data);
    $(data).find('message').each(function () {
        var message = $(this).find('content').text();
        var date = $(this).find('date').text();
        var senderID = $(this).find('senderID').text();
        var receiverID = $(this).find('recieverID').text();
        var messageID = $(this).find('id').text();
        messageArrayList.push([message, date, senderID, receiverID, messageID]);
        count = messageArrayList.length;
       
        console.log('Last Message Id ' + LastMessageId);
        console.log(' And the message is: ' + message);
    });
    console.log('The cout=nt is ' + count);
    for (var i = 0; i < count; i++) {
        if (LastMessageId <= messageArrayList[i][4]) {
            var senderId = messageArrayList[i][2];
            var recieverId = messageArrayList[i][3];
            var message = messageArrayList[i][0];
            var date    =messageArrayList[i][1].replace('EET 2016','');
            LastMessageId = messageArrayList[i][4];
            console.log('The message is ' + message);
            console.log('The last message id  ' + LastMessageId);
            console.log('The count is ' + count);
            LastMessageId++;
 
                $("#messageContent" + senderId + recieverId).append('<div class="media" ><a class="pull-left" href="#"><img src="https://upload.wikimedia.org/wikipedia/commons/7/7b/Yonina_Tulip.jpg"class="img-circle" alt="Cinque Terre" width="30" height="30"> </a><div class="media-body"><h5><span class="small pull-right" id="time">"'+date+'"</span></h5><p>'+message+'</p></div></div>');
               $("#messageContent" + recieverId + senderId).append('<div class="media" ><a class="pull-right" href="#"><img src="https://upload.wikimedia.org/wikipedia/commons/7/7b/Yonina_Tulip.jpg" class="img-circle" alt="Cinque Terre" width="30"<div class="media-body" style="float:right !important;"><h5> <span class="small pull-right">"'+date+'"</span></h5><p>'+message+'</p></div>');
 
                 
              
            
            
        }
    }



}

function connectToChatserver(RoomName) {


    if ($("#chatBox" + RoomName).length) {



        ($("#chatBox" + RoomName).toggle(100));





    } else {
        
       
    checkAuthentication(RoomName);

}
}
function getNotification(xml) {
    console.log(xml);
    var content;
    var type;
    var sagar="sagar";
    var sushil="sushil";
    $(xml).find('alert').each(function () {
        content = $(this).find('content').text();
        type = $(this).find('type').text();
        var senderId = $(this).find('senderID').text();
       
        $('#alert').append('<a href="#" data-toggle="modal" data-target='+content+' id="viewAlert">' + type + '</a>');
        // $('#container').find('#alertDetail').attr("id", content);
       // $('#container').find('#id').attr("id", content+sagar);
      //  $('#container').find('#message').attr("id", content+sushil);
        
                
        
        
        
       // $('#container').append(' <div class="modal fade" id="alertDetail" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">');
       // $('#message').append('<li class="form-control">' + " " + content + '</li>');
       // $('#type').append('<li class="form-control">' + type + '</li>');
    });
}

$(document).ready(function () {
     getUsersPolling();
    
      function getUsersPolling() {
        setInterval(function () {
            console.log('here');
            $.ajax({
                url: 'webapi/users',
                method: "GET",
                datatype: "xml",
                success: getUsers,
                error: function (xhr, ajaxOption, thrownError) {
                    console.log(xhr.status);
                    console.log(thrownError);
                }


            });
        }, 3000);
    }
    $.ajax({
            url: 'webapi/alerts',
            method: "GET",
            datatype: "xml",
            success: getNotification,
            error: function (xhr, ajaxOption, thrownError) {
                console.log(xhr.status);
                console.log(thrownError);
            }
        }, 3000);
   

    $("#eachchatbox").hide();
$('#sidr > #groupChatBox').on("click", "li", function (evt) {
        evt.preventDefault();
        var roomName = ($(this).text());
        connectToChatserver(roomName);

    });
   $("#hidemyauth").hide();
    $.ajax({
        url: baseUrl + "users",
        method: "GET",
        datatype: "xml",
        success: getUsers,
        error: function (xhr, ajaxOption, thrownError) {
            console.log(xhr.status);
            console.log(thrownError);
        }


    });
    $.ajax({
        url: baseUrl + "chatbox",
        method: "GET",
        datatype: "xml",
        success: getChatBox,
        error: function (xhr, ajaxOption, thrownError) {
            console.log(xhr.status);
            console.log(thrownError);
            console.log("error fetching data");
        }


    });
    $('#getSession').hide();

    $('#rightmenu').sidr({
        side: 'right',
        displace: false
    });




    $('body').on('click', '#minimize', function () {

        var parentdiv = $(this).parents(':eq(3)');
        parentdiv.children('div#chat').hide();

        parentdiv.children('div.portlet-heading').css({
            'background-color': 'blue',
            'position': 'fixed',
            'bottom': '0'
        });




    }).on('click', '#remove', function () {
        var parentdiv = $(this).parents(':eq(4)');
        parentdiv.toggle();
    });

});
