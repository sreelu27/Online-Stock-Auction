package orb;

import java.util.Map;
import java.util.Properties;

import org.omg.CORBA.Any;
import org.omg.CORBA.NVList;
import org.omg.CORBA.NamedValue;
import org.omg.CORBA.ORB;
import org.omg.CORBA.Request;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContext;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.CosNaming.NamingContextHelper;

import paymentgateway.SecurePaymentProcessor;
import paymentgateway.SecurePaymentProcessorHelper;

public class DIIMethodInvoker
{
	private static NamingContextExt ncRef;
	private static DIIMethodInvoker instance;
	private static org.omg.CORBA.Object objRef;
	private static ORB orb;
	
	private DIIMethodInvoker()
	{
		
	}
	
	public static DIIMethodInvoker getInstance() 
	{
		if(instance == null)
		{
			instance = new DIIMethodInvoker();
			try
			{
				if(ncRef == null)
				{
					//-ORBInitialPort 1050 -ORBInitialHost localhost
					Properties p = new Properties();
					p.put("org.omg.PortableInterceptor.ORBInitializerClass.orb.InterceptorORBInitializer", "");
					//ORB orb = ORB.init(args, p);
					
					String[] orbArgs = { "-ORBInitialHost", "localhost", "-ORBInitialPort", "1050" };
					//NO_NEED ORB orb = ORB.init( orbArgs, null );
					orb = ORB.init(orbArgs, p);
					//objRef = orb.resolve_initial_references( "NameService" );
					//ncRef = NamingContextExtHelper.narrow( objRef );
					
					//DII Additional configs
					org.omg.CORBA.Object ncRef = orb.resolve_initial_references ("NameService"); 
					NamingContext nc = NamingContextHelper.narrow (ncRef); 
					NameComponent nComp = new NameComponent ("ABC", ""); 
					NameComponent [] path = {nComp}; 
					objRef = nc.resolve (path); 
				}			
			}
			catch ( Exception e )
			{
				System.out.println( "Failed to initialise ORB: " + e );
				e.printStackTrace();
			}
		}
		return instance;
	}
	
	public String callRemoteMethod(Map<String,String> valueMap,String methodName)
	{
		try
		{
			//SecurePaymentProcessor processor = (SecurePaymentProcessor) SecurePaymentProcessorHelper.narrow(ncRef.resolve_str("ABC"));
			System.out.println("###### BEFORE CALLING REMOTE OBJECT ######");
			//processor.processPayment( "ACC_NO", "IBAN", "1400" );		   
		    System.out.println("ORB Object called successfully..!!!");
		    
		    NVList argList = orb.create_list (valueMap.size()); 
		    for (Map.Entry<String, String> entry : valueMap.entrySet()) {
		    	Any argument = orb.create_any (); 
		    	argument.insert_string (entry.getValue()); 
		        argList.add_value (entry.getKey().toLowerCase(), argument, org.omg.CORBA.ARG_IN.value); 
		    }
		    
		    
		    //Result
		    Any result = orb.create_any (); 
		    result.insert_string( null ); 
		    NamedValue resultVal = orb.create_named_value ("result", result, org.omg.CORBA.ARG_OUT.value); 
		    
		    //Invoking Method
		    Request thisReq = objRef._create_request (null, methodName, argList, resultVal); 
		    thisReq.invoke (); 
		    
		    //Extract Result
		    result = thisReq.result().value (); 
		    System.out.println ("get_price () returned: " + result.extract_string()); 
		    
		    
		}
		catch ( Exception e )
		{
			e.printStackTrace();
		}
		return null;
	}
}
