

var baseUrl = "http://localhost:8080/sampleHospital/webapi/";
var LastMessageId = 0;
var wsocket;
var serviceLocation = "ws://localhost:8080/sampleHospital/chat/";

var chatBoxDetails = {};
function checkAuthentication(RoomName) {
    var chatBoxId = chatBoxDetails[RoomName];
    var newDiv = $(document.createElement('div'));




    var password = prompt("Please enter password", "eg pass");
    callToserver(chatBoxId, password, RoomName);




}


function addRoom() {

    var users = [];
    var roomName = $('#roomName').val();

    var password = $('#password').val();
     var senderId = $('#getSession').val();
    var senderName = $("#getSessionName").val(); 

    $("input[type=checkbox]:checked").each(function () {

        users.push($(this).attr('id'));
    });
   var tonotification = '<content></content><type></type><senderID></senderID><senderName></senderName><id></id><date></date>';
   for(var i=0; i<users.length; i++){
       tonotification+='<userList id="'+users[i]+'"></userList>';
    }
      var toSendnotificatio='<alert>'+tonotification+'</alert>';
     
       
    var alert = $.parseXML(toSendnotificatio);
    var ajaxalert = $(alert);
    ajaxalert.find('content').append("Hello staff You have been assigned to case: "+roomName+". you can find that case on your case List\n\
 and can authneticate with the password "+password +". Br Admin sandesh. ");
    ajaxalert.find('type').append("New Case");
    ajaxalert.find('senderID').append(senderId);
    ajaxalert.find('senderName').append(senderName);
    
    for(var i=0; i<users.length; i++){
    ajaxalert.find('#'+users[i]).append(users[i]);
    }
    $('select[name=type]').val('');
    $('textarea#message').val('');
    $('input[type=checkbox]').prop('checked', false);

    
    $.ajax({
        url: 'webapi/alerts',
        type: 'post',
        data: alert,
        processData: false,
        contentType: 'application/xml',
        success: function (xml) {
            
           
        },
        error: function () {
           
        }
    });
    
   
   
    var toappend = '<chatBoxId></chatBoxId><chatName></chatName><password></password>';
    for (var i = 0; i < users.length; i++) {
        toappend += '<users id="' + users[i] + '"></users>';
    }
    var toSend = '<chatBox>' + toappend + '</chatBox>';


    var alert = $.parseXML(toSend);
    var ajaxRoom = $(alert);

    ajaxRoom.find('password').append(password);
    ajaxRoom.find('chatName').append(roomName);
    for (var i = 0; i < users.length; i++) {
        ajaxRoom.find('#' + users[i]).append(users[i]);
    }

    $.ajax({
        url: 'webapi/chatbox',
        contentType: 'application/xml',
        processData: false,
        data: alert,
        type: "POST",
        success: function (xml) {


        },
        error: function () {
            alert('error adding room');
        }



    });
}
UsersidList = [];
function getUsers(data, status) {


 


    $(data).find('user').each(function () {
        var name = $(this).find('fullName').text();


        var otherid = $(this).find('id').text();



        var myId = $("#getSession").val();




        
            if (UsersidList.indexOf(otherid) === -1) {
                $('#group').append('<input  class="messageCheckbox" type="checkbox" id="' + otherid + '" value=' + name + '\');">' + name + '<a href="javaScript:void(0)"><br>');
                $('#userList').append('<input  class="messageCheckbox" type="checkbox"  id="' + otherid + '" value=' + name + '\');">' + name + '<a href="javaScript:void(0)"><br>');
                $('#listusers').append('<li class=' + name + ' onclick="requestMessage(\'' + myId + '\',\'' + otherid + '\',\'' + name + '\');"><a href="javaScript:void(0)">' + name + '</li>');
                UsersidList.push(otherid);
            } else {



            }

        

    });

}

var allChatBox = [];
function getChatBox(data, status) {



    var $chatBoxList = $("#groupChatBox");
    $(data).find('chatBox').each(function () {
        var name = $(this).find('chatName').text();
        var chatId = $(this).find('chatBoxId').text();

        var userList = [];
        $(this).find('users').each(function () {
            var Id = $(this).text();


            userList.push(Id);


        });

        var sessionId = $("#getSession").val();

        if (isInArray(sessionId, userList)) {


            chatBoxDetails[name] = chatId;
            if (allChatBox.indexOf(chatId) == -1) {
                $chatBoxList.append(' <li id="' + chatId + '" >' + name + '</li>');
                allChatBox.push(chatId);
            }
        }



    });
}
function isInArray(value, array) {
    return array.indexOf(value) > -1;
}


