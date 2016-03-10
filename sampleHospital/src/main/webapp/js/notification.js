/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function getNotificationPolling() {
        setInterval(function () {
           
            $.ajax({
      url: 'webapi/alerts',
        method: "GET",
        datatype: "xml",
        success: getNotification,
        error: function (xhr, ajaxOption, thrownError) {
            
        }
            });
        }, 7000);
    }

function sendNotification() {
  var users=[];
    var type = $('select[name=type]').val();
    var message = $('textarea#message').val();
    var senderId = $('#getSession').val();
    var senderName = $("#getSessionName").val(); 
    $("input[type=checkbox]:checked").each(function() {
       
        users.push($(this).attr('id'));
    });
     var toappend='<content></content><type></type><senderID></senderID><senderName></senderName><id></id><date></date>';
    
    
    for(var i=0; i<users.length; i++){
       toappend+='<userList id="'+users[i]+'"></userList>';
    }
    
        toSend='<alert>'+toappend+'</alert>';
        
    if((message!=="") &&(message!=="") ){
       
    var alert = $.parseXML(toSend);
    var ajaxalert = $(alert);
    ajaxalert.find('content').append(message);
    ajaxalert.find('type').append(type);
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
    }
    else{
        $("span#allfeild").append("All field Required !!!!!");
    }
    
}
function reset(){
   count=0;
    var value = document.getElementById("count");
   
    value.innerHTML=count;
}
var messageID=[];
var count ;
function getNotification(xml) {
    count=0;
    
    var content;
    var type;
    var id;
    var senderid;
    var usersid;
    var senderName;
   var sessionID = $('#getSession').val();
    $(xml).find('alert').each(function () {
       
        content = $(this).find('content').text();
         senderid = $(this).find('senderID').text();
          senderName = $(this).find('senderName').text();
        type = $(this).find('type').text();
         id = $(this).find('id').text();
         var allId=[];
            $(this).find('userList').each(function(){
                
               var id=$(this).text();
                allId.push(id);
               
            }
                    );
             
        
        // for(var i=0; i<valueofID; i++){
           //  var id=$(this).find('userList').attr('id').
            
        // }
         //usersid = $(this).find('userList').length;
           
          var date1=$(this).find('date').text();
            var  date=date1.replace('EET','');
     
        
       if(allId.indexOf(sessionID)==-1){
           
       } 
       else{
       if(messageID.indexOf(id)===-1){
  
           if(type==="News"){
                  var clonedDiv = $('#alert').clone();
                $('#all').prepend(clonedDiv);
                clonedDiv.attr("id",id);
              
                 
                   $('#'+id).find('p#content').append('<p href="#" id='+type+id+' style="color:#90c5a9;" >' + content + '</p>');
                     $('#'+id).find('p#name').append('<li href="#" id='+type+id+' style="color:#ef6d3b;" >' + senderName + '</li>');
                    $('#'+id).find('span#date').append('<li href="#" id='+type+id+' style="color:#ef6d3b;">' + date + '</li>');
                    $('#'+id).find('span#type').append('<li href="#" id='+type+id+' >' + type + '</li>');
                messageID.push(id);
                count++;
                  var value = document.getElementById("count");
                  value.innerHTML=count;
                 
            }  
           if(type==="Emergency"){
                var clonedDiv = $('#alert').clone();
                $('#all').prepend(clonedDiv);
                clonedDiv.attr("id",id);
             
                  $('#'+id).find('p#content').append('<p href="#" id='+type+id+' " >' + content + '</p>');
                     $('#'+id).find('p#name').append('<li href="#" id='+type+id+' " >' + senderName + '</li>');
                    $('#'+id).find('span#date').append('<li href="#" id='+type+id+'">' + date + '</li>');
                    $('#'+id).find('span#type').append('<li href="#" id='+type+id+' >' + type + '</li>');
                messageID.push(id);
              
                count++;
                
                 var value = document.getElementById("count");
                  value.innerHTML=count;
            }  
            if(type==="Assignment"){
              var clonedDiv = $('#alert').clone();
                $('#all').prepend(clonedDiv);
                clonedDiv.attr("id",id);
                  $('#'+id).find('p#content').append('<p href="#" id='+type+id+' " >' + content + '</p>');
                     $('#'+id).find('p#name').append('<li href="#" id='+type+id+' " >' + senderName + '</li>');
                    $('#'+id).find('span#date').append('<li href="#" id='+type+id+' ">' + date + '</li>');
                    $('#'+id).find('span#type').append('<li href="#" id='+type+id+' >' + type + '</li>');
                      messageID.push(id);
            }  
            if(type==="Events"){
              var clonedDiv = $('#event').clone();
                $('#allEvents').prepend(clonedDiv);
                clonedDiv.attr("id",id);
                  $('#'+id).find('p#content').append('<p href="#" id='+type+id+' style="color:#90c5a9;" >' + content + '</p>');
                     $('#'+id).find('p#name').append('<li href="#" id='+type+id+' style="color:#ef6d3b;" >' + senderName + '</li>');
                    $('#'+id).find('span#date').append('<li href="#" id='+type+id+' style="color:#ef6d3b;">' + date + '</li>');
                    $('#'+id).find('span#type').append('<li href="#" id='+type+id+' >' + type + '</li>');
                    messageID.push(id);
            }  
            if(type==="Vacancy"){
  var clonedDiv = $('#vacancy').clone();
                $('#allVacancy').prepend(clonedDiv);
                clonedDiv.attr("id",id);
                   $('#'+id).find('p#content').append('<p href="#" id='+type+id+' " >' + content + '</p>');
                     $('#'+id).find('p#name').append('<li href="#" id='+type+id+' " >' + senderName + '</li>');
                    $('#'+id).find('span#date').append('<li href="#" id='+type+id+' ">' + date + '</li>');
                    $('#'+id).find('span#type').append('<li href="#" id='+type+id+' >' + type + '</li>');
                    messageID.push(id);
                 // $('#allVacancy').hide();
            }  
              
            if(type==="Announcements"){
               // $('#allAnnouncement').show();
              var clonedDiv = $('#announcement').clone();
                $('#allAnnouncement').prepend(clonedDiv);
                clonedDiv.attr("id",id);
                   $('#'+id).find('p#content').append('<p href="#" id='+type+id+' " >' + content + '</p>');
                     $('#'+id).find('p#name').append('<li href="#" id='+type+id+' " >' + senderName + '</li>');
                    $('#'+id).find('span#date').append('<li href="#" id='+type+id+' ">' + date + '</li>');
                    $('#'+id).find('span#type').append('<li href="#" id='+type+id+' >' + type + '</li>');   
                    messageID.push(id);
            }  
          
            
         }
       
                
        
        
        
            }

    });
}


$(document).ready(function () {
     getNotificationPolling();
     

    $.ajax({
        url: 'webapi/alerts',
        method: "GET",
        datatype: "xml",
        success: getNotification,
        error: function (xhr, ajaxOption, thrownError) {
            
        }


    });
});