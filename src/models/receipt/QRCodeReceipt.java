package models.receipt;

import java.util.List;

public class QRCodeReceipt extends Receipt
{
	private List<String> data;
	
	public QRCodeReceipt(Printing printing, List<String> data)
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
