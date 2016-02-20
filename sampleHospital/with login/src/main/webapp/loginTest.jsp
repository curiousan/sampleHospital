<%-- 
    Document   : loginTest.jsp
    Created on : Feb 17, 2016, 6:55:44 AM
    Author     : sandeshpoudel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" import="com.mycompany.samplehospital.model.User"%>
<!DOCTYPE html>
<html>
    <head>
          <% 
        if (null==session.getAttribute("user")){
            response.sendRedirect("/login.html");
        }else{
        }%>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        
        <h1>Hello User </h1> <br>
       <%   User newUser = (User) session.getAttribute("user"); 
        %>
your name: <%=  newUser.getFullName() %> 
        <form action="Logout" method="POST">
            
         <input type="submit" value="logout"/>
         
            
            
        </form>
    </body>
</html>
