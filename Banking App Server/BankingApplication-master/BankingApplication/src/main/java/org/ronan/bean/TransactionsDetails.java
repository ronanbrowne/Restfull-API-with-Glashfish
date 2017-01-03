package org.ronan.bean;


//====================================================================
//  data model class for transaction class
//====================================================================
public class TransactionsDetails {

    //vars
	int id;
	int u_id;
	double adding;
        double sub;
	String Desc;
	
        //constructor
	public TransactionsDetails() {
		super();
	}
	public TransactionsDetails(int id,int u_id, String Desc, double adding, double sub) {
		super();
		this.id = id;
		this.u_id = u_id;
		this.Desc = Desc;
		this.adding = adding;
		this.sub = sub;
	}
        
        //getters setters
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getU_id() {
		return u_id;
	}
	public void setU_id(int u_id) {
		this.u_id = u_id;
	}
	public double getAdding() {
		return adding;
	}
	public void setAdding(double adding) {
		this.adding = adding;
	}
	public double getSub() {
		return sub;
	}
	public void setSub(double sub) {
		this.sub = sub;
	}
	public String getDesc() {
		return Desc;
	}
	public void setDesc(String desc) {
		Desc = desc;
	}

	
	
}
   