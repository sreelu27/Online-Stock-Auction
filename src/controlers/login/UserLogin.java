package controlers.login;

import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;

import models.entity.User;
import models.login.LoginService;
import models.profile.ProfilesService;
import paymentgateway.SecurePaymentProcessor;
import paymentgateway.SecurePaymentProcessorHelper;

/**
 * Servlet implementation class UserLogin
 */
@WebServlet("/UserLogin")
public class UserLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserLogin() {
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
		LoginService.setServletContext( getServletContext() );//getting the profile
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		HttpSession session = request.getSession(true);
		test();

		session.setAttribute("username", request.getParameter("username"));
		response.setContentType("application/json");		
		User user = null;
		if(ProfilesService.getProfileServiceInstance(getServletContext()).validLoginCheck( username, password ))
		{
			user = ProfilesService.getProfileServiceInstance(getServletContext()).getProfile(username);
			//response.getWriter().append("{\"state\":\"Success\",\"message\":\"Login Successfull..!!\",\"page\""+":\""+user.getProfileType()+"\"}");
			response.getWriter().append("{\"state\":\"Success\",\"message\":\"Login Successfull..!!\",\"page\""+":\""+user.getProfileType()+"\",\"name\":\""+user.getUsername()+"\"}");
		}
		else
		{
			response.getWriter().append(ProfilesService.getProfileServiceInstance(getServletContext()).validLogin( username, password ));
		}		
	}
	
	void test ()
	{
		try
		{
			//-ORBInitialPort 1050 -ORBInitialHost localhost
			Properties p = new Properties();
			p.put("org.omg.PortableInterceptor.ORBInitializerClass.orb.InterceptorORBInitializer", "");
			//ORB orb = ORB.init(args, p);
			
			String[] orbArgs = { "-ORBInitialHost", "localhost", "-ORBInitialPort", "1050" };
			//ORB orb = ORB.init( orbArgs, null );
			ORB orb = ORB.init(orbArgs, p);
			org.omg.CORBA.Object objRef = orb.resolve_initial_references( "NameService" );
			NamingContextExt ncRef = NamingContextExtHelper.narrow( objRef );
			SecurePaymentProcessor processor = (SecurePaymentProcessor) SecurePaymentProcessorHelper.narrow(ncRef.resolve_str("ABC"));
			System.out.println("###### BEFORE CALLING REMOTE OBJECT ######");
			processor.processPayment( "ACC_NO", "IBAN", "1400" );
		   
		    System.out.println("ORB Object called successfully..!!");
		}
		catch ( Exception e )
		{
			System.out.println( "Failed to initialise ORB: " + e );
			e.printStackTrace();
			//throw new ServletException( "ORB initialisation failure", e );
		}
	}

}
