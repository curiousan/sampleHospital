package com.mycompany.samplehospital.Serialization;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Map;

import com.mycompany.samplehospital.Services.AlertServices;
import com.mycompany.samplehospital.Services.AllServices;
import com.mycompany.samplehospital.Services.MessageServices;
import com.mycompany.samplehospital.Services.UserServices;
import com.mycompany.samplehospital.model.Alert;
import com.mycompany.samplehospital.model.Message;
import com.mycompany.samplehospital.model.User;

public class WriteObject {
	public static void main(String[]args){
		getOutPutStream();
		
		
	}
	
    String text ="test";
	public static void getOutPutStream(){
		ArrayList<User> userList = (ArrayList<User>) UserServices.getUsers();
		ArrayList<Message> messageList = (ArrayList<Message>) MessageServices.getMessages();
		ArrayList<Alert> alertList = (ArrayList<Alert>) AlertServices.getAlerts();
		
		User test = new User(56,"test user");
		/** userList.add(new User(89,"sandesh"));
		messageList.add(new Message(3,4,"hello all"));
		alertList.add(new Alert(4,"ok")); */


		try {
			FileOutputStream fileName = new FileOutputStream("data.bin");
			ObjectOutputStream  ObjectStreamOutput = new ObjectOutputStream(fileName);
			ObjectStreamOutput.writeObject(userList);
			ObjectStreamOutput.writeObject(messageList);
			ObjectStreamOutput.writeObject(alertList);
			ObjectStreamOutput.writeObject(test);
			

			ObjectStreamOutput.flush();
			ObjectStreamOutput.close();

			fileName.close();
			System.out.println("wrote successfully");
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch(IOException ex){
			ex.printStackTrace();
		 
      
		
		} 
		
		
		
}
		
	


}
