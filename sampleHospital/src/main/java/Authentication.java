/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author sandeshpoudel
 */
import java.util.List;
import com.mycompany.samplehospital.Services.UserServices;
import com.mycompany.samplehospital.model.User;

public class Authentication {

    private static List<User> UserList = UserServices.getUsers();

    public Authentication() {

    }

    public static boolean checkAuthentication(String userName, String password) {
        /*for (int i=0;i<UserList.size();i++){
		 if (userName==UserList.get(i).getFullName()){
			 return true;
		 }
	 } 	 return false;
         */
     return true;
        }

    public static User getUser(String userName, String password) {
        User result;
        for (int i=0;i<UserList.size();i++){
		 if (userName==UserList.get(i).getFullName()){
			 return UserList.get(i);
		 }
     
	 }
        if ((userName.equals("sandesh")) && (password.equals("12345"))) {
            System.out.println(UserList.get(1));
            result = UserList.get(1);
            return result;

        } else {

            return null;

        }
    }
}
