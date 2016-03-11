package com.mycompany.samplehospital.Serialization;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import com.mycompany.samplehospital.model.Alert;
import com.mycompany.samplehospital.model.Message;
import com.mycompany.samplehospital.model.User;
//serialized the object instances
//not completed due to some reason
//will be completed soon

public class ReadObject {

    public static void main(String[] args) {
        try {
            getInputStream();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private static final ObjectInputStream ObjectStreamInput = null;

    public static void getInputStream() throws ClassNotFoundException {

        try {
            System.out.println("reading objects");

            FileInputStream fileName = new FileInputStream("data.bin");
            ObjectInputStream ObjectStreamInput = new ObjectInputStream(fileName);
            @SuppressWarnings("unchecked")
            ArrayList<User> readUser = (ArrayList<User>) ObjectStreamInput.readObject();
            ArrayList<Message> readMessage = (ArrayList<Message>) ObjectStreamInput.readObject();
            ArrayList<Alert> readAlerts = (ArrayList<Alert>) ObjectStreamInput.readObject();
            User newUser = (User) ObjectStreamInput.readObject();
            ObjectStreamInput.close();

            fileName.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();

        }

    }
}
