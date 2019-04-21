package models.entity;
//Author:Sreelekshmi Geetha
//Design Pattern:Command
//Concrete Command

public class DailyContract extends Contract implements Command
{	
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
	public String execute() {
		// TODO Auto-generated method stub
		return farmer.dispatchDaily();
	}

}
