<%-- 
    Document   : chat_Test
    Created on : Mar 2, 2016, 11:42:30 AM
    Author     : sandeshpoudel
--%>
<%-- 

<%@page import="com.mycompany.samplehospital.model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>

        <title>Chat Application</title>
        <style>
            .userListDiv{
                border:1px solid blue;
                overflow: auto;
                width:500px;

            }
            #messageBox{
                border:2px solid black;
                overflow: auto;
                height:auto;
                width:300px;
            }
            #messageInput{
                size:100px;
                border:1px solid black;
            }
            .messageBox{
                overflow: auto;
                margin:10px;
                border:1px solid red;
                width:500px;
            }
        </style>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet"
              href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
        <script
        src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <script
        src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
        <link href='http://fonts.googleapis.com/css?family=Raleway'
              rel='stylesheet' type='text/css'>
        <link rel="stylesheet"
              href="//cdn.jsdelivr.net/jquery.sidr/2.2.1/stylesheets/jquery.sidr.light.min.css">

    </head>
    <body>
        <% if (session.getAttribute("user") == null) {

                // New location to be redirected
                String site = "http://localhost:8080/sampleHospital/index.html";
                response.sendRedirect(site);
                return;

                   } %>
        <%   User newUser = (User) session.getAttribute("user");
        %>
        <input id="getSession" value=<%=  newUser.getId()%> />
        <input id="getSessionName" value=<%=  newUser.getFullName()%> />


        <div class="userListDiv"></div>





        <script>
            var baseUrl = "http://localhost:8080/sampleHospital/webapi/";
            $(document).ready(function () {
            $('#getSession').hide();
            $('#myId').hide();
            $('.chatbox').hide();
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
            });
            function sendMessage(senderId, recieverId) {
            var Inputmessage = $('#messageInput' + senderId + recieverId).val();
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
            function requestMessage(senderID, recieverID) {

            var timeInterval = 1000;
            var messageBox = 'messageBox' + senderID + recieverID + '';
            var messageInput = 'messageInput' + senderID + recieverID + '';
            if ($("#" + messageBox).length) {



            $("#" + messageBox).toggle(100);
            } else {
            $("body").append("<div id= '" + messageBox + "'  class='messageBox'></div>");
            $("#" + messageBox).append("<input type='text' class='messageInput' id='" + messageInput + "' placeHolder='your message here'/><button class='sendMessage'  onclick=\"sendMessage('" + senderID + "','" + recieverID + "');\">send message</button>");
            setInterval(function () {
            $.ajax({
            url: baseUrl + "messages/" + recieverID + "/" + senderID,
                    method: "GET",
                    datatype: "xml",
                    success: getMessage,
                    error: function (xhr, ajaxOptions, thrownError) {
                    console.log(xhr.status);
                    console.log(thrownError);
                    }
            });
            }, 500);
            }
            }
            function getMessage(data, status) {
            console.log("getting messages");
            console.log(data);
            var messageArrayList = [];
            //   var count;
            //    var LastMessageId2=sessionStorage.getItem("messageID"); 
            //   if(!LastMessageId2){
            //     LastMessageId2=0; 
            // }



            $(data).find('message').each(function () {


            var message = $(this).find('content').text();
            var date = $(this).find('date').text();
            var senderID = $(this).find('senderID').text();
            var receiverID = $(this).find('recieverID').text();
            var messageID = $(this).find('id').text();
            if ('lastMessageId' + senderID + recieverID){
            } else{
            window.'lastMessageId' + senderID + recieverID = 0;
            }



            messageArrayList.push([message, date, senderID, receiverID, messageID]);
            count = messageArrayList.length;
            });
            for (var i = 0; i < count; i++) {
            if ("lastMessageId" + senderId + recieverId < messageArrayList[i][4]) {
            var senderId = messageArrayList[i][2];
            var recieverId = messageArrayList[i][3];
            var message = messageArrayList[i][0];
            if ($("#messageBox" + senderId + recieverId).length) {
            $("#messageBox" + senderId + recieverId).append('<li>' + message + '</li>');
            } else {
            $("#messageBox" + recieverId + senderId).append('<li>' + message + '</li>');
            }
            console.log("total message" + count);
            console.log("info: current messageId " + messageArrayList[i][4]);
            console.log("info:messageId" + messageArrayList[i][4] + "compared with lastMessageID: " + "lastMessageCount" + senderId + recieverId);
            window.'lastMessageId' + senderID + recieverID = messageArrayList[i][4];
            //  sessionStorage.setItem("messageID",messageArrayList[i][4]);


            // localStorage.setItem("lastItem", LastMessageId);
            }
            console.log("info: messageId counter value" + sessionStorage.getItem(window.'lastMessageId' + senderID + recieverID));
            }


            }

            function getUsers(data, status) {
            console.log("getting users list");
            console.log(data);
            var userListBox = $(".userListDiv");
            userListBox.empty();
            userListBox.append('<ul class="usersList"></ul>');
            $(data).find('user').each(function () {
            var name = $(this).find('fullName').text();
            var otherid = $(this).find('id').text();
            var myId = $("#getSession").val();
            console.log(myId, otherid);
            "lastMessageId" + myId + otherid;
            $('.usersList').append('<li class=' + name + ' onclick="requestMessage(\'' + myId + '\',\'' + otherid + '\');"><a href="javaScript:void(0)">' + name + '</li>');
            });
            }
        </script>
    </body>
</htmlf
--%>
<%-- 
    Document   : chat_Test
    Created on : Mar 2, 2016, 11:42:30 AM
    Author     : sandeshpoudel
--%>

<%@page import="com.mycompany.samplehospital.model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
 
        <title>Chat Application</title>
        <style>
            .userListDiv{
                border:1px solid blue;
                overflow: auto;
                width:500px;

            }
            #messageBox{
                border:2px solid black;
                overflow: auto;
                height:auto;
                width:300px;
            }
            #messageInput{
                size:100px;
                border:1px solid black;
            }
            .messageBox{
                overflow: auto;
                margin:10px;
                border:1px solid red;
                width:500px;
            }
        </style>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet"
              href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
        <script
        src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <script
        src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
        <link href='http://fonts.googleapis.com/css?family=Raleway'
              rel='stylesheet' type='text/css'>
        <link rel="stylesheet"
              href="//cdn.jsdelivr.net/jquery.sidr/2.2.1/stylesheets/jquery.sidr.light.min.css">

    </head>
    <body>
               <% if (session.getAttribute("user") == null) { 
    
   // New location to be redirected
     String site = "http://localhost:8080/lat/index.html";
    response.sendRedirect(site);
    return;

  } %>
        <%   User newUser = (User) session.getAttribute("user");
        %>
        <input id="getSession" value=<%=  newUser.getId()%> />
        <input id="getSessionName" value=<%=  newUser.getFullName()%> />


        <div class="userListDiv"></div>





        <script>
            var baseUrl = "http://localhost:8080/sampleHospital/webapi/";
          
           var LastMessageId=0;
            $(document).ready(function () {
                $('#getSession').hide();

                $('#myId').hide();
                $('.chatbox').hide();




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

            });


            function sendMessage(senderId, recieverId) {
                var Inputmessage = $('#messageInput' + senderId + recieverId).val();
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
            function requestMessage(senderID, recieverID) {

                var timeInterval = 1000;
                var messageBox = 'messageBox' + senderID + recieverID + '';
                var messageInput = 'messageInput' + senderID + recieverID + '';
                if ($("#" + messageBox).length) {



                    $("#" + messageBox).toggle(100);





                } else {
                    $("body").append("<div id= '" + messageBox + "'  class='messageBox'></div>");


                    $("#" + messageBox).append("<input type='text' class='messageInput' id='" + messageInput + "' placeHolder='your message here'/><button class='sendMessage'  onclick=\"sendMessage('" + senderID + "','" + recieverID + "');\">send message</button>");

                    setInterval(function () {
                        $.ajax({
                            url: baseUrl + "messages/" + recieverID + "/" + senderID,
                            method: "GET",
                            datatype: "xml",
                            success: getMessage,
                            error: function (xhr, ajaxOptions, thrownError) {
                                console.log(xhr.status);
                                console.log(thrownError);
                            }
                        });

                    }, 500);
                }
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
                    //var lastMessageID=messageArrayList[messageArrayList.length-1][4];
                    //console.log('The id'+ lastMessageID);
                    console.log('Last Message Id '+ LastMessageId);
                    console.log(' And the message is: '+ message);
                });
                        console.log('The cout=nt is '+ count);
                for (var i = 0; i < count; i++) {
                    if (LastMessageId <= messageArrayList[i][4]) {
                        var senderId = messageArrayList[i][2];
                        var recieverId = messageArrayList[i][3];
                        var message = messageArrayList[i][0];
                        LastMessageId=messageArrayList[i][4];
                        console.log('The message is '  + message);
                        console.log('The last message id  '+LastMessageId);
                        console.log('The count is '+ count);
                         LastMessageId++;
                        

                        if ($("#messageBox" + senderId + recieverId).length) {
                            $("#messageBox" + senderId + recieverId).append('<li>' + message + '</li>');
                        } else {
                            $("#messageBox" + recieverId + senderId).append('<li>' + message + '</li>');

                        }
                        //LastMessageId=localStorage.setItem("lastCount",messageArrayList[i][4]);
                        
                     
                 }
             }



                }

                
            

            function getUsers(data, status) {
                console.log("getting users list");
                console.log(data);
                var userListBox = $(".userListDiv");
                userListBox.empty();
                userListBox.append('<ul class="usersList"></ul>');
                $(data).find('user').each(function () {
                    var name = $(this).find('fullName').text();
                    var otherid = $(this).find('id').text();


                    var myId = $("#getSession").val();

                    console.log(myId, otherid);



                    $('.usersList').append('<li class=' + name + ' onclick="requestMessage(\'' + myId + '\',\'' + otherid + '\');"><a href="javaScript:void(0)">' + name + '</li>');
                });

            }
        </script>
    </body>
</html>