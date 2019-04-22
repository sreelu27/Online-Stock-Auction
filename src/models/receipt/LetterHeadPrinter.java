package models.receipt;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.Element;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

import models.report.IReportStructure;
import models.report.PDFStructure;
import models.report.ReportCreator;
import models.report.ReportService;

public class LetterHeadPrinter
{
	public static void printDocumentWithQRCode(Map<String,String> data,HttpServletResponse response)
	{
		IReportStructure doc = new IReportStructure()
		{
			
			@Override
			// layout
			public String getHeader()
			{
				return data.get( PDFStructure.HEAD.name() );
			}
			
			@Override
			public String getFooter()
			{
				return data.get( PDFStructure.FOOTER.name());
			}
			
			@Override
			public PdfPTable getBody( PdfPTable table )
			{
				// the place we can integrate QR code generaton framework and add the QR image.
				PdfPCell header = new PdfPCell(new Phrase("CONTRACT DETAILS"));
				header.setRowspan(1);
				header.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(header);
				table.addCell(new PdfPCell(new Phrase(data.get( PDFStructure.BODY.name() ))));
			    return table;
			}
		};
		try
		{
			ReportService.getReportServiceInstance(ReportCreator.getReportFactory()).printDocument( doc, "PDF", response );
		}
		catch ( IOException e )
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void printDocumentWithBarcode(Map<String,String> data,HttpServletResponse response)
	{
		
	}
}
