package models.receipt;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

public class ISOInvoicePrinter
{
	public static void printISOReceiptWithQRCode(Map<String,String> data,HttpServletResponse response)
	{
		// Part to implement when ISO Printing is considered for QR code
	}
	
	public static void printISOReceiptWithBarcode(Map<String,String> data,HttpServletResponse response)
	{
		// Part to implement when ISO Printing is considered for Bar code
	}
}
