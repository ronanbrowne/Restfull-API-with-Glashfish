
package com.mycompany.bank_client;

/**
 *
 * @author Ronan
 */

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import java.util.Scanner;
import javax.ws.rs.HeaderParam;



public class Delete_Customer_Account {


    public static void main(String[] args) {
        // set connecting port and host in PortConstants class
        PortConstants ports = new PortConstants();
        final String HOST = ports.HOST;
        final int PORT = ports.PORT;

        try {
            String target = HOST + PORT + "/BankingApplication/rest/banking/Delete";

            Client client = Client.create();
            WebResource webResource = client.resource(target);
            System.out.println("**You are about to delete you account this can not be undone**");

            System.out.println("Enter AC number:");
            Scanner s1 = new Scanner(System.in);
            int AccNum = s1.nextInt();

            System.out.println("Enter password:");
            Scanner s2 = new Scanner(System.in);
            String password = s2.nextLine();

            ClientResponse response = webResource.header("Acc", AccNum).header("Password", password)
                    .delete(ClientResponse.class);

            //System.out.println("status: " + response.getEntity(String.class));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }//end main
}
