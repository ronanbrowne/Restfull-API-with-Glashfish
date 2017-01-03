package org.ronan.bean;

//====================================================================
//  data model class for customers
//====================================================================
public class CustomerDetails {

    //variables
    int id;
    String Name;
    String Address;
    String Email;
    String Password;
    int SortCode, AccountNumber;
    double Balance;

    
    //constructor
    public CustomerDetails() {
        super();
    }

    public CustomerDetails(int id, String Name, String Address, String Email, int AccountNumber, int SortCode, double Balance, String Password) {
        super();
        this.id = id;
        this.Name = Name;
        this.Address = Address;
        this.Email = Email;
        this.Password = Password;
        this.AccountNumber = AccountNumber;
        this.SortCode = SortCode;
        this.Balance = Balance;
    }

    
    //setters getters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public int getSortCode() {
        return SortCode;
    }

    public void setSortCode(int sortCode) {
        SortCode = sortCode;
    }

    public int getAccountNumber() {
        return AccountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        AccountNumber = accountNumber;
    }

    public double getBalance() {
        return Balance;
    }

    public void setBalance(double balance) {
        Balance = balance;
    }

}
