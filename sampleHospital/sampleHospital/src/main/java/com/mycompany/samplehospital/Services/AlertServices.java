package com.mycompany.samplehospital.Services;

import java.io.Serializable;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.mycompany.samplehospital.model.User;
import com.mycompany.samplehospital.model.Alert;

/**
 *
 * @author sandeshpoudel
 */
//alert services class provides all the services required for notification resources

public class AlertServices implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    UserServices users;
//list of all recievers
    //only allowed for those reciever lists
    
    List<Integer> recieverList = new ArrayList<Integer>();
//all the userlist avaialble 
    ArrayList<User> UserList = (ArrayList<User>) UserServices.getUsers();
//maps all the notification to the integer
    
    private static Map<Integer, Alert> alert = AllServices.getAlert();

    public AlertServices() {

        recieverList.add(1);
        recieverList.add(2);
        recieverList.add(3);
        recieverList.add(4);
        recieverList.add(5);

        //default notifications for testing purpose
        
        alert.put(1, new Alert(1, 1, "it's emergency", "Surendra Pandey", "Emergency", recieverList));
        alert.put(2, new Alert(2, 2, "Our expansion progress continues to make great strides in all areas! "
                + "Parking Construction on parking is currently underway, as concrete for the main hospital drive has been poured."
                + " A newly designed parking lot will e open in late June, "
                + "located in front of the main ER entrance—to help make your parking experience "
                + "at Jorvi a convenient one! Good Day To all !! ", "Sandesh Poudel", "Announcements", recieverList));
        alert.put(3, new Alert(3, 3, "We have vacancies in the following sectors", "Sushil Bastola", "Vacancy", recieverList));
        alert.put(4, new Alert(4, 4, "Jorvi Hospital today announced CLARITY Undiagnosed, its second international competition"
                + " to advance standards for genomic analysis in medicine. In this $25,000 crowd-sourcing "
                + "challenge, up to 30 research teams will seek to solve the medical mysteries "
                + "of five patients with undiagnosed disease and provide clinically useful, actionable information.", "Sagar Aryal", "Events", recieverList));

        alert.put(5, new Alert(5, 5, "Surgical Services (OR)\n" +
"Four new OR rooms opened in late April, and the other 4 will be completed in mid June, which has increased the capacity to a total of 16 ORs."
                + "", "Surakshya Kharel", "Announcment", recieverList));
        alert.put(6, new Alert(6, 6, "Clinical Governance Facilitator This is a new post and the successful candidate will work as an integral part of the Integrated Surgery, Cancer and Clinical"
                + " Support Services Directorate to facilitate the Directorate’s quality, safety and risk agenda. Working with the relevant teams the ...", "Sandesh Poudel", "Vacancy", recieverList));
        alert.put(7, new Alert(7, 7, "Lace up your green sneakers and bring your family and friends to this 5K run around Seattle Center presented by the Detlef Schrempf Foundation and supported by the Tailgaters Anonymous for Children’s Guild."
                + " A portion of the proceeds will benefit Seattle Children’s Autism Center; ticket prices vary. Use registration code SCACTEAM to save $3.", "Sushil Bastola", "Events", recieverList));
        alert.put(8, new Alert(8, 8, "Pop icon Madonna released her 13th album, Rebel Heart, in March 2015. The album’s tour currently has her travelling across the globe to perform...", "Sagar Aryal", "News", recieverList));
        
    }
//returns all the notifications
    public static List<Alert> getAlerts() {
        return new ArrayList<>(alert.values());

    }
//get specific notification based on it's id
    public Alert getAlert(int id) {
        return alert.get(id);
    }
// add a new notification
    public Alert AddAlert(Alert alrt) {
        alrt.setId(alert.size() + 1);
        alrt.setDate(new Date().toString());

        alert.put(alrt.getId(), alrt);
        return alrt;

    }
//update the previous notification
    public Alert updateAlert(Alert alrt) {
        if (alrt.getId() < 1) {
            return null;
        }
        alrt.setDate(new Date().toString());
        alert.put(alrt.getId(), alrt);
        return alrt;
    }
//remove the notification containing the following id
    public Alert removeAlert(Integer id) {
        return alert.remove(id);
    }
//gets all the notifications which are specific for the user with the given id
    public List<Alert> getAllAlertByUser(int userId) {
        ArrayList<Alert> myAlert = new ArrayList<>(alert.values());
        ArrayList<Alert> finalAlertList = new ArrayList<>();
        for (int i = 0; i < myAlert.size(); i++) {
            Alert alertAdder = myAlert.get(i);
            if (alertAdder.getSenderID() == userId) {
                finalAlertList.add(alertAdder);

            }
        }
        return finalAlertList;

    }

}
