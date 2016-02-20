
import Authentication.HashPassword;
import com.mycompany.samplehospital.Services.UserServices;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author sandeshpoudel
 */
public class main {
    public static  void main(String[]args) throws Exception{
        UserServices newService = new UserServices();
        HashPassword hs = new HashPassword();
        System.out.println(newService.getUser(1).getPassWord());
        System.out.println(newService.getUser(2).getPassWord());
        System.out.println(hs.decrypt(newService.getUser(2).getPassWord()));
        System.out.println(hs.encrypt("password"));
        
    }
    
}
