<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Profile</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet"
              href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
        <script
        src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <script
        src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
        <link rel="stylesheet"
              href="//cdn.jsdelivr.net/jquery.sidr/2.2.1/stylesheets/jquery.sidr.light.min.css">


        <style>
            body, html {
                background-image:
                    url('http://www.presidiacreative.com/wp-content/uploads/2013/11/whitebackgrounds/nice-background.jpg');
                background-size: cover;
                width: 100%;
                height: 100%;
            }

            body, h1, h2, h3, h4, h5, h6 {
                font-family: "Lato", "Helvetica Neue", Helvetica, Arial, sans-serif;
                font-weight: 700;
            }

            .modal-header, h4, .close {
                background-color: #5cb85c;
                color: white !important;
                text-align: center;
                font-size: 30px;
            }

            .modal-footer {
                background-color: #f9f9f9;
            }

            .topnav {
                font-size: 16px;
                border-bottom: 1px outset rgba(192, 192, 192, 0.5);
                background: rgba(192, 192, 192, 0.9);
                box-shadow: 0px 1px rgba(0, 0, 0, 0.3);
            }

            .topnav ul.navbar-nav li a {
                background: rgba(28, 28, 28, 0.1);
                color: #000;
            }

            .topnav ul.navbar-nav li a:hover, .topnav ul.navbar-nav li a:focus {
                background: rgba(255, 255, 255, 0.5);
                color: #000;
            }

            .topnav .navbar-brand {
                background: rgba(28, 28, 28, 0.0);
                color: #000;
            }

            .topnav .navbar-brand:hover, .topnav .navbar-brand:focus {
                background: rgba(255, 255, 255, 0.5);
                color: #000;
            }

            .lead {
                font-size: 18px;
                font-weight: 400;
            }

            .col-md-4 {
                position: absolute;
                bottom: -80px;
                left: 0;
                padding: 15px;
                z-index: 1;
            }

            .col-md-5 {
                padding-top: 30px;
                margin-left: 10px;
            }

            .row {
                padding: 10px;
                padding-top:25px;
                top: 80px;
                position: relative;
                overflow: hidden;
            }

            .jumbotron-special {
                background-color: white;
                padding: 20px;
            }

            hr {
                border: 0px solid;
                border-top: 2px solid;
                border-bottom: 2px solid;
                border-color: #f4e0e4;
                margin-top: 30px;
                width: 80%;
            }

            .jumbotron {
                top: 50px;
                position: relative;
                height: 500px;
                background-image:
                    url("https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcQIRc7Z8d73u02phXPl2AoAaTxlkdGwNYrO-LR7JojoZ5Q7ZZzS");
                background-attachment: fixed;
                background-repeat: no-repeat;
                background-size: cover;
                margin-bottom: 0px;
                margin-left: 20px;
                margin-right: 20px;
                position: relative;
            }

            .Row {
                display: table-row;
            }

            .Cell {
                display: table-cell;
                border: 1px;
                border-width: thin;
                padding-right: 30px;
                vertical-align: top;
                padding-left: 15px;
                padding-top: 10px; height : 50px;
                font-size: 20px;
                font-style: italic;
                font-weight: bold;
                font-family: cursive;
                border-bottom: 1px solid #eee;
                height: 50px;
            }
            #footer {
                background-color: #252528;
                text-align:center;
                color: #bcbbbd;
                height: 150px;
                margin-top: 200px;
                padding-top : 12px;
                position : relative;

            }
        </style>
    </head>
    <body>
        <%@page import="com.mycompany.samplehospital.model.User"%>
        <% if (session.getAttribute("user") == null) {
                String site
                        = "http://localhost:8080/sampleHospital/index.jsp";
                response.sendRedirect(site);
                return;
            } %>  <% User newUser = (User) session.getAttribute("user");%>
        <input id="getSession" value=<%= newUser.getId()%> />
        <nav class="navbar navbar-default navbar-fixed-top topnav"
             role="navigation">
            <div class=" container topnav">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse"
                            data-target="#bs-example-navbar-collapse-1">
                        <span class="sr-only">Toggle navigation</span> <span
                            class="icon-bar"></span> <span class="icon-bar"></span> <span
                            class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand topnav"
                       href="https://www.facebook.com/shurakshya.kharel" target="_blank">Shurakshya
                        Kharel</a>
                </div>

                <div class="collapse navbar-collapse"
                     id="bs-example-navbar-collapse-1">
                    <ul class="nav navbar-nav navbar-right">
                        <li><a href="index.html">Dashboard</a></li>
                        <li><a data-toggle="modal" href="#myModal">Edit Profile</a></li>
                        <li><a href="#task">Task List</a></li>
                    </ul>
                </div>

            </div>
        </nav>

        <div id="container-fluid">
            <div class="jumbotron">
                <div class="text-center">
                    <h1 style="color: #f4e0e4; padding-top: 30px;">Dr.Jonnas
                        Hospital</h1>
                    <hr>
                </div>

                <div class="col-md-4">
                    <img
                        src="https://encrypted-tbn2.gstatic.com/images?q=tbn:ANd9GcTQSVALEvEc9j8uWY81gskimOFTbFqrKxF0cDN83lvuCYM7RSBJ_z4Y6N3f"
                        class="img-thumbnail" alt="Cinque Terre" width="400" height="600">
                </div>
            </div>

            <div class="row">
                <div class="col-md-5">
                    <div class="jumbotron-special">
                        <h2 style="padding: 10px; color: blue;">
                            <span class="glyphicon glyphicon-globe"
                                  style="padding-right: 10px;"></span>Info
                        </h2>
                        <div class="Row">
                            <div class="Cell">
                                <p>Name</p>
                            </div>
                            <div class="Cell">
                                <p>Shurakshya Kharel</p>
                            </div>
                        </div>
                        <div class="Row">
                            <div class="Cell">

                                <p>Current city</p>
                            </div>
                            <div class="Cell">
                                <p>Vanha Maantie 6</p>
                            </div>
                        </div>
                        <div class="Row">
                            <div class="Cell">

                                <p>Data of Birth</p>
                            </div>
                            <div class="Cell">
                                <p>02 april 1995</p>
                            </div>
                        </div>
                        <div class="Row">
                            <div class="Cell">

                                <p>Contact into</p>
                            </div>
                            <div class="Cell">
                                <p>9849567834</p>
                            </div>
                        </div>
                        <div class="Row">
                            <div class="Cell">

                                <p>Title</p>
                            </div>
                            <div class="Cell">
                                <p>Doctor</p>
                            </div>
                        </div>
                        <div class="Row">
                            <div class="Cell">

                                <p>Gender</p>
                            </div>
                            <div class="Cell">
                                <p>Female</p>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-md-5">


                </div>
            </div>

            <div id="footer"> 

            </div>


        </div>


        <!-- Modal -->
        <div class="modal fade" id="myModal" role="dialog">
            <div class="modal-dialog">

                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header" style="padding: 35px 50px;">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4>
                            <span class="glyphicon glyphicon-lock"></span> Edit Profile
                        </h4>
                    </div>
                    <div class="modal-body" style="padding: 40px 50px;">
                        <form role="form">
                            <div class="form-group">
                                <label for="usrname"><span
                                        class="glyphicon glyphicon-user"></span> Username</label> <input
                                    type="text" class="form-control" id="usrname"
                                    placeholder="Enter Your username">
                            </div>
                            <div class="form-group">
                                <label for="psw"><span
                                        class="glyphicon glyphicon-eye-open"></span> Password</label> <input
                                    type="text" class="form-control" id="psw"
                                    placeholder="Enter password">
                            </div>
                            <div class="form-group">
                                <label for="Address"><span
                                        class="glyphicon glyphicon-globe"></span>Address</label> <input
                                    type="text" class="form-control" id="Address"
                                    placeholder="Enter Address">
                            </div>
                            <div class="form-group">
                                <label for="Age"><span
                                        class="glyphicon glyphicon-info-sign"></span>Age</label> <input
                                    type="text" class="form-control" id="Age"
                                    placeholder="Enter Age">
                            </div>
                            <div class="form-group">
                                <label for="Phoneno"><span
                                        class="glyphicon glyphicon-phone"></span>Phoneno</label> <input
                                    type="text" class="form-control" id="Phoneno"
                                    placeholder="Enter Phoneno">
                            </div>
                            <div class="form-group">
                                <label for="Title"><span
                                        class="glyphicon glyphicon-briefcase"></span>Title</label> <input
                                    type="text" class="form-control" id="Title"
                                    placeholder="Enter Title">
                            </div>
                            <div class="form-group">
                                <label for="sex"><span
                                        class="glyphicon glyphicon-question-sign"></span>Sex</label> <select
                                    class="form-control" id="sex">
                                    <option>Choose</option>
                                    <option>Male</option>
                                    <option>Female</option>
                                </select>
                            </div>
                            <button type="submit" class="btn btn-success btn-block">
                                <span class="glyphicon glyphicon-off"></span> Save Changes
                            </button>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="submit" class="btn btn-danger btn-default pull-left"
                                data-dismiss="modal">
                            <span class="glyphicon glyphicon-remove"></span> Cancel
                        </button>

                    </div>
                </div>

            </div>
        </div>


        <script src="//cdn.jsdelivr.net/jquery/2.2.0/jquery.min.js"></script>
        <!-- Including the Sidr JS -->
        <script src="//cdn.jsdelivr.net/jquery.sidr/2.2.1/jquery.sidr.min.js"></script>
        <script>
            $(document).ready(function () {


            });
        </script>
    </body>
</html>