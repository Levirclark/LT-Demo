import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Scanner;

class NetworkToolbox {

    private String charSet;

    NetworkToolbox(){
        this.charSet = StandardCharsets.UTF_8.name();
    }

    NetworkToolbox(String charSet){
        this.charSet = charSet;
    }

    String makeNetworkCallForString(String URL, HashMap<String, String> parameterValues){
        //Prepare the URL String
        String queryString = buildQueryString(parameterValues);
        String finalURL = URL + "?" + queryString;
        //Prepare the URL Request Connection
        URLConnection requestConnection = connectToURL(finalURL);
        if(requestConnection == null){
            return null;
        }
        //Prepare the URL Request Stream
        InputStream requestResponseStream = getStreamFromConnection(requestConnection);
        if(requestResponseStream == null){
            return null;
        }
        //Retrieve the string acquired from the URL
        String responseString = getStringFromRequestStream(requestResponseStream);
        //Close Stream
        try {
            requestResponseStream.close();
        } catch (IOException e) {
            System.out.println("The connection to " + finalURL + " failed to close. To ensure incorrect data is not shown, the web request has been canceled");
            return null;
        }
        return responseString;
    }

    URLConnection connectToURL(String url){
        URLConnection requestConnection;
        try {
            requestConnection = new URL(url).openConnection();
        } catch (IOException e) {
            System.out.println("The program was unable to make a network request to the URL: " + url);
            return null;
        }
        requestConnection.setRequestProperty("Accept-Charset", charSet);
        return requestConnection;
    }

    private InputStream getStreamFromConnection(URLConnection urlConnection){
        InputStream requestResponseStream;
        try {
            requestResponseStream = urlConnection.getInputStream();
        } catch (IOException e) {
            System.out.println("The program encountered an error while making a request to the URL: " + urlConnection.getURL().toString());
            return null;
        }
        return requestResponseStream;
    }

    private String getStringFromRequestStream(InputStream requestResponseStream){
        Scanner requestResponseScanner = new Scanner(requestResponseStream);
        try{
            String responseString = requestResponseScanner.useDelimiter("\\A").next();
            requestResponseScanner.close();
            return responseString;
        }
        catch(java.util.NoSuchElementException e){
            System.out.println("The data received from the web request was empty");
            return null;
        }
    }

    String buildQueryString(HashMap<String, String> parameterValues){
        if(parameterValues == null){
            return "";
        }
        StringBuilder returnValue = new StringBuilder();
        for(String key : parameterValues.keySet()){
            if(returnValue.length() > 0){
                returnValue.append("&");
            }
            try {
                returnValue = new StringBuilder(String.format(returnValue + key + "=%s", URLEncoder.encode(parameterValues.get(key), charSet)));
            } catch (UnsupportedEncodingException e) {
                System.out.println("The program was not able to correctly add a URL parameter to your request. The parameter in question was: " + parameterValues.get(key));
            }
        }
        return returnValue.toString();
    }
}
