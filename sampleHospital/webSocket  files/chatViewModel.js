MessageTypes.GETMESSAGES = 5;
MessageTypes.MESSAGELIST =6;
function chatViewModel(chatClient){
    //messages
    function joinMessage(name){
this.type= MessageTypes.MESSAGE;
  this.name = name;  
    }
    function NewChatMessage(userName,message){
        this.type= MessageTypes.MESSAGE;
        this.userName = userName;
        this.message = message;
        this.timeSent = new Date();
    }
    function DisplayChatMessage( userName,message,timeSent,isFirst,isDate){
   var self = this;
        this.type = MessageTypes.MESSAGE;
        this.userName = userName;
        this.message = message;
        this.isFirst = isFirst;
        this.isDate = isDate;
        this.timeSent = timeSent;
        
        this.timeSentTimeDisplay = ko.computed(function(){
            return moment(self.timeSent).format('h:mm:ss a');
            
            
        });
        
        this.timeSentDateDisplay = ko.computed(function(){
        return moment(self.timeSent).format("DD/MM/YYYY");    
                                               
                                               
       });
        this.timeSentFullDisplay = ko.computed(function(){
        return moment(self.timeSent).format("DD/MM/YYYY, h:mm:ss a");    

            
        });
        
    }





}
