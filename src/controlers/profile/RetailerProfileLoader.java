package controlers.profile;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.contract.ContractService;
import models.entity.Retailer;
import models.payment.PaymentService;
import models.product.ProductService;
import models.product.ProductStockService;
import models.profile.ProfilesService;

/**
 * Servlet implementation class FarmerProfileLoader
 */
@WebServlet("/RetailerProfileLoader")
public class RetailerProfileLoader extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public  RetailerProfileLoader() {
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
		String event =  request.getParameter("tabEvent");
		response.setContentType("application/json");
		HttpSession session = request.getSession(true);
		Retailer user = (Retailer)ProfilesService.getProfileServiceInstance(getServletContext()).getProfile((String)session.getAttribute("username"));	
		if(event != null &&  event.equals("Manage Contracts"))
		{
			response.getWriter().append(ProductService.getProductServiceInstance(getServletContext()).getProductsAsJSON());		    
		}
		else if(event != null &&  event.equals("Manage Payments"))
		{
			String contractJSON = ContractService.getContractServiceInstance(getServletContext()).getRetailerContracts( user.getUserID() );
			System.out.println( contractJSON );
			String accounts = PaymentService.getPaymentServiceInstance( getServletContext() ).getUserBankAccountsAsJSON( user.getUserID() );
			response.getWriter().append("{\"contracts\":"+contractJSON+",\"accounts\":"+accounts+"}");		    
		}
		else if (event != null &&  event.equals("View Product Catalogue"))
		{
			response.getWriter().append(ProductService.getProductServiceInstance(getServletContext()).getProductsAsJSON());			
		}
		else if(request.getParameter("product-dropdown") != null)
		{
			response.getWriter().append(ProductStockService.getProductStockServiceInstance( getServletContext() ).getSearchedProductStocksAsJSON(request.getParameter("product-dropdown"),request.getParameter("frequency-dropdown"),request.getParameter("quantity")));	
		}
		else
		{
			response.getWriter().append("{\"state\":\"Success\",\"message\":\"Login Successfull..!!\",\"page\":\""+user.getUsername()+"\",\"id\":\""+user.getUserID()+"\",\"address\":\""+user.getCompanyAddress()+"\",\"tel\":\""+user.getTel()+"\",\"spam\":\""+user.isSpam()+"\"}");			
		}		
	}

}
