package controlers.profile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.plugface.core.PluginManager;

import ie.ul.interfaces.Chart;
import models.contract.ContractService;
import models.entity.User;
import models.product.ProductService;
import models.profile.ProfilesService;
import models.report.export.DataExport;
import models.report.export.EmailDataExportAdapter;
import models.report.export.EmailExpoter;
import pluginconfig.PluginInHandler;

/**
 * Servlet implementation class AdminProfileLoader
 */
@WebServlet("/AdminProfileLoader")
public class AdminProfileLoader extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminProfileLoader() {
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
		HttpSession session = request.getSession(true);
		String username = (String)session.getAttribute("username");
		String event =  request.getParameter("tabEvent");
		String disableProduct =  request.getParameter("disableProduct");
		try
		{
			PluginManager pluginManager = PluginInHandler.getInstance(getServletContext()).getPluginManager();
			Chart chart = pluginManager.getPlugin(Chart.class); 
			chart.draw( new ArrayList<>());
		}
		catch ( Exception e )
		{
			e.printStackTrace();
		}
		
		response.setContentType("application/json");
		ProductService.getProductServiceInstance( getServletContext() );		
		User user = ProfilesService.getProfileServiceInstance(getServletContext()).getProfile(username);
		
		if(event != null &&  event.equals("Product"))
		{
			response.getWriter().append(ProductService.getProductServiceInstance( getServletContext() ).getProductsAsJSON());
		}
		else if(event != null &&  event.equals("Spams"))
		{
			response.getWriter().append(ProfilesService.getProfileServiceInstance( getServletContext() ).getProfilesAsJSON());
		}
		else if(disableProduct != null && disableProduct.length()>0)
		{
			response.getWriter().append(ProductService.getProductServiceInstance( getServletContext() ).disableEnableProduct( Long.parseLong( disableProduct ) ));
		}
		else if("generateReport".equals(request.getParameter("formSubmit"))) 
		{
			DataExport export = new EmailDataExportAdapter(new EmailExpoter());
			List<String> emailList = new ArrayList<>();
			export.exportData(ContractService.getContractServiceInstance(getServletContext()).getContractsPerFrequency(request.getParameter("frequency-dropdown")),emailList);
			response.getWriter().append("{\"state\":\"Success\",\"message\":\"Export to emails successfully..!!\",\"page\":\""+user.getUsername()+"\",\"id\":"+user.getUserID()+"}");
		}
		else
		{		
			response.getWriter().append("{\"state\":\"Success\",\"message\":\"Login Successfull..!!\",\"page\":\""+user.getUsername()+"\",\"id\":"+user.getUserID()+"}");
		}		
	}
	
	
	@SuppressWarnings("serial")
	@WebServlet("/logout")
	public class LogoutServlet extends HttpServlet {

	    @Override
	    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    	HttpSession session = request.getSession();
	    	session.removeAttribute("username");
	    	session.invalidate();
	        //request.getSession().invalidate();
	        response.sendRedirect(request.getContextPath() + "../index.jsp");
	    
	    }

	}

}
