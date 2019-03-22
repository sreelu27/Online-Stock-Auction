package models.receipt;

import java.util.Map;

public class ReceiptWithBarcode extends Receipt
{
	private Map<String,String> data;

	public ReceiptWithBarcode( Printing printing, Map<String,String> data )
	{
		super(printing);
		this.data = data;
	}

	@Override
	public void print()
	{
		printReceiptWithBarcode( data );
	}
}
