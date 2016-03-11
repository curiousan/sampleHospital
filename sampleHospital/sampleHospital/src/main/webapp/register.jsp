<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<!--  

Registration of a new user




-->
<html>
    <head>
        <meta charset="utf-8">
        <title>Daily UI - Day 1 Sign In</title>

        <!-- Google Fonts -->
        <link href='https://fonts.googleapis.com/css?family=Roboto+Slab:400,100,300,700|Lato:400,100,300,700,900' rel='stylesheet' type='text/css'>

        <link rel="stylesheet" href="css/animate.css">
        <!-- Custom Stylesheet -->
        <link rel="stylesheet" href="css/style.css">

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
    </head>
    <body>
        <% if (session.getAttribute("user") == null) {
                String site
                        = "http://localhost:8080/sampleHospital/index.jsp";
                response.sendRedirect(site);
                return;
            }%>

        <div class="container">
            <div class="top">
                <h1 id="title" class="hidden"><span id="logo">Sample <span>Hospital</span></span></h1>
            </div>
            <div class="login-box animated fadeInUp">
                <div class="box-header">
                    <h2>Add User</h2>
                </div>
                <form  method="post">
                    <label for="username">Username</label>
                    <br/>
                    <input type="text" id="username" name="username" required>
                    <br/>
                    <label for="password">Password</label>
                    <br/>
                    <input type="password" id="password" name="password" required>
                    <br/>
                    <label for="fullname">Full Name</label>
                    <br/>
                    <input type="text" id="fullname" name="fullname" required>
                    <br/>
                    <label for="age">Age</label>
                    <br/>
                    <input type="number" id="age" name="age" required>
                    <br/>
                    <label for="gender">Gender</label>
                    <br/>
                    <input type="text" id="gender" name="gender" required>
                    <br/>
                    <label for="address">Address</label>
                    <br/>
                    <input type="text" id="address" name="address" required>
                    <br/>
                    <label for="phone">Phone Number</label>
                    <br/>
                    <input type="text" id="phone" name="phone" required>
                    <br/>



                    <button type="button" onclick="register()" >Sign Up</button>
                </form>
                <br/>
                <a href="index.html"><p class="small">Already have account?</p></a>
            </div>
        </div>
    </body>

    <script>
        $(document).ready(function () {
            $('#logo').addClass('animated fadeInDown');
            $("input:text:visible:first").focus();
        });
        $('#username').focus(function () {
            $('label[for="username"]').addClass('selected');
        });
        $('#username').blur(function () {
            $('label[for="username"]').removeClass('selected');
        });
        $('#password').focus(function () {
            $('label[for="password"]').addClass('selected');
        });
        $('#password').blur(function () {
            $('label[for="password"]').removeClass('selected');
        });
    </script>
    <script src="js/function.js"></script>

</body>
</html>
