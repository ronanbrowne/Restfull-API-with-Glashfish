package com.mycompany.bank_client;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import java.util.Scanner;
/**
 * @author Ronan 
 * 
 * client to allow a user to log in and view their banking details
 * use the scanner input Via console to enter email and password of current user
 */

public class GET_Login_User {


    public static void main(String[] args) {
        // set connecting port and host in PortConstants class
        PortConstants ports = new PortConstants();
        final String HOST = ports.HOST;
        final int PORT = ports.PORT;
        
        try {
            String target = HOST + PORT + "/BankingApplication/rest/banking/login";

            Client client = Client.create();
            WebResource webResource = client.resource(target);

            System.out.println("Enter email:");
            Scanner s1 = new Scanner(System.in);
            String email = s1.nextLine();

            System.out.println("Enter password:");
            Scanner s2 = new Scanner(System.in);
            String password = s2.nextLine();

            ClientResponse response = webResource.header("email", email).header("pass", password)
                    .get(ClientResponse.class);

            System.out.println("User Details: " + response.getEntity(String.class));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }//end main
}//end class
