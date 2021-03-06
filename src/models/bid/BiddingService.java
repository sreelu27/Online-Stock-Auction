package models.bid;

import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import com.google.gson.reflect.TypeToken;

import models.entity.Bid;
import models.entity.EntityService;
import models.entity.Product;
import models.entity.ProductStock;
import models.entity.RandomNumberGenerator;
import models.entity.StockFrequency;
import models.notification.NotificationService;

public class BiddingService extends EntityService
{
	private static BiddingService instance;
	List<Bid> bids = new ArrayList<>();
	static String filePath = "/WEB-INF/db/bid/Bids.json";
	
	private BiddingService(ServletContext context)
	{
		super(context,filePath);
	}
	
	public static BiddingService getBiddingServiceInstance(ServletContext context)
	{
		if(instance == null)
		{
			instance = new BiddingService(context);
			instance.loadEntities();
		}
		return instance;
	}
	
	public String addBid(ProductStock stock,long retailerID,double agreedPrice)
	{
		String message = "";
		boolean bidExists = false;
		if(stock == null)
		{
			message = "{\"state\":\"ProductStock not found..\"}";
		}
		Bid bid = new Bid();
		bid.setRetailerID( retailerID );
		bid.setProductStock( stock );
		bid.setBidID( RandomNumberGenerator.getLongID() );
		bid.setFarmerID( stock.getFarmerID() );
		bid.setAgreedFinalPrice( agreedPrice );
		bid.setSignedByRetailer( true );
		for(Bid extBid : bids)
		{
			if(extBid.equals( bid ))
			{
				message = "{\"state\":\"You have added a bid already to this Product Stock\"}";
				bidExists = true;
				break;
			}
		}
		if(!bidExists)
		{
			NotificationService.getNotificationServiceInstance().updateFarmerAboutTheBid( Double.toString( agreedPrice ), stock );
			message = "{\"state\":\"Bid placed successfully\"}";
			bids.add( bid );
		}	
		return message;
	}
	
	public boolean bidAlreadyExists(long retailerID, ProductStock stock)
	{
		return false;
	}
	
	public void loadEntities()
	{
		try
		{
			super.loadEntities();
		}
		catch ( FileNotFoundException e )
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		TypeToken<List<Bid>> token = new TypeToken<List<Bid>>() {};
		bids = getGson().fromJson(new InputStreamReader(getIs()), token.getType());
	}
	
	private void testGSON()
	{
		List<Bid> bids = new ArrayList<>();
		bids.add( new Bid(new ProductStock( new Product("Carrots",3893930), 1000, StockFrequency.DAILY, 560000,999003 ),550000,10020,38839,true,true) );
		bids.add( new Bid(new ProductStock( new Product("Onions",5453434), 1500, StockFrequency.MONTHLY, 4000,999002 ),3900,13430,43434,true,true) );
		bids.add( new Bid(new ProductStock( new Product("Cabage",563354), 125000, StockFrequency.WEEKLY, 6000,999001 ),5500,23534,57688,true,true) );
		
		System.out.println( getGson().toJson(bids)); 
	}

	public List<Bid> getBids()
	{
		return bids;
	}
	
	public void addBid(Bid bid)
	{
		bids.add( bid );
	}
	
	public Bid getBid(long bidID)
	{
		for(Bid bid : this.bids)
		{
			if(bid.getBidID() == bidID)
				return bid;
		}
		return null;
	}
	
	public String getBidsAsJSON()
	{
		return getGson().toJson(bids);
	}
	
	public String getFarmerBids(long userID)
	{
		List<Bid> bids = new ArrayList<>();
		for(Bid bid : this.bids)
		{
			if(bid.getFarmerID() == userID)
				bids.add( bid );
		}
		return getGson().toJson(bids);
	}	
}
