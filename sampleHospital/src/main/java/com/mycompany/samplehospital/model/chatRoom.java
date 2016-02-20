
package com.mycompany.samplehospital.model;

import java.util.List;

/**
 *
 * @author sandeshpoudel
 */

public class chatRoom {
    private final  int roomId;
private List<User> userList;
private List<Message> messageList;
private  final String accessCode;
public chatRoom(String code,int Id){
    accessCode = "xyz";
    this.roomId = Id;
    
    
}

   
    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public List<Message> getMessageList() {
        return messageList;
    }

    public void setMessageList(List<Message> messageList) {
        this.messageList = messageList;
    }
} 


    

