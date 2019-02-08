package models.entity;

public class WeeklyContract extends Contract implements Command
{
    private Farmer farmer;
    
	public WeeklyContract(Bid bid)
	{
		super("weeklycontract"); // this is only a GSON library requirement for deserializing
		this.setStockFrequency(StockFrequency.WEEKLY);
		this.setAgreedBid(bid);
	}

	public WeeklyContract(Farmer farmer) {
		this.farmer=farmer;
	}
	
	@Override
	public double getPriceOnFrequency()
	{
		return getAgreedBid().getAgreedFinalPrice();
	}

	@Override
	public StringBuilder getContractConstraints()
	{
		getStringBuilder().append( "  > This contract roles out in weekly basis\n");
		return getStringBuilder();
	}
	
	@Override
	public String getFarmerDetails() {
		return "Farmer ID : "+ getAgreedBid().getFarmerID();
	}

	@Override
	public String getRetailerDetails() {
		return "Retailer ID : "+ getAgreedBid().getRetailerID();
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
		farmer.dispatchWeekly();
		
	}

}
