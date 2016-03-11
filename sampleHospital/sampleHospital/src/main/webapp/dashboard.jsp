<%@page import="com.mycompany.samplehospital.model.User"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Chat Application</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
        <script src="//code.jquery.com/jquery-1.10.2.js"></script>
        <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>

        <link rel="stylesheet"
              href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
        <script
        src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <script
        src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
        <link href='http://fonts.googleapis.com/css?family=Raleway'
              rel='stylesheet' type='text/css'>
        <link rel='stylesheet' type='text/css' media='all' href='css/chat.css'>
        <link rel='stylesheet' type='text/css' media='all' href='css/chatbox.css'>
        <link rel="stylesheet"
              href="//cdn.jsdelivr.net/jquery.sidr/2.2.1/stylesheets/jquery.sidr.light.min.css">

        <script src="js/dashboard.js" type="text/javascript"></script>
        <script src="js/chatBoxLogin.js" type="text/javascript"></script>
        <script src="js/notification.js" type="text/javascript"></script>


    </head>
    <body>
        <div id='chatLogin'></div>
        <% if (session.getAttribute("user") == null) {
                String site
                        = "http://localhost:8080/sampleHospital/index.html";
                response.sendRedirect(site);
                return;
            } %> <% User newUser = (User) session.getAttribute("user");%>
        <input id="getSession" value=<%= newUser.getId()%> />
        <input id="getSessionName" value=<%= newUser.getFullName()%>/>
        <div id="hidemyauth">
            <input type="text" placeholder="chatbox password" id="chatboxpassword" />
            <button id="checkChatAuthentication" value="getchatroom" />
        </div>
        <script>
            var myId = $("#getSession").val();
        </script> 
        <p />
        <nav class="navbar navbar-default sidebar" role="navigation">
            <div class="container-fluid">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse"
                            data-target="#bs-sidebar-navbar-collapse-1">
                        <span class="sr-only">Toggle navigation</span> <span
                            class="icon-bar"></span> <span class="icon-bar"></span> <span
                            class="icon-bar"></span>
                    </button>
                </div>
                <div class="collapse navbar-collapse"
                     id="bs-sidebar-navbar-collapse-1">
                    <ul class="nav navbar-nav">
                        <li class="active"><a href="#">Dashboard<span
                                    style="font-size: 16px;"
                                    class="pull-right hidden-xs showopacity glyphicon glyphicon-home"></span></a></li>
                        <li class="dropdown"><a href="#" class="dropdown-toggle"
                                                data-toggle="dropdown">My Task<span class="caret"></span><span
                                    style="font-size: 16px;"
                                    class="pull-right hidden-xs showopacity glyphicon glyphicon-user"></span></a>
                            <ul class="dropdown-menu forAnimate" role="menu" style="width: auto !important; height: auto !important; background-color: none;">
                                <li><a href="#notification" data-toggle="modal"
                                       id="SendNotification"> Send
                                        Notification
                                    </a></li>
                                <li><a href="{{URL::to('createusuario')}}">Send report</a></li>
                                <li><a href="#">Attach messages</a></li>

                                <li><a href="#">Images</a></li>
                                <li id="addChatRoom"><a href="#" data-toggle="modal" data-target="#myModal"
                                                        id="chatRoom"> Add Room </a></li>
                                <li id="addUser"><a href="register.jsp"
                                                    id="register"> Add User </a>  </li>
                            </ul></li>
                        <li><a data-toggle="modal" href="#changepassworddmodal">Change
                                Password<span style="font-size: 16px;"
                                              class="pull-right hidden-xs showopacity glyphicon glyphicon-tags"></span>
                            </a></li>
                    </ul>
                </div>
            </div>
        </nav>

        <div class="clearfix">
            <div class="container-fluid">
                <ul class="pull-left">
                    <li class="profile-info dropdown"><a href="#">
                            <img
                                src="https://www.globalvetlink.com/wp-content/uploads/2015/07/anonymous.png"
                                alt="image" class="test"> <%= newUser.getFullName()%> </a></li>

                </ul>


                <ul class="pull-left pull-right-xs pull-none-xsm"
                    style="margin-top: 20px;">
                    <li id="notice" class="notifications dropdown"
                        style="padding-right: 10px;"><a href=""
                                                    class="dropdown-toggle" data-toggle="dropdown"
                                                    data-hover="dropdown" data-close-others="true" id="reset" onclick="reset()" style="color:#ef6d3b;"><span
                                class="glyphicon glyphicon-alert"><span class="badge" id="count">
                                </span></span></a>
                        <ul class="dropdown-menu">
                            <div id="allalerts">
                                <div id="all">
                                    <li class="wrapit" id="alert">
                                        <p class="small" id="name">

                                            <span class="pull-right" style="color:red;font-sze: 12px;" id="date"></span></p>
                                        <p style="font-size:14px; font-weight:bold; " id="content">
                                            <span class="pull-right"  id="type" ></span> 
                                        </p>
                                        <hr style="border-top:1px solid rgba(199, 191, 191, 0.91); margin:0px;">
                                    </li>
                                </div>
                            </div>

                        </ul>

                    </li>

                    <li class="chatinfo" style="padding-right: 15px;"><a
                            href="#sidr" id="rightmenu"> Chat </a></li>
                   




                </ul>

                <ul class="pull-right pull-left-xs"
                    style="margin-top: 20px; padding-right: 30px;">
                    <li style="padding-right: 15px;"><form action ="Logout" method="POST"><input type="submit" value="logout"/></form></li>



                    <div id="sidr" >
                        <h3><label>Personal Chat</label></h3>
                        <ul id="listusers" class="userList">


                        </ul>
                        <h3> <label>Group Chat</label></h3>

                        <ul class="GroupChat" id="groupChatBox">

                        </ul>
                    </div>
                </ul>


                <!--  The box for the bit of explanation of the hospital -->


                <div id="myCarousel" class="carousel slide" data-ride="carousel">
                    <!-- Indicators -->
                    <ol class="carousel-indicators">
                        <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
                        <li data-target="#myCarousel" data-slide-to="1"></li>
                        <li data-target="#myCarousel" data-slide-to="2"></li>
                        <li data-target="#myCarousel" data-slide-to="3"></li>
                    </ol>
                    <div class="carousel-inner" role="listbox" style = "text-align:left; font-size:40px">
                        <div class="item active">
                            <img src="im.jpg" alt="Image" style="height:650px">
                            <div class="carousel-caption">
                                <h3 style ="font-family:impact;color:#99ffcc"> Happiness </h3>
                                <p style ="font-family:impact;color:#99ffcc">In Your Hands</p>
                            </div>      
                        </div>

                        <div class="item">
                            <img src="im1.jpg" alt="Image"  style="height:650px" >
                            <div class="carousel-caption">
                                <h3 style ="font-family:impact;color:#99ffcc">Assurance</h3>
                                <p style ="font-family:impact;color:#99ffcc">With Best Deal</p>
                            </div>      
                        </div>
                        <div class="item">
                            <img src="im2.jpg" alt="Image" style="height:650px" >
                            <div class="carousel-caption">
                                <h3 style ="font-family:impact;color:#99ffcc"> Strength </h3>
                                <p style ="font-family:impact;color:#99ffcc"> Of The World </p>
                            </div>      
                        </div>
                        <div class="item">
                            <img src="im3.jpg" alt="Image" style="height:650px" >
                            <div class="carousel-caption">
                                <h3 style ="font-family:impact;color:#99ffcc"> Strength </h3>
                                <p style ="font-family:impact;color:#99ffcc"> Of The World </p>
                            </div>      
                        </div>
                    </div>
                </div>





                <!--  the foot element to write many other description of the chat application and the Hospital -->
                <div id="footelement" >
                    <div class="row" style="padding-bottom: 10px; margin-bottom:50px;">
                        <div class="col-md-4">
                            <h4 class="mytitle">Vacancy</h4>
                            <hr>
                            <div id="allVacancy"  >
                                <div class="well" id="vacancy">

                                    <p id="content">
                                    </p>
                                    <p class="small" style="color:blue" id="name">
                                        <span class="pull-right" id="date"></span>

                                    </p>
                                </div>


                            </div>
                        </div>
                        <div class="col-md-4" class="Divall">
                            <h4 class="mytitle">Announcements</h4>
                            <hr>
                            <div id="allAnnouncement" >
                                <div class="well" id="announcement">

                                    <p id="content" >
                                    </p>
                                    <p class="small" style="color:blue" id="name">
                                        <span class="pull-right" id="date"></span>

                                    </p>
                                </div>

                            </div>

                        </div>
                        <div class="col-md-4">
                            <h4 class="mytitle">Events</h4>
                            <hr>
                            <div id="allEvents" >
                                <div class="well" id="event">

                                    <p id="content">
                                    </p>
                                    <p class="small" style="color:blue" id="name">
                                        <span class="pull-right" id="date"></span>

                                    </p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>


                <!--  end of foot element -->

                <footer>


                </footer>



                <div id="chatbox">
                    <div id="eachchatbox"
                         style="display: none; position: fixed; bottom: 0;">
                        <div class="col-md-3">
                            <div class="portlet portlet-default">
                                <div class="portlet-heading">
                                    <div class="portlet-title">
                                        <h4>
                                            <span id="username" style="color: white"></span>
                                        </h4>
                                    </div>
                                    <div class="portlet-widgets">
                                        <div class="btn-group">
                                            <button type="button"
                                                    class="btn btn-white dropdown-toggle btn-xs"
                                                    data-toggle="dropdown">
                                                Status <span class="caret"></span>
                                            </button>
                                            <button type="button" class="btn btn-white btn-xs"
                                                    id="minimize">
                                                <span class="glyphicon glyphicon-minus"
                                                      style="font-size: 12px;"></span>
                                            </button>
                                            <button type="button" class="btn btn-white btn-xs" id="remove">
                                                <span class="glyphicon glyphicon-remove"
                                                      style="font-size: 12px;"></span>
                                            </button>
                                            <ul class="dropdown-menu" role="menu"
                                                style="width: auto !important; height: auto !important; background-image: none;">
                                                <li><a href="#"> Online</a></li>
                                                <li><a href="#"> Away</a></li>
                                                <li><a href="#"> Offline</a></li>
                                            </ul>
                                        </div>
                                        <span class="divider"></span> <a data-toggle="collapse"
                                                                         data-parent="#accordion" href="#chat"></a>
                                    </div>
                                    <div class="clearfix" style="background-color:#34495e !important;background-image:none;"></div>
                                </div>
                                <div id="chat" class="panel-collapse collapse in">
                                    <div class="portlet-body chat-widget"
                                         style="overflow-y: scroll; width: auto; height:200px;">
                                        <div class="row">
                                            <div class="col-lg-12" id="message-area">
                                            </div>
                                        </div>

                                    </div>



                                    <div class="portlet-footer">
                                        <form role="form">
                                            <div class="form-group">
                                                <textarea class="form-control" id="input-area" placeholder="Enter message..."></textarea>
                                            </div>
                                            <div class="form-group">

                                                <div id="button-append"></div>

                                                <div class="clearfix" style="background-image:none;background-color:#e0e7e8;"></div>
                                            </div>
                                        </form>

                                    </div>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>
                <!--  end of chat box -->


                <!-- Add room Modal -->
                <div class="modal fade" id="myModal" tabindex="-1" role="dialog"
                     aria-labelledby="myModalLabel">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal"
                                        aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                                <h4 class="modal-title " id="myModalLabel">Room Details</h4>
                            </div>
                            <div class="modal-body">


                                <form role="form">
                                    <div class="form-group">
                                        <label for="user">Name:</label> <input type="text"
                                                                               class="form-control" id="roomName">
                                    </div>
                                    <div class="form-group">
                                        <label for="password">Password:</label> <input type="password"
                                                                                       class="form-control" id="password">
                                    </div>

                                    <label for="user">Add Users</label><br> <div
                                        id="userList"   multiple name="receivers">

                                    </div>
                                </form>

                            </div>


                            <div class="modal-footer">
                                <button type="button" class="btn btn-default"
                                        data-dismiss="modal">Cancel</button>
                                <button type="button" class="btn btn-primary"
                                        onclick="addRoom();"data-dismiss="modal">Create</button>
                            </div>

                        </div>


                    </div>
                </div>
                <!--  ENd modal add room -->


                <!--  Modal send notification -->

                <div class="modal fade" id="notification" tabindex="-1"
                     role="dialog" aria-labelledby="myModalLabel">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal"
                                        aria-label="Close" required>
                                    <span aria-hidden="true">&times;</span>
                                </button>
                                <h4 class="modal-title " id="myModalLabel">Notification
                                    Details</h4>
                            </div>
                            <div class="modal-body">


                                <form role="form">
                                    <div class="form-group">
                                        <label for="type">Type</label> <select class="form-control"
                                                                               id="sel1" name="type" required>
                                            <option>Emergency</option>
                                            <option>News</option>
                                            <option>Events</option>

                                            <option>Announcements</option>
                                            <option>Vacancy</option>

                                        </select>
                                    </div>
                                    <div class="form-group">
                                        <label for="user">Receivers </label><br> <div
                                            id="group"  multiple name="receivers" required>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="user">Compose Message </label><br>
                                        <textarea class="form-control" rows="5" id="message"
                                                  placeholder="Message here..."></textarea>
                                    </div>

                                </form>

                            </div>

                            <div class="modal-footer">
                                <button type="button" class="btn btn-default"
                                        data-dismiss="modal">Cancel</button>
                                <button type="button" class="btn btn-primary"
                                        onclick="sendNotification()" data-dismiss="modal">Send</button>
                            </div>

                        </div>
                    </div>
                </div>

                <!--  end notifications modal -->
                <!-- start of the change password modal -->
                <div class="modal fade" id="changepassworddmodal" role="dialog">
                    <div class="modal-dialog">

                        <!-- Modal content-->
                        <div class="modal-content">
                            <div class="modal-header" style="padding: 35px 50px;">
                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                                <h4>
                                    <span class="glyphicon glyphicon-lock"></span> Change Password
                                </h4>
                            </div>
                            <div class="modal-body" style="padding: 40px 50px;">
                                <form role="form">
                                    <div class="form-group">
                                        <label for="oldpwd"><span
                                                class="glyphicon glyphicon-eye-open"></span> Old Password</label> <input
                                            type="text" class="form-control" id="oldpwd">
                                    </div>
                                    <div class="form-group">
                                        <label for="newpwd"><span
                                                class="glyphicon glyphicon-eye-open"></span> New Password</label> <input
                                            type="text" class="form-control" id="newpwd">
                                    </div>
                                    <div class="form-group">
                                        <label for="newpwdmatch"><span
                                                class="glyphicon glyphicon-eye-open"></span> Confirm Password</label>
                                        <input type="text" class="form-control" id="newpwdmatch">
                                    </div>
                                    <button type="submit" class="btn btn-success btn-block">
                                        <span class="glyphicon glyphicon-off"></span> save
                                    </button>
                                </form>
                            </div>
                            <div class="modal-footer">
                                <button type="submit"
                                        class="btn btn-danger btn-default pull-left"
                                        data-dismiss="modal">
                                    <span class="glyphicon glyphicon-remove"></span> Cancel
                                </button>

                            </div>
                        </div>

                    </div>
                </div>

                <!--  end of the modal -->


            </div>
        </div>



        <script src="//cdn.jsdelivr.net/jquery/2.2.0/jquery.min.js"></script>
        <!-- Including the Sidr JS -->
        <script
        src="//cdn.jsdelivr.net/jquery.sidr/2.2.1/jquery.sidr.min.js"></script>


    </body>
</html>