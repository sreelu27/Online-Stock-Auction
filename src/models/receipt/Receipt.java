package models.receipt;

import java.util.List;

public abstract class Receipt
{
	protected Printing printing;
	
	public Receipt(Printing printing)
	{
		this.printing = printing;
	}
	
	public abstract void print();
	
	protected void printReceiptToQRCode(List<String> data)
	{
		printing.printReceiptToQRCode( data );
	}
	
	protected void printReceiptWithBarcode(List<String> data)
	{
		printing.printReceiptWithBarcode( data );
	}
}
