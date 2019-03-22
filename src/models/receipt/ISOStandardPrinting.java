package models.receipt;

import java.util.List;

public class ISOStandardPrinting extends Printing
{

	@Override
	public void printReceiptToQRCode( List<String> data )
	{
		ISOInvoicePrinter.printISOReceiptWithQRCode( data );
	}

	@Override
	public void printReceiptWithBarcode( List<String> data )
	{
		ISOInvoicePrinter.printISOReceiptWithBarcode( data );
	}

}
