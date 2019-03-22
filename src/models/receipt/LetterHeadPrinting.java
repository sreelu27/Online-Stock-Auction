package models.receipt;

import java.util.List;

public class LetterHeadPrinting extends Printing
{
	@Override
	public void printReceiptToQRCode( List<String> data )
	{
		LetterHeadPrinter.printDocumentWithQRCode( data );
	}

	@Override
	public void printReceiptWithBarcode( List<String> data )
	{
		LetterHeadPrinter.printDocumentWithBarcode( data );
	}
}
