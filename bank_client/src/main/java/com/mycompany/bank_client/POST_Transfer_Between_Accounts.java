package com.mycompany.bank_client;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import java.util.List;
import java.util.Scanner;
import javax.ws.rs.HeaderParam;

/**
 *
 * @author Ronan
 * 
 * Client to allow the transfer of funds between two accounts
 * must use valid AC number eg 4321 and 124
 */
public class POST_Transfer_Between_Accounts {


    public static void main(String[] args) {
        // set connecting port and host in PortConstants class
        PortConstants ports = new PortConstants();
        final String HOST = ports.HOST;
        final int PORT = ports.PORT;
        
        try {
            String target = HOST + PORT + "/BankingApplication/rest/banking/Transfer";

            Client client = Client.create();
            WebResource webResource = client.resource(target);

            System.out.println("Enter senders AC number:");
            Scanner s1 = new Scanner(System.in);
            int sender = s1.nextInt();

            System.out.println("Enter recivers AC number:");
            Scanner s2 = new Scanner(System.in);
            int reciever = s2.nextInt();

            System.out.println("Enter amount to transfer");
            Scanner s3 = new Scanner(System.in);
            Double amount = s3.nextDouble();

            ClientResponse response = webResource.header("sAcc", sender).header("rAcc", reciever)
                    .header("Amount", amount)
                    .post(ClientResponse.class
                    );

           
            
            //format the string returned for output
            String result = response.getEntity(String.class);
      
            //extract  data split string at coma
            String one = result.split(",")[0];
            one = one.split("\\[")[1];
            String two = result.split(",")[1];
            two = two.split("\\]")[0];

            System.out.println("\n**Transfer Succesfull **\n " );
            System.out.println("Amount transfered: " + one);
            System.out.println("New banance: " + two);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
