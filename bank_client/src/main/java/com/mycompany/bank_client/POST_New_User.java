/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bank_client;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import java.util.List;
import java.util.Scanner;
import javax.ws.rs.HeaderParam;

/**
 * @author Ronan
 *
 * client to allow for creation of a new user use the scanner input Via console
 * to enter details when prompted they will be added to DB
 */
public class POST_New_User {


    public static void main(String[] args) {
        // set connecting port and host in PortConstants class
        PortConstants ports = new PortConstants();
        final String HOST = ports.HOST;
        final int PORT = ports.PORT;
        
        try {
            String target = HOST + PORT + "/BankingApplication/rest/banking/NewUser";

            Client client = Client.create();
            WebResource webResource = client.resource(target);

            System.out.println("Enter AC name:");
            Scanner s1 = new Scanner(System.in);
            String name = s1.nextLine();

            System.out.println("Enter email:");
            Scanner s2 = new Scanner(System.in);
            String email = s2.nextLine();

            System.out.println("Enter password:");
            Scanner s3 = new Scanner(System.in);
            String password = s3.nextLine();

            System.out.println("Enter home address:");
            Scanner s4 = new Scanner(System.in);
            String address = s4.nextLine();

            System.out.println("Enter sortcode:");
            Scanner s5 = new Scanner(System.in);
            int sort = s5.nextInt();

            ClientResponse response = webResource.header("Name", name).header("Email", email)
                    .header("Password", password)
                    .header("Address", address)
                    .header("SortCode", sort)
                    .post(ClientResponse.class
                    );

            System.out.println("Status: " + response.getEntity(String.class)+"\n");
         
            //out put new user details to screen using the login API to confirm creation
            System.out.println("New User Details: \n");
            String target_confirmUser = "http://127.0.0.1:" + PORT + "/JAXRSJsonCRUDExample/rest/banking/login";
            Client client_confirmUser = Client.create();
            WebResource webResource_confirmUser = client_confirmUser.resource(target_confirmUser);

            ClientResponse response_confirmUser = webResource_confirmUser.header("email", email).header("pass", password)
                    .get(ClientResponse.class);

            System.out.println(response_confirmUser.getEntity(String.class));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
