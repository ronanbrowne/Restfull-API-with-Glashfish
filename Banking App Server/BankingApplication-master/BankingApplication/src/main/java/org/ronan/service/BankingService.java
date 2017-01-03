package org.ronan.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.sql.*;

import org.ronan.bean.BankingDetails;
import org.ronan.bean.TransactionsDetails;
import org.ronan.bean.CustomerDetails;

//====================================================================
//  here we handle the lodgic and prepared SQL statment that will be 
//  called from the Banking_entry_point class
//====================================================================
public class BankingService {

//maps for storage purposes, using these along with lists to retuen values
    static HashMap<Integer, BankingDetails> hashMapBank = getBankIdMap();
    static HashMap<Integer, CustomerDetails> hashMapDeatails = gethashMapDeatails();
    static HashMap<Integer, TransactionsDetails> hashMapTrans = getTransactionsIdMap();

//connection data
    static final String JDBC = "com.mysql.jdbc.Driver";
    static final String DatabaseConnection = "jdbc:mysql://localhost:3306/BankingApp";
    static final String DatabaseUser = "root";
    static final String DatabasePassword = "password";

    
    //get all transactions
    public List<TransactionsDetails> getAllTransactions(int U_id) {
        
        // variables 
        hashMapTrans = new HashMap<Integer, TransactionsDetails>();
        Connection conn = null;
        PreparedStatement GetTrans;
        ResultSet rs;
        ArrayList<TransactionsDetails> list = new ArrayList<TransactionsDetails>();
        
        
        try {
            //connecting to DB with connection data
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DatabaseConnection, DatabaseUser, DatabasePassword);
            try {
                //get all transactions for this user id
                GetTrans = conn.prepareStatement("Select * from Transactions Where U_Id=" + U_id + ";");
                try {
                    //run query for every enrty and add to list
                    rs = GetTrans.executeQuery();
                    int x = 0;
                    while (rs.next()) {
                        x++;
                        list.add(new TransactionsDetails(rs.getInt("Id"), rs.getInt("U_Id"), rs.getString("Description"),
                                rs.getDouble("Adding"), rs.getDouble("subtracting")));
                    }
                    //close stream
                    GetTrans.close();
                    rs.close();
                    conn.close();

                } catch (Exception e) {
                    //catch for transaction setter
                    conn.close();
                    GetTrans.close();
                    System.out.println(e);
                }
            } catch (Exception e) {
                //catch for connetion failed
                conn.close();
                System.out.println(e);
            }
        } catch (Exception e) {
            //catch for drivemanager fail
            System.out.println(e);
        }
        
        //put all from list into hash map
        for (int x = 0; x < list.size(); x++) {
            hashMapTrans.put(x + 1, list.get(x));
        }
        
