package models.receipt;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import models.entity.Contract;
import models.report.PDFStructure;

public class ReceiptService
{
	private static ReceiptService instance;
	
	private ReceiptService()
	{
		
	}
	
	public static ReceiptService getInstance() 
	{
		if(instance == null)
		{
			instance = new ReceiptService();
		}
		return instance;
	}
	
	public static Receipt createReceipt(ReceiptTypes type,Contract contract,Printing printing,HttpServletResponse response)
	{
		Receipt receipt = null;
		if(type.equals( ReceiptTypes.QR_CODE_RECEIPT ))
		{
			Map<String, String> data = new HashMap<>();
			data.put( PDFStructure.BODY.name()	, "CONTRACT ID : "+contract.getContractID()+"\nFARMER ID : "+contract.getAgreedBid().getFarmerID()+"\nPUBLISHED PRICE : "+contract.getAgreedBid().getProductStock().getUnitPrice());
			data.put( PDFStructure.FOOTER.name(), String.valueOf("FINAL AGREED PRICE : "+contract.getAgreedBid().getAgreedFinalPrice() ));
			data.put( PDFStructure.HEAD.name()	, "RECEIPT ISSUED DATE : "+new Date().toString());
			receipt = new QRCodeReceipt( printing, data,response );
		}
		return receipt;
	}
}
