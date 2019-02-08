package models.entity;

public class DailyContract extends Contract implements Command
{	
	private Farmer farmer;

	public DailyContract(Bid bid)
	{
		super("dailycontract"); // this is only a GSON library requirement for deserializing
		this.setStockFrequency(StockFrequency.DAILY);
		this.setAgreedBid(bid);
	}
	
	public DailyContract(Farmer farmer) {
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
		getStringBuilder().append( "  > This contract roles out in daily basis\n");
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
		farmer.dispatchDaily();
	}

}
