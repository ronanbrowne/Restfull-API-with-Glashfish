
package com.mycompany.bank_client;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import java.util.Scanner;



/**
 * @author Ronan 
 * 
 * client to allow to lodge cash to their account
 * use the scanner input Via console to enter details when prompted
 * amount added and new balance of AC will then be displayed
 */
public class POST_Lodge_To_AC {


    public static void main(String[] args) {
        // set connecting port and host in PortConstants class
        PortConstants ports = new PortConstants();
        final String HOST = ports.HOST;
        final int PORT = ports.PORT;
        
        try {
            String target = HOST + PORT + "/BankingApplication/rest/banking/Lodge";

            Client client = Client.create();
            WebResource webResource = client.resource(target);

            System.out.println("Enter Ac number:");
            Scanner s1 = new Scanner(System.in);
            int accountNumber = s1.nextInt();

            System.out.println("Enter lodgement amount:");
            Scanner s2 = new Scanner(System.in);
            int amount = s2.nextInt();

            ClientResponse response = webResource.header("Acc", accountNumber).header("Amount", amount)
                    .post(ClientResponse.class);

            String result = response.getEntity(String.class);
            System.out.println(result);
            //extract  data split string at coma
            String one = result.split(",")[0];
            one = one.split("\\[")[1];
            String two = result.split(",")[1];
            two = two.split("\\]")[0];

            System.out.println("\n**Lodgemnet successful**\n " );
            System.out.println("Amount added to banance: " + one);
            System.out.println("new banance: " + two);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
