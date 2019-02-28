package models.receipt;

import java.util.List;

public class ReceiptWithBarcode extends Receipt
{
	private List<String> data;

	public ReceiptWithBarcode( Printing printing, List<String> data )
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
