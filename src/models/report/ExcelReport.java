package models.report;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

public class ExcelReport implements IReport
{

	@Override
	public void generateReport( Object reportObject, HttpServletResponse response ) throws IOException
	{
		System.out.println( "Code for generating Excel report" );// excel
	}

	@Override
	public void emailDocument( StringBuilder builder )
	{
		System.out.println( "Code for emailing Excel report" );
	}

}
