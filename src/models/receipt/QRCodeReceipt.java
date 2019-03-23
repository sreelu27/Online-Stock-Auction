package models.receipt;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

public class QRCodeReceipt extends Receipt
{
	
	
	public QRCodeReceipt(Printing printing, Map<String,String> data,HttpServletResponse response)
	{
		super(printing);
		this.data = data;		
		this.response = response;
	}

	@Override
	public void print()
	{
		printReceiptToQRCode( data ,response);
	}

}
