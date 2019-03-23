package models.receipt;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

public class LetterHeadPrinting extends Printing
{
	@Override
	public void printReceiptToQRCode( Map<String,String> data ,HttpServletResponse response)
	{
		LetterHeadPrinter.printDocumentWithQRCode( data,response );
	}

	@Override
	public void printReceiptWithBarcode( Map<String,String> data,HttpServletResponse response )
	{
		LetterHeadPrinter.printDocumentWithBarcode( data,response );
	}
}
