import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/loginServlet")
public class showServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {

        // read form fields
        String cmessage = request.getParameter("cmessage");

        System.out.println("cover message: " + cmessage);




        StringBuilder bitString = new StringBuilder();


        for ( int i = 0; i < cmessage.length(); i++) {
            if (cmessage.charAt(i) == ' ' && cmessage.charAt(i-1) == ' ' && cmessage.charAt(i+1)!= ' ' && cmessage.charAt(i-2)!= ' ' ){
                bitString.append(0);
            }
            else if (cmessage.charAt(i) == ' ' && cmessage.charAt(i-1)== ' ' && cmessage.charAt(i+1)== ' ' ) {
                bitString.append(1);
            }

        }
        cmessage = cmessage.replaceAll("( +)"," ").trim();
        System.out.println("\nThe original cover text is: " + cmessage);
        System.out.println("BitString: " + bitString);
        String smessage = new StringBuffer(binaryToString(bitString.toString())).reverse().toString();
        System.out.println("The secret Message is: " + smessage);

        PrintWriter writer = response.getWriter();

        // build HTML code
        String htmlRespone = cmessage;
        htmlRespone += "\nYour secret message is: " + smessage ;

        writer.println(htmlRespone);
        response.setContentType("text/plain");
        response.setHeader("Content-Disposition", "attachment; filename=\"ShowingTextUsingWhiteSpace.txt\"");
        try {
            OutputStream outputStream = response.getOutputStream();
            outputStream.write(Integer.parseInt(cmessage));
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static String binaryToString(String bitString) {
        StringBuilder secretMessage = new StringBuilder();
        int charCode;
        for (int i = 0; i < bitString.length(); i += 7) {
            charCode = Integer.parseInt(bitString.substring(i, i + 7), 2);
            String returnChar = Character.toString((char) charCode);
            secretMessage.append(returnChar);
        }
        return secretMessage.toString();
    }
}




