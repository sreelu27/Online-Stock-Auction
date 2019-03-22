package controlers.payment;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.contract.ContractService;
import models.entity.Contract;
import models.payment.PaymentService;
import models.receipt.LetterHeadPrinting;
import models.receipt.QRCodeReceipt;
import models.receipt.Receipt;
import models.receipt.ReceiptService;
import models.receipt.ReceiptTypes;

/**
 * Servlet implementation class PaymentController
 */
@WebServlet("/PaymentController")
public class PaymentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PaymentController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		String selectedContractID = request.getParameter("contract-dropdown");
		String fundAmount = request.getParameter("fundAmount");

		String account = request.getParameter("account-dropdown");
		String addFundsAccount = request.getParameter("addfunds-dropdown");
		if(fundAmount != null)
		{
			response.getWriter().append(PaymentService.getPaymentServiceInstance( getServletContext() ).addFundsToAccount( Double.parseDouble( fundAmount ), addFundsAccount ));
		}
		else if(selectedContractID != null && !"printReceipt_clicked".equals(request.getParameter("printReceipt")))
		{
			Contract contract = ContractService.getContractServiceInstance( getServletContext() ).getContractByID(selectedContractID);
			response.getWriter().append(PaymentService.getPaymentServiceInstance( getServletContext() ).makePayment( contract, account ));
		}		
		else if("printReceipt_clicked".equals(request.getParameter("printReceipt")))
		{
			Contract contract = ContractService.getContractServiceInstance( getServletContext() ).getContractByID(selectedContractID);
			if(PaymentService.getPaymentServiceInstance( getServletContext() ).checkIfPaymentAllocated( contract ))
			{
				// Print receipt
				Receipt receipt = ReceiptService.getInstance().createReceipt( ReceiptTypes.QR_CODE_RECEIPT, contract, new LetterHeadPrinting() );
				receipt.print();
			}
			else
			{
				response.getWriter().append("{\"state\":\"success\",\"message\":\"A payment has not allocated to this\"}");
			}
		}
	}

}
