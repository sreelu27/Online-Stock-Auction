package models.receipt;

import java.util.Map;

public abstract class Printing
{
	abstract public void printReceiptToQRCode(Map<String,String> data);
	abstract public void printReceiptWithBarcode(Map<String,String> data);
}
