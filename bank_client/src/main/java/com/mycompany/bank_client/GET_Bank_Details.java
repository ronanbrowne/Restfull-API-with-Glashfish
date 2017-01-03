package com.mycompany.bank_client;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

/**
 * @author Ronan
 *
 * client to return transaction details.
 */
public class GET_Bank_Details {

        
    public static void main(String[] args) {
        
        // set connecting port and host in PortConstants class
        PortConstants ports = new PortConstants();
        final String HOST = ports.HOST;
        final int PORT = ports.PORT;
        
        try {
            String target = HOST + PORT + "/BankingApplication/rest/banking/BankDetails";

            Client client = Client.create();
            WebResource webResource = client.resource(target);
            ClientResponse response = webResource.get(ClientResponse.class);

            System.out.println("Result: " + response.getEntity(String.class));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
