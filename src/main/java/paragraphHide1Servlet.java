import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "paragraphHide1Servlet")
public class paragraphHide1Servlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        try (PrintWriter writer = response.getWriter()) {
            writer.println("<!DOCTYPE html><html>");
            writer.println("<head>");
            writer.println("<meta charset=\"UTF-8\" />");
            writer.println("<title>ParagraphHidingServlet.java:doGet(): Servlet code!</title>");
            writer.println("<style>");
            writer.print(".cover-text{\n" +
                    "    height:200px;\n" +
                    "}"
            );
            writer.println("</style>");

            writer.println("<h2> Paragraph Hiding Technique </h2>");
            writer.println("</head>");
            writer.println("<body>");
            writer.println("<form name=\"loginForm\" method=\"post\" action=\"paragraphHide2Servlet\">\n" +
                    "  <label for=\"cmessage\">Enter Cover Message:</label><br>\n" +
                    "  <textarea type=\"input\" class =\"cover-text\" id=\"cmessage\" name=\"cmessage\"></textarea><br>\n" +
                    "  <label for=\"smessage\">Enter Secret Message:</label><br>\n" +
                    "  <input type=\"text\" id=\"smessage\" name=\"smessage\">\n" +
                    "  <input type=\"submit\" value=\"Hide\">"+
                    "</form>\n");
            writer.println("</body>");
            writer.println("</html>");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
