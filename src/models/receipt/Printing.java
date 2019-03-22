package models.receipt;

import java.util.List;

public abstract class Printing
{
	abstract public void printReceiptToQRCode(List<String> data);
	abstract public void printReceiptWithBarcode(List<String> data);
}
