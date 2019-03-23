package models.receipt;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

public class ISOStandardPrinting extends Printing
{

	@Override
	public void printReceiptToQRCode( Map<String,String> data ,HttpServletResponse response)
	{
		ISOInvoicePrinter.printISOReceiptWithQRCode( data ,response);
	}

	@Override
	public void printReceiptWithBarcode( Map<String,String> data,HttpServletResponse response )
	{
		ISOInvoicePrinter.printISOReceiptWithBarcode( data,response );
	}

}
