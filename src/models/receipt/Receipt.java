package models.receipt;

import java.util.List;
import java.util.Map;

public abstract class Receipt
{
	protected Printing printing;
	
	public Receipt(Printing printing)
	{
		this.printing = printing;
	}
	
	public abstract void print();
	
	protected void printReceiptToQRCode(Map<String,String> data)
	{
		printing.printReceiptToQRCode( data );
	}
	
	protected void printReceiptWithBarcode(Map<String,String> data)
	{
		printing.printReceiptWithBarcode( data );
	}
}
