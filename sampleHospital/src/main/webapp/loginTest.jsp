<%-- 
    Document   : loginTest.jsp
    Created on : Feb 17, 2016, 6:55:44 AM
    Author     : sandeshpoudel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" import="com.mycompany.samplehospital.model.User"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <!--[if IE]>
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <![endif]-->
    <title>Bootstrap Chat Box Example</title>
    <!-- BOOTSTRAP CORE STYLE CSS -->
    <link href="css/bootstrap.css" rel="stylesheet" />
    <!-- FONT AWESOME  CSS -->
    <link href="css/font-awesome.css" rel="stylesheet" />
    <!-- CUSTOM STYLE CSS -->
    <link href="css/style1.css" rel="stylesheet" />
    <style type="text/css" media="screen">
            
        </style>
          <% 
        if (null==session.getAttribute("user")){
            response.sendRedirect("/login.html");
        }else{
        }%>
       
    </head>
    <body>
          <div class="container">
        <div class="row pad-top pad-bottom">


            <div class=" col-lg-6 col-md-6 col-sm-6">
                <div class="chat-box-div">
                    <div class="chat-box-head">
                        GROUP CHAT HISTORY
                            <div class="btn-group pull-right">
                                <button type="button" class="btn btn-info dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
                                    <span class="fa fa-cogs"></span>
                                    <span class="sr-only">Toggle Dropdown</span>
                                </button>
                                <ul class="dropdown-menu" role="menu">
                                    <li><a href="#"><span class="fa fa-map-marker"></span>&nbsp;Invisible</a></li>
                                    <li><a href="#"><span class="fa fa-comments-o"></span>&nbsp;Online</a></li>
                                    <li><a href="#"><span class="fa fa-lock"></span>&nbsp;Busy</a></li>
                                    <li class="divider"></li>
                                    <li><a href="#"><span class="fa fa-circle-o-notch"></span>&nbsp;Logout</a></li>
                                </ul>
                            </div>
                    </div>
                    <div class="panel-body chat-box-main" id="displaybox">
                        <div class="chat-box-left">
                               Lorem ipsum dolor sit amet, consectetur adipiscing elit.
                           Lorem ipsum dolor sit amet, consectetur adipiscing elit.
                        </div>
                        <div class="chat-box-name-left">
                            <img src="img/user.png" alt="bootstrap Chat box user image" class="img-circle" />
                         
                            -  Justine Goliyad
                        </div>
                        <hr class="hr-clas" />
                        <div class="chat-box-right">
                            Lorem ipsum dolor sit amet, consectetur adipiscing elit.
                           Lorem ipsum dolor sit amet, consectetur adipiscing elit.
                        </div>
                        <div class="chat-box-name-right">
                            <img src="img/user.gif" alt="bootstrap Chat box user image" class="img-circle" />
                            - Romin Royeelin
                        </div>
                        <hr class="hr-clas" />
                        <div class="chat-box-left">
                            Lorem ipsum dolor sit amet, consectetur adipiscing elit.
                           Lorem ipsum dolor sit amet, consectetur adipiscing elit.
                        </div>
                        <div class="chat-box-name-left">
                            <img src="img/user.png" alt="bootstrap Chat box user image" class="img-circle" />
                            -  Justine Goliyad
                        </div>
                        <hr class="hr-clas" />
                        <div class="chat-box-right">
                            Lorem ipsum dolor sit amet, consectetur adipiscing elit.
                           Lorem ipsum dolor sit amet, consectetur adipiscing elit.
                        </div>
                        <div class="chat-box-name-right">
                            <img src="img/user.gif" alt="bootstrap Chat box user image" class="img-circle" />
                            - Romin Royeelin
                        </div>
                        <hr class="hr-clas" />
                    </div>
                    <div class="chat-box-footer">
                        <div class="input-group">
                            <input type="text" class="form-control" id="message" placeholder="Enter Text Here...">
                            <span class="input-group-btn">
                                <button class="btn btn-info" type="submit" id="send" onclick=" postRequest(); sendMessage();">SEND</button>
                            </span>
                        </div>
                    </div>

                </div>

            </div>
            <div class="col-lg-3 col-md-3 col-sm-3">
                <div class="chat-box-online-div">
                    <div class="chat-box-online-head">
                        ONLINE USERS (120)
                    </div>
                    <div class="panel-body chat-box-online">

                        <div class="chat-box-online-left">
                            <img src="img/user.png" alt="bootstrap Chat box user image" class="img-circle" />
                            -  Justine Goliyad
                            <br />
                            ( <small>Active from 3 hours</small> )
                        </div>
                        <hr class="hr-clas-low" />

                        <div class="chat-box-online-right">
                            <img src="img/user.gif" alt="bootstrap Chat box user image" class="img-circle" />
                            -  Romin Royeelin
                            <br />
                            ( <small>Active from 10 hours</small> )
                        </div>
                        <hr class="hr-clas-low" />
                        <div class="chat-box-online-left">
                            <img src="img/user.png" alt="bootstrap Chat box user image" class="img-circle" />
                            -  Justine Goliyad
                            <br />
                            ( <small>Active from 3 hours</small> )
                        </div>
                        <hr class="hr-clas-low" />

                        <div class="chat-box-online-right">
                            <img src="img/user.gif" alt="bootstrap Chat box user image" class="img-circle" />
                            -  Romin Royeelin
                            <br />
                            ( <small>Active from 10 hours</small> )
                        </div>
                        <hr class="hr-clas-low" />
                        <div class="chat-box-online-left">
                            <img src="img/user.png" alt="bootstrap Chat box user image" class="img-circle" />
                            -  Justine Goliyad
                            <br />
                            ( <small>Active from 3 hours</small> )
                        </div>
                        <hr class="hr-clas-low" />

                        <div class="chat-box-online-right">
                            <img src="img/user.gif" alt="bootstrap Chat box user image" class="img-circle" />
                            -  Romin Royeelin
                            <br />
                            ( <small>Active from 10 hours</small> )
                        </div>
                        <hr class="hr-clas-low" />
                        <div class="chat-box-online-left">
                            <img src="img/user.png" alt="bootstrap Chat box user image" class="img-circle" />
                            -  Justine Goliyad
                            <br />
                            ( <small>Active from 3 hours</small> )
                        </div>
                        <hr class="hr-clas-low" />

                        <div class="chat-box-online-right">
                            <img src="img/user.gif" alt="bootstrap Chat box user image" class="img-circle" />
                            -  Romin Royeelin
                            <br />
                            ( <small>Active from 10 hours</small> )
                        </div>
                        <hr class="hr-clas-low" />
                    </div>

                </div>

            </div>
            <div class="col-lg-3 col-md-3 col-sm-3">
                <div class="chat-box-new-div">
                    <div class="chat-box-new-head">
                        NEW CHATS (4)
                    </div>
                    <div class="panel-body chat-box-new">


                        <img src="img/user.png" alt="bootstrap Chat box user image" class="img-circle" />
                        (@Justine)
                           
                            <hr class="hr-clas-low" />


                        <img src="assets/img/user.gif" alt="bootstrap Chat box user image" class="img-circle" />
                        (@Romin)
                              
                            <hr class="hr-clas-low" />
                        <img src="img/user.png" alt="bootstrap Chat box user image" class="img-circle" />
                        (@Justine)
                           
                            <hr class="hr-clas-low" />


                        <img src="img/user.gif" alt="bootstrap Chat box user image" class="img-circle" />
                        (@Romin)
                              
                            <hr class="hr-clas-low" />
                    </div>

                </div>

            </div>
        </div>
    </div>

    <!-- USING SCRIPTS BELOW TO REDUCE THE LOAD TIME -->
    <!-- CORE JQUERY SCRIPTS FILE -->
    <script src="js/jquery-1.11.1.js"></script>
    <script src="js/function.js"></script>
   
    <script src="js/bootstrap.js"></script>
    
        
        <h1>Hello User </h1> <br>
       <%   User newUser = (User) session.getAttribute("user"); 
        %>
your name: <%=  newUser.getFullName() %> 
        <form action="Logout" method="POST">
            
         <input type="submit" value="logout"/>
         
            
            
        </form>
    </body>
</html>
