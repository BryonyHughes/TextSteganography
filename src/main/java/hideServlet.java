import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/loginServlet")
public class hideServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {

        // read form fields
        String cmessage = request.getParameter("cmessage");
        String smessage = request.getParameter("smessage");

        System.out.println("cover message: " + cmessage);
        System.out.println("secret message: " + smessage);

        String bitString;
        int j = 0;

        bitString = stringToBinary(smessage);
        bitString += 0;
        System.out.println(bitString);

        StringBuilder sentence = new StringBuilder();


        for (int i = 0; i < cmessage.length(); i++) {
                System.out.println(cmessage.charAt(i));
                System.out.println(bitString.charAt(j));
            if (j < bitString.length() -1 ) {
                if (cmessage.charAt(i) == ' ' && bitString.charAt(j) == '1') {
                    sentence.append(' ');
                    sentence.append(' ');
                    sentence.append(' ');
                    j++;

                } else if (cmessage.charAt(i) == ' ' && bitString.charAt(j) == '0') {
                    sentence.append(' ');
                    sentence.append(' ');
                    j++;
                } else {
                    sentence.append(cmessage.charAt(i));
                }
            }else {
                    sentence.append(cmessage.charAt(i));
                }
        }
        PrintWriter writer = response.getWriter();
        // build HTML code

        StringBuilder htmlRespone = sentence ;


        // return response
        writer.println(htmlRespone);
        response.setContentType("text/plain");
        response.setHeader("Content-Disposition", "attachment; filename=\"HidingTextUsingWhiteSpace.txt\"");
        try {
            OutputStream outputStream = response.getOutputStream();
            outputStream.write(Integer.parseInt(sentence.toString()));
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
        }
        binString = reverse(binString);
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

        // do some processing here...


    }


