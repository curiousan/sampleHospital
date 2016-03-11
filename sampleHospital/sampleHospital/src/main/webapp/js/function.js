/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
            var webSocket = new WebSocket("ws://localhost:8080/sampleHospital/chatClientEndPoint");
            var jsonData;
            webSocket.onmessage = function processMessage(message) {
                 jsonData = JSON.parse(message.data);
                
                console.log(jsonData);
                
                if (jsonData.message !== null){
                    var newDivMsg=$(' <div class="chat-box-left">');
                    var newDivName=$('<div class="chat-box-name-left">');
                    var img=$(' <img src="img/user.png" alt="bootstrap Chat box user image" class="img-circle" />');
                    var line=$('<hr class="hr-clas" />');
                    var $output=$('#displaybox');
                    $output.append(newDivMsg,newDivName);
                    newDivName.append('--Sent by....');
                    $output.append(line);
                    newDivMsg.append(jsonData.message);
                   
                    
                    
                   
                    
                }
            };
          
            function sendMessage() {
                webSocket.send(message.value);
                message.value = "";
            }


var id=$('#id').val();
        
        function deleteMessage()
        {
            $.ajax({
            url:'webapi/messages'+id,
            type:'delete',
            success:renderMessage,
            
            error: function(){
                alert('something went wrong'); 
            }
          
            
        });
            
            
        }
        
        
         function getRequest(){
             $.ajax({
                 url:'webapi/messages',
                 datatype:'text/plain',
                 processData:false,
                 type:'get',
                 success:renderMessage,
                 error:function(){
                     alert('Something went wrong ');
                     
                 }
                 
                 
                 
             });
             
             
             
             
         }
         
        function renderMessage(xml){
                    //returnedData=(new XMLSerailizer()).seri
            console.log(xml);
            
            
        }
        
        function register(){
            var username=$('#username').val();
            var password=$('#password').val();
            var fullname=$('#fullname').val();
            var age=$('#age').val();
            var gender=$('#gender').val();
            var address=$('#address').val();
            var phone=$('#phone').val();
            var user='<user><userName></userName><passWord></passWord><id></id><fullName></fullName><age></age><sex></sex><phoneNo></phoneNo><title></title><address></address></user>';
            var xml=$.parseXML(user);
            var doc=$(xml);   
            doc.find('userName').append(username);
            doc.find('fullName').append(fullname);
            doc.find('passWord').append(password);
            doc.find('age').append(age);
            doc.find('sex').append(gender);
            doc.find('address').append(address);
             doc.find('phoneNo').append(phone);
            serializedString=(new XMLSerializer()).serializeToString(xml);
            console.log(serializedString);
            $.ajax({
                type:'post',
                url:'webapi/users',
                data:xml,
                processData:false,
                contentType:"application/xml",
                dataType:'xml',
                success:function(){
                    alert('User Added');
                    
                },
                error: function(){
                    alert('Something went wrong');
                    
                    
                }
            
        });
    }
        function postRequest(){
            var message='<message><content></content><date></date><id></id><recieverID></recieverID><senderID></senderID></message>';
            var xmlDoc=$.parseXML(message);
            var $xml=$(xmlDoc);
            var messageValue= $('#message').val();
            $xml.find('content').append(messageValue);
            serializedString=(new XMLSerializer()).serializeToString(xmlDoc);
            console.log(serializedString);
            
            $.ajax({
                type:'post',
                url:'webapi/messages',
                data:xmlDoc,
                processData:false,
                contentType:"application/xml",
                dataType:'xml',
                success:renderMsg,
                error: function(){
                    alert('Something went wrong');
                    
                    
                }
                
                
                });
               function renderMsg(xml){
                    var $xml=$(xml);
                    console.log(xml);
                    /*
                    var newDivMsg=$(' <div class="chat-box-left">');
                    var newDivName=$('<div class="chat-box-name-left">');
                    var img=$(' <img src="img/user.png" alt="bootstrap Chat box user image" class="img-circle" />');
                    var line=$('<hr class="hr-clas" />');
                    console.log($xml);
                    var $output=$('#displaybox');
                    $output.append(newDivMsg,newDivName);
                    $xml.find('message').each(function(){
                        newDivMsg.append($(this).find('content').text());
                        var id=$(this).find('id').text();
                        newDivName.append(img);
                        newDivName.append('--Sent by '+ id);
                        $output.append(line);
                    });
                    */
                    
                }
           
            
            
        }
        