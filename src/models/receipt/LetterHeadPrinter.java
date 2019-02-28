package models.receipt;

import java.util.List;

public class LetterHeadPrinter
{
	public static void printDocumentWithQRCode(List<String> data)
	{
		for( String item : data)
		{
			System.out.println( item );
		}
	}
	
	public static void printDocumentWithBarcode(List<String> data)
	{
		for( String item : data)
		{
			System.out.println( item );
		}
	}
}