        //return results
        List<TransactionsDetails> TransactionList = new ArrayList<TransactionsDetails>(hashMapTrans.values());
        return TransactionList;
    }

    //get user details
    public List<CustomerDetails> getUserDetails(String password, String email) {
        
        //variables 
        hashMapDeatails = new HashMap<Integer, CustomerDetails>();
        Connection conn = null;
        PreparedStatement GetUD;
        ResultSet rs;
        ArrayList<CustomerDetails> list = new ArrayList<CustomerDetails>();
        
        
        try {
            //connecting to DB with connection data
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DatabaseConnection, DatabaseUser, DatabasePassword);
            try {
                GetUD = conn.prepareStatement(
                        //get all details for user providing pass and email
                        "Select * from Users Where Password='" + password + "' and email='" + email + "';");
                try {
                    //run query for every enrty and add to list
                    rs = GetUD.executeQuery();
                    while (rs.next()) {
                        list.add(new CustomerDetails(rs.getInt("Id"), rs.getString("Name"), rs.getString("Address"),
                                rs.getString("Email"), rs.getInt("AccNum"), rs.getInt("B_SortCode"),
                                rs.getDouble("AccBalance"), rs.getString("Password")));
                    }
                    //close the stream
                    GetUD.close();
                    rs.close();
                    conn.close();

                } catch (Exception e) {
                    //catch for setter
                    conn.close();
                    GetUD.close();
                    System.out.println(e);
                }
            } catch (Exception e) {
                //catch for connetion failed
                conn.close();
                System.out.println(e);
            }
        } catch (Exception e) {
            //catch for drivemanager fail
            System.out.println(e);
        }
        //put all from list into hash map
        for (int x = 0; x < list.size(); x++) {
            hashMapDeatails.put(x + 1, list.get(x));
        }

        //return results
        List<CustomerDetails> UserDetailsList = new ArrayList<CustomerDetails>(hashMapDeatails.values());
        return UserDetailsList;
    }

    //list bank details
    public List<BankingDetails> getBankDetails() {
        
        //variables
        hashMapBank = new HashMap<Integer, BankingDetails>();
        Connection conn = null;
        PreparedStatement GetB;
        ResultSet rs;
        ArrayList<BankingDetails> list = new ArrayList<BankingDetails>();
        
        try {
            //connecting to DB with connection data
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DatabaseConnection, DatabaseUser, DatabasePassword);
            
            try {
                //get all details from Banks
                GetB = conn.prepareStatement("Select * from Banks;");
                try {
                    //run query and add to list
                    rs = GetB.executeQuery();
                    while (rs.next()) {
                        list.add(new BankingDetails(rs.getInt("Id"), rs.getString("Name"), rs.getString("Address"),
                                rs.getInt("SortCode")));
                    }
                    //close the stream
                    GetB.close();
                    rs.close();
                    conn.close();

                } catch (Exception e) {
                    //catch for setter
                    conn.close();
                    GetB.close();
                    System.out.println(e);
                }
            } catch (Exception e) {
                //catch for connetion failed
                conn.close();
                System.out.println(e);
            }
        } catch (Exception e) {
            //catch for drivemanager fail
            System.out.println(e);
        }
        //put all from list into hash map
        for (int x = 0; x < list.size(); x++) {
            hashMapBank.put(x + 1, list.get(x));
        }

        //return results
        List<BankingDetails> BankList = new ArrayList<BankingDetails>(hashMapBank.values());
        return BankList;

    }

    //create a user
    public List<String> CreateUser(String name, String email, String password, String address, int sortcode) {
        
        //variables
        hashMapDeatails = new HashMap<Integer, CustomerDetails>();
        boolean param = false;
        Connection conn = null;
        PreparedStatement GetUD, SetNU, GetID;
        ResultSet rs;
        ArrayList<CustomerDetails> list = new ArrayList<CustomerDetails>();
        int id = 0;
        
        
        try {
            //connecting to DB with connection data
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DatabaseConnection, DatabaseUser, DatabasePassword);
            try {
                //get new unique id
                GetID = conn.prepareStatement("Select Id from Users order by Id desc limit 1;");
                
                //add user's details
                SetNU = conn.prepareStatement(
                        "INSERT INTO  Users(Name,email,Password,AccNum,Address,AccBalance,B_SortCode) Values(?,?,?,?,?,0,?);");
               
                //get all details for this user
                GetUD = conn.prepareStatement("Select * from Users Where Password='" + password + "' and AccNum=?;");
                try {
                    //run the query
                    rs = GetID.executeQuery();
                    while (rs.next()) {
                        id = rs.getInt("Id");
                    }
                    //set deailts in corisponding fields and update
                    SetNU.setString(1, name);
                    SetNU.setString(2, email);
                    SetNU.setString(3, password);
                    SetNU.setInt(4, 123 + id);
                    SetNU.setString(5, address);
                    SetNU.setInt(6, sortcode);
                    SetNU.executeUpdate();
                    
                    //add to list
                    GetUD.setInt(1, sortcode);
                    rs = GetUD.executeQuery();
                    while (rs.next()) {
                        list.add(new CustomerDetails(rs.getInt("Id"), rs.getString("Name"), rs.getString("Address"),
                                rs.getString("Email"), rs.getInt("AccNum"), rs.getInt("B_SortCode"),
                                rs.getDouble("AccBalance"), rs.getString("Password")));
                    }
                    
                    //close the streams
                    GetUD.close();
                    GetID.close();
                    SetNU.close();
                    rs.close();
                    conn.close();
                    param = true;

                } catch (Exception e) {
                    //catch for setter
                    conn.close();
                    GetUD.close();
                    GetID.close();
                    SetNU.close();
                    System.out.println(e);
                }
            } catch (Exception e) {
                //catch for connetion failed
                conn.close();
                System.out.println(e);
            }
        } catch (Exception e) {
            //catch for drivemanager fail
            System.out.println(e);
        }
        //put all from list into hash map
        for (int x = 0; x < list.size(); x++) {
            hashMapDeatails.put(x + 1, list.get(x));
        }

        //return results if filled 
        if (param == true) {
            List<String> message = Arrays.asList("Complete");
            return message;
        } else {
            List<String> message = Arrays.asList("Failed");
            return message;
        }

    }

    //Lodge money to account
    public List<Double> LodgeMoney(int accNum, double amount) {
        
        //variables 
        boolean tester = false;
        double balance = 0.00;
        int id = 0;
        Connection conn = null;
        PreparedStatement GetBal, SetTran, UpdateBal;
        ResultSet rs;
        
        try {
            //connecting to DB with connection data
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DatabaseConnection, DatabaseUser, DatabasePassword);
            
            try {
                // get balance for user
                GetBal = conn.prepareStatement("Select AccBalance, Id from Users Where accNum=" + accNum + ";");
                
                // set the transaction
                SetTran = conn.prepareStatement(
                        "INSERT INTO Transactions(U_Id, Description, Adding,subtracting) Values(?,'Lodgment',?,0) ;");
                
                //update balance
                UpdateBal = conn.prepareStatement("UPDATE Users SET AccBalance=? WHERE accNum=" + accNum + ";");

                try {
                    //run the query
                    rs = GetBal.executeQuery();
                    while (rs.next()) {
                        id = rs.getInt("Id");
                        balance = rs.getDouble("AccBalance");
                    }

                    //set transaction details and update
                    SetTran.setInt(1, id);
                    SetTran.setDouble(2, amount);
                    SetTran.executeUpdate();

                    //set balance details and update
                    UpdateBal.setDouble(1, (amount + balance));
                    UpdateBal.executeUpdate();

                    // update balance amount
                    balance = balance + amount;

                    //close the stream
                    GetBal.close();
                    SetTran.close();
                    UpdateBal.close();
                    rs.close();
                    conn.close();
                    tester = true;

                } catch (Exception e) {
                    //catch for setter
                    conn.close();
                    GetBal.close();
                    SetTran.close();
                    UpdateBal.close();
                    System.out.println(e);
                }
            } catch (Exception e) {
                //catch for connetion failed
                conn.close();
                System.out.println(e);
            }
        } catch (Exception e) {
            //catch for drivemanager fail
            System.out.println(e);
        }
        
        //return results if transaction complete
        List<Double> BankBalance = Arrays.asList(amount, balance);
        if (tester == true) {
            return BankBalance;
        } else {
            return null;
        }

    }

    // transfer money to account
    public List<Double> TransferMoney(int sAccNum, int rAccNum, double amount) {
        
        //variables
        boolean tester = false;
        int[] id = new int[2];
        double[] balance = new double[2];
        Connection conn = null;
        PreparedStatement GetBalSender, SetTran, SetTran2, UpdateBalSender, UpdateBalRe, GetBalRe;
        ResultSet rs;
        
        try {
            //connecting to DB with connection data
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DatabaseConnection, DatabaseUser, DatabasePassword);
            
            try {
                //get sender of the money
                GetBalSender = conn.prepareStatement("Select AccBalance, Id from Users Where accNum=" + sAccNum + ";");
                
                //get reciver of the money
                GetBalRe = conn.prepareStatement("Select AccBalance, Id from Users Where accNum=" + rAccNum + ";");
                
                //update transactions list
                SetTran = conn.prepareStatement(
                        "INSERT INTO Transactions(U_Id, Description, Adding,subtracting) Values(?,'Transfer',?,0);");
                SetTran2 = conn.prepareStatement(
                        "INSERT INTO Transactions(U_Id, Description, Adding,subtracting) Values(?,'Transfer',0,?);");
                
                //update balances of users
                UpdateBalSender = conn.prepareStatement("UPDATE Users SET AccBalance=? WHERE accNum=" + sAccNum + ";");
                UpdateBalRe = conn.prepareStatement("UPDATE Users SET AccBalance=? WHERE accNum=" + rAccNum + ";");

                try {

                    //run the querys
                    rs = GetBalSender.executeQuery();
                    while (rs.next()) {
                        id[0] = rs.getInt("Id");
                        balance[0] = rs.getDouble("AccBalance");
                    }
                    rs = GetBalRe.executeQuery();
                    while (rs.next()) {
                        id[1] = rs.getInt("Id");
                        balance[1] = rs.getDouble("AccBalance");
                    }
                    
                    //if the is more than the amount being transfered set the amounts and update
                    if (balance[0] > amount) {
                        SetTran.setInt(1, id[0]);
                        SetTran.setDouble(2, amount);
                        SetTran.executeUpdate();
                        SetTran2.setInt(1, id[1]);
                        SetTran2.setDouble(2, amount);
                        SetTran2.executeUpdate();

                        UpdateBalSender.setDouble(1, (balance[0] - amount));
                        UpdateBalSender.executeUpdate();
                        
                        UpdateBalRe.setDouble(1, (amount + balance[1]));
                        UpdateBalRe.executeUpdate();
                    }
                    
                    //close the stream
                    GetBalSender.close();
                    GetBalRe.close();
                    SetTran.close();
                    UpdateBalSender.close();
                    UpdateBalRe.close();
                    rs.close();
                    conn.close();
                    tester = true;

                } catch (Exception e) {
                    //catch for setter
                    GetBalSender.close();
                    GetBalRe.close();
                    SetTran.close();
                    UpdateBalSender.close();
                    UpdateBalRe.close();
                    System.out.println(e);
                }
            } catch (Exception e) {
                //catch for connetion failed
                conn.close();
                System.out.println(e);
            }
        } catch (Exception e) {
            //catch for drivemanager fail
            System.out.println(e);
        }
        
        //return results if transaction complete
        List<Double> BankBalance = Arrays.asList(amount, (balance[0] - amount));
        if (tester == true) {
            return BankBalance;
        } else {
            return null;
        }

    }

    public List<Double> WithdrawMoney(String accNum, double amount, String Password) {
        
        //variables
        boolean tester = false;
        double balance = 0.00;
        int id = 0;
        Connection conn = null;
        PreparedStatement GetBal, SetTran, UpdateBal;
        ResultSet rs;
        
        try {
            //connecting to DB with connection data
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DatabaseConnection, DatabaseUser, DatabasePassword);
            try {
                //get the users balance
                GetBal = conn.prepareStatement("Select AccBalance, Id from Users Where accNum=" + accNum
                        + " and Password ='" + Password + "';");
                
                //insert transaction
                SetTran = conn.prepareStatement(
                        "INSERT INTO Transactions(U_Id, Description, Adding,subtracting) Values(?,'Lodgment',0,?) ;");
                
                //update users balance
                UpdateBal = conn.prepareStatement("UPDATE Users SET AccBalance=? WHERE accNum=" + accNum + ";");

                try {
                    //run the query
                    rs = GetBal.executeQuery();
                    while (rs.next()) {
                        id = rs.getInt("Id");
                        balance = rs.getDouble("AccBalance");
                    }

                    //if balance is greater than withdraw amount update the details
                    if (balance > amount) {
                        SetTran.setInt(1, id);
                        SetTran.setDouble(2, amount);
                        SetTran.executeUpdate();

                        UpdateBal.setDouble(1, (balance - amount));
                        UpdateBal.executeUpdate();

                        balance = balance - amount;
                    }
                    GetBal.close();
                    SetTran.close();
                    UpdateBal.close();
                    rs.close();
                    conn.close();
                    tester = true;

                } catch (Exception e) {
                    //catch for setter
                    GetBal.close();
                    SetTran.close();
                    UpdateBal.close();
                    System.out.println(e);
                }
            } catch (Exception e) {
                //catch for connetion failed
                conn.close();
                System.out.println(e);
            }
        } catch (Exception e) {
            //catch for drivemanager fail
            System.out.println(e);
        }
        
        //return results if transaction complete
        List<Double> BankBalance = Arrays.asList(amount, balance);
        if (tester == true) {
            return BankBalance;
        } else {
            return null;
        }

    }

    public void deleteAcc(int acc, String password) {

        //variables
        Connection conn = null;
        PreparedStatement DeleteUser;

        try {
            //connecting to DB with connection data
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DatabaseConnection, DatabaseUser, DatabasePassword);
            try {
                // delete this user
                DeleteUser = conn.prepareStatement("DELETE FROM Users Where AccNum=? and Password=?;");

                try {
                    //set details and update
                    DeleteUser.setInt(1, acc);
                    DeleteUser.setString(2, password);
                    DeleteUser.executeUpdate();
                    
                    //close the stream
                    DeleteUser.close();
                    conn.close();
                    
                } catch (Exception e) {
                    //catch for setter
                    DeleteUser.close();
                    System.out.println(e);
                }
            } catch (Exception e) {
                //catch for connetion failed
                conn.close();
                System.out.println(e);
            }
        } catch (Exception e) {
            //catch for drivemanager fail
            System.out.println(e);
        }

    }

    public void UpdateUser(int id, String name, String email, String password, String address) {
        
        //variables
        Connection conn = null;
        PreparedStatement UpdateUser, GetUser;
        ResultSet rs;
        String s1 = null, s2 = null, s3 = null, s4 = null;
        int a1 = 0, a2 = 0, a3 = 0;
        double c1 = 0.00;

        try {
            //connecting to DB with connection data
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DatabaseConnection, DatabaseUser, DatabasePassword);
            try {
                // get user's details
                GetUser = conn.prepareStatement("Select * from Users Where Id=" + id + ";");
                
                //update new details
                UpdateUser = conn.prepareStatement("UPDATE Users SET Name =?, email =?, Password =?, Address=? Where id=?;");
                
                try {
                    //run the query 
                    rs = GetUser.executeQuery();
                    while (rs.next()) {
                        //set new details
                        a1 = rs.getInt("Id");
                        s1 = rs.getString("Name");
                        s2 = rs.getString("Address");
                        s3 = rs.getString("Email");
                        a2 = rs.getInt("AccNum");
                        a3 = rs.getInt("B_SortCode");
                        c1 = rs.getDouble("AccBalance");
                        s4 = rs.getString("Password");

                    }
                    //close the stream
                    GetUser.close();

                    //catch for null values
                    if (name.equals("null")) {
                        name = s1;
                    }
                    if (address.equals("null")) {
                        address = s2;
                    }
                    if (email.equals("null")) {
                        email = s3;
                    }
                    if (password.equals("null")) {
                        password = s4;
                    }

                    //update details
                    UpdateUser.setString(1, name);
                    UpdateUser.setString(2, email);
                    UpdateUser.setString(3, password);
                    UpdateUser.setString(4, address);
                    UpdateUser.setInt(5, a1);

                    UpdateUser.executeUpdate();

                    UpdateUser.close();
                    conn.close();
                } catch (Exception e) {
                    //catch for setter
                    GetUser.close();
                    UpdateUser.close();
                    System.out.println(e);
                }
            } catch (Exception e) {
                //catch for connetion failed
                conn.close();
                System.out.println(e);
            }
        } catch (Exception e) {
            //catch for drivemanager fail
            System.out.println(e);
        }

    }

    //return hash maps
    public static HashMap<Integer, BankingDetails> getBankIdMap() {
        return hashMapBank;
    }

    public static HashMap<Integer, TransactionsDetails> getTransactionsIdMap() {
        return hashMapTrans;
    }

    public static HashMap<Integer, CustomerDetails> gethashMapDeatails() {
        return hashMapDeatails;
    }

}
