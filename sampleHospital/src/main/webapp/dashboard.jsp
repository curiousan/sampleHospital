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
<script src="js/Static.js" type="text/javascript"></script>
<script src="js/notification.js" type="text/javascript"></script>
    <script>
        
        $(function() {
    $( "#chatLogIn" ).uidialog();
  });
    </script>

</head>
<body>
    <div id='chatLogin'></div>
	<% if (session.getAttribute("user") == null) { 
		String site =
   "http://localhost:8080/sampleHospital/index.html";
	response.sendRedirect(site); return; } %> <% User newUser = (User)
	session.getAttribute("user"); %>
	<input id="getSession" value=<%= newUser.getId()%> />
	<p id="getCurrentName"><%= newUser.getFullName()%>
	<div id="hidemyauth">
		<input type="text" placeholder="chatbox password" id="chatboxpassword" />
		<button id="checkChatAuthentication" value="getchatroom" />
	</div>

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
						<ul class="dropdown-menu forAnimate" role="menu">
							<li><a href="{{URL::to('createusuario')}}"> Report</a></li>
							<li><a href="#">Send report</a></li>
							<li><a href="#">Attached messages</a></li>
							<li class="divider"></li>
							<li><a href="#">Form</a></li>
							<li class="divider"></li>
							<li><a href="#">Images</a></li>
							<li><a href="#" data-toggle="modal" data-target="#myModal"
								id="chatRoom"> Add Room </a></li>
						</ul></li>
					<li>
						<form action="Logout" method="POST">

							<input type="submit" value="logout" />
						</form>
					</li>
					<li><a href="#" data-toggle="modal"
						data-target="#notification" id="SendNotification"> Send
							Notification </a><span style="font-size: 16px;"
						class="pull-right hidden-xs showopacity glyphicon glyphicon-tags"></span></a></li>
				</ul>
			</div>
		</div>
	</nav>

	<div class="clearfix">
		<div class="container-fluid">
			<ul class="pull-left">
				<li class="profile-info dropdown"><a href="#"
					class="dropdown-toggle" data-toggle="dropdown" aria-expanded="true">
						<img
						src="https://upload.wikimedia.org/wikipedia/commons/7/7b/Yonina_Tulip.jpg"
						alt="image" class="test"> <%= newUser.getFullName()%>
				</a>
			</ul>
			
			
			<ul class="pull-left pull-right-xs pull-none-xsm"
				style="margin-top: 20px;">
				<li id="notice" class="notifications dropdown"
					style="padding-right: 10px;"><a href="#"
					class="dropdown-toggle" data-toggle="dropdown"
					data-hover="dropdown" data-close-others="true"><span
						class="glyphicon glyphicon-alert"><span class="badge">5
						</span></span></a>
					<ul class="dropdown-menu">
						<li class="top">
							<p class="small">
								<a href="#" class="pull-right">Mark all read</a> "you have "<strong>
									3</strong> "new notifications."
							</p>
							<hr style="border-top:1px solid rgba(199, 191, 191, 0.91); margin:0px;">
						</li>
						<li class="wrapit">
						<p class="small" >
						<span class="pull-right" style="color:red;font-sze: 12px;">15:11 08.03.2015</span>Sandesh Poudel</p>
						<p style="font-size:14px; font-weight:bold; ">
						<span class="pull-right" style="background-color:red;padding-left:10px;" >ALERT</span>Sandesh has made my life hell. he doesnot allow time for me to rest. 
						Now i wanna die frown emoticon </p>
						<hr style="border-top:1px solid rgba(199, 191, 191, 0.91); margin:0px;">
						</li>
						
					</ul>
					</li>

				<li class="notifications dropdown col-sm-offset-1"><a href="#"
					class="dropdown-toggle" data-toggle="dropdown"
					data-hover="dropdown" data-close-others="true">Reports </a>
					<ul class="dropdown-menu">
						<li class="top">
							<p class="small">
								<a href="#" class="pull-right">Mark all read</a> "you have "<strong>
									3</strong> "new notifications."

							</p>
						</li>
						<li id="listReports"></li>
					</ul></li>
				<li id="message" class="notifications dropdown col-sm-offset-1"
					style="padding-right: 10px;"><a href="#"
					class="dropdown-toggle" data-toggle="dropdown"
					data-hover="dropdown" data-close-others="true"><span
						class="glyphicon glyphicon-envelope"><span class="badge">3
						</span></span></a>
					<ul class="dropdown-menu">
						<li class="top">
							<p class="small">
								<a href="#" class="pull-right">Mark all read</a> "you have "<strong>
									3</strong> "new notifications."
							</p>
						</li>
						<li id="listMessage"></li>
					</ul></li>




			</ul>

			<ul class="pull-right pull-left-xs"
				style="margin-top: 20px; padding-right: 30px;">
				<li style="padding-right: 15px;">Logout</li>

				<li class="chatinfo" style="padding-right: 15px;"><a
					href="#sidr" id="rightmenu"> Chat </a>

					<div id="sidr">
						<ul id="listusers" class="userList">


						</ul>
						<ul class="GroupChat" id="groupChatBox">

						</ul>
					</div>
			</ul>

			
			<!--  The box for the bit of explanation of the hospital -->
			<div class="jumbotron">
				<h2 class="text-uppercase" style="position: relative;">Jorvi
					Hospital</h2>
				<img class="img-responsive"
					src="http://previews.123rf.com/images/dolgachov/dolgachov1307/dolgachov130701752/21034411-healthcare-medical-and-technology-doctor-showing-something-patient-on-tablet-pc-in-hospital-Stock-Photo.jpg"
					alt="chatapp" width="300" height="300" style="float: right;">
				<p class="small" style="position: relative">Neon Theme comes
					with pre-defined functions to handle layout behaviors. You have
					tens of options to make your theme layout look differently. In this
					page, you are going to see how you can show, hide, remove or add
					layout elements. In order to customize the layer JS file
					neon-api.js is required to load.</p>

			</div>



			<!--  the foot element to write many other description of the chat application and the Hospital -->
			<div id="footelement" >
				<div class="row" style="padding-bottom: 10px; margin-bottom:50px;">
					<div class="col-md-4 col-xs-12 col-sm-6 col-lg-2">
						<h4>Announcements</h4>
						<hr>
					<div class="well">
					
					<p> tell me tell me something i Dnt know smthng i dnt know. kendall jenner donot need bf to prove her... Gigi 
					hadid vogue covers looks great.
					</p>
					<p class="small" style="color:blue">
					<span class="pull-right">12.52 Pm 08.03.2016</span>
					Shurakshya Kharel 
					</p>
					</div>
					<div class="well">
					
					<p> tell me tell me something i Dnt know smthng i dnt know. kendall jenner donot need bf to prove her... Gigi 
					hadid vogue covers looks great.
					</p>
					<p class="small" style="color:blue">
					<span class="pull-right">12.52 Pm 08.03.2016</span>
					Shurakshya Kharel 
					</p>
					</div>

					</div>
					<div class="col-md-4 col-xs-12 col-sm-6 col-lg-2">
						<h4>Vacancy</h4>
						<hr>
					<div class="well">
					
					<p> tell me tell me something i Dnt know smthng i dnt know. kendall jenner donot need bf to prove her... Gigi 
					hadid vogue covers looks great.
					</p>
					<p class="small" style="color:blue">
					<span class="pull-right">12.52 Pm 08.03.2016</span>
					Shurakshya Kharel 
					</p>
					</div>
					<div class="well">
					
					<p> tell me tell me something i Dnt know smthng i dnt know. kendall jenner donot need bf to prove her... Gigi 
					hadid vogue covers looks great.
					</p>
					<p class="small" style="color:blue">
					<span class="pull-right">12.52 Pm 08.03.2016</span>
					Shurakshya Kharel 
					</p>
					</div>
						
					</div>
					<div class="col-md-4 col-xs-12 col-sm-6 col-lg-2 col-sm-offset-1">
						<h4>Events</h4>
						<hr>
						<div class="well">
					
					<p> tell me tell me something i Dnt know smthng i dnt know. kendall jenner donot need bf to prove her... Gigi 
					hadid vogue covers looks great.
					</p>
					<p class="small" style="color:blue">
					<span class="pull-right">12.52 Pm 08.03.2016</span>
					Shurakshya Kharel 
					</p>
					</div>
					<div class="well">
					
					<p> tell me tell me something i Dnt know smthng i dnt know. kendall jenner donot need bf to prove her... Gigi 
					hadid vogue covers looks great.
					</p>
					<p class="small" style="color:blue">
					<span class="pull-right">12.52 Pm 08.03.2016</span>
					Shurakshya Kharel 
					</p>
					</div>
						
					</div>
				</div>
			</div>


			<!--  end of foot element -->
		
			<div id="footer"> &copy; Shurakshya Kharel And the team. All rights reserved.
	</div>


			<div id="chatbox">
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

				<!-- Modal -->
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

									<label for="user">Add Users</label><br> <select
										id="userList" class="form-control" multiple>

									</select>
								</form>

							</div>


							<div class="modal-footer">
								<button type="button" class="btn btn-default"
									data-dismiss="modal">Cancel</button>
								<button type="button" class="btn btn-primary"
									onclick="addRoom();">Create</button>
							</div>

						</div>


					</div>
				</div>
 <!--  add room -->


<!--  Modal send notification -->

				<div class="modal fade" id="notification" tabindex="-1"
					role="dialog" aria-labelledby="myModalLabel">
					<div class="modal-dialog" role="document">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal"
									aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
								<h4 class="modal-title " id="myModalLabel">Notification
									Details</h4>
							</div>
							<div class="modal-body">


								<form role="form">
									<div class="form-group">
										<label for="type">Type</label> <select class="form-control"
											id="sel1" name="type">
											<option>Emergency</option>
											<option>News</option>
											<option>Assignments</option>

										</select>
									</div>
									<div class="form-group">
										<label for="user">Receivers </label><br> <select
											id="group" class="form-control" multiple name="receivers">
										</select>
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
									onclick="sendNotification()">Send</button>
							</div>

						</div>
					</div>
				</div>
				
			<!--  end notifications modal -->

</div>
</div>



				<script src="//cdn.jsdelivr.net/jquery/2.2.0/jquery.min.js"></script>
				<!-- Including the Sidr JS -->
				<script
					src="//cdn.jsdelivr.net/jquery.sidr/2.2.1/jquery.sidr.min.js"></script>
				<%--
				<script>
					
				</script>
				--%>
</body>
</html>