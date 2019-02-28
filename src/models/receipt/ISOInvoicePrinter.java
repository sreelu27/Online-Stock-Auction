package models.receipt;

import java.util.List;

public class ISOInvoicePrinter
{
	public static void printISOReceiptWithQRCode(List<String> data)
	{
		for( String item : data)
		{
			System.out.println( item );
		}
	}
	
	public static void printISOReceiptWithBarcode(List<String> data)
	{
		for( String item : data)
		{
			System.out.println( item );
		}
	}
}
