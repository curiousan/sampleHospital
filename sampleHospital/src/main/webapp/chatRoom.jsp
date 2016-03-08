<%-- 
    Document   : chatRoom
    Created on : Feb 22, 2016, 6:31:40 PM
    Author     : sandeshpoudel
--%>

<%@page import="com.mycompany.samplehospital.model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%
            if (null == session.getAttribute("user")) {
                response.sendRedirect("http://localhost:8080/sampleHospital/index.html");
                  
                return;
            } else {
            }%>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <meta charset="utf-8">

        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="Micha Kops">

        <script src="resource/js/jquery-1.10.2.min.js"></script>

        <!-- Le styles -->
        <link href="./resource/css/bootstrap.css" rel="stylesheet">
        <style type="text/css">
            body {
                padding-top: 40px;
                padding-bottom: 40px;
                background-color: #f5f5f5;
            }

            .form-signin {
                max-width: 300px;
                padding: 19px 29px 29px;
                margin: 0 auto 20px;
                background-color: #fff;
                border: 1px solid #e5e5e5;
                -webkit-border-radius: 5px;
                -moz-border-radius: 5px;
                border-radius: 5px;
                -webkit-box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
                -moz-box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
                box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
            }

            .form-signin .form-signin-heading,.form-signin .checkbox {
                margin-bottom: 10px;
            }

            .form-signin input[type="text"],.form-signin input[type="password"] {
                font-size: 16px;
                height: auto;
                margin-bottom: 15px;
                padding: 7px 9px;
            }

            #chatroom {
                font-size: 16px;
                height: 40px;
                line-height: 40px;
                width: 300px;
            }

            .received {
                width: 160px;
                font-size: 10px;
            }
        </style>
        <link href="./resource/css/bootstrap-responsive.css" rel="stylesheet">

        <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
        <!--[if lt IE 9]>
              <script src="./resource/js/html5shiv.js"></script>
            <![endif]-->

        <!-- Fav and touch icons -->
        <link rel="apple-touch-icon-precomposed" sizes="144x144"
              href="./resource/ico/apple-touch-icon-144-precomposed.png">
        <link rel="apple-touch-icon-precomposed" sizes="114x114"
              href="./resource/ico/apple-touch-icon-114-precomposed.png">
        <link rel="apple-touch-icon-precomposed" sizes="72x72"
              href="./resource/ico/apple-touch-icon-72-precomposed.png">
        <link rel="apple-touch-icon-precomposed"
              href="./resource/ico/apple-touch-icon-57-precomposed.png">
        <link rel="shortcut icon" href="./resource/ico/favicon.png">
        <script>
            var wsocket;
            var serviceLocation = "ws://localhost:8080/sampleHospital/chat/";
            var $nickName;        
            var $message;
            var $chatWindow;
            var room = '';

            function onMessageReceived(evt) {
                var msg = JSON.parse(evt.data); // native API
                var $messageLine = $('<tr><td class="received">' + msg.received
                        + '</td><td class="user label label-info">' + msg.sender
                        + '</td><td class="message badge">' + msg.message
                        + '</td></tr>');
                $chatWindow.append($messageLine);
            }
            function sendMessage() {
                var msg = '{"message":"' + $message.val() + '", "sender":"'
                        + $nickName.val() + '", "received":""}';
                wsocket.send(msg);
                $message.val('').focus();
            }

            function connectToChatserver() {
                room = $('#case').val();
                wsocket = new WebSocket(serviceLocation + room);
                wsocket.onmessage = onMessageReceived;
            }

            function leaveRoom() {
                wsocket.close();
                $chatWindow.empty();
                $('.chat-wrapper').hide();
                $('.chat-signin').show();
                $nickName.focus();
            }

            $(document).ready(function () {
                $nickName = $('#name');
                $nickName.hide();

                $message = $('#message');
                $chatWindow = $('#response');
                $('.chat-wrapper').hide();
                $nickName.focus();

                $('#enterRoom').click(function (evt) {
                    evt.preventDefault();
                    connectToChatserver();
                    $('.chat-wrapper h2').text(room);
                    $('.chat-signin').hide();
                    $('.chat-wrapper').show();
                    $message.focus();
                });
                $('#do-chat').submit(function (evt) {
                    evt.preventDefault();
                    sendMessage()
                });

                $('#leave-room').click(function () {
                    leaveRoom();
                });
            });
        </script>

    </head>
    <body>
        <%   User newUser = (User) session.getAttribute("user");
        %>
        <input id="name" value=<%=  newUser.getFullName()%> />

        <div class="container chat-signin">
            <form class="form-signin">
                <h2 class="form-signin-heading">Chat sign in</h2>

                <div class="btn-group">
                    <label for="chatroom">Chatroom</label> 
                    <select   size="1" id="case">
                        id="chatroom">
                        <option  value="case1">Case 1</option>
                        <option value="case2">Case 2</option>
                        <option  value="case3">Case 3</option>
                        <option value="case4">Case 4</option>
                    </select>
                </div>
                <button class="btn btn-large btn-primary" type="submit"
                        id="enterRoom">Enter</button>
            </form>
        </div>
        <!-- /container -->

        <div class="container chat-wrapper">
            <form id="do-chat">
                <h2 class="alert alert-success"></h2>
                <table id="response" class="table table-bordered"></table>
                <fieldset>
                    <legend>Enter your message..</legend>
                    <div class="controls">
                        <input type="text" class="input-block-level" placeholder="Your message..." id="message" style="height:60px"/>

                        <input type="submit" class="btn btn-large btn-block btn-primary"

                               <div class="form-group">
                            <label class="control-label col-sm-2 "for="img"> Image</label>
                            <div class="col-sm-5">
                                <input type="file" name="image" class="form-control" id="zpc" >
                            </div>
                            <button class="btn"  id="imageinput" >send file </button>

                            <button class="btn btn-large btn-block" type="button" id="leave-room">Leave
                                room</button>
                        </div>
                </fieldset>
            </form>
        </div>
        <div class="container">

            <a href="chat_Test.jsp"><button type="button" class="btn btn-default">Private chat</button></a>
            <a href="fileSend.html"><button type="button" class="btn btn-default">File streaming</button></a>
            <a href="index2.html"><button type="button" class="btn btn-default">profile</button></a>
            <!-- <a href="chat_Test.jsp"><button type="button" class="btn btn-default">Private chat</button></a> -->

        </div>          
    </body>
</html>
