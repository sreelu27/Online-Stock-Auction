package models.receipt;

import java.util.Map;

public class QRCodeReceipt extends Receipt
{
	private Map<String,String> data;
	
	public QRCodeReceipt(Printing printing, Map<String,String> data)
	{
		super(printing);
		this.data = data;		
	}

	@Override
	public void print()
	{
		printReceiptToQRCode( data );
	}

}
