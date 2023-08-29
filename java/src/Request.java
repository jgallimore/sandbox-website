import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Request {

    private static final String USER_AGENT = "Mozilla/5.0";

    private static final String POST_URL = "https://region1.google-analytics.com/g/collect?v=2&tid=G-PPH1H6SJ3W&gtm=45je38n0&_p=2089230605&cid=154959056.1693319095&ul=en-gb&sr=2560x1440&_s=1&sid=1693317469&sct=1&seg=0&dl=https%3A%2F%2Fjgallimore.github.io%2Fsandbox-website%2F&dt=&en=page_view&_fv=1&_nsi=1&_ss=1&_ee=1'";

    private static final String POST_PARAMS = "";

    public static void main(String[] args) throws IOException {
        sendPOST();
        System.out.println("POST DONE");
    }


    private static void sendPOST() throws IOException {
        URL obj = new URL(POST_URL);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("User-Agent", USER_AGENT);

        // For POST only - START
        con.setDoOutput(true);
        OutputStream os = con.getOutputStream();
        os.write(POST_PARAMS.getBytes());
        os.flush();
        os.close();
        // For POST only - END

        int responseCode = con.getResponseCode();
        System.out.println("POST Response Code :: " + responseCode);

        if (responseCode == HttpURLConnection.HTTP_OK) { //success
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // print result
            System.out.println(response);
        } else {
            System.out.println("POST request did not work.");
        }
    }

}