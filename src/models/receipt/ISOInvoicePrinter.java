package models.receipt;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

public class ISOInvoicePrinter
{
	public static void printISOReceiptWithQRCode(Map<String,String> data,HttpServletResponse response)
	{
		data.forEach((k,v)->{
			System.out.println("Item : " + k + " Count : " + v);
			if("E".equals(k)){
				System.out.println("Hello E");
			}
		});
	}
	
	public static void printISOReceiptWithBarcode(Map<String,String> data,HttpServletResponse response)
	{
		data.forEach((k,v)->{
			System.out.println("Item : " + k + " Count : " + v);
			if("E".equals(k)){
				System.out.println("Hello E");
			}
		});
	}
}