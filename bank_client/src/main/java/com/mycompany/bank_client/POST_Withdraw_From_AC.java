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
 */
public class POST_Withdraw_From_AC {
    

    public static void main(String[] args) {
        // set connecting port and host in PortConstants class
        PortConstants ports = new PortConstants();
        final String HOST = ports.HOST;
        final int PORT = ports.PORT;
        
        try {
            String target = HOST + PORT + "/BankingApplication/rest/banking/Withdraw";

            Client client = Client.create();
            WebResource webResource = client.resource(target);

            System.out.println("Enter Ac number:");
            Scanner s1 = new Scanner(System.in);
            int accountNumber = s1.nextInt();

            System.out.println("Enter withdraw amount:");
            Scanner s2 = new Scanner(System.in);
            double amount = s2.nextDouble();
            
            System.out.println("Enter password:");
            Scanner s3 = new Scanner(System.in);
            String password = s3.nextLine();

            ClientResponse response = webResource.header("Acc", accountNumber).header("Amount", amount).header("Password", password)
                    .post(ClientResponse.class);

            String result = response.getEntity(String.class);
            System.out.println(result);
            //extract  data split string at coma
            String one = result.split(",")[0];
            one = one.split("\\[")[1];
            String two = result.split(",")[1];
            two = two.split("\\]")[0];

            System.out.println("\n**Lodgemnet successful**\n " );
            System.out.println("Amount Withdrawn from AC: " + one);
            System.out.println("new banance: " + two);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
