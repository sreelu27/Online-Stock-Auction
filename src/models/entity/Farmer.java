package models.entity;

public class Farmer extends User
{
	String farmAddress;
	String telephone;
	boolean spam;
	private String statusType;
	private String [] productsInvolved;
	
	public Farmer()
	{
		// TODO Auto-generated constructor stub
	}
	
	public Farmer(String username,long userID,String password,String farmAddress,String telephone,Priority priority,String type)
	{
		super(username,userID,password,ProfileType.FARMER,priority,type);
		this.farmAddress = farmAddress;
		this.telephone = telephone;
	}

	public String getStatusType() {
		return statusType;
	}

	public void setStatusType(String statusType) {
		this.statusType = statusType;
	}
	
	public String getFarmAddress()
	{
		return farmAddress;
	}

	public void setFarmAddress( String farmAddress )
	{
		this.farmAddress = farmAddress;
	}

	public String getTelephone()
	{
		return telephone;
	}

	public void setTelephone( String telephone )
	{
		this.telephone = telephone;
	}

	public boolean isSpam()
	{
		return spam;
	}

	public void setSpam( boolean spam )
	{
		this.spam = spam;
	}

	public String[] getProductsInvolved()
	{
		return productsInvolved;
	}	
	
	public void dispatchDaily() {
		System.out.println("Delivery of daily contract is in progress!!!");
	}
 
	public void dispatchWeekly() {
		System.out.println("Delivery of weekly contract is in progress!!!");
	}
	
	public void dispatchMonthly() {
		System.out.println("Delivery of monthly contract is in progress!!!");
	}
 
	public void dispatchYearly() {
		System.out.println("Delivery of yearly contract is in progress!!!");
	}
	
}
