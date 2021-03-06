import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

@WebServlet(name = "paragraphHide2Servlet")
public class paragraphHide2Servlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cmessage = request.getParameter("cmessage");
        String smessage = request.getParameter("smessage");


        int j = 0;
        String keyString = "";
        String bitString = stringToBinary(smessage);
        bitString = bitString + 0;
        System.out.println(bitString);

        String[] strArray = cmessage.split(" ");
        for (int i = 0; i < strArray.length; i++) {
            if (j < bitString.length() - 1) {
                int n = strArray[i].length();
                if (strArray[i].charAt(0) == strArray[i].charAt(n - 1)) {
                    System.out.println("Valid");
                } else {
                    System.out.println("nope");
                    if (bitString.charAt(j) == '0') {
                        keyString = keyString + strArray[i].charAt(0);
                        ++j;
                    } else {
                        keyString = keyString + strArray[i].charAt(n - 1);
                        ++j;
                    }


                }
            }
            System.out.println(strArray[i]);
        }
        System.out.println(keyString);


        PrintWriter writer = response.getWriter();

        // build HTML code
        String htmlRespone = cmessage;
        htmlRespone += "\nYour Key is: " + keyString ;

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


    public static String stringToBinary(String secretMessage) {
        int n = secretMessage.length();
        String binString = "";

        for (int i = 0; i < n; i++) {
            // convert each char to
            // ASCII value
            int val = Integer.valueOf(secretMessage.charAt(i));

            // Convert ASCII value to binary
            while (val > 0) {
                if (val % 2 == 1) {
                    binString += '1';
                } else
                    binString += '0';
                val /= 2;
            }
            binString = reverse(binString);



        }

        return binString;

    }
    static String reverse (String binString)
    {
        char[] a = binString.toCharArray();
        int l, r = 0;
        r = a.length - 1;

        for (l = 0; l < r; l++, r--) {
            // Swap values of l and r
            char temp = a[l];
            a[l] = a[r];
            a[r] = temp;
        }
        return String.valueOf(a);
    }


}
