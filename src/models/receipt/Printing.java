package models.receipt;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

public abstract class Printing
{
	abstract public void printReceiptToQRCode(Map<String,String> data,HttpServletResponse response);
	abstract public void printReceiptWithBarcode(Map<String,String> data,HttpServletResponse response);
}
