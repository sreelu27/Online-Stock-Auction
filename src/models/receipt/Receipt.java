package models.receipt;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

public abstract class Receipt
{
	protected Printing printing;
	protected Map<String,String> data;
	protected HttpServletResponse response;
	
	public Receipt(Printing printing)
	{
		this.printing = printing;
	}
	
	public abstract void print();
	
	protected void printReceiptToQRCode(Map<String,String> data,HttpServletResponse response)
	{
		printing.printReceiptToQRCode( data,response );
	}
	
	protected void printReceiptWithBarcode(Map<String,String> data,HttpServletResponse response)
	{
		printing.printReceiptWithBarcode( data,response );
	}
	
	public void setData(Map<String,String> data)
	{
		this.data = data;
	}
}
