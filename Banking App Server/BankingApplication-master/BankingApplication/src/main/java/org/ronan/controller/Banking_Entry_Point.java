package org.ronan.controller;

import java.util.Arrays;
import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.ronan.bean.BankingDetails;
import org.ronan.bean.TransactionsDetails;
import org.ronan.bean.CustomerDetails;
import org.ronan.service.BankingService;

//====================================================================
//  Class to handel the URL entry points for this appliaction
//====================================================================
@Path("/banking")
public class Banking_Entry_Point {

    //set up
    BankingService BankingService = new BankingService();
    CustomerDetails ud = new CustomerDetails();

    
    //====================================================================
    //  get transactions
    //==================================================================
    @GET
    @Path("/trans/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<TransactionsDetails> get(@PathParam("id") int U_id) {
        List<TransactionsDetails> listOfTransactions = BankingService.getAllTransactions(U_id);
        return listOfTransactions;
    }

    //====================================================================
    //  returns a certain users AC details if they enter email and password 
    //==================================================================
    @GET
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    public List<CustomerDetails> getUserDetails(@HeaderParam("email") String Email, @HeaderParam("pass") String Password) {
        System.out.println(Email + Password);
        return BankingService.getUserDetails(Password, Email);
    }

    //====================================================================
    //  all details accociated wiht a particular bank 
    //====================================================================
    @GET
    @Path("/BankDetails")
    @Produces(MediaType.APPLICATION_JSON)
    public List<BankingDetails> getBankDetails() {
        return BankingService.getBankDetails();
    }

    @PUT
    @Path("/UpdateUser")
    @Produces(MediaType.APPLICATION_JSON)
    public void UpdateUser(@HeaderParam("Id") int id, @HeaderParam("Name") String name, @HeaderParam("Email") String email, @HeaderParam("Password") String password, @HeaderParam("Address") String address) {
        if (name.equals("")) {
            name = "null";
        }
        if (email.equals("")) {
            email = "null";
        }
        if (password.equals("")) {
            password = "null";
        }
        if (address.equals("")) {
            address = "null";
        } else {
            BankingService.UpdateUser(id, name, email, password, address);
        }
    }

    //====================================================================
    // create a new user
    //====================================================================
    @POST
    @Path("/NewUser")
    @Produces(MediaType.APPLICATION_JSON)
    public List<String> CreateUser(@HeaderParam("Name") String name, @HeaderParam("Email") String email, @HeaderParam("Password") String password, @HeaderParam("Address") String address, @HeaderParam("SortCode") int sortcode) {

        if (email.equals("") || password.equals("") || address.equals("") || sortcode == 0) {
            return null;
        } else {
            return BankingService.CreateUser(name, email, password, address, sortcode);
        }
    }
    //====================================================================
    //  Lodge cash to a AC
    //====================================================================

    @POST
    @Path("/Lodge")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Double> LodgeMoney(@HeaderParam("Acc") int AccNum, @HeaderParam("Amount") double amount) {
        List<Double> error = Arrays.asList(0.00);
        if (AccNum == 0 || amount == 0) {
            return error;
        } else {
            return BankingService.LodgeMoney(AccNum, amount);
        }
    }

    //====================================================================
    //  transfer between ACs
    //====================================================================
    @POST
    @Path("/Transfer")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Double> TransferMoney(@HeaderParam("sAcc") int sAccNum, @HeaderParam("rAcc") int rAccNum, @HeaderParam("Amount") double amount) {
        List<Double> error = Arrays.asList(0.00);
        System.out.println(sAccNum + "" + amount);
        if (rAccNum == 0 || amount == 0 || sAccNum == 0) {
            return error;
        } else {
            return BankingService.TransferMoney(sAccNum, rAccNum, amount);
        }
    }

    //====================================================================
    //  withdraw form AC
    //====================================================================
    @POST
    @Path("/Withdraw")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Double> WithDraw(@HeaderParam("Acc") String AccNum, @HeaderParam("Amount") double amount, @HeaderParam("Password") String Password) {
        List<Double> error = Arrays.asList(0.00);
        if (AccNum.equals("") || amount == 0 || Password.equals("")) {
            return error;
        } else {
            return BankingService.WithdrawMoney(AccNum, amount, Password);
        }
    }

    //====================================================================
    //  Delete a AC
    //====================================================================
    @DELETE
    @Path("/Delete")
    @Produces(MediaType.APPLICATION_JSON)
    public void deleteAcc(@HeaderParam("Acc") int AccNum, @HeaderParam("Password") String password) {
        BankingService.deleteAcc(AccNum, password);
    }

}
