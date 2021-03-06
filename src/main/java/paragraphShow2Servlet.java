import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

@WebServlet(name = "paragraphShow2Servlet")
public class paragraphShow2Servlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cmessage = request.getParameter("cmessage");
        String kmessage = request.getParameter("smessage");

        StringBuilder bitString = new StringBuilder();

        int j = 0;

        String[] strArray = cmessage.split(" ");
        for (int i = 0; i < strArray.length; i++) {
            if (j < kmessage.length()) {
                int n = strArray[i].length();
                if (strArray[i].charAt(0) == strArray[i].charAt(n - 1)) {
                    System.out.println("Valid");
                } else {
                    System.out.println("nope");
                    if (kmessage.charAt(j) == strArray[i].charAt(0)) {
                        bitString.append(0);
                        ++j;
                    } else if (kmessage.charAt(j) == strArray[i].charAt(n - 1)){
                        bitString.append(1);
                        ++j;
                    }


                }
            }
            System.out.println(strArray[i]);
        }
        System.out.println(bitString);
        String secretMessage = binaryToString(bitString.toString());
        System.out.println(secretMessage);


        PrintWriter writer = response.getWriter();

        // build HTML code
        String htmlRespone = cmessage;
        htmlRespone += "\nThe secret message is: " + secretMessage;

        writer.println(htmlRespone);
        response.setContentType("text/plain");
        response.setHeader("Content-Disposition", "attachment; filename=\"HidingTextUsingHidingInParagraphs.txt\"");
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
