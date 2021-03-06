package ws;
import javax.jws.WebService;
import javax.jws.WebMethod;
import java.util.Enumeration;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;
import javax.annotation.PostConstruct;

@WebService
public class Greeting
{
   @Resource 
   WebServiceContext context;

   @WebMethod
   public String hello(String userName)
   {
      System.out.println("\n\t[Greeting WebService] hello() called: Welcome Mr./Ms. " + userName + "! Have a nice day...");
      return "Welcome Mr./Ms. " + userName + "! Have a nice day...";
   }

   @WebMethod
   public String getRequestHeaders()
   {
      System.out.println("\n\t[Greeting WebService] exploreHttpSession() called");
      MessageContext mc = context.getMessageContext();   // This is line that causes the NullPointer exception
      HttpServletRequest request = (HttpServletRequest) mc.get(MessageContext.SERVLET_REQUEST);
      HttpSession session = request.getSession(true);
      System.out.println("\n\t Got HttpSession  session.getId(): "+session.getId());

      System.out.println("\n\t Exploring HttpServletRequest Headers");
      Enumeration en = request.getHeaderNames();
      String allHttpHeaders="HEADERS: ";
      while(en.hasMoreElements())
        {
            String headerKey=(String)en.nextElement();
            String headerValue=request.getHeader(headerKey);
            System.out.println("\n"+headerKey+" = "+headerValue);     
            allHttpHeaders=allHttpHeaders+ "\n"+headerKey+" = "+headerValue;
        }
      return allHttpHeaders;
   }
}


