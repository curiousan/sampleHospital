package Authentication;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author sandeshpoudel
 */
import com.mycompany.samplehospital.Services.ChatBoxServices;
import java.util.List;
import com.mycompany.samplehospital.Services.UserServices;
import com.mycompany.samplehospital.model.ChatBox;
import com.mycompany.samplehospital.model.User;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;



public class Authentication  {
UserServices myService ;
    public Authentication() throws Exception {
        myService = new UserServices();
       

    }
    
     List<User> newList = UserServices.getUsers();
     List<ChatBox> newChatList = ChatBoxServices.getAllChatBOx();
     
  
    public  boolean checkAuthentication(String userName, String passWord) throws Exception {
          
           HashPassword hs = new HashPassword();
          String  newPassWord = hs.encrypt(passWord);
        
        for (int i = 0; i < newList.size(); i++) {
            System.out.println(userName);
            System.out.println(newPassWord);
            System.out.println(newList.get(i).getPassWord());
          if ((newList.get(i).getUserName().equals(userName)) &&( newList.get(i).getPassWord().equals(newPassWord)) ){ 
           //   if ((newList.get(i).getUserName().equals(userName)) &&( newList.get(i).getPassWord().equals(passWord)) ){
           return true;

            }
        }
      
                return false;

    }
    public boolean checkPasswordAuthentication(Integer id,String password) throws Exception{
    try {
        HashPassword hs = new HashPassword();
        String newPassWord = hs.encrypt(password);
         for (ChatBox mychatBox:newChatList ){
         if ((Objects.equals(mychatBox.getChatBoxId(), id)) &&( mychatBox.getPassword().equals(newPassWord)) ){ 
         return true;
         }
    } 
    } catch(Exception ex){
        ex.printStackTrace();
    }
        
    return false;
        
    }
    public  User getUser(String userName, String passWord) throws Exception {
            HashPassword hs = new HashPassword();
       String newPassWord = hs.encrypt(passWord);
        for (int i = 0; i < newList.size(); i++) {
         if (newList.get(i).getUserName().equals(userName) && newList.get(i).getPassWord().equals(newPassWord)) { 
          //    if ((newList.get(i).getUserName().equals(userName)) &&( newList.get(i).getPassWord().equals(passWord)) ){
       return newList.get(i);

            }

    }
        return null;
}
}