function sendMessage(senderId, recieverId) {

    var Inputmessage = $('#messageInput' + senderId + recieverId + '').val();

    var message = $.parseXML('<message><date></date><id></id><senderID></senderID><recieverID></recieverID><content></content></message>');
    var $messageXml = $(message);
    $messageXml.find('content').append(Inputmessage);
    $messageXml.find('senderID').append(senderId);
    $messageXml.find('recieverID').append(recieverId);


    ;
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


        }


    });

}
function requestMessage(senderID, recieverID, name) {


    var messageBox = 'messageBox' + senderID + recieverID + '';
    var messageContent = 'messageContent' + senderID + recieverID + '';
    var messageInput = 'messageInput' + senderID + recieverID + '';
    var messageButton = 'button' + senderID + recieverID + '';
    var messageFile = 'file' + senderID + recieverID + '';


    if ($("#" + messageBox).length) {



        $("#" + messageBox).toggle();





    } else {
        var chatDiv = $('#eachchatbox').html();
        $('#chatbox').append('<div id="' + messageBox + '"></div>');



        $("#" + messageBox).html(chatDiv);


        $('#' + messageBox).find("#username").append(name);
        $('#' + messageBox).find("#message-area").attr("id", messageContent);
      
        $('#' + messageBox).find("#file-area").attr("id", messageFile);
          $('#' + messageBox).find("#input-area").attr("id", messageInput).keypress(function(e){
              if(e.which ==13){
                  e.preventDefault();
                  sendMessage(senderID,recieverID);
              }
          });


        $('#' + messageBox).find('#button-append').append("<button id='" + messageButton + "' onClick=\"sendMessage('" + senderID + "','" + recieverID + "'); return false;\">send message</button>");





    }



    setInterval(function () {
        $.ajax({
            url: baseUrl + "messages?firstUserId=" + recieverID + "&secondUserId" + senderID,
            method: "GET",
            datatype: "xml",
            success: getMessage,
            error: function (xhr, ajaxOptions, thrownError) {


            }
        });

    }, 3000);
}

function getMessage(data, status) {
    var messageArrayList = [];
    var count;



    $(data).find('message').each(function () {
        var message = $(this).find('content').text();
        var date = $(this).find('date').text();
        var senderID = $(this).find('senderID').text();
        var receiverID = $(this).find('recieverID').text();
        var messageID = $(this).find('id').text();
        messageArrayList.push([message, date, senderID, receiverID, messageID]);
        count = messageArrayList.length;



    });


    for (var i = 0; i < count; i++) {
        if (LastMessageId <= messageArrayList[i][4]) {
            var senderId = messageArrayList[i][2];
            var recieverId = messageArrayList[i][3];
            var message = messageArrayList[i][0];
            var date = messageArrayList[i][1].replace('EET 2016', '');
            LastMessageId = messageArrayList[i][4];


            LastMessageId++;

            $("#messageContent" + senderId + recieverId).append('<div class="media" ><img src="https://www.globalvetlink.com/wp-content/uploads/2015/07/anonymous.png"class="img-circle" alt="Cinque Terre" width="30" height="30"><div class="media-body"><h5> <span class="small pull-right" id="time">' + date + '</span></h5><p>' + message + '</p></div></div>');
            $("#messageContent" + recieverId + senderId).append('<div class="media" ><img src="https://www.globalvetlink.com/wp-content/uploads/2015/07/anonymous.png"class="img-circle" alt="Cinque Terre" width="30" height="30" style="float :right !important;"> <div class="media-body" style="float :right !important;"><h5><span class="small pull-right" id="time">' + date + '</span></h5><p>' + message + '</p></div></div>');




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


    var content;
    var type;

    $(xml).find('alert').each(function () {
        content = $(this).find('content').text();
        type = $(this).find('type').text();
        var senderId = $(this).find('senderID').text();

        $('#alert').append('<a href="#" data-toggle="modal" data-target=' + content + ' id="viewAlert">' + type + '</a>');
     
    });
}
function getChatboxPolling() {

    setInterval(function () {



        $.ajax({
            url: baseUrl + "chatbox",
            method: "GET",
            datatype: "xml",
            success: getChatBox,
            error: function (xhr, ajaxOption, thrownError) {


            }


        }, 1000);
    });
}


$(document).ready(function () {
    
   
    $('#rightmenu').sidr({
        side: 'right',
        displace: false
    });
   
   
    if ($("#getSession").val()!= 2) {
        $("#addChatRoom").hide();
        $("#addUser").hide();

    }

    $("#getSessionName").hide();


    getUsersPolling();
    getChatboxPolling();


    function getUsersPolling() {
        setInterval(function () {


            $.ajax({
                url: 'webapi/users',
                method: "GET",
                datatype: "xml",
                success: getUsers,
                error: function (xhr, ajaxOption, thrownError) {


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


        }


    });
    $.ajax({
        url: baseUrl + "chatbox",
        method: "GET",
        datatype: "xml",
        success: getChatBox,
        error: function (xhr, ajaxOption, thrownError) {

        }


    });
    $('#getSession').hide();

    $('body').on('click', '#minimize', function () {
        var parentdiv = $(this).parents(':eq(3)');
        parentdiv.children('div#chat').slideToggle();
        
    }).on('click', '#remove', function () {
        var parentdiv = $(this).parents(':eq(4)');
        parentdiv.detach();
    });



});
