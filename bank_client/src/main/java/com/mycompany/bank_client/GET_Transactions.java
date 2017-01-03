/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bank_client;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import java.util.Scanner;

/**
 *
 * @author Ronan
 * 
 * client for transaction logs change ID for different output
 */
public class GET_Transactions {

    private static final int ID = 1;

    public static void main(String[] args) {
        // set connecting port and host in PortConstants class
        PortConstants ports = new PortConstants();
        final String HOST = ports.HOST;
        final int PORT = ports.PORT;
        
        try {

     String target = HOST + PORT + "/BankingApplication/rest/banking/trans/"+ID;

            Client client = Client.create();
        WebResource resource = client.resource(target);
        ClientResponse response = resource.get(ClientResponse.class);

        System.out.println(response.getEntity(String.class));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
