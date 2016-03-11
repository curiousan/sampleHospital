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

public class Authentication {

    UserServices myService;

    public Authentication() throws Exception {
        myService = new UserServices();

    }
//all the list of users 
    List<User> newList = UserServices.getUsers();
    //all the list of Chat Room
    List<ChatBox> newChatList = ChatBoxServices.getAllChatBOx();
    //method to check the authentication,,,,
    //takes the username and password ,encrypt the password and checks if it is valid for any user
    //valid case returns true 

    public boolean checkAuthentication(String userName, String passWord) throws Exception {

        HashPassword hs = new HashPassword();
        String newPassWord = hs.encrypt(passWord);

        for (int i = 0; i < newList.size(); i++) {
            System.out.println(userName);
            System.out.println(newPassWord);
            System.out.println(newList.get(i).getPassWord());
            if ((newList.get(i).getUserName().equals(userName)) && (newList.get(i).getPassWord().equals(newPassWord))) {
                return true;

            }
        }

        return false;

    }
//method to authenticate for the chat room,,,
    //password is provided by the admin
    //same signature as user authentication
    //valid case returns true

    public boolean checkPasswordAuthentication(Integer id, String password) throws Exception {
        try {
            HashPassword hs = new HashPassword();
            String newPassWord = hs.encrypt(password);
            for (ChatBox mychatBox : newChatList) {
                if ((Objects.equals(mychatBox.getChatBoxId(), id)) && (mychatBox.getPassword().equals(newPassWord))) {
                    return true;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return false;

    }
//gets the specific user based on username and password

    public User getUser(String userName, String passWord) throws Exception {
        HashPassword hs = new HashPassword();
        String newPassWord = hs.encrypt(passWord);
        for (int i = 0; i < newList.size(); i++) {
            if (newList.get(i).getUserName().equals(userName) && newList.get(i).getPassWord().equals(newPassWord)) {
                return newList.get(i);

            }

        }
        return null;
    }
}
