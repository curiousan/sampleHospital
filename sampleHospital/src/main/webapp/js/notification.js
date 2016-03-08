/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function sendNotification() {
    var type = $('select[name=type]').val();
    var message = $('textarea#message').val();
    var senderId = $('#getSession').val();
    var alert = $.parseXML('<alert><content></content><type></type><senderID></senderID><id></id><date></date></alert>');
    var ajaxalert = $(alert);
    ajaxalert.find('content').append(message);
    ajaxalert.find('type').append(type);
    ajaxalert.find('senderID').append(senderId);
    //console.log(receivers);
    console.log('the message ' + message);
    console.log('senderid ' + senderId);
    //console.log(try1);
    $.ajax({
        url: 'webapi/alerts',
        type: 'post',
        data: alert,
        processData: false,
        contentType: 'application/xml',
        success: function (xml) {
            console.log(xml);
        },
        error: function () {
            console.log('error');
        }
    });
}
function getNotification(xml) {
    console.log(xml);
    var content;
    var type;
    var sagar="sagar";
    var sushil="sushil";
   var typeCollection=[];
    $(xml).find('alert').each(function () {
        content = $(this).find('content').text();
        type = $(this).find('type').text();
        var senderId = $(this).find('senderID').text();
        var recieversList =[];
        var  sessionId = $("#getSession").val();
        $(this).find('userList').find('userList').each(function () {
         var id = $(this).find('userList').text();
          recieversList.push(id);
        });
        if((isInArray(sessionId,recieversList))){
       if(typeCollection.indexOf(type)===-1){
            
             $('#alert').append('<a href="#" id='+type+'>' + type + '</a>');
                typeCollection.push(type);
                if(typeCollection.indexOf(type)!=-1){                
                $('#'+type).append('<br>'+content+senderId);
         }
       }
            else{
                $('#'+type).append('<br>'+content+senderId)
            }    
        
        }
        
      

    
    });
}
$(document).ready(function () {
    getUsersPolling();
    $.ajax({
        url: 'webapi/alerts',
        method: "GET",
        datatype: "xml",
        success: getNotification,
        error: function (xhr, ajaxOption, thrownError) {
            console.log(xhr.status);
            console.log(thrownError);
        }


    });
});