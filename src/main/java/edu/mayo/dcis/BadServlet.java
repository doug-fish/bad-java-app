package edu.mayo.dcis;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Created by m089269 on 10/30/17.
 */
public class BadServlet extends HttpServlet{
  private static final String MODE_LEAK = "leak";
  private static final String MODE_SLOW = "slow";
  private static final String MODE_BEHAVE = "behave";
  private static final int LEAK_SIZE = 1048576;

  private String mode = BadServlet.MODE_BEHAVE;
  private ArrayList leakList = new ArrayList();
  private int delay = 0;

  /**
   * Respond to a GET request for the content produced by
   * this servlet.
   *
   * @param request The servlet request we are processing
   * @param response The servlet response we are producing
   *
   * @exception IOException if an input/output error occurs
   * @exception ServletException if a servlet error occurs
   */
  public void doGet(HttpServletRequest request,
                    HttpServletResponse response)
      throws IOException, ServletException {
    
    String query = request.getQueryString();
    if (query != null) {
        doAction(query);
    }

    doModeEffect();

    HttpSession session = request.getSession();
    Object sessionLifeO = session.getAttribute("sessionLife");
    Integer sessionLife = null;
    if (sessionLifeO == null) {
        sessionLife = new Integer(0);
    } else {
        sessionLife = new Integer(((Integer)sessionLifeO).intValue() + 1);
    }
    session.setAttribute("sessionLife", sessionLife);
    
    response.setContentType("text/html");
    PrintWriter writer = response.getWriter();
    writer.println("<html>");
    writer.println("<head>");
    writer.println("<title>Sample Application Servlet Page</title>");
    writer.println("<meta http-equiv=\"refresh\" content=\"5\">");
    writer.println("</head>");
    writer.println("<body bgcolor=white>");

    writer.println("<table border=\"0\" cellpadding=\"10\">");
    writer.println("<tr>");
    writer.println("<td>");
    writer.println("<img src=\"images/Pivotal_Logo.png\">");
    writer.println("</td>");
    writer.println("<td>");
    writer.println("<h1>Sample Application Servlet</h1>");
    writer.println("</td>");
    writer.println("</tr>");

    writer.println("<tr>");
    writer.println("<td>");
    writer.println("servlet id");
    writer.println("</td>");
    writer.println("<td>");
    writer.println(this.hashCode());
    writer.println("</td>");
    writer.println("</tr>");

    writer.println("<tr>");
    writer.println("<td>");
    writer.println("Session Request lifetime");
    writer.println("</td>");
    writer.println("<td>");
    writer.println(session.getAttribute("sessionLife"));
    writer.println("</td>");
    writer.println("</tr>");

    writer.println("<tr>");
    writer.println("<td>");
    writer.println("Mode");
    writer.println("</td>");
    writer.println("<td>");
    writer.println(this.mode);
    writer.println("</td>");
    writer.println("</tr>");

    writer.println("<tr>");
    writer.println("<td>");
    writer.println("Leaked Memory");
    writer.println("</td>");
    writer.println("<td>");
    writer.println(this.leakList.size() * BadServlet.LEAK_SIZE);
    writer.println("</td>");
    writer.println("</tr>");

    writer.println("<tr>");
    writer.println("<td>");
    writer.println("Delay");
    writer.println("</td>");
    writer.println("<td>");
    writer.println(this.delay);
    writer.println("</td>");
    writer.println("</tr>");

    writer.println("</table>");

    writer.println("</body>");
    writer.println("</html>");
  }

  private void doAction(String string) {
      System.out.println("changing mode to " + string);
      switch (string) {
          case "behave":
              this.mode = BadServlet.MODE_BEHAVE;
              this.leakList = new ArrayList();
              this.delay = 0;
              break;
          case "leak":
              this.mode = BadServlet.MODE_LEAK; 
              break;
          case BadServlet.MODE_SLOW:
              this.mode = BadServlet.MODE_SLOW;
              break;
          default:
              System.out.println("unrecognized action:" + string);
              break;
      }
  }

  private void doModeEffect() {
      switch (mode) {
          case BadServlet.MODE_BEHAVE:
              break;
          case BadServlet.MODE_LEAK:
              this.leakList.add(new StringBuffer(BadServlet.LEAK_SIZE));
              System.out.println("leaked " + (this.leakList.size() * BadServlet.LEAK_SIZE));
              break;
          case BadServlet.MODE_SLOW:
              this.delay = this.delay + 3000;
              System.out.println("waiting for fake processing " + this.delay);
              try {
                  Thread.sleep(this.delay);
              } catch (InterruptedException e) {}
          default:
              break;
      }
  }

}
