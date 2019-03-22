package models.receipt;

import java.util.Map;

public class LetterHeadPrinter
{
	public static void printDocumentWithQRCode(Map<String,String> data)
	{
		data.forEach((k,v)->{
			System.out.println("Item : " + k + " Count : " + v);
			if("E".equals(k)){
				System.out.println("Hello E");
			}
		});
	}
	
	public static void printDocumentWithBarcode(Map<String,String> data)
	{
		data.forEach((k,v)->{
			System.out.println("Item : " + k + " Count : " + v);
			if("E".equals(k)){
				System.out.println("Hello E");
			}
		});
	}
}
